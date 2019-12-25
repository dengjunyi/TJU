package com.example.comm.pojo.orders;

import com.example.comm.pojo.customer.Customer;
import lombok.Data;

@Data
public class Orders {
    private Integer id;
    private String o_orderid;
    private String o_category;
    private String o_Specifications;
    private Integer o_number;
    private Integer o_complete;
    private Integer c_id;
    private String o_port;
    private Customer customer;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", o_orderid='" + o_orderid + '\'' +
                ", o_category='" + o_category + '\'' +
                ", o_Specifications='" + o_Specifications + '\'' +
                ", o_number=" + o_number +
                ", o_complete=" + o_complete +
                ", c_id=" + c_id +
                ", o_port=" + o_port +
                '}';
    }
}
