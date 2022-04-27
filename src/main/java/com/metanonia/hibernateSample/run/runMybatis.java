package com.metanonia.hibernateSample.run;

import com.metanonia.hibernateSample.mapper.MysqlMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// https://mybatis.org/mybatis-3/getting-started.html
public class runMybatis {
    public static void main(String[] args) {

        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
            MysqlMapper mapper = session.getMapper(MysqlMapper.class);
            mapper.insOrgInfo("SSSS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
