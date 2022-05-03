package com.metanonia.hibernateSample.mapper;

import com.metanonia.hibernateSample.model.Candle;
import com.metanonia.hibernateSample.model.CandleId;
import org.apache.ibatis.annotations.Delete;

import java.sql.Timestamp;
import java.util.HashMap;

public interface MysqlMapper {
    Integer insCandle(Candle Candle);

    @Delete("DELETE FROM candle WHERE symbol=#{SYMBOL} AND ts=#{ts, jdbcType=TIMESTAMP}")
    Integer delCandle(String symbol, Timestamp ts);

    HashMap<String,Object> getCandle(CandleId candleId);
}
