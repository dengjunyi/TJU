package com.example.comm.pojo.ng;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

public class Ng {
    private Integer id;
    private String n_barcode;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date n_date;
    private Integer n_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getN_barcode() {
        return n_barcode;
    }

    public void setN_barcode(String n_barcode) {
        this.n_barcode = n_barcode;
    }

    public Date getN_date() {
        return n_date;
    }

    public void setN_date(Date n_date) {
        this.n_date = n_date;
    }

    public Integer getN_number() {
        return n_number;
    }

    public void setN_number(Integer n_number) {
        this.n_number = n_number;
    }

    @Override
    public String toString() {
        return "Ng{" +
                "id=" + id +
                ", n_barcode='" + n_barcode + '\'' +
                ", n_date=" + n_date +
                ", n_number=" + n_number +
                '}';
    }
}
