package org.dilant.deemo.structure;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThomasVadeSmileLee
 */
public abstract class TreeNode {

    public class PathException extends Exception {
        PathException() {
            super("未找到路径");
        }
    }

    /**
     * @param str 参数
     * @throws Exception 抛出的异常
     */
    public abstract void run(String str) throws Exception;

    private String str_; //该节点路径名
    private TreeNode parent_; //父节点
    private List<TreeNode> children_; //子节点

    public TreeNode(@NotNull String str, TreeNode parent) {
        parent_ = parent;
        str_ = str;
        if (parent_ != null) parent_.children_.add(this);
        children_ = new ArrayList<>();
    }

    /**
     * @param path 路径
     * @return 该路径下的子节点
     * @throws PathException 当路径不存在时抛出异常
     */
    public TreeNode getChild(String path) throws PathException {
        int i;
        if (path.contains(".")) { //如果不是最下层路径
            i = path.indexOf(".");
            String nextPath = path.substring(0, i); //获取该层路径名
            for (TreeNode child : children_) {
                if (child.str_.equals(nextPath)) { //寻找子节点
                    return child.getChild(path.substring(i + 1)); //递归确定
                }
            }
            throw new PathException(); //没有找到相应的子节点，抛出异常
        } else { //当前为底层路径
            for (TreeNode child : children_) {
                if (child.str_.equals(path)) { //寻找子节点
                    return child; //就是所要的节点
                }
            }
            throw new PathException(); //没有找到相应的子节点，抛出异常
        }
    }

    /**
     * @param pathAndParameter 路径+一个空格+参数
     * @throws Exception 可能抛出的异常
     */
    public void runPath(String pathAndParameter) throws Exception {
        int i;
        if (pathAndParameter.contains(".") && (!pathAndParameter.contains(" ") || pathAndParameter.indexOf(' ') > pathAndParameter.indexOf('.'))) {  //不为底层路径
            i = pathAndParameter.indexOf('.');
            String nextPath = pathAndParameter.substring(0, i); //获取该层路径名
            for (TreeNode child : children_) {
                if (child.str_.equals(nextPath)) { //寻找子节点
                    child.runPath(pathAndParameter.substring(i + 1)); //递归确定
                    return;
                }
            }
            throw new PathException(); //没有找到相应的子节点，抛出异常
        } else { //当前为底层路径
            if (pathAndParameter.contains(" ")) { //有参数
                i = pathAndParameter.indexOf(' ');
                String nextPath = pathAndParameter.substring(0, i); //获取该层路径名
                for (TreeNode child : children_) {
                    if (child.str_.equals(nextPath)) { //寻找子节点
                        child.run(pathAndParameter.substring(i + 1)); //执行相应函数
                        return;
                    }
                }
                throw new PathException(); //没有找到相应的子节点，抛出异常
            } else { //无参数
                String nextPath = pathAndParameter;
                for (TreeNode child : children_) {
                    if (child.str_.equals(nextPath)) { //寻找子节点
                        child.run(""); //执行相应函数
                        return;
                    }
                }
                throw new PathException();//没有找到相应的子节点，抛出异常
            }
        }
    }

    public String getStr() {
        return str_;
    }

    public void setStr(@NotNull String str) {
        str_ = str;
    }

    public TreeNode getParent() {
        return parent_;
    }

    public void setParent(TreeNode parent) {
        parent_ = parent;
    }

    public List<TreeNode> getChildren() {
        return children_;
    }

    public void setChildren(List<TreeNode> children) {
        children_ = children;
    }
}
