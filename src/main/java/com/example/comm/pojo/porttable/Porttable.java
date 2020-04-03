package com.example.comm.pojo.porttable;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class Porttable {
    private Integer p_id;
    private String p_port;
    private Integer p_state;

}
