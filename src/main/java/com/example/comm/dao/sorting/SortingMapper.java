package com.example.comm.dao.sorting;

;
import com.example.comm.pojo.sorting.Sorting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by self on 2019/11/28 14:18
 * dao层 sorting
 */
public interface SortingMapper {

    /**
     * 查询记录信息
     *
     * @return
     */
    public List<Sorting> getListSorting() throws Exception;


    /**
     * 如果有这个条形码,则返回一个对象
     * @param order_Barcode
     * @return
     * @throws Exception
     */
    public Sorting getSorting(@Param("order_Barcode") String order_Barcode) throws Exception;


}
