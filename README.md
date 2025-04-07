# 教务通（JavaWeb开发初级项目实战-从入门到部署）
<<JavaWeb开发初级项目实战-从入门到部署>> 手把手教你项目上线    
适合初学者的前后端Web全栈项目(前端vue3 后端springboot)
黑马程序员教学视频的前后端分离小项目。包含后端、前端开发、项目部署上线等、学生可以白嫖阿里云ECS、OSS、SSL等 域名(10元左右) 整个项目到上线花费10左右。
# 学习这个项目后你可以获得：
独立设计并创建MySQL数据表结构，使用Maven构建基于Spring Boot + MyBatis的后端服务和 Vue3 + Element Plus 的前端应用，实现用户登录认证、数据增删改查、文件上传、记录日志、全局异常处理以及数据统计报表展示等功能。通过 RESTful API 接口进行前后端交互，并使用 JWT 实现用户登录认证。最后基于Docker引擎将项目部署在阿里云提供的云计算服务平台。
# 项目结构与打开方式（将项目下载到本地后）
  1.利用sql脚本创建表结构与数据  
  2.打开前端源代码，以vscode为例：在package.json依赖目录下打开命令行窗口执行->npm install 下载好依赖后就可以执行npm 脚本打开了  
  3.打开后端源代码，项目结构为：（以intellij为例 打开项目后 被maven识别后依赖下载完毕->然后进行Spring Boot 配置文件修改）  
  ```
    教务通 \\ 一个空项目结构 理解为一个文件夹  
      -tlias-parent 父工程  
      -tlias-pojo 实体类 继承父工程  
      -tlias-utils 工具类 继承父工程  
      -tlias-web-managent 核心代码 继承父工程  
        -src.main.java.cn.zimeedu  
          -TliasWebManagementApplication 启动类  
        -src/main/resources/  
          -application.yml SpringBboot 配置文件    需要自己修改  
    代码修改完成后需要 如果需要用到头像上传功能 需要在SpringBboot 配置文件 中配置自己的阿里云OSS环境变量
 ```
  4.项目部署 阿里云云服务全家桶，可以参考黑马教学视频。  
# 技术栈版本
  ```
  后端：jdk-21.0.6 、 maven-3.9.9 、  springboot-3.4.3 、 mysql-8
  前端：vue3-3.2.38
  部署：nginx-1.26.3 、 阿里云ECS-2核2GIB内存-RockyLinux-9.5 64位操作系统
  ```
# B站教程与作者的开发文档  
  视频教程：https://www.bilibili.com/video/BV1yGydYEE3H/?spm_id_from=333.1007.top_right_bar_window_default_collection.content.click&vd_source=b91376d2f18244b8dbc5a643b35d123f  
  项目开发文档：https://heuqqdmbyk.feishu.cn/wiki/G9W0wL5KYiKWrykH8UAcmxnbnuh  
# 学习交流群QQ
  QQ群：669144159  
# 最后给个星星吧  (╥﹏╥)
