package com.example.comm.pojo.ng;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

@Data
public class Ng {
    private Integer n_id;
    private String n_name;
    private String n_oid;
    private String n_barcode;
    private String n_out;
    private String n_Specifications;
    private String n_Item_info;
    private Double n_weight;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date n_date;


    @Override
    public String toString() {
        return "Ng{" +
                "n_id=" + n_id +
                ", n_name='" + n_name + '\'' +
                ", n_oid='" + n_oid + '\'' +
                ", n_barcode='" + n_barcode + '\'' +
                ", n_out='" + n_out + '\'' +
                ", n_Specifications='" + n_Specifications + '\'' +
                ", n_Item_info='" + n_Item_info + '\'' +
                ", n_weight=" + n_weight +
                ", n_date=" + n_date +
                '}';
    }
}
