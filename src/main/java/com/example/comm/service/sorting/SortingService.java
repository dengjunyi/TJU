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
     * 查询记录信息
     *
     * @return
     */
    public List<Sorting> getListSorting();

    /**
     * 通过条形码查询,返回一个对象
     * @param barcode
     * @return
     */
    public Sorting getSorting(@Param("barcode") String barcode);



}
