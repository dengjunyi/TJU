package com.example.comm.util;

import com.example.comm.controller.SortingController;
import com.example.comm.pojo.ng.Ng;
import com.example.comm.pojo.sorting.Sorting;
import com.example.comm.server.WebSocketServer;
import com.example.comm.server.WebSocketServer2;
import com.example.comm.service.Orders.OrdersService;
import com.example.comm.service.barcode.BarcodeService;
import com.example.comm.service.cursors.CursoursService;
import com.example.comm.service.customer.CustomerService;
import com.example.comm.service.ng.NgService;
import com.example.comm.service.porttable.PorttableService;
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
    private BarcodeService barcodeService;
    @Resource
    private CursoursService cursoursService;
    @Resource
    private CustomerService customerService;
    @Resource
    private NgService ngService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private PorttableService porttableService;
    @Resource
    private SortingService sortingService;//获取资源





    @Override
    public void run(ApplicationArguments args) throws Exception {
        SortingController.barcodeService2 = barcodeService;
        SortingController.cursoursService2 = cursoursService;
        SortingController.customerService2 = customerService;
        SortingController.ngService2 = ngService;
        SortingController.ordersService2 = ordersService;
        SortingController.porttableService2 = porttableService;
        SortingController.sortingService2 = sortingService;


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
