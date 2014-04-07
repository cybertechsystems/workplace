package com.ctech.admin.beans.menu_item;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	private String categoryID;
	private String description;
	private String descriptionCN;
	private String imgUrl;
	private String isShown;
	private String sequence;
	private String templateId;
	private String iPodOnly;
	private String iPadOnly;
	
	private MultipartFile imageFile;

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescriptionCN() {
		return descriptionCN;
	}

	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}

	public String getimgUrl() {
		return imgUrl;
	}

	public void setimgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getisShown() {
		return isShown;
	}

	public void setisShown(String isShown) {
		this.isShown = isShown;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSequence() {
		return sequence;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return templateId;
	}
    public String getiPodOnly() {
		return iPodOnly;
	}

	public void setiPodOnly(String flag) {
		this.iPodOnly = flag;
	}
	public String getiPadOnly() {
		return iPadOnly;
	}

	public void setiPadOnly(String flag) {
		this.iPadOnly = flag;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
}
