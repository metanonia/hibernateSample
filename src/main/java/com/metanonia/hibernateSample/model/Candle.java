package com.metanonia.hibernateSample.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "candle")
public class Candle {
    @EmbeddedId
    private CandleId candleId;
    @Column(name = "o")
    private BigDecimal open;
    @Column(name = "h")
    private BigDecimal high;
    @Column(name = "l")
    private BigDecimal low;
    @Column(name = "c")
    private BigDecimal close;
    @Column(name = "v")
    private BigDecimal volume;

    public Candle() {
    }

    public CandleId getCandleId() {
        return candleId;
    }

    public void setCandleId(CandleId candleId) {
        this.candleId = candleId;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
