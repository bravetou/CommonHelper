# Android常用工具类框架-CommonHelper

---

Bring together some common Android help and tool classes to make project development easier.

<br/>

#### 引用

---

```java
    implementation 'com.brave.common:common:1.0.6'
```

<br/>

#### 初始化

---

 - 在Application的onCreate方法中调用
 
```java
    CommonConfig.init(this);
```

 - 直接继承至[CommonApplication](/common/src/main/java/com/brave/common/base/CommonApplication.java)

#### 功能特点

---

 - 实现 Activity、Fragment、Dialog、Application 基类
 - 载入 PermissionsUtils工具类，实现动态权限监测、管理与请求
 - 载入 ActivityUtils工具类，实现Activity栈 管理
 - 载入 BroadCastUtils工具类，实现动态广播 管理（注册、监听、销毁）
 - 载入 cipher工具包，实现对称加密（CipherUtils）、MD5加密（MD5Utils）等
 - 载入 io工具包，实现数据储存（实现文件、XML储存两种），数据库存储 推荐使用三方开源[Realm](https://github.com/realm/realm-java)、[greenDAO](https://github.com/greenrobot/greenDAO)等
 
 - 载入 NetworkUtils工具类,实现网络监测
 - 载入 AssetUtils工具类，实现对assets资源包下文件的存取
 - 载入 SpannableString工具类，告别繁琐的（图文混排）操作
 - 载入 SystemBarUtils(StatusBarUtils、NavBarUtils)工具类，轻巧的实现对状态栏与导航栏的操作
 - 载入 TimeUtils、CountdownUtils工具类，轻巧的实现对时间戳、时间字符串、倒计时的操作
 - 载入 AppUtils工具类，快速获取APP信息
 - 载入 ClipboardUtils工具类，轻巧的实现对剪贴板的操作
 - 载入 ColorUtils工具类，轻巧的实现颜色（argb）转换
 - 载入 DensityUtils工具类，轻巧的实现（密度）单位换算
 - 载入 DeviceUtils工具类，轻巧的实现对设备的操作
 - 载入 KeyboardUtils工具类，轻巧的实现对键盘的操作
 - 载入 RegexUtils工具类，轻巧的实现对正则需求
 - 载入 ScreenshotUtils工具类，轻巧的实现对屏幕及其View的截图
 - 载入 ScreenUtils工具类，屏幕相关判断与操作
 - 载入 ServiceUtils工具类，实现Service（服务）的启动、停止、绑定与解绑
 - 载入 ZipUtils工具类，实现原生的Zip压缩与解压，如果不足以实现需求，推荐使用[Zip4j](http://www.lingala.net/zip4j/)、[java-unrar](http://www.java2s.com/Code/Jar/j/Downloadjavaunrar05jar.htm)

#### 使用方法

---

 - [使用文档](/art/usage.md)

