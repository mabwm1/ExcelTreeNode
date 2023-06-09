package cn.bdqn.demo.controller;

import cn.bdqn.demo.entity.Excel;
import cn.bdqn.demo.entity.TreeNode;
import cn.bdqn.demo.listener.ExcelListener;
import cn.bdqn.demo.utils.ExcelToTree;
import cn.bdqn.demo.utils.Tree;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class TreeNodeTest {
    @Autowired
    private HttpServletRequest request;
    //上传文件
    @RequestMapping("/upload")
    public List<TreeNode> upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 验证上传的文件是否为 Excel
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (!suffix.equalsIgnoreCase("xls") && !suffix.equalsIgnoreCase("xlsx")) {
            System.out.println("上传的文件不是 Excel 文件");
            return null;
        }
        String filePath = "D:\\" + fileName;
        // 创建文件对象
        File dest = new File(filePath);
        // 将文件保存到指定路径
        file.transferTo(dest);
        List<Excel> list = EasyExcel.read(filePath, Excel.class, new ExcelListener()).sheet().doReadSync();
        List<TreeNode> trees = new ArrayList<>();
        for (Excel d: list) {
            TreeNode tree = new TreeNode();
            tree.setId(d.getId());
            tree.setLabel(d.getLabel());
            tree.setParents(d.getParents());
            trees.add(tree);
        }
        List<TreeNode> data = Tree.buildTree(trees);
        System.out.println(data);
        return data;
    }

    //请求路径
    @GetMapping("/index")
    public List<TreeNode> index() throws IOException {
        //读取文件
        FileInputStream in = new FileInputStream("H:\\IntelliJ IDEA Project\\demo\\src\\main\\resources\\static\\sichuan.xlsx");
        //创建表格的实体的List并读取数据
        List<Excel> result = ExcelToTree.read(in, Excel.class);
        //创建需要发送给前端的数据List
        List<TreeNode> parents = new ArrayList<>();
        //处理父节点数据
        for (Excel vo : result) {
            vo.setId(vo.getId().replace("0x", "00"));
            vo.setParents(vo.getParents().replace("0x", ""));
            if (Objects.equals(vo.getId(), vo.getParents())) {
                parents.add(BeanUtil.copyProperties(vo, TreeNode.class));
            }
        }
        //处理子节点数据
        for (TreeNode parent : parents) {
            //填充子集数据
            fillTreeData(parent, result);
        }
        //返回数据给前端(TreeNode创建的数据)
        return parents;
    }
    //处理树形结构
    private void fillTreeData(TreeNode parent, List<Excel> list) {
        //子节点
        List<TreeNode> children = new ArrayList<>();
        for (Excel vo : list) {
            if (Objects.equals(parent.getParents().concat(vo.getId()), vo.getParents())) {
                TreeNode respVo = BeanUtil.copyProperties(vo, TreeNode.class);
                fillTreeData(respVo, list);
                children.add(respVo);
            }
        }
        if (CollectionUtil.isNotEmpty(children)) {
            parent.setChildren(children);
        }
    }
}
