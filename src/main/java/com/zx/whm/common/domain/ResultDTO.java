package com.zx.whm.common.domain;

import java.util.List;

/** 
* @ClassName: ResultDTO
* @Description: 分页类
* @author superwing
* @param <T>
*/
public class ResultDTO<T> {
	
	/**
	 * Default limit data number per page.
	 */
	private static final int LIMIT_PER_PAGE = 10;

	// 须要显示的数据集
	private List<T>				rows;

	// 每页多少条
	private int					limit = LIMIT_PER_PAGE;

	// 当前页码
	private int					page;

	// 数据总数
	private int					records;

	// 可显示的页数
	private int					total;

	private String errorCode;

	private String errorMsg;

    public ResultDTO(){

    }


    public ResultDTO(int page, int limit){
        this.page = page;
        this.limit = limit;
    }

	/**
	 * 
	* @Title: getRows
	* @Description: getRows
	* @param @return
	* @return List<T>
	* @throws
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 
	* @Title: setRows
	* @Description: setRows
	* @param rows - row data
	* @return
	* @throws
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/**
	 * 
	* @Title: getPage
	* @Description: getPage
	* @param
	* @return int
	* @throws
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 
	* @Title: setPage
	* @Description: setPage
	* @param page - page number
	* @return
	* @throws
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 
	* @Title: getRecords
	* @Description: getRecords
	* @param @return
	* @return int
	* @throws
	 */
	public int getRecords() {
		return records;
	}

	/**
	 * 
	* @Title: setRecords
	* @Description: setRecords
	* @param records - records number
	* @return
	* @throws
	 */
	public void setRecords(int records) {
		this.records = records;
	}

	/**
	 * 
	* @Title: getTotal
	* @Description: getTotal
	* @param
	* @return int
	* @throws
	 */
	public int getTotal() {

		total = records / limit;

		if (records % limit != 0) {

			total++;
		}
		return total;
	}

	/**
	 * 
	* @Title: setTotal
	* @Description: setTotal
	* @param total - total number
	* @return
	* @throws
	 */
	public void setTotal(int total) {
		this.total = total;
	}


	/**
	 *
	* @Title: getLimit
	* @Description: getLimit
	* @param
	* @return int
	* @throws
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 *
	* @Title: setLimit
	* @Description: setLimit
	* @param limit - limit number per page
	* @return
	* @throws
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

    public int getStart(){
        return (this.getPage()-1)  * this.getLimit() + 1;
    }

    public int getEnd() {
        return this.getPage() * this.getLimit();
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
