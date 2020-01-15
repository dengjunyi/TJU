package com.example.comm.controller;


import com.example.comm.pojo.allocation.Allocation;
import com.example.comm.pojo.cursors.Cursors;


import com.example.comm.pojo.ng.Ng;
import com.example.comm.pojo.orders.Orders;
import com.example.comm.service.Orders.OrdersService;
import com.example.comm.service.allocation.AllocationService;
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


@RestController
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
    @Resource
    private AllocationService allocationService;//获取资源



    public static BarcodeService barcodeService2;//获取资源
    public static CursoursService cursoursService2;//获取资源
    public static CustomerService customerService2;//获取资源
    public static NgService ngService2;//获取资源
    public static OrdersService ordersService2;
    public static PorttableService porttableService2;
    public static SortingService sortingService2;//获取资源
    public static AllocationService allocationService2;



    @RequestMapping("/index")
    public String helloSpringBoot() {
        System.out.println("进入Index界面");
        return "index";
    }

    @RequestMapping(value = "/getCursors", method = RequestMethod.POST)
    @ResponseBody
    public List<Cursors> getCursors() throws Exception {
        //System.out.println("cookie:"+cookie);@CookieValue("JSESSIONID") String cookie
        System.out.println("进行AJAX");
        List<Cursors> cursors = cursoursService.getCursors();
        return cursors;
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
    public List<Ng> ng() throws Exception {
        System.out.println("进行ng");
        List<Ng> ngList = ngService.getNgList();
        return ngList;
    }

    //在面界中显示出订单信息
    @RequestMapping(value = "/getOrdersByCustomer", method = RequestMethod.POST)
    @ResponseBody
    public List<Orders> getOrdersByCustomer() throws Exception {
        System.out.println("进行AJAX:订单信息");
        List<Orders> ordersList=ordersService.getOrdersByCustomer();
        for (Orders orders : ordersList) {
            System.out.println("客户名称 :"+orders.getCustomer().getCustomer_name());
            System.out.println("订单号 :"+orders.getO_orderid());
            System.out.println("端口号 :"+orders.getO_port());
        }
        return ordersList;
    }

    //在面界中显示出订单信息
    @RequestMapping(value = "/getA_state", method = RequestMethod.POST)
    @ResponseBody
    public Integer getA_state() throws Exception {
        System.out.println("进行AJAX:分配状态");
        Allocation allocation = allocationService.getAllocation();
        Integer a_state = allocation.getA_state();
        System.out.println("状态:"+a_state);
        return a_state;
    }

    //在面界中显示出订单信息
    @RequestMapping(value = "/getOrdersByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public Integer getOrdersByOrderId(@RequestParam("orderids") String orderids,@RequestParam("complete") String complete) throws Exception {
        System.out.println("进行AJAX:获取此客户所有订单的数量");
        System.out.println("订单ID:"+orderids);
        System.out.println("未完成状态:"+complete);
        int ordersByOrderId = ordersService.getOrdersByOrderId(orderids,complete);
        return ordersByOrderId;
    }

    //在面界中显示出订单信息
    @RequestMapping(value = "/updateCursorsBydisplayStatus", method = RequestMethod.POST)
    @ResponseBody
    public Integer updateCursorsBydisplayStatus(@RequestParam("c_out")String c_out) throws Exception {
        System.out.println("修改显示装态");
        int i = cursoursService.updateCursorsBydisplayStatus(c_out);
        System.out.println("修改状态:"+i);
        return i;
    }


}
