package com.example.comm.pojo.ng;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

@Data
public class Ng {
    private Integer n_id;
    private String n_barcode;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date n_date;
    private Integer n_number;

    @Override
    public String toString() {
        return "Ng{" +
                "n_id=" + n_id +
                ", n_barcode='" + n_barcode + '\'' +
                ", n_date=" + n_date +
                ", n_number=" + n_number +
                '}';
    }
}
