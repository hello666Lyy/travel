# Travel 旅游管理系统

## 项目简介

Travel是一个基于Spring Boot开发的**前后端不分离**旅游管理系统，提供了完整的前后台功能，包括旅游产品管理、用户管理、角色权限管理等核心功能。系统采用了现代化的技术栈和架构设计，具有良好的可扩展性和维护性。

**前后端不分离架构特点**：
- 使用Thymeleaf模板引擎渲染页面，后端直接生成HTML响应
- 前端资源（CSS、JavaScript、图片）与后端代码部署在一起
- 服务器端处理页面跳转和表单提交
- 适合中小型项目，开发效率高，部署简单

## 技术栈

### 后端技术
- **Spring Boot 3.0.6**：应用框架，提供自动配置、嵌入式服务器等特性
- **Java 17**：开发语言
- **Spring Security**：安全框架，提供认证和授权功能
- **MyBatis Plus 3.5.4**：ORM框架，简化数据库操作
- **MySQL 8.0**：关系型数据库
- **Spring AOP**：面向切面编程，实现日志记录等横切关注点
- **Spring Mail**：邮件发送功能
- **Lombok**：代码简化工具，减少样板代码

### 前端技术
- **Thymeleaf**：模板引擎
- **Bootstrap**：前端UI框架
- **jQuery**：JavaScript库
- **AdminLTE**：后台管理系统模板
- **wangEditor**：富文本编辑器

## 项目结构

```
src/
├── main/
│   ├── java/com/itbaizhan/
│   │   ├── aop/                # 面向切面编程实现
│   │   ├── bean/               # 数据传输对象和结果封装
│   │   ├── config/             # 配置类
│   │   ├── controller/         # 控制器层
│   │   │   ├── backstage/      # 后台管理控制器
│   │   │   ├── frontdesk/      # 前台用户控制器
│   │   │   └── PageController.java # 页面跳转控制器
│   │   ├── interceptor/        # 拦截器
│   │   ├── mapper/             # 数据访问层
│   │   ├── pojo/               # 实体类
│   │   ├── security/           # 安全配置
│   │   ├── service/            # 业务逻辑层
│   │   └── util/               # 工具类
│   └── resources/
│       ├── com/itbaizhan/mapper/ # MyBatis映射文件
│       ├── static/             # 静态资源
│       │   └── backstage/      # 后台管理界面资源
│       └── application.yml     # 应用配置文件
└── test/                       # 测试代码
```

## 核心功能

### 后台管理功能
1. **管理员管理**：管理员账号的增删改查
2. **产品管理**：旅游产品的发布、编辑、删除和查询
3. **角色管理**：角色的创建、分配权限和管理
4. **权限管理**：系统权限的定义和分配
5. **会员管理**：前台用户的管理

### 前台用户功能
1. **用户注册/登录**：会员账号的创建和登录
2. **产品浏览**：查看旅游产品信息
3. **个人中心**：用户个人信息管理

## 架构设计

### 分层架构

系统采用经典的三层架构设计：

1. **表现层（Controller）**：处理HTTP请求，返回响应结果
   - 分为后台管理控制器和前台用户控制器
   - 使用Thymeleaf模板引擎渲染页面

2. **业务逻辑层（Service）**：实现核心业务逻辑
   - 封装复杂的业务规则和流程
   - 调用数据访问层完成数据操作

3. **数据访问层（Mapper）**：与数据库交互
   - 使用MyBatis Plus简化数据库操作
   - 提供CRUD和复杂查询功能

### 安全架构

- 使用Spring Security实现认证和授权
- 基于角色的访问控制（RBAC）
- 密码加密存储
- 权限拦截和过滤

### AOP应用

- 日志记录：使用AOP记录系统操作日志
- 事务管理：声明式事务支持

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone <项目地址>
   cd travel
   ```

2. **配置数据库**
   - 创建名为`travel`的数据库
   - 修改`application.yml`中的数据库配置：
     ```yaml
     spring:
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         url: jdbc:mysql:///travel?serverTimezone=UTC
         username: root
         password: your_password
     ```

3. **编译项目**
   ```bash
   mvn clean install
   ```

4. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

5. **访问系统**
   - 后台管理：`http://localhost/admin/login`
   - 前台首页：`http://localhost`

## 配置说明

### 主要配置项

| 配置项 | 说明 | 默认值 |
|-------|------|-------|
| server.port | 服务器端口 | 80 |
| spring.datasource | 数据库配置 | - |
| spring.mail | 邮件发送配置 | - |
| mybatis-plus | MyBatis Plus配置 | - |
| logging | 日志配置 | - |
| project.path | 项目路径 | http://localhost |

### 邮件配置

```yaml
mail:
  user: your_email@qq.com
  password: your_email_password
  host: smtp.qq.com
  port: 587
```

## 开发指南

### 代码规范
- 遵循Spring Boot代码规范
- 使用Lombok简化代码
- 接口和方法添加适当的注释
- 异常处理统一管理

### 数据库设计

系统主要包含以下核心表：
- `admin`：管理员表
- `member`：会员表
- `product`：产品表
- `category`：产品分类表
- `role`：角色表
- `permission`：权限表
- `admin_role`：管理员角色关联表
- `role_permission`：角色权限关联表

### 开发流程
1. 创建实体类（pojo）
2. 创建Mapper接口
3. 创建Service接口和实现类
4. 创建Controller
5. 编写前端页面
6. 测试功能

## 系统截图

### 后台管理界面
- 产品管理列表
- 角色权限分配
- 管理员管理

### 前台用户界面
- 产品浏览
- 用户登录
- 个人中心

## 许可证

MIT License

## 贡献

欢迎提交Issue和Pull Request！

## 联系方式

如有问题或建议，请联系：
- 邮箱：your_email@example.com
- GitHub：https://github.com/your_username/travel
