package com.test.lee.project.common;

import com.test.lee.project.common.data.Paging;
import com.test.lee.project.common.data.PagingList;
import com.test.lee.project.common.data.SearchDataForMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by RED on 2016-07-06.
 */
public interface MongoSupportSv
{

    /**
     * 페이징을 만든다.
     * @param entityClass 엔트리 클래스
     * @param paging 페이징
     * @param pagingList 페이징 리스트
     */
    <T> void makePaging(Class<T> entityClass, Paging paging, PagingList<T> pagingList);

    <T> PagingList<T> makePaging(Class<T> entityClass, Paging paging);

    <T> void upSert(Class<T> entityClass, Query q, Update u);

    <T> Page<T> makePaging (Class<T> entityClass, Pageable pageable, SearchDataForMongo searchData);

    <T> Integer getCnt (Class<T> entityClass, Query q);

    <T> void update (Class<T> entityClass, Query q, Update u);

    <T> void delete(Class<T> entityClass, Query q);

    <T> T findOne(Class<T> entityClass, Query q);

    /**
     * 검색한 리스트를 리턴한다.
     * @param entityClass 엔트리 클래스
     * @param q 조건
     * @return 검색 리스트
     */
    <T> List<T> getAllList (Class<T> entityClass, Query q);

    /**
     * 검색한 리스트를 리턴한다. - 정렬 포함
     * @param entityClass 엔트리 클래스
     * @param q 조건
     * @param orders 정렬 조건
     * @return 검색 리스트
     */
    <T> List<T> getAllList (Class<T> entityClass, Query q, List<Sort.Order> orders);

    /**
     * 더보기 기능이 있는 리스트
     * @param entityClass
     * @param s
     */
    <T>List<T> getListLimit(Class<T> entityClass, SearchDataForMongo s);

    /**
     * 더보기 기능이 있는 리스트
     * @param entityClass
     * @param q
     * @param endLimit
     */
    <T>List<T> getListLimit (Class<T> entityClass, Query q, List<Sort.Order> orders, int endLimit);

    /**
     * 더보기 기능이 있는 리스트
     * @param entityClass
     * @param q
     * @param endLimit
     */
    <T>List<T> getListLimit (Class<T> entityClass, Query q, int endLimit);

    <T> List<T> findDistinct(Class<T> entityClass, Query q, String field);

}
