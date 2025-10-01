# 短链接后管联调中台接口测试文档

## 📝 架构说明

- **admin服务（后管）**: `http://127.0.0.1:8902`
- **project服务（中台）**: `http://127.0.0.1:8001`
- **调用关系**: 前端 → admin → project

## 🚀 启动步骤

### 1. 启动 project 服务（中台）
```bash
cd project
mvn spring-boot:run
```
访问: http://127.0.0.1:8001

### 2. 启动 admin 服务（后管）
```bash
cd admin
mvn spring-boot:run
```
访问: http://127.0.0.1:8902

## 📡 API 接口测试

### 1️⃣ 创建短链接

**请求方式**: POST  
**admin接口**: `http://127.0.0.1:8902/api/short-link/admin/v1/create`  
**project接口**: `http://127.0.0.1:8001/api/short-link/v1/create`

**请求体示例**:
```json
{
  "domain": "nurl.ink",
  "originUrl": "https://www.baidu.com",
  "gid": "test123",
  "createdType": 0,
  "validDateType": 0,
  "describe": "测试短链接"
}
```

**响应示例**:
```json
{
  "code": "0",
  "message": "success",
  "data": {
    "gid": "test123",
    "originUrl": "https://www.baidu.com",
    "fullShortUrl": "nurl.ink/abc123"
  }
}
```

### 2️⃣ 分页查询短链接

**请求方式**: GET  
**admin接口**: `http://127.0.0.1:8902/api/short-link/admin/v1/page?gid=test123&current=1&size=10`  
**project接口**: `http://127.0.0.1:8001/api/short-link/v1/page?gid=test123&current=1&size=10`

**请求参数**:
- `gid`: 分组标识（必填）
- `current`: 当前页码（必填）
- `size`: 每页条数（必填）

**响应示例**:
```json
{
  "code": "0",
  "message": "success",
  "data": {
    "records": [
      {
        "id": 1,
        "domain": "nurl.ink",
        "shortUri": "abc123",
        "fullShortUrl": "nurl.ink/abc123",
        "originUrl": "https://www.baidu.com",
        "clickNum": 0,
        "gid": "test123",
        "validDateType": 0,
        "validDate": null,
        "createTime": "2025-10-01 10:00:00",
        "describe": "测试短链接",
        "favicon": null
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1,
    "pages": 1
  }
}
```

## 🧪 使用 cURL 测试

### 创建短链接
```bash
curl -X POST http://127.0.0.1:8902/api/short-link/admin/v1/create \
  -H "Content-Type: application/json" \
  -d '{
    "domain": "nurl.ink",
    "originUrl": "https://www.baidu.com",
    "gid": "test123",
    "createdType": 0,
    "validDateType": 0,
    "describe": "测试短链接"
  }'
```

### 分页查询短链接
```bash
curl -X GET "http://127.0.0.1:8902/api/short-link/admin/v1/page?gid=test123&current=1&size=10"
```

## 🔧 使用 Postman 测试

### 1. 创建短链接
1. 新建请求，选择 `POST` 方法
2. URL: `http://127.0.0.1:8902/api/short-link/admin/v1/create`
3. Headers 添加: `Content-Type: application/json`
4. Body 选择 `raw` -> `JSON`，填入请求体
5. 点击 Send

### 2. 分页查询短链接
1. 新建请求，选择 `GET` 方法
2. URL: `http://127.0.0.1:8902/api/short-link/admin/v1/page`
3. Params 添加:
   - Key: `gid`, Value: `test123`
   - Key: `current`, Value: `1`
   - Key: `size`, Value: `10`
4. 点击 Send

## 📊 配置说明

### admin/src/main/resources/application.yaml
```yaml
# 短链接中台服务地址配置
short-link:
  project:
    url: http://127.0.0.1:8001
```

如果project服务部署在其他地址，修改此配置即可。

## ⚠️ 注意事项

1. **确保两个服务都已启动**：先启动 project，再启动 admin
2. **端口不要冲突**：admin(8902), project(8001)
3. **数据库和Redis已启动**：确保MySQL和Redis服务正常运行
4. **网络连通性**：确保admin能访问project服务
5. **日志查看**：如果调用失败，查看控制台日志排查问题

## 🐛 常见问题

### 1. 连接被拒绝 (Connection refused)
- 检查project服务是否启动
- 检查端口8001是否被占用
- 检查防火墙设置

### 2. 超时 (Timeout)
- 检查网络连接
- 检查project服务响应是否正常
- 可以先直接访问project接口测试

### 3. 返回404
- 检查URL路径是否正确
- 检查Controller的@RequestMapping配置

### 4. JSON解析错误
- 检查请求体格式是否正确
- 检查Content-Type是否为application/json 