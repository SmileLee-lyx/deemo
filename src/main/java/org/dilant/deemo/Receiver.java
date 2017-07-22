package org.dilant.deemo;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.*;

import java.util.*;

import static org.dilant.deemo.util.ClientUtil.*;

/**
 * 消息接收器 - Receiver
 *
 * @author Dilant
 * @date 2017/7/8
 */

public class Receiver {

    private static List<Friend> friendList = new ArrayList<>();                 //好友列表
    private static List<Group> groupList = new ArrayList<>();                   //群列表
    private static List<Discuss> discussList = new ArrayList<>();               //讨论组列表
    private static Map<Long, Friend> friendFromID = new HashMap<>();            //好友id到好友映射
    private static Map<Long, Group> groupFromID = new HashMap<>();              //群id到群映射
    private static Map<Long, GroupInfo> groupInfoFromID = new HashMap<>();      //群id到群详情映射
    private static Map<Long, Discuss> discussFromID = new HashMap<>();          //讨论组id到讨论组映射
    private static Map<Long, DiscussInfo> discussInfoFromID = new HashMap<>();  //讨论组id到讨论组详情映射

    private static boolean isReceivingMessage; //是否正在接收消息

    /**
     * SmartQQ客户端
     */
    private static SmartQQClient client = new SmartQQClient(new MessageCallback() {

        @Override
        public void onMessage(Message msg) {
            if (!isReceivingMessage) {
                return;
            }

            String time = getTime();
            long uin = msg.getUserId();
            Friend user = getFriend(msg);
            String nick = getFriendNick(user);
            String content = msg.getContent();

            try {
                System.out.println("[" + time + "] [私聊] " + nick + "：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onGroupMessage(GroupMessage msg) {
            if (!isReceivingMessage) {
                return;
            }

            String time = getTime();
            String groupName = getGroupName(msg);
            long groupId = msg.getGroupId();
            long uin = msg.getUserId();
            GroupUser user = getGroupUser(msg);
            String nick = getGroupUserNick(user);
            String content = msg.getContent();

            try {
                System.out.println("[" + time + "] [" + groupName + "] " + nick + "：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDiscussMessage(DiscussMessage msg) {
            if (!isReceivingMessage) {
                return;
            }

            String time = getTime();
            String discussName = getDiscussName(msg);
            long discussId = msg.getDiscussId();
            long uin = msg.getUserId();
            DiscussUser user = getDiscussUser(msg);
            String nick = getDiscussUserNick(user);
            String content = msg.getContent();

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
                if (!buildIndex()) { //更新索引，失败则退出程序
                    System.exit(1);
                }
            }
        }, 0, 6 * 60 * 60 * 1000); //每隔6小时执行一次，可根据需要自行调整
    }
}