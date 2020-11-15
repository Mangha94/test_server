package com.test.lee.project.model.member.searchData;

import com.test.lee.project.common.data.SearchDataForMongo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class MemberSearchData extends SearchDataForMongo {
    String memberID;

    String phone;

    String spot;

    String position;

    @Override
    public Query makeQuery() {
        Criteria criteria = new Criteria();

        makeQuery(criteria, memberID, "id", "REGEX", "");
        makeQuery(criteria, phone, "memberName", "REGEX", "");
        makeQuery(criteria, spot, "spot", "IS", "");
        makeQuery(criteria, position, "position", "IS", "");

        return makeQuery(criteria);
    }
}
