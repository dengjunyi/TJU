package com.example.comm.dao.porttable;

import com.example.comm.pojo.porttable.Porttable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PorttableMapper {

    /**
     * 通过端口号来查询
     * @param p_port
     * @return
     * @throws Exception
     */
    public Porttable getPorttableByPort(@Param("p_port") String p_port) throws Exception;

    /**
     * 通过端口号修改这个端口的状态
     * @param p_port
     * @return
     * @throws Exception
     */
    public int updatePorttable(@Param("p_port") String p_port) throws Exception;

    /**
     * 查询端口表状态,默认为0
     * @return
     * @throws Exception
     */
    public List<Porttable> getPorttable() throws Exception;

    /**
     * 通过端口号修改这个端口的状态
     * @param p_port
     * @return
     * @throws Exception
     */
    public int updatePorttableByPort(@Param("p_port")String p_port) throws Exception;

}
