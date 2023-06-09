package cn.bdqn.demo.entity;

/*视图*/
import lombok.Data;
import java.util.List;

/**
 * 传到前端需要的数据
 */
@Data
public class TreeNode {
    private String id;
    private String code;
    private String label;
    private String parents;
    private List<TreeNode> children;
}
