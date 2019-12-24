package com.example.comm.pojo.barcode;

import lombok.Data;

@Data
public class Barcode {

    private Integer id;
    private Integer b_number;
    private String b_Item_info;
    private String b_barcode;
    private String o_id;



    @Override
    public String toString() {
        return "Barcode{" +
                "id=" + id +
                ", b_number=" + b_number +
                ", b_Item_info='" + b_Item_info + '\'' +
                ", b_barcode='" + b_barcode + '\'' +
                ", o_id='" + o_id + '\'' +
                '}';
    }
}
