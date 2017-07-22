package org.dilant.deemo.function;

import com.scienjus.smartqq.model.GroupUser;
import org.dilant.deemo.structure.RegexFunction;
import org.dilant.deemo.structure.TreeNode;

import java.util.Objects;

import static org.dilant.deemo.Deemo.*;
import static org.dilant.deemo.function.Reply.sendToGroup;
import static org.dilant.deemo.structure.Strings.*;
import static org.dilant.deemo.util.ClientUtil.getGroupInfoFromID;
import static org.dilant.deemo.util.ClientUtil.getGroupUserNick;

/**
 * 指令
 *
 * @author Dilant
 * @date 2017/7/19
 */

public class Command {

    //TreeNode根节点
    public static TreeNode root = new TreeNode("", null) {
        @Override
        public void run(String str) {
        }
    };

    //deemo指令根节点
    private static TreeNode deemo = new TreeNode("deemo", root) {
        @Override
        public void run(String str) {
        }
    };

    //deemo.help 帮助
    private static TreeNode deemeHelp = new TreeNode("help", deemo) {
        @Override
        public void run(String str) {
            sendToGroup(groupId, client, deemoHelpReply);
        }
    };

    //deemo.sudo 权限相关
    private static TreeNode deemoSudo = new TreeNode("sudo", deemo) {
        @Override
        public void run(String str) {
            sendToGroup(groupId, client, deemoSudoReply);
        }
    };

    //deemo.sudo.exit 关闭机器人
    //需要管理员权限
    private static TreeNode deemoSudoExit = new TreeNode("exit", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                sendToGroup(groupId, client, deemoSudoExitReply);
                System.exit(0);
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.sudo.pause 暂停互动
    //需要管理员权限
    private static TreeNode deemoSudoPause = new TreeNode("pause", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                sendToGroup(groupId, client, deemoSudoPauseReply);
                isPaused = true;
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.sudo.resume 恢复互动
    //需要管理员权限
    private static TreeNode deemoSudoResume = new TreeNode("resume", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                sendToGroup(groupId, client, deemoSudoResumeReply);
                isPaused = false;
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.util 工具
    private static TreeNode deemoUtil = new TreeNode("util", deemo) {
        @Override
        public void run(String str) {
            sendToGroup(groupId, client, deemoUtilReply);
        }
    };

    //deemo.util.coin 抛硬币
    private static TreeNode deemoUtilCoin = new TreeNode("coin", deemoUtil) {
        @Override
        public void run(String str) {
            sendToGroup(groupId, client, deemoUtilCoinReply());
        }
    };

    //deemo.util.dice 掷骰子
    //deemo.util.dice {surface} 掷{surface}面骰子
    //对{surface}<=0，=1，=2，>100，非数字的情况有特殊处理
    private static TreeNode deemoUtilDice = new TreeNode("dice", deemoUtil) {
        @Override
        public void run(String str) {
            if (Objects.equals(str, "")) {
                sendToGroup(groupId, client, deemoUtilDiceReply(6));
            } else {
                try {
                    int surface = Integer.parseInt(str);
                    if (surface == 1) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_1);
                    } else if (surface == 2) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_2);
                        sleeper.delay();
                        sendToGroup(groupId, client, deemoUtilCoinReply());
                    } else if (surface == 3) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_3);
                    } else if (surface < 1) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_Illegal);
                    } else if (surface > 100) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_100);
                    } else if (surface != 6) {
                        sendToGroup(groupId, client, deemoUtilDiceReply_Special(surface));
                    } else {
                        sendToGroup(groupId, client, deemoUtilDiceReply(surface));
                    }
                } catch (Exception e) {
                    sendToGroup(groupId, client, deemoUtilDiceReply_Illegal);
                }
            }

        }
    };

    //deemo.sudo.authorize {nick} 设置群名片为{nick}的用户为管理员
    //需要管理员权限
    private static TreeNode deemoSudoAuthorize = new TreeNode("authorize", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                try {
                    GroupUser processingUser = null;
                    if (str.matches("^@.* $")) {
                        str = str.substring(1, str.length() - 1);
                    }
                    for (GroupUser user : getGroupInfoFromID(groupMsg.getGroupId()).getUsers()) {
                        if (str.startsWith(getGroupUserNick(user))) {
                            processingUser = user;
                            break;
                        }
                    }
                    perm.authorize(processingUser);
                    sendToGroup(groupId, client, deemoSudoAuthorizeReply(getGroupUserNick(processingUser)));
                } catch (Exception e) {
                    sendToGroup(groupId, client, deemoSudoFailedReply);
                }
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.sudo.unauthorize {nick} 取消群名片为{nick}的用户的管理员权限
    //仅主人可操作
    private static TreeNode deemoSudoUnauthorize = new TreeNode("unauthorize", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isRoot(groupUser)) {
                try {
                    GroupUser processingUser = null;
                    if (str.matches("^@.* $")) {
                        str = str.substring(1, str.length() - 1);
                    }
                    for (GroupUser user : getGroupInfoFromID(groupMsg.getGroupId()).getUsers()) {
                        if (str.startsWith(getGroupUserNick(user))) {
                            processingUser = user;
                            break;
                        }
                    }
                    perm.unauthorize(processingUser);
                    sendToGroup(groupId, client, deemoSudoUnauthorizeReply(getGroupUserNick(processingUser)));
                } catch (Exception e) {
                    sendToGroup(groupId, client, deemoSudoFailedReply);
                }
            } else {
                sendToGroup(groupId, client, notRootReply);
            }
        }
    };

    //deemo.sudo.test 查询权限
    private static TreeNode deemoSudoTest = new TreeNode("test", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isRoot(groupUser)) {
                sendToGroup(groupId, client, deemoSudoTestReply_Root);
            } else if (perm.isAdmin(groupUser)) {
                sendToGroup(groupId, client, deemoSudoTestReply_Admin);
            } else {
                sendToGroup(groupId, client, deemoSudoTestReply_Normal);
            }
        }
    };

    //deemo.sudo.ban {nick} 屏蔽群名片为{nick}的用户
    //需要管理员权限
    private static TreeNode deemoSudoBan = new TreeNode("ban", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                try {
                    GroupUser processingUser = null;
                    if (str.matches("^@.* $")) {
                        str = str.substring(1, str.length() - 1);
                    }
                    for (GroupUser user : getGroupInfoFromID(groupMsg.getGroupId()).getUsers()) {
                        if (str.startsWith(getGroupUserNick(user))) {
                            processingUser = user;
                            break;
                        }
                    }
                    perm.ban(processingUser);
                    sendToGroup(groupId, client, deemoSudoBanReply(getGroupUserNick(processingUser)));
                } catch (Exception e) {
                    sendToGroup(groupId, client, deemoSudoFailedReply);
                }
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.sudo.unban {nick} 取消对群名片为{nick}的用户的屏蔽
    //需要管理员权限
    private static TreeNode deemoSudoUnban = new TreeNode("unban", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                try {
                    GroupUser processingUser = null;
                    if (str.matches("^@.* $")) {
                        str = str.substring(1, str.length() - 1);
                    }
                    for (GroupUser user : getGroupInfoFromID(groupMsg.getGroupId()).getUsers()) {
                        if (str.startsWith(getGroupUserNick(user))) {
                            processingUser = user;
                            break;
                        }
                    }
                    perm.unban(processingUser);
                    sendToGroup(groupId, client, deemoSudoUnbanReply(getGroupUserNick(processingUser)));
                } catch (Exception e) {
                    sendToGroup(groupId, client, deemoSudoFailedReply);
                }
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };

    //deemo.sudo.say {str} 使机器人发送内容为{str}的信息
    //机器人本身发出的信息不会触发指令
    //需要管理员权限
    private static TreeNode deemoSudoSay = new TreeNode("say", deemoSudo) {
        @Override
        public void run(String str) {
            if (perm.isAdmin(groupUser)) {
                sendToGroup(groupId, client, str);
            } else {
                sendToGroup(groupId, client, notAdminReply);
            }
        }
    };


    //RegexFunction根节点
    //若消息内容命中多个触发条件，仅触发从上到下第一条指令
    public static RegexFunction rootRF = new RegexFunction("", null) {
        @Override
        public void run(String str) throws Exception {
        }
    };

    //消息内容包含“是不是”时触发
    private static RegexFunction yesOrNoRF = new RegexFunction(".*是不是.*", rootRF) {
        @Override
        public void run(String str) throws Exception {
            sendToGroup(groupId, client, yesOrNoRFReply);
        }
    };

    //消息内容包含“有没有”时触发
    private static RegexFunction haveOrHaveNotRF = new RegexFunction(".*有没有.*", rootRF) {
        @Override
        public void run(String str) throws Exception {
            sendToGroup(groupId, client, haveOrHaveNotRFReply);
        }
    };

    //消息内容包含“表白迪莫”或“表白@迪莫”时触发
    private static RegexFunction confessRF = new RegexFunction(".*表白@?迪莫.*", rootRF) {
        @Override
        public void run(String str) throws Exception {
            sendToGroup(groupId, client, confessRFReply(nick));
        }
    };

    //消息内容包含“迪莫”时触发
    private static RegexFunction callDeemoRF = new RegexFunction(".*迪莫.*", rootRF) {
        @Override
        public void run(String str) throws Exception {
            sendToGroup(groupId, client, callDeemoRFReply);
        }
    };
}