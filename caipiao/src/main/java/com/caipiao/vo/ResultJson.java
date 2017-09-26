package com.caipiao.vo;

import java.util.List;

/**
 * 返回结果集
 * @author Fox
 *
 * @param <E>
 */
public class ResultJson<E> {
	 /**
     * 总记录数
     */
	private int total;
	/**
     * 当前页结果集
     */
	private List<E> rows;
	
	 public ResultJson() {}
	 
	public ResultJson(int total, List<E> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
