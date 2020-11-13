package com.test.lee.project.common.data;

import java.util.List;

public class PagingList<T>
{
	private List<T>	listData	= null;
	private Paging	paging;

	public PagingList()
	{
	}

	public PagingList(List<T> listData, Paging paging)
	{
		this.listData = listData;
		this.paging = paging;
	}

	public List<T> getListData ()
	{
		return listData;
	}

	public void setListData (List<T> listData)
	{
		this.listData = listData;
	}

	public Paging getPaging ()
	{
		return paging;
	}

	public void setPaging (Paging paging)
	{
		this.paging = paging;
	}

	@Override
	public String toString ()
	{
		return "ListVo [listData=" + listData + ", paging=" + paging + "]";
	}
}
