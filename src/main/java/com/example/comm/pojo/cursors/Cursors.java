package com.example.comm.pojo.cursors;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Cursors {

  private long id;
  private String c_order;
  private String c_barcode;
  private String c_out;
  private long c_count;
  private String c_specification;
  private String c_describe;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date c_current;
  private String c_customername;
  private long c_number;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getC_order() {
    return c_order;
  }

  public void setC_order(String c_order) {
    this.c_order = c_order;
  }

  public String getC_barcode() {
    return c_barcode;
  }

  public void setC_barcode(String c_barcode) {
    this.c_barcode = c_barcode;
  }

  public String getC_out() {
    return c_out;
  }

  public void setC_out(String c_out) {
    this.c_out = c_out;
  }

  public long getC_count() {
    return c_count;
  }

  public void setC_count(long c_count) {
    this.c_count = c_count;
  }

  public String getC_specification() {
    return c_specification;
  }

  public void setC_specification(String c_specification) {
    this.c_specification = c_specification;
  }

  public String getC_describe() {
    return c_describe;
  }

  public void setC_describe(String c_describe) {
    this.c_describe = c_describe;
  }

  public Date getC_current() {
    return c_current;
  }

  public void setC_current(Date c_current) {
    this.c_current = c_current;
  }

  public String getC_customername() {
    return c_customername;
  }

  public void setC_customername(String c_customername) {
    this.c_customername = c_customername;
  }

  public long getC_number() {
    return c_number;
  }

  public void setC_number(long c_number) {
    this.c_number = c_number;
  }

  @Override
  public String toString() {
    return "Cursors{" +
            "id=" + id +
            ", c_order='" + c_order + '\'' +
            ", c_barcode='" + c_barcode + '\'' +
            ", c_out='" + c_out + '\'' +
            ", c_count=" + c_count +
            ", c_specification='" + c_specification + '\'' +
            ", c_describe='" + c_describe + '\'' +
            ", c_current=" + c_current +
            ", c_customername='" + c_customername + '\'' +
            ", c_number=" + c_number +
            '}';
  }
}
