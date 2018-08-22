package org.bean;

public class Page {
	private int totalNum;
	private int totalPage;
	private int pageNumber;
	private int currentPage;

	public Page() {
		this.currentPage = 1;
		this.pageNumber = 5;
	}

	public void count() {
        if(this.totalNum/this.pageNumber==0) {
        	this.totalPage=1;
        }
        if(this.totalNum/this.pageNumber>0&&this.totalNum%this.pageNumber!=0) {
        	this.totalPage=this.totalNum/this.pageNumber+1;
        }
        if(this.totalNum/this.pageNumber>0&&this.totalNum%this.pageNumber==0) {
        	this.totalPage=this.totalNum/this.pageNumber;
        }
        if(this.currentPage>this.totalPage) {
        	this.currentPage=this.totalPage;
        }
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		this.count();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
