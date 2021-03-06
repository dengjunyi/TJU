package com.example.comm.service.barcode;

import com.example.comm.dao.barcode.BarcodeMapper;
import com.example.comm.dao.cursors.CursorsMapper;
import com.example.comm.pojo.barcode.Barcode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BarcodeServiceImpl implements BarcodeService {

    @Resource
    private BarcodeMapper barcodeMapper;


    @Override
    public List<Barcode> getBarcodeByBarcode(String o_id, String b_barcode) throws Exception {
        return barcodeMapper.getBarcodeByBarcode(o_id,b_barcode);
    }

    @Override
    public Barcode getBarcodeByOidByBarcode(String b_barcode, String o_id) throws Exception {
        return barcodeMapper.getBarcodeByOidByBarcode(b_barcode,o_id);
    }

    @Override
    public int updateBarcodeByWeight(Double b_weight, String b_barcode, String o_id) throws Exception {
        return barcodeMapper.updateBarcodeByWeight(b_weight,b_barcode,o_id);
    }
}
