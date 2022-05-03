package com.metanonia.hibernateSample.run;

import com.metanonia.hibernateSample.mapper.MysqlMapper;
import com.metanonia.hibernateSample.model.Candle;
import com.metanonia.hibernateSample.model.CandleId;
import com.metanonia.hibernateSample.service.RestService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

// https://mybatis.org/mybatis-3/getting-started.html
public class runMybatis {
    private static Logger log = Logger.getLogger(runMybatis.class.getName());

    public static void main(String[] args) {
        String symbol = "BTCUSDT";
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
            MysqlMapper mapper = session.getMapper(MysqlMapper.class);

            String ret = RestService.Rest(null, null, "GET", "/api/v3/klines?symbol="+symbol+"&interval=1m", null);
            JSONArray jsonArray = new JSONArray(ret);
            for(int i=0; i<jsonArray.length(); i++) {
                JSONArray item = jsonArray.getJSONArray(i);

                long openTime = item.getLong(0);
                BigDecimal openPrice = item.getBigDecimal(1);
                BigDecimal highPrice = item.getBigDecimal(2);
                BigDecimal lowPrice = item.getBigDecimal(3);
                BigDecimal closePrice = item.getBigDecimal(4);
                BigDecimal vol = item.getBigDecimal(5);
                long closeTime = item.getLong(6);
                BigDecimal volAsset = item.getBigDecimal(7);
                long numTrade = item.getLong(8);
                BigDecimal takeBaseAsset = item.getBigDecimal(9);
                BigDecimal takeQuoteAsset = item.getBigDecimal(10);
                BigDecimal ignore = item.getBigDecimal(11);
                Date dd = new Date(openTime);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
                String str = sdf.format(dd);
                Timestamp openTimestamp = Timestamp.valueOf(str);

                CandleId candleId = new CandleId(symbol, openTimestamp);
                Candle candle = new Candle();
                candle.setCandleId(candleId);
                candle.setOpen(openPrice);
                candle.setHigh(highPrice);
                candle.setLow(lowPrice);
                candle.setClose(closePrice);
                candle.setVolume(vol);

                try {
                    HashMap<String,Object> find = mapper.getCandle(candle.getCandleId());
                    if(find == null) {
                            Integer cnt = mapper.insCandle(candle);
                            log.info(cnt.toString()+":" + candle.getCandleId().getTimeStamp().toString());
                    }
                }
                catch (Exception e) {
                    log.info(e.toString());
                }
            }
            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
