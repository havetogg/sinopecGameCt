package org.jumutang.project.base;

public class Page {
	/**
	 * 每页记录数
	 */
	private Integer pageSize = 10;

	/**
	 * 当前页数
	 */
	private Integer currentPage;
	/**
	 * 页数
	 */
	private Integer pageCount;
	/**
	 * 记录数
	 */
	private Integer recordCount;

	public int getCurrentPage() {
		if (currentPage==null||currentPage < 1)
			currentPage = 1;
		if (currentPage > pageCount)
			currentPage = pageCount;
		return Integer.valueOf(currentPage).intValue();
	}

	/**
	 * 
	 * @return
	 */
	public int getStartRow() {
		Integer startRow = pageSize * (getCurrentPage() - 1);
		if (startRow < 1) {
			return 0;
		}
		return startRow.intValue();
	}

	/**
	 * 
	 * @param totaItemNum
	 * @param pageItemCount
	 * @return
	 */
	public Integer getPageCount(Integer totaItemNum, Integer pageItemCount) {
		Integer totalPageNum = totaItemNum / pageItemCount;
		if (totaItemNum % pageItemCount != 0) {
			totalPageNum++;
		}
		return totalPageNum;
	}
	/**
	 * 
	 * @param recordCount
	 */
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
		if (pageSize > 0) {
			setPageCount(getPageCount(recordCount, pageSize));
		}
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return Integer.valueOf(pageSize).intValue();
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRecordCount() {
		return recordCount;
	}
}
