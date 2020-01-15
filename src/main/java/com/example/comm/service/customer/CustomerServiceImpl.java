package com.example.comm.service.customer;

import com.example.comm.dao.customer.CustomerMapper;
import com.example.comm.pojo.customer.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> getCustomerBystate() throws Exception {
        return customerMapper.getCustomerBystate();
    }

    @Override
    public int updateCustomerByCid(Integer c_id) throws Exception {
        return customerMapper.updateCustomerByCid(c_id);
    }

    @Override
    public Customer getCustomerByCid(Integer c_id) throws Exception {
        return customerMapper.getCustomerByCid(c_id);
    }

    @Override
    public int updateCustomerByCids(Integer c_id) throws Exception {
        return customerMapper.updateCustomerByCids(c_id);
    }

    @Override
    public List<Customer> getCustomerByPort(String o_port) throws Exception {
        return customerMapper.getCustomerByPort(o_port);
    }

    @Override
    public Customer getCustomerByName(String customer_name) throws Exception {
        return customerMapper.getCustomerByName(customer_name);
    }


}
