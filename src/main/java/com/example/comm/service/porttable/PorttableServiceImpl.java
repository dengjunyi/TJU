package com.example.comm.service.porttable;

import com.example.comm.dao.porttable.PorttableMapper;
import com.example.comm.pojo.porttable.Porttable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PorttableServiceImpl implements PorttableService {

    @Resource
    private PorttableMapper porttableMapper;

    @Override
    public Porttable getPorttableByPort(String p_port) throws Exception {
        return porttableMapper.getPorttableByPort(p_port);
    }

    @Override
    public int updatePorttable(String p_port) throws Exception {
        return porttableMapper.updatePorttable(p_port);
    }

    @Override
    public List<Porttable> getPorttable() throws Exception {
        return porttableMapper.getPorttable();
    }

    @Override
    public int updatePorttableByPort(String p_port) throws Exception {
        return porttableMapper.updatePorttableByPort(p_port);
    }
}
