package com.example.comm.service.porttable;

import com.example.comm.pojo.porttable.Porttable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PorttableService {

    /**
     * 通过端口号来查询
     */
    public Porttable getPorttableByPort(String p_port) throws Exception;

    /**
     * 通过端口号修改这个端口的状态
     */
    public int updatePorttable(String p_port) throws Exception;

    /**
     * 查询端口表状态,默认为0
     */
    public List<Porttable> getPorttable() throws Exception;

    /**
     * 通过端口号修改这个端口的状态
     */
    public int updatePorttableByPort(String p_port) throws Exception;

}
