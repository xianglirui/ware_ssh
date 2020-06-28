package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Inware {
    private int id;
    private String oid;
    private String inTime;
    private String man;
    private String price;
    private String note;
    private String detail;
    private int uid;
    private String name;
    private int num;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
    @Column(name = "inTime")
    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
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
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        Inware inware = (Inware) o;
        return id == inware.id &&
                Objects.equals(oid, inware.oid) &&
                Objects.equals(inTime, inware.inTime) &&
                Objects.equals(man, inware.man) &&
                Objects.equals(price, inware.price) &&
                Objects.equals(note, inware.note) &&
                Objects.equals(detail, inware.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oid, inTime, man, price, note, detail);
    }
}
