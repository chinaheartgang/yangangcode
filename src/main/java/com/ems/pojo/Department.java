package com.ems.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String id;

    private String depName;

    private String depNote;

    private String operation;

    private Date registTime;


}