package com.test.lee.project.model.department.searchData;

import com.test.lee.project.common.data.SearchDataForMongo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class DepartmentSearchData extends SearchDataForMongo {
    private String parent;

    private Integer seq;

    @Override
    public Query makeQuery() {
        Criteria criteria = new Criteria();

        makeQuery(criteria, parent, "parent", "IS", "");
        makeQuery(criteria, String.valueOf(seq), "seq", "GTE", "");

        return makeQuery(criteria);
    }
}
