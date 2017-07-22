package org.dilant.deemo.util;

import com.scienjus.smartqq.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.dilant.deemo.Deemo.*;

public class ClientUtil {
    /**
     * 获取本地系统时间
     *
     * @return 本地系统时间
     */
    public static String getTime() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(new Date());
    }

    /**
     * 获取私聊消息发送者
     *
     * @param msg 被查询的私聊消息
     * @return 该消息发送者
     */
    public static Friend getFriend(Message msg) {
        return friendFromID.get(msg.getUserId());
    }

    /**
     * 获取群消息所在群
     *
     * @param msg 被查询的群消息
     * @return 该消息所在群
     */
    public static Group getGroup(GroupMessage msg) {
        return groupFromID.get(msg.getGroupId());
    }

    /**
     * 获取讨论组消息所在讨论组
     *
     * @param msg 被查询的讨论组消息
     * @return 该消息所在讨论组
     */
    public static Discuss getDiscuss(DiscussMessage msg) {
        return discussFromID.get(msg.getDiscussId());
    }

    /**
     * 获取私聊消息发送者详情
     *
     * @param msg 被查询的私聊消息
     * @return 该消息发送者详情
     */
    public static UserInfo getFriendInfo(Message msg) {
        return client.getFriendInfo(msg.getUserId());
    }

    /**
     * 获取群id对应群详情
     *
     * @param id 被查询的群id
     * @return 该群详情
     */
    public static GroupInfo getGroupInfoFromID(long id) {
        if (!groupInfoFromID.containsKey(id)) {
            groupInfoFromID.put(id, client.getGroupInfo(groupFromID.get(id).getCode()));
        }
        return groupInfoFromID.get(id);
    }

    /**
     * 获取讨论组id对应讨论组详情
     *
     * @param id 被查询的讨论组id
     * @return 该讨论组详情
     */
    public static DiscussInfo getDiscussInfoFromID(long id) {
        if (!discussInfoFromID.containsKey(id)) {
            discussInfoFromID.put(id, client.getDiscussInfo(discussFromID.get(id).getId()));
        }
        return discussInfoFromID.get(id);
    }

    /**
     * 获取群消息所在群名称
     *
     * @param msg 被查询的群消息
     * @return 该消息所在群名称
     */
    public static String getGroupName(GroupMessage msg) {
        return getGroup(msg).getName();
    }

    /**
     * 获取讨论组消息所在讨论组名称
     *
     * @param msg 被查询的讨论组消息
     * @return 该消息所在讨论组名称
     */
    public static String getDiscussName(DiscussMessage msg) {
        return getDiscuss(msg).getName();
    }

    /**
     * 获取群消息发送者
     *
     * @param msg 被查询的群消息
     * @return 该消息发送者
     */
    public static GroupUser getGroupUser(GroupMessage msg) {
        for (GroupUser user : getGroupInfoFromID(msg.getGroupId()).getUsers()) {
            if (user.getUin() == msg.getUserId()) {
                return user; //返回发送者
            }
        }
        return null; //在群成员列表中查询不到时触发
        //TODO: 也有可能是自己，新加群的用户或匿名用户
    }

    /**
     * 获取群消息所在群中特定uin对应的成员
     *
     * @param msg 该成员所在群的某条消息
     * @param uin 被查询的uin
     * @return 该uin对应的群成员
     */
    public static GroupUser getGroupUserFromUin(GroupMessage msg, long uin) {
        for (GroupUser user : getGroupInfoFromID(msg.getGroupId()).getUsers()) {
            if (user.getUin() == uin) {
                return user; //返回该群成员
            }
        }
        return null; //在群成员列表中查询不到时触发
    }

    /**
     * 获取讨论组消息发送者
     *
     * @param msg 被查询的讨论组消息
     * @return 该消息发送者
     */
    public static DiscussUser getDiscussUser(DiscussMessage msg) {
        for (DiscussUser user : getDiscussInfoFromID(msg.getDiscussId()).getUsers()) {
            if (user.getUin() == msg.getUserId()) {
                return user; //返回发送者
            }
        }
        return null; //在讨论组成员列表中查询不到时触发
        //TODO: 也有可能是自己或新加讨论组的用户
    }

    /**
     * 获取讨论组消息所在讨论组中特定uin对应的成员
     *
     * @param msg 该成员所在讨论组的某条消息
     * @param uin 被查询的uin
     * @return 该uin对应的群成员
     */
    public static DiscussUser getDiscussUserFromUin(DiscussMessage msg, long uin) {
        for (DiscussUser user : getDiscussInfoFromID(msg.getDiscussId()).getUsers()) {
            if (user.getUin() == uin) {
                return user; //返回该讨论组成员
            }
        }
        return null; //在讨论组成员列表中查询不到时触发
    }

    /**
     * 获取私聊消息发送者昵称
     *
     * @param user 被查询的用户
     * @return 该消息发送者昵称
     */
    public static String getFriendNick(Friend user) {
        if (user.getMarkname() == null || user.getMarkname().equals("")) {
            return user.getNickname(); //若发送者无备注则返回其昵称
        } else {
            return user.getMarkname(); //否则返回其备注
        }
    }

    /**
     * 获取群消息发送者昵称
     *
     * @param user 被查询的群成员
     * @return 该消息发送者昵称
     */
    public static String getGroupUserNick(GroupUser user) {
        if (user == null) {
            return "群成员"; //在群成员列表中查询不到时触发
        }
        if (user.getCard() == null || user.getCard().equals("")) {
            return user.getNick(); //若发送者无群名片则返回其昵称
        } else {
            return user.getCard(); //否则返回其群名片
        }
    }

    /**
     * 获取讨论组消息发送者昵称
     *
     * @param user 被查询的讨论组成员
     * @return 该消息发送者昵称
     */
    public static String getDiscussUserNick(DiscussUser user) {
        if (user == null) {
            return "讨论组成员"; //在讨论组成员列表中查询不到时触发
        }
        return user.getNick(); //返回发送者昵称
    }

    /**
     * 建立索引
     *
     * @return 是否成功建立所有索引
     */
    public static boolean buildIndex() {
        int retry;                      //当前流程重试次数
        final int MAX_RETRY_TIMES = 3;  //最大允许重试次数
        boolean succeeded;              //当前流程是否成功
        isReceivingMessage = false;     //索引建立完毕前暂停接收消息以避免NullPointerException
        System.out.println("[" + getTime() + "] 开始建立索引，暂停接收消息");

        //获取好友列表
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                friendList = client.getFriendList(); //获取好友列表
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 获取好友列表屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //获取群列表
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                groupList = client.getGroupList(); //获取群列表
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 获取群列表屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //获取讨论组列表
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                discussList = client.getDiscussList(); //获取讨论组列表
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 获取讨论组列表屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //建立好友id到好友映射
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                friendFromID.clear();
                for (Friend friend : friendList) { //建立好友id到好友映射
                    friendFromID.put(friend.getUserId(), friend);
                }
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 建立好友id到好友映射屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //建立群id到群映射
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                groupFromID.clear();
                groupInfoFromID.clear();
                for (Group group : groupList) { //建立群id到群映射
                    groupFromID.put(group.getId(), group);
                }
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 建立群id到群映射屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //建立讨论组id到讨论组映射
        retry = 0;
        succeeded = false;
        while (!succeeded) {
            try {
                sleep(100);
                discussFromID.clear();
                discussInfoFromID.clear();
                for (Discuss discuss : discussList) { //建立讨论组id到讨论组映射
                    discussFromID.put(discuss.getId(), discuss);
                }
                succeeded = true;
            } catch (Exception e) {
                retry++;
                if (retry > MAX_RETRY_TIMES) {
                    System.out.println("[" + getTime() + "] 建立讨论组id到讨论组映射屡次失败，程序无法继续运行");
                    return false;
                }
            }
        }

        //为防止请求过多导致服务器启动自我保护
        //群id到群详情映射 和 讨论组id到讨论组详情映射 将在第一次请求时创建
        System.out.println("[" + getTime() + "] 索引建立完毕，开始接收消息");
        isReceivingMessage = true; //索引建立完毕后继续接收消息
        return true; //若中途未意外终止，则索引建立成功
    }
}
