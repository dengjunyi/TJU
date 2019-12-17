package com.example.comm.service.ng;

import com.example.comm.pojo.ng.Ng;

public interface NgService {
    /**
     * 查询最新一条信息
     * @return
     * @throws Exception
     */
    public Ng getNg() throws Exception;
}
