# 迪莫机器人 - Robot Deemo

基于 [ScienJus/smartqq][sj] 制作的 QQ 机器人。

### 写在最前面

- **本项目仅在Windows平台上做过测试。**
如果你在使用其他平台的系统，需要自行修改部分代码。
（已知`org.dilant.deemo.frame.QRCodeFrame`会造成一些问题）

- **本项目基于[SmartQQ][qq]**，
所以暂不能实现收发图片/语音/文件等功能。

- **请勿删除data/qrcode/文件夹**，
否则会导致`com.scienjus.smartqq.client.SmartQQClient`抛出`IOException`。

### 使用方法

你可以在此基础上进行修改，
也可以~~砍掉重练~~重写一套自己的业务逻辑，
或者加入更多的功能，例如——

- 图形化操作界面
- 将机器人部署在服务器上
- 保存并汇总聊天记录
- 控制远程设备（吃瓜群众请右转寻找[TeamViewer][tv]）
- ~~单开一个群和朋友玩狼人杀~~
- ……

没错，其中一部分也是我的目标。

**基于本项目修改**
1. 配置JDK环境和IDE
2. 给机器人改个名（包括但不限于包名、指令和文案）
~~（不要拉着我的迪莫到处晃悠）~~ 
3. 修改`org.dilant.deemo.function.Command`中的指令和触发逻辑
4. 修改`org.dilant.deemo.structure.Strings`中的文案
5. enjoy

**编写自己的业务逻辑**
1. 配置JDK环境和IDE
2. 给机器人改个名
3. 关注`org.dilant.deemo.Receiver`和`org.dilant.deemo.util.*`，它们会助你一臂之力。

### 常见错误

**编译错误**
- 本项目的最低语言要求是Java 7。请关注你的JDK和IDE版本。
- 由于精力有限，暂只保证Windows平台上的可用性。

**API错误**
- 检查你的网络连接。~~（看好家里的宠物和小孩！）~~
- 在[SmartQQ][qq]上作测试，确认是否为腾讯服务器的问题。
- 如果可以确定是项目的API部分出现问题，请到[原项目][sj]处反馈。

**机器人不按期望方式运行**
- 检查自己编写的逻辑是否正确。（善用调试功能）
- 如果是本项目的模块（例如`org.dilant.deemo.function.Permission`）出现问题，欢迎Issue反馈。

#### 重大更新日志

- 2017-7-22 创建repo。

### 其他版本

1. @ScienJus 的 [API 封装][sj]
2. @88250 制作的机器人[小薇][xiaov]
3. @sjdy521 使用Perl语言编写的 [Mojo-Webqq][mojo]
4. @TJYSunset 使用C#语言编写的 [DumbQQ][dumb]
5. @ThomasVadeSmileLee 使用kotlin语言编写的机器人[穿云裂石][cyls]

### 联系方式

欢迎通过Issue反馈，或是通过邮件联系我：`dilant@foxmail.com`。

由于我是寄宿高中生，可能无法及时回复，但闲暇之时我一定会给出反馈。

2018-6-7 高考加油！ :D

[sj]: https://github.com/ScienJus/smartqq
[tv]: https://www.teamviewer.com/
[qq]: http://w.qq.com/
[xiaov]: https://github.com/b3log/xiaov
[mojo]: https://github.com/sjdy521/Mojo-Webqq
[dumb]: https://github.com/TJYSunset/DumbQQ
[cyls]: https://github.com/ThomasVadeSmileLee/cyls