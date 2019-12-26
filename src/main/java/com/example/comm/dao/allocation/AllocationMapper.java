package com.example.comm.dao.allocation;

import com.example.comm.pojo.allocation.Allocation;
import org.apache.ibatis.annotations.Param;

public interface AllocationMapper {

    /**
     * 查询
     * @return
     * @throws Exception
     */
    public Allocation getAllocation() throws Exception;

    /**
     * 修改状态
     * @param a_state
     * @return
     */
    public int updateAllocationByState(@Param("a_state") Integer a_state);

}
