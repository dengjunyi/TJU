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
    public Sorting getSortingByOidByBarcode(String s_orderid, String s_barcode) throws Exception {
        return sortingMapper.getSortingByOidByBarcode(s_orderid,s_barcode);
    }

    @Override
    public int updateSorting(Sorting sorting) throws Exception {
        return sortingMapper.updateSorting(sorting);
    }
}
