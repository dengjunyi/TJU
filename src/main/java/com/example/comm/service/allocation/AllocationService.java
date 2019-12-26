package com.example.comm.service.allocation;

import com.example.comm.pojo.allocation.Allocation;
import org.apache.ibatis.annotations.Param;

public interface AllocationService {

    /**
     * 查询
     */
    public Allocation getAllocation() throws Exception;

    /**
     * 修改状态
     */
    public int updateAllocationByState(Integer a_state);

}
