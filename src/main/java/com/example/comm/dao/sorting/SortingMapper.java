package com.example.comm.dao.sorting;

import com.example.comm.pojo.sorting.Sorting;
import org.apache.ibatis.annotations.Param;

public interface SortingMapper {

    /**
     * 通过订单号和条形码查询出详细信息
     * @param s_orderid
     * @param s_barcode
     * @return
     * @throws Exception
     */
    public Sorting getSortingByOidByBarcode(@Param("s_orderid") String s_orderid,@Param("s_barcode") String s_barcode) throws Exception;

    /**
     * 修改详细表的信息
     * @param sorting
     * @return
     * @throws Exception
     */
    public int updateSorting(Sorting sorting) throws Exception;


}
