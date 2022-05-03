package com.metanonia.hibernateSample.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Embeddable
public class CandleId implements Serializable {
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "ts")
    private Timestamp timeStamp;

    public CandleId() {
    }

    public CandleId(String symbol, Timestamp timeStamp) {
        this.symbol = symbol;
        this.timeStamp = timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandleId candleId = (CandleId) o;
        return symbol.equals(candleId.symbol) && timeStamp.equals(candleId.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, timeStamp);
    }
}
