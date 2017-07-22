package org.dilant.deemo.function;

import com.scienjus.smartqq.client.SmartQQClient;

import java.util.List;

public class Reply {

    /**
     * @param x 范围：0~(x-1)
     * @return 一个随机整数
     */
    public static int ranInt(int x) {
        return (int) (Math.random() * x);
    }

    /**
     * @param groupId 被回复的群
     *                //     * @param chance 回复的概率
     * @param content 发送的内容
     *                //     * @param contents 发送的内容数组
     */
    public static void sendToGroup(Long groupId, SmartQQClient client, String content) {
        client.sendMessageToGroup(groupId, content);
    }

    public static void sendToGroup(Long groupId, SmartQQClient client, String... contents) {
        sendToGroup(groupId, client, contents[ranInt(contents.length)]);
    }

    public static void sendToGroup(Long groupId, SmartQQClient client, List<String> contents) {
        sendToGroup(groupId, client, contents.get(ranInt(contents.size())));
    }

    public static void sendToGroupByChance(Long groupId, SmartQQClient client, double chance, String content) {
        if (Math.random() < chance)
            sendToGroup(groupId, client, content);
    }

    public static void sendToGroupByChance(Long groupId, double chance, SmartQQClient client, String... contents) {
        if (Math.random() < chance)
            sendToGroup(groupId, client, contents);
    }

    public static void sendToGroupByChance(Long groupId, double chance, SmartQQClient client, List<String> contents) {
        if (Math.random() < chance)
            sendToGroup(groupId, client, contents);
    }
}
