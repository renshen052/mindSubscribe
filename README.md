# 心理咨询预约管理系统



个人毕业设计用的一个玩具项目，无框架。

MVC模式，普通Servlet控制器，jsp视图，前台Bootstrap

工具版本：mysql5.7，JDK1.8，Tomcat8.5

## 主要功能模块

分为三个端：管理员、来访者（需要预约的人），咨询师

**管理员**端

* 首页 

  系统近况（咨询师和注册来访者数量，预约数量）
  显示最新的消息、留言和公告（最新十条）

* 主要功能

  咨询师管理(添加、修改、删除，账号激活停用)

  来访者管理（账号激活停用）

  预约问卷管理（添加、修改、删除）

* 公共和个人信息模块

  消息、公告和留言管理（发送接受消息，发布公告，控制留言显示和隐藏）

  个人信息管理（修改基本信息和密码）

**咨询师端**

- 首页 
  显示最新的消息、留言和公告（最新十条）

- 主要功能

  查看预约详情，安排咨询申请（安排咨询时间或驳回）

  咨询完毕建立咨询档案

  查看所有咨询历史

- 公共和个人信息模块

  消息、公告和留言管理（发送接受消息，查看公告，留言）

  个人信息管理（修改基本信息和密码）

**来访者端**

- 首页 
  显示最新的消息、留言和公告（最新十条）

- 主要功能

  发起预约申请，等待安排

  在我的及时了解预约状态(待安排、准备咨询、被取消）

  查看所有咨询历史

- 公共和个人信息模块

  消息、公告和留言管理（发送接受消息，查看公告，留言）

  个人信息管理（修改基本信息和密码）

预约状态变动时邮件和站内消息通知来访者



**其他**

来访者注册

管理员、咨询师和来访者登录



**预约流程**

1. 来访者申请预约

*来访者向某位咨询师申请预约，并填写预约申请表（期望时间地点和评估问卷）*

2. 咨询师安排咨询
*处理咨询申请，通过申请安排咨询，取消咨询*

3. 咨询档案
*咨询完成，咨询师上传咨询档案；每次咨询都会记录(可在我的咨询中查看)*

4. 来访者评价
*咨询完成，来访者对本次咨询进行评价*





## 项目工程文件

**src**

bean是数据库表对应实体类

model包下分别是dao层和service层

servlet下的是公共模块部分，admin，client，doctor分别是三个端对应的控制器

test是测试包，无用

utils.filter过滤器，登录过滤，字符过滤等等

utils.jdbc数据库工具类，配置和得到连接池的连接

utils.mail是邮件模块

utils下的ConfigProperties是读入配置文件，ResultDate和UploadResult分别是请求响应结果和文件上传结果对象，Util封装了常用的工具

config.properties 配置参数，配置邮件发送，文件上传大小类型限制等

pool.properties 是连接池配置文件

**WebContext**

amidn,client,doctor分别是三个端对应的页面

plunge是用到的插件

mutualResource 是js，css样式模版目录



**mind_sub.sql是数据库表脚本**

数据库名：mind_sub



## 运行项目

* 新建数据库mind_sub，运行脚本mind_sub.sql

* 修改pool.properties中的username和password

* 修改config.properties中的邮件服务器为你自己的

  HostName ，AuthenticationEmail，AuthenticationPwd

  （我这里用的qq的）

  修改upload_path路径

* 在要部署的Tomcat中修改它的server.xml，在Host标签下

  增加一行 `<Context docBase="F:\upload" path="/mind_upload" reloadable="true"/>`

  这是文件和图片上传存放的位置

  **注意要与你config.properties中upload_path路径一致**

  

然后把项目部署到Tomcat运行即可