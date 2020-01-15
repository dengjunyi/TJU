package com.example.comm.service.ng;

import com.example.comm.pojo.ng.Ng;

import java.util.List;

public interface NgService {
    /**
     * 查询最新一条信息
     */
    public Ng getNg() throws Exception;

    /**
     * 添加一条数据到NG表
     */
    public int addNg(Ng ng) throws Exception;

    /**
     * 查询ng表最后五条数据
     * @return
     * @throws Exception
     */
    public List<Ng> getNgList() throws Exception;
}
