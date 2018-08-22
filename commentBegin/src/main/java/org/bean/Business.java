package org.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Business extends BasePage{
	private Integer id;
	private String imgFileName;
	private String title;
	private Long price;
	private Long distance;
	private Integer number;
	private String desc;
	private String city;
	private String category;
	private Integer starTotalNum;
	private Integer commentTotalNum;
	private String subTitle;
	private Dic cityDic;
	private Dic categoryDic;
	
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Dic getCityDic() {
		return cityDic;
	}

	public void setCityDic(Dic cityDic) {
		this.cityDic = cityDic;
	}

	public Dic getCategoryDic() {
		return categoryDic;
	}

	public void setCategoryDic(Dic categoryDic) {
		this.categoryDic = categoryDic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStarTotalNum() {
		return starTotalNum;
	}

	public void setStarTotalNum(Integer starTotalNum) {
		this.starTotalNum = starTotalNum;
	}

	public Integer getCommentTotalNum() {
		return commentTotalNum;
	}

	public void setCommentTotalNum(Integer commentTotalNum) {
		this.commentTotalNum = commentTotalNum;
	}
     
}
