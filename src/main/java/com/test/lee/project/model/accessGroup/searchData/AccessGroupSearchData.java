package com.test.lee.project.model.accessGroup.searchData;

import com.test.lee.project.common.data.SearchDataForMongo;
import lombok.Data;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Data
public class AccessGroupSearchData extends SearchDataForMongo {
    private String name;

    @Override
    public Query makeQuery() {
        Criteria criteria = new Criteria();

        makeQuery(criteria, name, "name", "REGEX", "");

        return makeQuery(criteria);
    }
}
