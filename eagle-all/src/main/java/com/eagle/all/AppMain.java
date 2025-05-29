package com.eagle.all;

import com.eagle.eye.util.EyeUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.eagle"})
@MapperScan(basePackages = {"com.eagle.base.mapper"})
@Slf4j
@EnableScheduling
public class AppMain {
    public static void main(String[] args) {

        SpringApplication.run(AppMain.class, args);
//        new Thread(() -> {
//            while (true) {
//                EyeUtil util = EyeUtil.getInstance();
//                System.out.println(util.getSystemInfo());
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
//        }).start();
    }
}
