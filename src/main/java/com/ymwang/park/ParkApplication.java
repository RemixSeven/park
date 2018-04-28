package com.ymwang.park;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement//开启事务管理
@MapperScan("com.ymwang.park.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
@SpringBootApplication
public class ParkApplication{
	public static void main(String[] args) {
		SpringApplication.run(ParkApplication.class, args);
	}
}
