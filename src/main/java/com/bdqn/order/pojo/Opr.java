package com.bdqn.order.pojo;

import java.util.Date;

public class Opr {
    private Integer id;

    private String oprType;

    private String oprName;

    private Date oprTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType == null ? null : oprType.trim();
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName == null ? null : oprName.trim();
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }
}