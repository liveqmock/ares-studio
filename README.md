### How to download
ftp:aresstudio:at2re@192.168.51.31/ARES Studio4CRES_V1.0.exe

### How to build
cd 到根目录

```
mvn clean install
```

命令执行后，updatesite的zip包会生成在build/com.hundsun.ares.studio.repository/target/目录下; 生成的安装包在build/com.hundsun.ares.studio.product/target/products目录下。
_**初次构建由于要下载很多jar包，会比较慢。 另外，如果不需要打安装包，在pom中去掉com.hundsun.ares.studio.product这个module也可以节省时间**_
