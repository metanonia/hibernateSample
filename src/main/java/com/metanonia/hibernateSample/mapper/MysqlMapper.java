package com.metanonia.hibernateSample.mapper;

import com.metanonia.hibernateSample.model.Candle;
import com.metanonia.hibernateSample.model.CandleId;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.HashMap;

public interface MysqlMapper {
    Integer insCandle(Candle Candle);

    @Select("SELECT * FROM candle WHERE symbol=#{symbol} AND ts=#{timeStamp, jdbcType=TIMESTAMP}")
    HashMap<String,Object> getCandle(CandleId candleId);
}
