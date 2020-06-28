package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Outware {
    private int id;
    private String oid;
    private Integer uid;
    private String name;
    private Integer num;
    private BigDecimal price;
    private String man;
    private String outTime;
    private String note;
    private String detail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "oid")
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "man")
    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    @Basic
    @Column(name = "outTime")
    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outware outware = (Outware) o;
        return id == outware.id &&
                Objects.equals(oid, outware.oid) &&
                Objects.equals(uid, outware.uid) &&
                Objects.equals(name, outware.name) &&
                Objects.equals(num, outware.num) &&
                Objects.equals(price, outware.price) &&
                Objects.equals(man, outware.man) &&
                Objects.equals(outTime, outware.outTime) &&
                Objects.equals(note, outware.note) &&
                Objects.equals(detail, outware.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oid, uid, name, num, price, man, outTime, note, detail);
    }
}
