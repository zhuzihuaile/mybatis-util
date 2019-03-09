mybatis-util
======

简介
------------
* mybatis-util是为了简化mybatis开发过程中的sql.xml的开发和维护；
* 本项目支持Bean对象注解，已达到增删查改的业务需求；
* 本项目是spring boot结构
* insert:支持自增ID；
* update:以ID为查询条件修改非主键字段数据；
* 查询当前支持 =，!=，IN，右模糊查询（like 'aa%‘），>，>=，<，<=查询条件；
* 查询支持查询结果和Bean自动转换
* 支持排序分页（支持limit max,offset min校验）
* 本项目当前支持单表操作
* 本项目当前只支持MySql数据库

测试/示例
------------
可以查看：[mybatis-util-example](https://github.com/zhuzihuaile/mybatis-util-example)

