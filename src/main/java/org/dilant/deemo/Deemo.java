package org.dilant.deemo;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.*;
import org.dilant.deemo.function.Permission;
import org.dilant.deemo.structure.TreeNode.PathException;
import org.dilant.deemo.util.Sleeper;

import java.util.*;

import static org.dilant.deemo.function.Command.root;
import static org.dilant.deemo.function.Command.rootRF;
import static org.dilant.deemo.function.Reply.*;
import static org.dilant.deemo.util.ClientUtil.*;

/**
 * 迪莫机器人 - Deemo
 *
 * @author Dilant
 * @date 2017/7/4
 */

public class Deemo {

    public static final Sleeper sleeper = new Sleeper();
    public static List<Friend> friendList = new ArrayList<>();                 //好友列表
    public static List<Group> groupList = new ArrayList<>();                   //群列表
    public static List<Discuss> discussList = new ArrayList<>();               //讨论组列表
    public static Map<Long, Friend> friendFromID = new HashMap<>();            //好友id到好友映射
    public static Map<Long, Group> groupFromID = new HashMap<>();              //群id到群映射
    public static Map<Long, GroupInfo> groupInfoFromID = new HashMap<>();      //群id到群详情映射
    public static Map<Long, Discuss> discussFromID = new HashMap<>();          //讨论组id到讨论组映射
    public static Map<Long, DiscussInfo> discussInfoFromID = new HashMap<>();  //讨论组id到讨论组详情映射

    public static Permission perm = new Permission();

    public static boolean isPaused = false;

    public static boolean isReceivingMessage; //是否正在接收消息

    public static Message msg;
    public static GroupMessage groupMsg;
    public static DiscussMessage discussMsg;
    public static String time;
    public static String groupName;
    public static String discussName;
    public static Long groupId;
    public static Long discussId;
    public static Long uin;
    public static Friend friend;
    public static GroupUser groupUser;
    public static DiscussUser discussUser;
    public static String nick;
    public static String content;

    /**
     * SmartQQ客户端
     */
    public static SmartQQClient client = new SmartQQClient(new MessageCallback() {

        @Override
        public void onMessage(Message m) {
            if (!isReceivingMessage) {
                return;
            }
            msg = m;
            time = getTime();
            uin = m.getUserId();
            friend = getFriend(m);
            nick = getFriendNick(friend);
            content = m.getContent();
            if (Objects.equals(content, "")) {
                content = "[图片]"; //图片消息content为""
            }

            try {
                System.out.println("[" + time + "] [私聊] " + nick + "：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onGroupMessage(GroupMessage m) {
            if (!isReceivingMessage) {
                return;
            }
            groupMsg = m;
            time = getTime();
            groupName = getGroupName(m);
            groupId = m.getGroupId();
            uin = m.getUserId();
            groupUser = getGroupUser(m);
            nick = getGroupUserNick(groupUser);
            content = m.getContent();
            if (Objects.equals(content, "")) {
                content = "[图片]"; //图片消息content为""
            }

            try {
                System.out.println("[" + time + "] [" + groupName + "] " + nick + "：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (perm.isAdmin(groupUser) || (!isPaused && !perm.isBanned(groupUser))) {
                    if (content.matches("^deemo\\..*")) { //当前消息为指令
                        try {
                            root.runPath(content); //执行指令相应的函数
                        } catch (PathException e) {
                            if (!isPaused) {
                                sendToGroup(groupId, client, "请确保命令存在哦 =_=");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (!isPaused) { //当前消息不是指令
                        try {
                            rootRF.runRegexPath(content); //匹配预设的指令并执行函数
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                //忽略异常
            }
        }

        @Override
        public void onDiscussMessage(DiscussMessage m) {
            if (!isReceivingMessage) {
                return;
            }
            discussMsg = m;
            time = getTime();
            discussName = getDiscussName(m);
            discussId = m.getDiscussId();
            uin = m.getUserId();
            discussUser = getDiscussUser(m);
            nick = getDiscussUserNick(discussUser);
            content = m.getContent();
            if (Objects.equals(content, "")) {
                content = "[图片]"; //图片消息content为""
            }

            try {
                System.out.println("[" + time + "] [" + discussName + "] " + nick + "：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    );

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!buildIndex()) { //更新映射，失败则退出程序
                    System.exit(1);
                }
            }
        }, 0, 6 * 60 * 60 * 1000); //每隔6小时执行一次，可根据需要自行调整
    }
}