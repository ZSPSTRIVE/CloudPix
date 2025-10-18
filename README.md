# 大鸟云图库

## 📖 项目介绍

云图库是一个企业级的图片管理和协作平台，支持多用户协作、智能图片处理、空间管理等功能。本项目采用前后端分离架构，后端基于DDD领域驱动设计，前端使用Vue3构建。

## 🎯 项目特色

- 🏗️ **DDD架构**：采用领域驱动设计，代码结构清晰，易于维护和扩展
- 🔐 **权限管理**：基于RBAC模型的复杂权限控制系统
- 🚀 **高性能**：Redis + Caffeine多级缓存，Disruptor无锁队列
- 📊 **分库分表**：ShardingSphere实现动态扩容
- 🤝 **实时协作**：WebSocket实现多端实时通信
- 🎨 **AI绘图**：集成AI大模型，支持智能图片处理
- 🌐 **对象存储**：腾讯云COS高效文件存储

## 🛠️ 技术栈

### 后端技术
- **框架**：Spring Boot 3.x
- **数据库**：MySQL 8.0 + MyBatis-Plus
- **缓存**：Redis + Caffeine
- **权限**：Sa-Token
- **存储**：腾讯云 COS
- **消息队列**：Disruptor
- **分库分表**：ShardingSphere
- **实时通信**：WebSocket

### 前端技术
- **框架**：Vue 3 + TypeScript
- **构建工具**：Vite
- **UI组件**：Ant Design Vue
- **状态管理**：Pinia
- **HTTP客户端**：Axios
- **代码规范**：ESLint + Prettier

## 📁 项目结构

```
📦 协同云图库
├── 📂 yu-picture-backend-ddd/     # DDD架构后端项目
│   ├── 📂 src/main/java/com/yupi/yupicture/
│   │   ├── 📂 domain/             # 领域层
│   │   ├── 📂 application/        # 应用层
│   │   ├── 📂 infrastructure/     # 基础设施层
│   │   └── 📂 shared/            # 共享层
│   └── 📂 httpTest/              # HTTP接口测试
├── 📂 yu-picture-frontend/        # Vue3前端项目
│   ├── 📂 src/
│   │   ├── 📂 components/        # 组件
│   │   ├── 📂 pages/            # 页面
│   │   ├── 📂 api/              # API接口
│   │   └── 📂 stores/           # 状态管理
│   └── 📄 package.json
├── 📂 yu-picture-backend/         # 传统架构后端（学习对比）
├── 📄 picture.sql                # 数据库建表脚本
├── 📄 yu_picture.sql             # 初始化数据脚本
└── 📚 DDD开发文档/               # 详细开发文档
```

## 🏃‍♂️ 快速开始

### 环境要求
- JDK 17+
- Node.js 16+
- MySQL 8.0+
- Redis 6.0+

### 后端启动
```bash
cd yu-picture-backend-ddd
mvn clean install
mvn spring-boot:run
```

### 前端启动
```bash
cd yu-picture-frontend
npm install
npm run dev
```



## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

## 📄 开源协议

本项目基于 MIT 协议开源。

---

⭐ 如果这个项目对你有帮助，请给个星标支持！
