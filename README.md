## 资料
[Spring 官网示例] https://spring.io/guides/gs/rest-service/
[Github OAuth] https://docs.github.com/en/free-pro-team@latest/developers/apps/building-oauth-apps
##配置密钥
https://blog.csdn.net/lqlqlq007/article/details/78983879
## 追加提交
git commit --amend --no-edit  ##如果删除了gitigrone 需要先pull 再push

##配置密钥
https://blog.csdn.net/lqlqlq007/article/details/78983879


## mysql 版本要求：
至少mysql5.7 
mysql8.0 以上需要更改 mysql-connector-java 依赖版本
和spring.datasource.driver-class-name 的值
## 逆向工程构建命令 自动根据数据库字段生成pojo对象和对应的mapper文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

## postman app没有登录
添加cookie token= 浏览器的token即可