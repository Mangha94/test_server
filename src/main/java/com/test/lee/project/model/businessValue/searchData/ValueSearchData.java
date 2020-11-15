package com.test.lee.project.model.businessValue.searchData;

import com.test.lee.project.common.data.SearchDataForMongo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data

public class ValueSearchData extends SearchDataForMongo {
    String valueType;

    @Override
    public Query makeQuery() {
        Criteria criteria = new Criteria();

        makeQuery(criteria, valueType, "valueType", "IS", "ENUM");

        return makeQuery(criteria);
    }
}
