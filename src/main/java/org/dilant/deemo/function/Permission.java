package org.dilant.deemo.function;

import com.scienjus.smartqq.model.GroupUser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 权限相关
 *
 * @author Dilant
 * @date 2017/7/4
 */

public class Permission {
    private static String rootUser = "迪兰特";
    private static String qqbotUser = "迪莫";
    private ArrayList<GroupUser> admin = new ArrayList<>();
    private ArrayList<GroupUser> blacklist = new ArrayList<>();

    public boolean isBanned(GroupUser user) {
        return this.blacklist.contains(user);
    }

    public boolean isAdmin(GroupUser user) {
        return isRoot(user) || this.admin.contains(user);
    }

    public boolean isRoot(GroupUser user) {
        String nick = user.getNick();
        return Objects.equals(nick, rootUser);
    }

    public void ban(GroupUser user) throws Exception {
        if (user == null) {
            throw new IllegalStateException("该成员不存在");
        }
        this.blacklist.add(user);
    }

    public void unban(GroupUser user) throws Exception {
        if (user == null) {
            throw new IllegalStateException("该成员不存在");
        }
        while (this.blacklist.remove(user)) ; //将重复入列的都删除
    }

    public void authorize(GroupUser user) throws Exception {
        if (user == null) {
            throw new IllegalStateException("该成员不存在");
        }
        this.admin.add(user);
    }

    public void unauthorize(GroupUser user) throws Exception {
        if (user == null) {
            throw new IllegalStateException("该成员不存在");
        }
        while (this.admin.remove(user)) ; //将重复入列的都删除
    }
}
