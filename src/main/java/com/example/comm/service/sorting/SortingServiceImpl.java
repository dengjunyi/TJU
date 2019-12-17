package com.example.comm.service.sorting;


import com.example.comm.dao.sorting.SortingMapper;
import com.example.comm.pojo.sorting.Sorting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by self on 2019/11/28 14:25
 * Sorting业务实现层
 */
@Service
public class SortingServiceImpl implements SortingService {

    @Resource
    private SortingMapper sortingMapper;//获取资源

    @Override
    public List<Sorting> getListSorting() {//查询所有记录
        List<Sorting> sortingList = null;
        try {
            sortingList = sortingMapper.getListSorting();

        } catch (Exception e) {//异常信息
            e.printStackTrace();
        }
        return sortingList;
    }

    @Override
    public Sorting getSorting(String order_Barcode) {
        Sorting sorting = null;
        try {
            sorting = sortingMapper.getSorting(order_Barcode);
        } catch (Exception e) {//异常信息
            e.printStackTrace();
        }
        return sorting;
    }


}
