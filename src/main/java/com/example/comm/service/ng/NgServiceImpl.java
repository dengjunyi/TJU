package com.example.comm.service.ng;

import com.example.comm.dao.ng.NgMapper;
import com.example.comm.pojo.ng.Ng;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NgServiceImpl implements NgService{

    @Resource
    private NgMapper ngMapper;

    @Override
    public Ng getNg() throws Exception {
        return ngMapper.getNg();
    }

    @Override
    public int addNg(Ng ng) throws Exception {
        return ngMapper.addNg(ng);
    }
}
