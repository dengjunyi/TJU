package com.example.comm.dao.cursors;

import com.example.comm.pojo.cursors.Cursors;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CursorsMapper {

    /**
     *通过条形码查询
     * @param c_order
     * @return
     * @throws Exception
     */
    public Cursors getCursorsBybarcode(@Param("c_order") String c_order) throws Exception;

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

    /**
     * 查询所有临时表的信息
     * @return
     * @throws Exception
     */
    public List<Cursors> getCursors() throws Exception;

    /**
     * 修改显示状态
     * @param c_out
     * @return
     * @throws Exception
     */
    public int updateCursorsBydisplayStatus(@Param("c_out") String c_out) throws Exception;

}
