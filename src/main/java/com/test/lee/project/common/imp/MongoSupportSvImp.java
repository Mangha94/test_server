package com.test.lee.project.common.imp;

import com.test.lee.project.common.MongoSupportSv;
import com.test.lee.project.common.data.Paging;
import com.test.lee.project.common.data.PagingList;
import com.test.lee.project.common.data.SearchDataForMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RED on 2016-07-06.
 */
@Service
@RequiredArgsConstructor
public class MongoSupportSvImp implements MongoSupportSv
{
    final private MongoTemplate mongoTemplate;

    @Override
    public <T> void makePaging (Class<T> entityClass, Paging paging, PagingList<T> pagingList)
    {
        List<T> pagingCriteria = getPagingCriteria (entityClass, paging);

        // 페이징 리스트를 설정한다.
        pagingList.setPaging (paging);
        pagingList.setListData (pagingCriteria);
    }

    @Override
    public <T> PagingList<T> makePaging (Class<T> entityClass, Paging paging)
    {
        List<T> pagingCriteria = getPagingCriteria (entityClass, paging);

        return new PagingList<> (pagingCriteria, paging);
    }

    @Override
    public <T> Integer getCnt(Class<T> entityClass, Query q) {

        return (int)mongoTemplate.count(q,entityClass);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Query q) {
        mongoTemplate.remove(q,entityClass);
    }

    @Override
    public <T> T findOne(Class<T> entityClass, Query q) {
        return mongoTemplate.findOne(q, entityClass);
    }

    private <T> List<T> getPagingCriteria (Class<T> entityClass, Paging paging)
    {
        Query q = paging.getQuery ();

        int totalCnt = (int) mongoTemplate.count (q, entityClass);

        paging.setTotalArticles (totalCnt);

        q.skip (paging.getLimitStart ());
        q.limit (paging.getArticlesPerPage ());

        q.with (new Sort(paging.getOrerListMongo ()));

        return mongoTemplate.find (q, entityClass);
    }

    @Override
    public <T> List<T> getAllList (Class<T> entityClass, Query q)
    {
        return mongoTemplate.find (q, entityClass);
    }

    @Override
    public <T> List<T> getAllList (Class<T> entityClass, Query q, List<Sort.Order> orders)
    {
        q.with (new Sort(orders));

        return mongoTemplate.find (q, entityClass);
    }

    @Override
    public <T> List<T> getListLimit(Class<T> entityClass, Query qIn, List<Sort.Order> orders, int endLimit)
    {
        Query q = qIn;

        q.with (new Sort(orders));

        if(endLimit > 0)
            q.limit(endLimit);

        return mongoTemplate.find(q,entityClass);
    }

    @Override
    public <T> List<T> getListLimit(Class<T> entityClass, Query qIn, int endLimit)
    {
        Query q = qIn;

        if(endLimit > 0)
            q.limit(endLimit);

        return mongoTemplate.find(q,entityClass);
    }

    @Override
    public <T> List<T> getListLimit(Class<T> entityClass, SearchDataForMongo s)
    {
        Query q = s.makeQuery();

        if(s.getLimitStart()>=1)
        {
            q.skip((s.getLimitStart()-1)*s.getLimitEnd());
        }

        if(s.getLimitEnd() > 0)
            q.limit(s.getLimitEnd());

        List<Sort.Order> orders = s.makeOrder ();

        if (orders != null && orders.size () > 0)
            q.with (new Sort(orders));

        return mongoTemplate.find(q,entityClass);
    }

    @Override
    public <T> void update (Class<T> entityClass, Query q, Update u)
    {
        mongoTemplate.updateMulti (q, u, entityClass);
    }
    @Override
    public <T> void upSert(Class<T> entityClass, Query q, Update u)
    {
        mongoTemplate.findAndModify(q
                , u
                , FindAndModifyOptions.options().upsert(true)
                , entityClass);
    }


    @Override
    public <T> Page<T> makePaging (Class<T> entityClass, Pageable pageable, SearchDataForMongo searchData)
    {
        Query q = searchData.makeQuery ();

        long totalCnt = mongoTemplate.count (q, entityClass);

        q = q.with (pageable);
        List<Sort.Order> orders = searchData.makeOrder ();

        if (orders != null && orders.size () > 0)
            q.with (new Sort(orders));

        return new PageImpl(mongoTemplate.find (q, entityClass), pageable, totalCnt);
    }

    @Override
    public <T> List<T> findDistinct(Class<T> entityClass, Query q, String field) {
        return mongoTemplate.findDistinct(q,field,entityClass,entityClass);
    }

}
