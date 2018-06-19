#使用示例

---

初始化方式：

 - 在Application的onCreate方法中调用
 
```
CommonConfig.getInstance().initialize(this);
```

 - 直接继承至[CommonApplication](/common/src/java/com/brave/common/base/CommonApplication.java)