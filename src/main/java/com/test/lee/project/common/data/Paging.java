package com.test.lee.project.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paging
{
	/**
	 * 전체 데이터 수
	 */
	private int totalArticles;
	
	private int currentPage;
	
	private int showPages;
	
	private int articlesPerPage;
	
	private int limitStart;
	
	private int startPage;
	
	private int lastPage;
	
	private int prevPage;
	
	private int nextPage;
	
	private int prevBlock;
	
	private int nextBlock;

	@JsonIgnore
	private Map<String, Object> searchData	= null;

	/**
	 * 쿼리
	 */
	@JsonIgnore
	private Query query;

	/**
	 * 정릴 리스트
	 */
	@JsonIgnore
	private List<Sort.Order> orerListMongo	=	new ArrayList<>();

	public Paging()
	{}

	public Paging(int totalArticles, int currentPage, int showPages, int articlesPerPage)
	{
		this.totalArticles = totalArticles;
		this.currentPage = currentPage;
		this.showPages = showPages;
		this.articlesPerPage = articlesPerPage;

		settingPaginData ();
	}

	public void setTotalArticles (int val)
	{
		this.totalArticles = val;

		settingPaginData ();
	}

	public int getTotalArticles ()
	{
		return (this.totalArticles);
	}

	public void setCurrentPage (int val)
	{
		this.currentPage = val;
	}

	public int getCurrentPage ()
	{
		return (this.currentPage);
	}

	public void setShowPages (int val)
	{
		this.showPages = val;
	}

	public int getShowPages ()
	{
		return (this.showPages);
	}

	public void setArticlesPerPage (int val)
	{
		this.articlesPerPage = val;
	}

	public int getArticlesPerPage ()
	{
		return (this.articlesPerPage);
	}

	public void setStartPage (int val)
	{
		this.startPage = val;
	}

	public int getStartPage ()
	{
		return (this.startPage);
	}

	public void setLastPage (int val)
	{
		this.lastPage = val;
	}

	public int getLastPage ()
	{
		return (this.lastPage);
	}

	public void setPrevPage (int val)
	{
		this.prevPage = val;
	}

	public int getPrevPage ()
	{
		return (this.prevPage);
	}

	public void setNextPage (int val)
	{
		this.nextPage = val;
	}

	public int getNextPage ()
	{
		return (this.nextPage);
	}

	public void setPrevBlock (int val)
	{
		this.prevBlock = val;
	}

	public int getPrevBlock ()
	{
		return (this.prevBlock);
	}

	public void setNextBlock (int val)
	{
		this.nextBlock = val;
	}

	public int getNextBlock ()
	{
		return (this.nextBlock);
	}

	public int getLimitStart ()
	{
		return limitStart;
	}

	public void setLimitStart (int limitStart)
	{
		this.limitStart = limitStart;
	}

	/**
	 * 페이징을 설정한다.
	 */
	private void settingPaginData ()
	{
		// startPage
		this.startPage = this.currentPage - (this.currentPage - 1) % this.showPages;

		// lastPage
		this.lastPage = (int) Math.ceil ((double) this.totalArticles / this.articlesPerPage);
		if (this.lastPage <= 0)
			this.lastPage = 1;

		// prevPage
		if (this.currentPage == 1)
			this.prevPage = 1;
		else
			this.prevPage = this.currentPage - 1;

		// nextPage
		if (this.currentPage == this.lastPage)
			this.nextPage = this.lastPage;
		else
			this.nextPage = this.currentPage + 1;

		// prevBlock
		this.prevBlock = this.currentPage - (this.currentPage - 1) % this.showPages - this.showPages;

		// nextBlock
		this.nextBlock = this.currentPage - (this.currentPage - 1) % this.showPages + this.showPages;
	}

	@JsonIgnore
	public Map<String, Object> getSearchData ()
	{
		return searchData;
	}

	public void setSearchData (Map<String, Object> searchData)
	{
		this.searchData = searchData;
	}

	/**
	 * iBatis 에 넘기기 위한 map 객체로 만든다.
	 *
	 * @return : 검색 데이터가 포함된 map
	 */
	public Map<String, Object> makeMap ()
	{
		Map<String, Object> mapData = new HashMap<> ();

		mapData.put ("limitStart", limitStart);
		mapData.put ("articlesPerPage", articlesPerPage);
		mapData.put ("showPages", showPages);
		mapData.put ("currentPage", currentPage);
		mapData.put ("totalArticles", totalArticles);
		mapData.put ("isList", "T");

		// 검색 조건이 NULL 이 아니면 검색조건을 추가한다.
		if (searchData != null)
			mapData.putAll (searchData);

		return mapData;
	}

	/**
	 * iBatis 에 넘기기 위한 map 객체로 만든다. (Count 용)
	 *
	 * @return 맵객체
	 */
	public Map<String, Object> makeCntMap ()
	{
		if (searchData != null)
			return searchData;
		else
			return new HashMap<> ();
	}

	public Query getQuery ()
	{
		return query;
	}

	public Paging setQuery (Query query)
	{
		this.query = query;
		return this;
	}

	public List<Sort.Order> getOrerListMongo ()
	{
		return orerListMongo;
	}

	public Paging setOrerListMongo (List<Sort.Order> orerListMongo)
	{
		this.orerListMongo = orerListMongo;
		return this;
	}

	@Override
	public String toString() {
		return "Paging{" +
				"totalArticles=" + totalArticles +
				", currentPage=" + currentPage +
				", showPages=" + showPages +
				", articlesPerPage=" + articlesPerPage +
				", limitStart=" + limitStart +
				", startPage=" + startPage +
				", lastPage=" + lastPage +
				", prevPage=" + prevPage +
				", nextPage=" + nextPage +
				", prevBlock=" + prevBlock +
				", nextBlock=" + nextBlock +
				", searchData=" + searchData +
				", query=" + query +
				", orerListMongo=" + orerListMongo +
				'}';
	}
}
