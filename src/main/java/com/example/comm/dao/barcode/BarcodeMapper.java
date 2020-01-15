package com.example.comm.dao.barcode;

import com.example.comm.pojo.barcode.Barcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BarcodeMapper {

    /**
     * 通过订单号和条形码查询所对应的物品信息
     * @param b_barcode
     * @return
     * @throws Exception
     */
    public List<Barcode> getBarcodeByBarcode(@Param("o_id")String o_id,@Param("b_barcode") String b_barcode) throws Exception;

    /**
     * 通过订单号和条形码来查询到物品信息
     * @param b_barcode
     * @param o_id
     * @return
     * @throws Exception
     */
    public Barcode getBarcodeByOidByBarcode(@Param("b_barcode")String b_barcode,@Param("o_id")String o_id) throws Exception;

    /**
     * 通过条形码和订单号修改物品的重量
     * @param b_barcode
     * @param o_id
     * @return
     * @throws Exception
     */
    public int updateBarcodeByWeight(@Param("b_weight")Double b_weight,@Param("b_barcode")String b_barcode,@Param("o_id")String o_id) throws Exception;



}
