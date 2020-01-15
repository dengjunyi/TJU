package com.example.comm.service.sorting;


import com.example.comm.pojo.sorting.Sorting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by self on 2019/11/28 14:21
 * Sorting实现层
 */
public interface SortingService {

    /**
     * 通过订单号和条形码查询出详细信息
     */
    public Sorting getSortingByOidByBarcode(String s_orderid,String s_barcode) throws Exception;

    /**
     * 修改详细表的信息
     */
    public int updateSorting(Sorting sorting) throws Exception;

    /**
     * 添加一条数据到详细表
     */
    public int addSorting(Sorting sorting) throws Exception;

    /**
     * 获取条形码最后扫描到的一条数据
     */
    public Sorting getSortingByDate(String s_barcode) throws Exception;

}
