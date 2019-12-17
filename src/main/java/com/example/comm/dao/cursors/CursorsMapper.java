package com.example.comm.dao.cursors;

import com.example.comm.pojo.cursors.Cursors;
import org.apache.ibatis.annotations.Param;

public interface CursorsMapper {

    /**
     *通过条形码查询
     * @param c_order
     * @return
     * @throws Exception
     */
    public Cursors getCursorsBybarcode(@Param("c_order") String c_order) throws Exception;
}
