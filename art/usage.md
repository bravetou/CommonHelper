# 代码示例

---

#### 初始化方式：

---

 - 在Application的onCreate方法中调用
 
```java
    CommonConfig.init(this);
```

 - 直接继承至[CommonApplication](/common/src/main/java/com/brave/common/base/CommonApplication.java)
 
 
#### 使用方法

---

```java
    // 短时间显示提示
    showTooltip();
    
    // 长时间显示提示
    showLongTooltip();
    
    // 打开 Views 接收焦点
    openViewsFocusable();
    
    // 关闭 Views 接收焦点
    closeViewsFocusable();
    
    // 设置 Views 的启用状态(toggle方法为双开关)
    setViewsEnabled();
    
    // 设置 Views 的选择状态(toggle方法为双开关)
    setViewsSelected();
    
    // 设置 Views 单击事件的启用状态(toggle方法为双开关)
    setViewsClickable();
    
    // 设置 Views 长按事件的启用状态(toggle方法为双开关)
    setViewsLongClickable();
    
    // 根据 ResId 查找 View
    findViewById();
    
    // 显示 Views
    showViews();
    
    // 隐藏 Views
    hideViews();
    
    // 移动光标到末尾
    moveCursorToEnd();
```

#### 其他工具

---

<b>

 - [FileUtils](/common/src/main/java/com/brave/common/utils/io/FileUtils.java) - 文件操作相关
 - [NetworkUtils](/common/src/main/java/com/brave/common/utils/network/NetworkUtils.java) - 网络相关
 - [SpanUtils](/common/src/main/java/com/brave/common/utils/span/SpanUtils.java) - 文本操作相关（可实现图文混排）
 - [StatusBarUtils](/common/src/main/java/com/brave/common/utils/system/StatusBarUtils.java) - 状态栏相关
 - [NavBarUtils](/common/src/main/java/com/brave/common/utils/system/NavBarUtils.java) - 导航栏 相关
 - [SystemBarUtils](/common/src/main/java/com/brave/common/utils/system/SystemBarUtils.java) - 系统栏（含：状态栏、导航栏）相关
 - [CountdownUtils](/common/src/main/java/com/brave/common/utils/time/CountdownUtils.java) - 倒计时相关
 - [TimeUtils](/common/src/main/java/com/brave/common/utils/time/TimeUtils.java) - 时间相关
 
 - [AppUtils](/common/src/main/java/com/brave/common/utils/AppUtils.java) - App相关
 - [ClipboardUtils](/common/src/main/java/com/brave/common/utils/ClipboardUtils.java) - 剪贴板相关
 - [ColorUtils](/common/src/main/java/com/brave/common/utils/ColorUtils.java) - 颜色相关
 - [DensityUtils](/common/src/main/java/com/brave/common/utils/DensityUtils.java) - 单位换算相关
 - [DeviceUtils](/common/src/main/java/com/brave/common/utils/DeviceUtils.java) - 设备相关
 - [KeyboardUtils](/common/src/main/java/com/brave/common/utils/KeyboardUtils.java) - 键盘相关
 - [RegexUtils](/common/src/main/java/com/brave/common/utils/RegexUtils.java) - 正则相关
 - [ScreenshotUtils](/common/src/main/java/com/brave/common/utils/ScreenshotUtils.java) - 截图相关
 - [ScreenUtils](/common/src/main/java/com/brave/common/utils/ScreenUtils.java) - 屏幕相关
 - [ServiceUtils](/common/src/main/java/com/brave/common/utils/ServiceUtils.java) - 服务相关
 - [SettingsUtils](/common/src/main/java/com/brave/common/utils/SettingsUtils.java) - 手机设置界面相关
 - [ZipUtils](/common/src/main/java/com/brave/common/utils/ZipUtils.java) - 原生Zip压缩、解压相关
 
 </b>
 
#### 备注

---

 - 数据库推荐使用[Realm](https://github.com/realm/realm-java)、[greenDAO](https://github.com/greenrobot/greenDAO)等
 
 - 图片操作推荐使用[ImageLaoder](https://github.com/nostra13/Android-Universal-Image-Loader)、[Picasso](https://github.com/square/picasso)、[Glide](https://github.com/bumptech/glide)、[fresco](https://github.com/facebook/fresco)
 
 - 网络请求推荐使用[Retrofit](http://square.github.io/retrofit/) + rxjava + rxandroid
 
 - 实例化View推荐使用[Butter Knife](http://jakewharton.github.io/butterknife/)
 
 - 图表框架推荐使用[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
 
 - 内存检测推荐使用[leakcanary](https://github.com/square/leakcanary) 
 
 - 组件间通信推荐使用[EventBus](https://github.com/greenrobot/EventBus)
 
 - 二维码、条形码等推荐使用[zxing](https://github.com/zxing/zxing)
 
 - 动画类推荐使用[lottie-android](https://github.com/airbnb/lottie-android)
 
 - 如果你也在使用RecyclerView，那么强烈推荐你使用[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

---

优秀的框架还有更多，我只是推荐了一些一般项目必用的框架..