package com.example.comm.service.Orders;

import com.example.comm.pojo.orders.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersService {

    /**
     * 查询此客户的订单里未分拣完成的订单
     */
    public List<Orders> getOrdersByCid(Integer c_id) throws Exception;

    /**
     * 查询订单表未分拣完成的和未分配端口号
     */
    public List<Orders> getOrders() throws Exception;

    /**
     * 通过订单ID修改端口号
     */
    public int updateOrdersByOidPort(String o_port,String o_orderid) throws Exception;

    /**
     * 通过订单号来查询订单信息
     */
    public Orders getOrdersByOid(String o_orderid) throws Exception;

    /**
     * 修改订单号分配完成
     */
    public int updateOrdersByOid(String o_orderid) throws Exception;

    /**
     * 查询此客户的所有订单未完成的个数
     */
    public int getOrdersByCids(Integer c_id) throws Exception;

    /**
     * 在页面中显示,双表查询
     */
    public List<Orders> getOrdersByCustomer() throws Exception;


}
