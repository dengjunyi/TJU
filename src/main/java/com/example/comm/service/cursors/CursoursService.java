package com.example.comm.service.cursors;

import com.example.comm.pojo.cursors.Cursors;
import org.apache.ibatis.annotations.Param;

public interface CursoursService {

    /**
     * 查询
     * @param c_order
     * @return
     * @throws Exception
     */
    public Cursors getCursorsBybarcode(String c_order) throws Exception;
}
