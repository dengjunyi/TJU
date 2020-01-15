package com.example.comm.dao.customer;

import com.example.comm.pojo.customer.Customer;
import com.example.comm.pojo.orders.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface CustomerMapper {

    /**
     * 查询客户表未分拣完和未分配端口的客户
     * @return
     * @throws Exception
     */
    public List<Customer> getCustomerBystate() throws Exception;

    /**
     * 通过客户ID来改客户分配端口的状态
     * @param c_id
     * @return
     * @throws Exception
     */
    public int updateCustomerByCid(@Param("c_id") Integer c_id) throws Exception;

    /**
     * 根据客户ID查询
     * @param c_id
     * @return
     * @throws Exception
     */
    public Customer getCustomerByCid(@Param("c_id") Integer c_id) throws Exception;

    /**
     * 通过客户ID来改客户分拣的状态
     * @param c_id
     * @return
     * @throws Exception
     */
    public int updateCustomerByCids(@Param("c_id") Integer c_id) throws Exception;

    /**
     * 通过端口获取客户名称
     * @param o_port
     * @return
     * @throws Exception
     */
    public List<Customer> getCustomerByPort(@Param("o_port") String o_port) throws Exception;

    /**
     * 后台
     */
    //跟根客户名称查询
    public Customer getCustomerByName(@Param("customer_name") String customer_name) throws Exception;

}
