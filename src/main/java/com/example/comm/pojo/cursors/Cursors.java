package com.example.comm.pojo.cursors;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Cursors {

  private Integer id;
  private String c_order;
  private String c_barcode;
  private String c_out;
  private Integer c_count;
  private String c_specification;
  private String c_Item_info;
  private Double c_weight;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date c_time;
  private String c_customername;
  private Integer c_number;
  private Integer c_displayStatus;

  @Override
  public String toString() {
    return "Cursors{" +
            "id=" + id +
            ", c_order='" + c_order + '\'' +
            ", c_barcode='" + c_barcode + '\'' +
            ", c_out='" + c_out + '\'' +
            ", c_count=" + c_count +
            ", c_specification='" + c_specification + '\'' +
            ", c_Item_info='" + c_Item_info + '\'' +
            ", c_weight=" + c_weight +
            ", c_time=" + c_time +
            ", c_customername='" + c_customername + '\'' +
            ", c_number=" + c_number +
            ", c_displayStatus=" + c_displayStatus +
            '}';
  }
}
