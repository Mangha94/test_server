package com.test.lee.project.common.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.lee.project.lib.StrLib;
import com.test.lee.project.model.businessValue.type.ValueType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by RED on 2016-07-06.
 */
@Getter
@Setter
@ToString
public class SearchDataForMongo
{

	/**
	 * 시작일
	 */
	private String startDate;

	/**
	 * 종료일
	 */
	private String endDate;

	/**
	 * 정렬값
	 */
	private String orderVal;

	/**
	 * 정렬항목
	 */
	private boolean orderAsc;

	/**
	 * 시작 순번
	 */
	private int   limitStart;

	/**
	 * 종료 순번
	 */
	private int   limitEnd;

	private int   page;

	private int   size;

	private Query query;

	/**
	 * 정렬 조건
	 */
	@JsonIgnore
	private List<Order> orders;

	protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public SearchDataForMongo()
	{
		orders = new ArrayList<> ();
	}

	public Query makeQuery ()
	{
		return makeQuery (null);
	}

	protected Query makeQuery (Criteria criteria)
	{
		if(this.query != null)
		{
			return this.query;
		}
		else
			return criteria != null ? query (criteria) : new Query();
	}

	public void setQuery (Criteria criteria)
	{
		this.query = query(criteria);
	}

	public Criteria makeCriteria(){
		return new Criteria();
	};

	public Criteria makeCriteria(Criteria initCriteria, String field)
	{
		Criteria criteria;

		if (initCriteria == null)
			criteria = where (field);
		else
			criteria = initCriteria.and (field);

		return criteria;
	}

	public String getStartDate ()
	{
		return startDate;
	}

	public SearchDataForMongo setStartDate (String startDate)
	{
		this.startDate = startDate;
		return this;
	}

	public String getEndDate ()
	{
		return endDate;
	}

	public SearchDataForMongo setEndDate (String endDate)
	{
		this.endDate = endDate;
		return this;
	}

	public String getOrderVal ()
	{
		return orderVal;
	}

	public SearchDataForMongo setOrderVal (String orderVal)
	{
		this.orderVal = orderVal;
		return this;
	}

	public boolean isOrderAsc ()
	{
		return orderAsc;
	}

	public SearchDataForMongo setOrderAsc (boolean orderAsc)
	{
		this.orderAsc = orderAsc;
		return this;
	}

	public List<Order> getOrders ()
	{
		return orders;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public int getLimitEnd() {
		return limitEnd;
	}

	public List<Order> makeOrder ()
	{
		return makeOrder (null);
	}

	public List<Order> makeOrder (Order defaultOrder)
	{
		// TODO (leenzze 17.10.24) : 게시판 멀티 정렬을 위해 가림.
//		if (orders.size () > 0)
//			return orders;

		if (!StrLib.isEmptyStr (getOrderVal ()))
		{
			if (getOrderVal ().contains (","))
			{
				String [] orderStrs = getOrderVal ().split (",");

				Arrays.stream (orderStrs).forEach (this::ordersAdd);
			}
			else
			{
				ordersAdd(getOrderVal ());
			}
		}
		else
		{
			if (defaultOrder != null)
				orders.add (defaultOrder);
		}

		return orders;
	}

	private void ordersAdd(String o) {
		if (o.endsWith ("_DESC"))
		{
			o = o.replace("_DESC","");
			orders.add (new Order(Sort.Direction.DESC, o));
		}
		else
		{
			o = o.replace("_ASC","");
			orders.add (new Order(Sort.Direction.ASC, o));
		}
	}

	public void makeOrderList (List<Order> orderList)
	{
		orderList.forEach(c->{
			makeOrder(c);
		});
	}

	/**
	 * 등록일 검색 로직
	 * @param criteria 검색 조건
	 * @return 설정된 검색 조건
	 */
	protected Criteria regDateWhere(Criteria criteria)
	{
		return regDateWhere (criteria, "regModDate.regDate");
	}

	/**
	 * 해당일 검색 로직
	 * @param criteria 검색 조건
	 * @param field 필드
	 * @return 설정된 검색 조건
	 */
	public Criteria regDateWhereString(Criteria criteria,String field)
	{
		if (!StrLib.isEmptyStr(getStartDate()) && !StrLib.isEmptyStr(getEndDate()))
		{
			makeCriteria(criteria, field).gte(getStartDate() + " 00:00:00").lte(getEndDate() + "23:59:59");

		}
		else if (!StrLib.isEmptyStr(getStartDate()))
		{
			criteria = makeCriteria(criteria, field).gte(getStartDate() + " 00:00:00");
		}
		else if (!StrLib.isEmptyStr(getEndDate()))
		{
			criteria = makeCriteria(criteria, field).lte(getEndDate() + "23:59:59");
		}

		return criteria;
	}

	/**
	 * 해당일 검색 로직
	 * @param criteria 검색 조건
	 * @param field 필드
	 * @return 설정된 검색 조건
	 */
	public Criteria regDateWhereStringNotHyphen (Criteria criteria,String field)
	{
		if (!StrLib.isEmptyStr(getStartDate()) && !StrLib.isEmptyStr(getEndDate()))
		{
			makeCriteria(criteria, field).gte(getStartDate() + " 000000").lte(getEndDate() + "235959");

		}
		else if (!StrLib.isEmptyStr(getStartDate()))
		{
			criteria = makeCriteria(criteria, field).gte(getStartDate() + " 000000");
		}
		else if (!StrLib.isEmptyStr(getEndDate()))
		{
			criteria = makeCriteria(criteria, field).lte(getEndDate() + "235959");
		}

		return criteria;
	}


	/**
	 * 해당일 검색 로직
	 * @param criteria 검색 조건
	 * @param fieldName 검색 필드
	 * @return 설정된 검색 조건
	 */
	public Criteria regDateWhere(Criteria criteria, String fieldName)
	{

		try
		{

			if (!StrLib.isEmptyStr(getStartDate()) && !StrLib.isEmptyStr(getEndDate()))
			{
				if(getStartDate().split("-").length == 2)
					criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate() + "-01" + " 00:00:00")).lte(df.parse(getEndDate() + "-31" + " 23:59:59"));
				else
					criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate() + " 00:00:00")).lte(df.parse(getEndDate() + " 23:59:59"));

			}
			else if (!StrLib.isEmptyStr(getStartDate()))
			{
				if(getStartDate().split("-").length == 2)
					criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate() + "-01" + " 00:00:00"));
				else
					criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate() + " 00:00:00"));
			}
			else if (!StrLib.isEmptyStr(getEndDate()))
			{
				if(getEndDate().split("-").length == 2)
					criteria = makeCriteria(criteria, fieldName).lte(df.parse(getEndDate() + "-31" + " 23:59:59"));
				else
					criteria = makeCriteria(criteria, fieldName).lte(df.parse(getEndDate() + " 23:59:59"));
			}

		}catch (ParseException e) {
			e.printStackTrace();
		}

		return criteria;
	}

	/**
	 * 해당일 검색 로직
	 * @param criteria 검색 조건
	 * @param fieldName 검색 필드
	 * @return 설정된 검색 조건
	 */
	public Criteria regDateWherePass(Criteria criteria, String fieldName)
	{

		try
		{
			if (!StrLib.isEmptyStr(getStartDate()) && !StrLib.isEmptyStr(getEndDate()))
			{
				criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate())).lte(df.parse(getEndDate()));
			}
			else if (!StrLib.isEmptyStr(getStartDate()))
			{
				criteria = makeCriteria(criteria, fieldName).gte(df.parse(getStartDate()));
			}
			else if (!StrLib.isEmptyStr(getEndDate()))
			{
				criteria = makeCriteria(criteria, fieldName).lte(df.parse(getEndDate()));
			}

		}catch (ParseException e) {
			e.printStackTrace();
		}

		return criteria;
	}

	public Criteria regDateWhereFromDate(Criteria criteria, String fieldName, Date start, Date end) {
		criteria = makeCriteria(criteria, fieldName).gte(start).lte(end);
		return criteria;
	}

	public SearchDataForMongo setLimitStart(int limitStart) {
		this.limitStart = limitStart;
		return this;
	}

	public SearchDataForMongo setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
		return this;
	}

	protected void makeQuery(Criteria criteria, String value, String field, String where, String fieldType){

		if (!StrLib.isEmptyStr (value)) {

			if(where.equals("IS"))
			{
				Object enumObj = null;
				if(fieldType.equals("ENUM"))
					enumObj = stringToEnum(field, value);
				makeCriteria (criteria, field).is((enumObj != null ? enumObj : value));

			}
			else if (where.equals("REGEX"))
			{
				makeCriteria (criteria, field).regex (value);
			}
			else if (where.equals("NE"))
			{
				makeCriteria (criteria, field).ne (value);
			}
			else if (where.equals("GTE"))
			{
				makeCriteria (criteria, field).gte (value);
			}

		}

	}

	protected Criteria is (Criteria criteria, String fieldName, String val)
	{
		if (!StrLib.isEmptyStr (val))
			criteria = makeCriteria (criteria, fieldName).is (val);

		return criteria;
	}

	protected Criteria gte (Criteria criteria, String fieldName, String val)
	{
		if (!StrLib.isEmptyStr (val)){
			Integer intVal = Integer.parseInt(val);
			criteria = makeCriteria (criteria, fieldName).gte (intVal);
		}

		return criteria;
	}

	protected Criteria ne (Criteria criteria, String fieldName, String val)
	{
		if (!StrLib.isEmptyStr (val))
			criteria = makeCriteria (criteria, fieldName).ne (val);

		return criteria;
	}

	protected <T> Criteria is2 (Criteria criteria, String fieldName, T val)
	{
		if (val != null)
			criteria = makeCriteria (criteria, fieldName).is (val);

		return criteria;
	}

	protected Criteria regex (Criteria criteria, String fieldName, String val)
	{
		if (!StrLib.isEmptyStr (val))
			criteria = makeCriteria (criteria, fieldName).regex (val);

		return criteria;
	}

	protected Criteria isLike (Criteria criteria, String fieldName, String val, String likeVal)
	{
		if (!StrLib.isEmptyStr (val))
			criteria = makeCriteria (criteria, fieldName).is (val);
		else if (!StrLib.isEmptyStr (likeVal))
			criteria = makeCriteria (criteria, fieldName).regex (likeVal);

		return criteria;
	}

	private Object stringToEnum(String field, String value){
		switch (field){
			case "valueType":
				return ValueType.valueOf(value);
		}

		return null;
	}
}
