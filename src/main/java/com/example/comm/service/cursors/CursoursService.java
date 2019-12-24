package com.example.comm.service.cursors;

import com.example.comm.pojo.cursors.Cursors;
import com.example.comm.pojo.customer.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CursoursService {

    /**
     * 查询
     */
    public Cursors getCursorsBybarcode(String c_order) throws Exception;

    /**
     * 修改临时表
     * @param cursors
     * @return
     * @throws Exception
     */
    public int updateCursors(Cursors cursors) throws Exception;

    /**
     * 添加一条临时表的信息
     * @param cursors
     * @return
     * @throws Exception
     */
    public int addCursors(Cursors cursors) throws Exception;



}
