package com.example.comm.service.allocation;

import com.example.comm.dao.allocation.AllocationMapper;
import com.example.comm.pojo.allocation.Allocation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AllocationServiceImpl implements AllocationService{
    @Resource
    private AllocationMapper allocationMapper;

    @Override
    public Allocation getAllocation() throws Exception {
        return allocationMapper.getAllocation();
    }

    @Override
    public int updateAllocationByState(Integer a_state) {
        return allocationMapper.updateAllocationByState(a_state);
    }
}
