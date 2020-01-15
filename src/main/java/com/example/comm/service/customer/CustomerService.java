package com.example.comm.service.customer;

import com.example.comm.pojo.customer.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerService {

    /**
     * 查询客户表未分拣完和未分配端口的客户
     */
    public List<Customer> getCustomerBystate() throws Exception;

    /**
     * 通过客户ID来改客户分配端口的状态
     */
    public int updateCustomerByCid(Integer c_id) throws Exception;

    /**
     * 根据客户ID查询
     */
    public Customer getCustomerByCid(Integer c_id) throws Exception;

    /**
     * 通过客户ID来改客户分拣的状态
     */
    public int updateCustomerByCids(Integer c_id) throws Exception;

    /**
     * 通过端口获取客户名称
     */
    public List<Customer> getCustomerByPort(@Param("o_port") String o_port) throws Exception;

    //跟根客户名称查询
    public Customer getCustomerByName(String customer_name) throws Exception;

}
