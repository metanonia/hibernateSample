package com.metanonia.hibernateSample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "binance")
public class Binance {
    @Id
    @Column(name = "ymdhm")
    private String ymdhm;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "curHash")
    private String curHash;
    @Column(name = "sumHash")
    private String sumHash;

    public String getYmdhm() {
        return ymdhm;
    }

    public void setYmdhm(String ymdhm) {
        this.ymdhm = ymdhm;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurHash() {
        return curHash;
    }

    public void setCurHash(String curHash) {
        this.curHash = curHash;
    }

    public String getSumHash() {
        return sumHash;
    }

    public void setSumHash(String sumHash) {
        this.sumHash = sumHash;
    }
}
