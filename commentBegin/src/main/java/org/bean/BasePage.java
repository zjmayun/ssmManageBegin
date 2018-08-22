package org.bean;

public class BasePage {
    private Page page;

    public BasePage() {
    	this.page=new Page();
    }
    
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
    
    
}
