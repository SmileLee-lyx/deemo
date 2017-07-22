package org.dilant.deemo.structure;

public class Strings {

    //TreeNode deemeHelp
    public static String deemoHelpReply = "别急，迪兰特还没写帮助呢~";

    //TreeNode deemoSudo
    public static String deemoSudoReply = "别急，迪兰特还没写帮助呢~";

    //TreeNode deemoSudoExit
    public static String deemoSudoExitReply[] = {
            "大家再见~",
            "后会有期~"
    };

    //TreeNode deemoSudoPause
    public static String[] deemoSudoPauseReply = {
            "嘛，睡觉去咯。",
            "我先歇一歇，稍后再来~"
    };

    //TreeNode deemoSudoResume
    public static String[] deemoSudoResumeReply = {
            "呀，迪莫归来！",
            "哈哈哈，被吓到了吧？"
    };

    //TreeNode deemoUtil
    public static String deemoUtilReply = "别急，迪兰特还没写帮助呢~";

    //TreeNode deemoUtilCoin
    public static String[] deemoUtilCoinReply() {
        long rand = System.currentTimeMillis() % 2;
        String result = (rand > 0 ? "正" : "反");
        return new String[]{
                result + "面落地，不知是不是你期望的结果呢？",
                "你真要用硬币作决定？好吧，" + result + "面。"
        };
    }

    //TreeNode deemoUtilDice 正常情况
    public static String[] deemoUtilDiceReply(int surface) {
        long rand = System.currentTimeMillis() % surface + 1;
        return new String[]{
                rand + "点，可还满意？",
                "嗯，" + rand + "点……"
        };
    }

    //TreeNode deemoUtilDice 非标准骰子
    public static String[] deemoUtilDiceReply_Special(int surface) {
        return new String[]{
                "有人已经不满足于标准骰子了，他们需要一个" + surface + "面的骰子……",
                surface + "面骰子？看我现做一个——"
        };
    }

    //TreeNode deemoUtilDice 一面骰子
    public static String[] deemoUtilDiceReply_1 = {
            "迪莫可不玩弹珠 =_=",
            "嘿，你的玻璃球~",
            "结果嘛……你觉得呢？"
    };

    //TreeNode deemoUtilDice 二面骰子
    public static String[] deemoUtilDiceReply_2 = {
            "两面的骰子……你说的是硬币吧？"
    };

    //TreeNode deemoUtilDice 三面骰子
    public static String[] deemoUtilDiceReply_3 = {
            "你整个三面体给我看看？"
    };

    //TreeNode deemoUtilDice 太多面的骰子
    public static String[] deemoUtilDiceReply_100 = {
            "这……我可做不出这么多面的骰子 =_="
    };

    //TreeNode deemoUtilDice 不存在的骰子
    public static String[] deemoUtilDiceReply_Illegal = {
            "这是啥骰子……Kidding me? ",
            "还有这种骰子？",
            "不存在的~"
    };

    //TreeNode deemoSudoTest 主人
    public static String deemoSudoTestReply_Root[] = {
            "迪兰特啊，你咋把我也忘了呢……",
            "看一眼电脑屏幕，然后告诉我答案——"
    };

    //TreeNode deemoSudoTest 管理员
    public static String deemoSudoTestReply_Admin[] = {
            "你可是管理员啊，又玩忽职守了？",
            "信不信我把你炒鱿鱼啊 =_="
    };

    //TreeNode deemoSudoTest 普通成员
    public static String deemoSudoTestReply_Normal[] = {
            "洗洗睡吧，别做白日梦了……",
            "在平凡中创造不平凡吧，孩子。"
    };

    //TreeNode deemoSudo 非管理员
    public static String notAdminReply[] = {
            "嘿，你还不是管理员！",
            "只有管理员才可以进行该操作哦。"
    };

    //TreeNode deemoSudo 非主人
    public static String notRootReply[] = {
            "哼，不听不听。",
            "只有迪兰特才可以进行该操作哦。"
    };

    //TreeNode deemoSudoAuthorize
    public static String[] deemoSudoAuthorizeReply(String nick) {
        return new String[]{
                nick + "已经成为管理员咯。",
                "欢迎新管理员" + nick + "上任~"
        };
    }

    //TreeNode deemoSudoUnauthorize
    public static String[] deemoSudoUnauthorizeReply(String nick) {
        return new String[]{
                "城头变幻大王旗呐……",
                "只有失去了，才会懂得珍惜吧。"
        };
    }

    //TreeNode deemoSudoBan
    public static String[] deemoSudoBanReply(String nick) {
        return new String[]{
                "哼，迪莫不理你了！",
                "我可懒得管你。"
        };
    }

    //TreeNode deemoSudoUnban
    public static String[] deemoSudoUnbanReply(String nick) {
        return new String[]{
                "看在管理员的份上，我就网开一面吧。",
                "你你你你怎么回来了？……也罢也罢。"
        };
    }

    //TreeNode deemoSudo 操作失败
    public static String deemoSudoFailedReply[] = {
            "查无此人 =_=",
            "唔，好像有点难办……",
            "我似乎做不到。"
    };

    //RegexFunction yesOrNoRF
    public static String yesOrNoRFReply[] = {
            "是啊",
            "当然咯",
            "You got it :D",
            "No, no, no... ",
            "不要乱来……",
            "我猜不是（逃）",
            "你觉得呢？",
            "猜猜看~",
            "啥……？",
            "云裂怎么看？"
    };

    //RegexFunction haveOrHaveNotRF
    public static String haveOrHaveNotRFReply[] = {
            "有有有——",
            "当然咯",
            "肯定的嘛",
            "不存在的~",
            "你又调皮了 =_=",
            "估计是没有了（摊手）",
            "你说有就有呗",
            "猜猜看呐~~",
            "... Excuse me? ",
            "嘿嘿嘿（逃）"
    };

    //RegexFunction confessRF
    public static String confessRFReply(String nick) {
        return "表白" + nick + "~";
    }

    //RegexFunction callDeemoRF
    public static String callDeemoRFReply[] = {
            "似乎有人叫我？",
            "Who's there? ",
            "来者何人？",
            "哈？",
            "手动幽灵",
            "又想调戏我？"
    };
}