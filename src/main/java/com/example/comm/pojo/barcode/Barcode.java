package com.example.comm.pojo.barcode;

import org.springframework.data.relational.core.sql.In;

public class Barcode {

    private Integer id;
    private Integer b_number;
    private String b_Item_info;
    private String b_barcode;
    private String o_id;

    public Barcode() {
    }

    public Barcode(int id, int b_number, String b_item_info, String b_barcode, String o_id) {
        this.id = id;
        this.b_number = b_number;
        this.b_Item_info = b_Item_info;
        this.b_barcode = b_barcode;
        this.o_id = o_id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getB_number() {
        return b_number;
    }

    public void setB_number(Integer b_number) {
        this.b_number = b_number;
    }

    public String getB_Item_info() {
        return b_Item_info;
    }

    public void setB_Item_info(String b_Item_info) {
        this.b_Item_info = b_Item_info;
    }

    public String getB_barcode() {
        return b_barcode;
    }

    public void setB_barcode(String b_barcode) {
        this.b_barcode = b_barcode;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

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
