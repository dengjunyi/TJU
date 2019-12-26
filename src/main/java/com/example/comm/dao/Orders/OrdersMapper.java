package com.example.comm.dao.Orders;

import com.example.comm.pojo.orders.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {

    /**
     * 查询此客户的订单里未分拣完成的订单
     * @param c_id
     * @return
     * @throws Exception
     */
    public List<Orders> getOrdersByCid(@Param("c_id") Integer c_id) throws Exception;

    /**
     * 查询订单表未分拣完成的和未分配端口号
     * @return
     * @throws Exception
     */
    public List<Orders> getOrders() throws Exception;

    /**
     * 通过订单ID修改端口号
     * @param o_port
     * @param o_orderid
     * @return
     * @throws Exception
     */
    public int updateOrdersByOidPort(@Param("o_port") String o_port,@Param("o_orderid") String o_orderid) throws Exception;

    /**
     * 通过订单号来查询订单信息
     * @param o_orderid
     * @return
     * @throws Exception
     */
    public Orders getOrdersByOid(@Param("o_orderid") String o_orderid) throws Exception;

    /**
     * 修改订单号分配完成
     * @param o_orderid
     * @return
     * @throws Exception
     */
    public int updateOrdersByOid(@Param("o_orderid")String o_orderid) throws Exception;

    /**
     * 查询此客户的所有订单未完成的个数
     * @param c_id
     * @return
     * @throws Exception
     */
    public int getOrdersByCids(@Param("c_id") Integer c_id) throws Exception;

    /**
     * 在页面中显示,双表查询
     * @return
     * @throws Exception
     */
    public List<Orders> getOrdersByCustomer() throws Exception;

    /**
     * 根据客户名字获取客户ID
     * @param o_orderid
     * @param o_complete
     * @return
     * @throws Exception
     */
    public int getOrdersByOrderId(@Param("o_orderid") String o_orderid,@Param("o_complete")String o_complete) throws Exception;

}
