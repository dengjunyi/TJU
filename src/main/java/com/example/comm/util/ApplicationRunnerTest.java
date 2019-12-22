package com.example.comm.util;

import com.example.comm.controller.SortingController;
import com.example.comm.pojo.ng.Ng;
import com.example.comm.pojo.sorting.Sorting;
import com.example.comm.server.WebSocketServer;
import com.example.comm.server.WebSocketServer2;
import com.example.comm.service.ng.NgService;
import com.example.comm.service.sorting.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Component
public class ApplicationRunnerTest implements ApplicationRunner {

    @Resource
    private SortingService sortingService;//获取资源

    @Resource
    private NgService ngService;//获取资源


    @Override
    public void run(ApplicationArguments args) throws Exception {

        SortingController.ngService2 = ngService;
      /*  Ng ng = SortingController.ngService2.getNg();
        System.out.println("NG:"+ng.getN_date());*/

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
