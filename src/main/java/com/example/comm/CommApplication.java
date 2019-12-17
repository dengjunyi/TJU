package com.example.comm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan({"com.example.comm.dao"})
@SpringBootApplication
//继承SpringBootServletInitializer类
public class CommApplication extends SpringBootServletInitializer {

     public static void main(String[] args) throws Exception {
         SpringApplication.run(CommApplication.class, args);
     }

    /**
     * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
     */
    public class SpringBootStartApplication extends SpringBootServletInitializer {

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            // 注意这里要指向原先用main方法执行的Application启动类
            return builder.sources(CommApplication.class);
        }
    }


}


