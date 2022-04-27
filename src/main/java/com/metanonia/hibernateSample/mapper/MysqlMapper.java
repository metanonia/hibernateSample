package com.metanonia.hibernateSample.mapper;

import org.apache.ibatis.annotations.Delete;

public interface MysqlMapper {
    Integer insOrgInfo(String orgInfo);

    @Delete("DELETE FROM org_info WHERE ID_ORG=#{idOrg}")
    Integer delOrgInfo(String idOrg);
}
