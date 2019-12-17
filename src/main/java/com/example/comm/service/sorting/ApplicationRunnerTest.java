package com.example.comm.service.sorting;

import com.example.comm.server.WebSocketServer;
import com.example.comm.server.WebSocketServer2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("初始化");
                    new WebSocketServer2().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("初始化");
                    new WebSocketServer().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
