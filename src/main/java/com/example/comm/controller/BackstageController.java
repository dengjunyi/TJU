package com.example.comm.controller;

import com.example.comm.pojo.allocation.Allocation;
import com.example.comm.pojo.barcode.Barcode;
import com.example.comm.pojo.cursors.Cursors;
import com.example.comm.pojo.customer.Customer;
import com.example.comm.pojo.orders.Orders;
import com.example.comm.pojo.porttable.Porttable;
import com.example.comm.service.Orders.OrdersService;
import com.example.comm.service.barcode.BarcodeService;
import com.example.comm.service.customer.CustomerService;
import com.example.comm.service.ng.NgService;
import com.example.comm.service.porttable.PorttableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/backstage")
public class BackstageController {

    @Resource
    private OrdersService ordersService;//获取资源
    @Resource
    private PorttableService porttableService;//获取资源
    @Resource
    private CustomerService customerService;//获取资源
    @Resource
    private BarcodeService barcodeService;//获取资源

    @RequestMapping("/Management")
    public String helloSpringBoot() {
        System.out.println("进入Management界面");
        return "Management";
    }

    /**
     * 端口
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPorttableList", method = RequestMethod.POST)
    @ResponseBody
    public List<Porttable> getPorttableList() throws Exception {
        System.out.println("进行AJAX");
        List<Porttable> porttableList = porttableService.getPorttableList();
        return porttableList;
    }

    @RequestMapping(value = "/getCustomerByPort", method = RequestMethod.POST)
    @ResponseBody
    public String getCustomerByPort(@RequestParam("o_port") String o_port) throws Exception {
        System.out.println("进行AJAX");
        System.out.println("端口号:" + o_port);
        List<Customer> customerByPort = customerService.getCustomerByPort(o_port);
        String customer_name = "";
        try {

            for (Customer customer : customerByPort) {
                if (customerByPort.size() > 1) {
                    customer_name += customer.getCustomer_name() + "  ";
                } else {
                    customer_name += customer.getCustomer_name();
                }

            }
        } catch (Exception e) {
            System.out.println("异常信息:");
            //e.printStackTrace();
            customer_name = null;
        }
        System.out.println("客户名称:" + customer_name);
        return customer_name;
    }

    @RequestMapping(value = "/addPort", method = RequestMethod.POST)
    @ResponseBody
    public Integer addPort(@RequestParam("p_port") String p_port) throws Exception {
        int i = porttableService.addPort(p_port);
        return i;
    }

    @RequestMapping(value = "/delPort", method = RequestMethod.POST)
    @ResponseBody
    public Integer delPort(@RequestParam("p_id") String p_id) throws Exception {
        int i = porttableService.delPort(Integer.valueOf(p_id));
        return i;
    }

    /**
     * 订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/getOrdersList", method = RequestMethod.POST)
    @ResponseBody
    public List<Orders> getOrdersList(@RequestParam("o_name") String o_name, @RequestParam("o_orderid") String o_orderid) throws Exception {
        System.out.println("1:" + o_name);
        String c_id;
        Customer customer = null;
        if (!o_name.isEmpty()) {
            customer = customerService.getCustomerByName(o_name);
            c_id = String.valueOf(customer.getC_id());
        } else {
            c_id = null;
        }
        List<Orders> ordersList = ordersService.getOrdersList(c_id, o_orderid);
        return ordersList;
    }

    @RequestMapping(value = "/getCustomerByCid", method = RequestMethod.POST)
    @ResponseBody
    public String getCustomerByCid(@RequestParam("c_id") String c_id) throws Exception {
        Customer customerByCid = customerService.getCustomerByCid(Integer.valueOf(c_id));
        return customerByCid.getCustomer_name();
    }

    //物品
    @RequestMapping(value = "/getBarcodeByBarcode", method = RequestMethod.POST)
    @ResponseBody
    public List<Barcode> getBarcodeByBarcode(@RequestParam("b_oid") String b_oid, @RequestParam("b_barcode") String b_barcode) throws Exception {
        System.out.println("物品");
        List<Barcode> barcodeByBarcode = barcodeService.getBarcodeByBarcode(b_oid, b_barcode);
        return barcodeByBarcode;
    }

}
