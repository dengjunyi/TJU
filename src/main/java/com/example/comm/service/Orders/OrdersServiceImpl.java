package com.example.comm.service.Orders;

import com.example.comm.dao.Orders.OrdersMapper;
import com.example.comm.pojo.orders.Orders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getOrdersByCid(Integer c_id) throws Exception {
        return ordersMapper.getOrdersByCid(c_id);
    }

    @Override
    public List<Orders> getOrders() throws Exception {
        return ordersMapper.getOrders();
    }

    @Override
    public int updateOrdersByOidPort(String o_port, String o_orderid) throws Exception {
        return ordersMapper.updateOrdersByOidPort(o_port,o_orderid);
    }

    @Override
    public Orders getOrdersByOid(String o_orderid) throws Exception {
        return ordersMapper.getOrdersByOid(o_orderid);
    }

    @Override
    public int updateOrdersByOid(String o_orderid) throws Exception {
        return ordersMapper.updateOrdersByOid(o_orderid);
    }

    @Override
    public int getOrdersByCids(Integer c_id) throws Exception {
        return ordersMapper.getOrdersByCids(c_id);
    }

    @Override
    public List<Orders> getOrdersByCustomer() throws Exception {
        return ordersMapper.getOrdersByCustomer();
    }
}
