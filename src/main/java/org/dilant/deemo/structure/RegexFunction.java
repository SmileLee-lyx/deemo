package org.dilant.deemo.structure;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThomasVadeSmileLee
 */
public abstract class RegexFunction {

    /**
     * @param str 参数
     * @throws Exception 抛出的异常
     */
    public abstract void run(String str) throws Exception;

    private String str_; //该节点路径名
    private RegexFunction parent_; //父节点
    private List<RegexFunction> children_; //子节点

    public RegexFunction(@NotNull String str, RegexFunction parent) {
        parent_ = parent;
        str_ = str;
        if (parent_ != null) parent_.children_.add(this);
        children_ = new ArrayList<>();
    }

    public void runRegexPath(String parameter) throws Exception {
        for (RegexFunction child : children_) {
            if (parameter.matches(child.str_)) { //寻找子节点
                child.runRegexPath(parameter); //递归确定
                return;
            }
        }
        run(parameter);//没有匹配的子节点，则调用默认
    }

    public String getStr() {
        return str_;
    }

    public void setStr(@NotNull String str) {
        str_ = str;
    }

    public RegexFunction getParent() {
        return parent_;
    }

    public void setParent(RegexFunction parent) {
        parent_ = parent;
    }

    public List<RegexFunction> getChildren() {
        return children_;
    }

    public void setChildren(List<RegexFunction> children) {
        children_ = children;
    }
}
