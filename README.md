## Nanxi's Space

## References
[Spring Guide](https://spring.io/guides/gs/serving-web-content/)  
[Spring Boot Doc 2.2.2](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/)   
[Spring Boot Embedded database supprot](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-embedded-database-support)

## Resources  
[Bootstrap](https://v3.bootcss.com/components/#navbar-default)  
[GitHub OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[Logo](https://www.designevo.com/cn/)  
[okHttp](https://square.github.io/okhttp/)    
[MyBatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)   
[Maven Repository - H2](https://mvnrepository.com/artifact/com.h2database/h2)  
[Maven Repository - FastJSON](https://mvnrepository.com/artifact/com.alibaba/fastjson)  

## Project Diary (Blog) 
[Nanxi's Place](https://nanxinanxi.github.io/)

## Script  
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```