package com.mvc.pojo;

import java.util.Date;
import java.util.Objects;

/**
 * 普通java类，病案主页表的数据封装
 */
public class Bazy {
    /**
     * 病人id
     */
    private Integer brid;
    /**
     * 主页id
     */
    private Integer zyid;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 住院号
     */
    private Integer zyh;
    /**
     * 入院日期
     */
    private Date ryrq;
    /**
     * 出院日期
     */
    private Date cyrq;
    /**
     * 费用和
     */
    private Double fyh;

    /**
     *
     */

    public Bazy() {
    }

    public Bazy(Integer brid, Integer zyid, String xm, Integer zyh, Date ryrq, Date cyrq) {
        this.brid = brid;
        this.zyid = zyid;
        this.xm = xm;
        this.zyh = zyh;
        this.ryrq = ryrq;
        this.cyrq = cyrq;
    }

    public Bazy(Integer brid, Integer zyid, String xm, Integer zyh, java.sql.Date ryrq, java.sql.Date cyrq, Double fyh) {
        this.brid = brid;
        this.zyid = zyid;
        this.xm = xm;
        this.zyh = zyh;
        this.ryrq = ryrq;
        this.cyrq = cyrq;
        this.fyh = fyh;

    }

    @Override
    public String toString() {
        return "Bazy{" +
                "brid=" + brid +
                ", zyid=" + zyid +
                ", xm='" + xm + '\'' +
                ", zyh=" + zyh +
                ", ryrq=" + ryrq +
                ", cyrq=" + cyrq +
                ", fyh=" + fyh +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bazy bazy = (Bazy) o;
        return Objects.equals(brid, bazy.brid) && Objects.equals(zyid, bazy.zyid) && Objects.equals(xm, bazy.xm) && Objects.equals(zyh, bazy.zyh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brid, zyid, xm, zyh);
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }

    public Integer getZyid() {
        return zyid;
    }

    public void setZyid(Integer zyid) {
        this.zyid = zyid;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Date getRyrq() {
        return ryrq;
    }

    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
    }

    public Date getCyrq() {
        return cyrq;
    }

    public void setCyrq(Date cyrq) {
        this.cyrq = cyrq;
    }

    public Double getFyh() {
        return fyh;
    }

    public void setFyh(Double fyh) {
        this.fyh = fyh;
    }
}
