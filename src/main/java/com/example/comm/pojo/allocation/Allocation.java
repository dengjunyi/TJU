package com.example.comm.pojo.allocation;

import lombok.Data;

@Data
public class Allocation {
    private Integer a_id;
    private Integer a_state;//0:未分配 1:客户 2:订单
}
