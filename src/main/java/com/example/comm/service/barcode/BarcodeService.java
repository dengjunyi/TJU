package com.example.comm.service.barcode;

import com.example.comm.pojo.barcode.Barcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BarcodeService {

    /**
     * 通过条形码查询所对应的订单
     */
    public List<Barcode> getBarcodeByBarcode(String b_barcode) throws Exception;

    /**
     * 通过订单号和条形码来查询到物品信息
     */
    public Barcode getBarcodeByOidByBarcode(String b_barcode,String o_id) throws Exception;



}
