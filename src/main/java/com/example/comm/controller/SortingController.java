package com.example.comm.controller;


import com.example.comm.pojo.cursors.Cursors;


import com.example.comm.pojo.ng.Ng;
import com.example.comm.pojo.orders.Orders;
import com.example.comm.service.Orders.OrdersService;
import com.example.comm.service.barcode.BarcodeService;
import com.example.comm.service.cursors.CursoursService;
import com.example.comm.service.customer.CustomerService;
import com.example.comm.service.ng.NgService;
import com.example.comm.service.porttable.PorttableService;
import com.example.comm.service.sorting.SortingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Create by self on 2019/11/28 14:28
 * sorting控制层
 */


@Controller
@RequestMapping(value = "/sorting")
public class SortingController {

    private Logger logger = Logger.getLogger(SortingController.class);

    @Resource
    public SortingService sortingService;//获取资源
    @Resource
    private CursoursService cursoursService;//获取资源
    @Resource
    private OrdersService ordersService;//获取资源
    @Resource
    private NgService ngService;//获取资源



    public static BarcodeService barcodeService2;//获取资源
    public static CursoursService cursoursService2;//获取资源
    public static CustomerService customerService2;//获取资源
    public static NgService ngService2;//获取资源
    public static OrdersService ordersService2;
    public static PorttableService porttableService2;
    public static SortingService sortingService2;//获取资源


    @RequestMapping("/index")
    public String helloSpringBoot() {
        System.out.println("进入Index界面");
        return "index";
    }


    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public Cursors ajax(Model model, @RequestParam("order") String order) throws Exception {
        System.out.println("进行AJAX");
        System.out.println("order" + order);
        Cursors cursors = cursoursService.getCursorsBybarcode(order);//把获取到的记录存到List里
        System.out.println("时间:"+cursors.getC_time());
        String c_item_info = cursors.getC_Item_info();
        System.out.println("描述:"+c_item_info);
        return cursors;
    }

    @RequestMapping(value = "/ng", method = RequestMethod.POST)
    @ResponseBody
    public Ng ng(@RequestParam("ng") String ng) throws Exception {
        System.out.println("进行AJAX");
        System.out.println("ng" + ng);
        Ng ng1 = ngService.getNg();
        System.out.println("时间" + ng1.getN_date());
        return ng1;
    }

    //在面界中显示出订单信息
    @RequestMapping(value = "/getOrdersByCustomer", method = RequestMethod.POST)
    @ResponseBody
    public List<Orders> getOrdersByCustomer(Model model) throws Exception {
        System.out.println("进行AJAX:订单信息");
        List<Orders> ordersList=ordersService.getOrdersByCustomer();
        for (Orders orders : ordersList) {
            System.out.println("客户名称 :"+orders.getCustomer().getCustomer_name());
            System.out.println("订单号 :"+orders.getO_orderid());
            System.out.println("端口号 :"+orders.getO_port());
        }
        return ordersList;
    }


}
