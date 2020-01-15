package com.example.comm.dao.ng;

import com.example.comm.pojo.ng.Ng;

import java.util.List;

public interface NgMapper {

    /**
     * 查询ng表的最后一条数据
     * @return
     * @throws Exception
     */
    public Ng getNg() throws Exception;

    /**
     * 添加一条数据到NG表
     * @param ng
     * @return
     * @throws Exception
     */
    public int addNg(Ng ng) throws Exception;

    /**
     * 查询ng表最后五条数据
     * @return
     * @throws Exception
     */
    public List<Ng> getNgList() throws Exception;
}
