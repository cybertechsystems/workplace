package com.ctech.admin.beans.menu_item;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String zPk;
	private String zProductId;
	private String zName;
	private String zNameCN;
	private String isCondiment;
	private String usualPrice;
	private String basePrice;
	private String takeawayPrice;
	private String categoryId;
	private String isSideDish;
	private String isSauce;
	private String isAdvertisement = "0";
	private String adsStartDate;
	private String adsEndDate;
	private String zThumbImgUrl;
	private String description;
	private String descriptionCN;
	private String specialCat;
	private String specialCat2;
	private String sequence;
	private String completeMeal;
	private String promotionsTime;
	private String promotionsDay;
	private String iPodOnly = "0";
	private String iPadOnly = "0";
	private String isPOSCombo = "1";
	private String compulsoryItem;
	private String maxSubItem;
	private String subCategories;
	private String primaryCategory;
	

	private String promotionsStartTime;
	private String promotionsEndTime;
	
	private String stock;
	
	private MultipartFile imageFile;

	public String getzPk() {
		return zPk;
	}

	public void setzPk(String zPk) {
		this.zPk = zPk;
	}

	public String getzProductId() {
		return zProductId;
	}

	public void setzProductId(String zProductId) {
		this.zProductId = zProductId;
	}

	public String getzName() {
		return zName;
	}

	public void setzName(String zName) {
		this.zName = zName;
	}
	
	public String getzNameCN() {
		return zNameCN;
	}

	public void setzNameCN(String zNameCN) {
		this.zNameCN = zNameCN;
	}

	public String getIsCondiment() {
		return isCondiment;
	}

	public void setIsCondiment(String isCondiment) {
		this.isCondiment = isCondiment;
	}

	public String getUsualPrice() {
		return usualPrice;
	}

	public String getBasePrice() {
		return basePrice;
	}
	
	public String getTakeawayPrice() {
		return takeawayPrice;
	}
	
	public void setUsualPrice(String usualPrice) {
		this.usualPrice = usualPrice;
	}
	
	public void setTakeawayPrice(String takeawayPrice) {
		this.takeawayPrice = takeawayPrice;
	}
	
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getIsSideDish() {
		return isSideDish;
	}

	public void setIsSideDish(String isSideDish) {
		this.isSideDish = isSideDish;
	}

	public String getIsSauce() {
		return isSauce;
	}

	public void setIsSauce(String isSauce) {
		this.isSauce = isSauce;
	}

	public String getIsAdvertisement() {
		return isAdvertisement;
	}

	public void setIsAdvertisement(String isAdvertisement) {
		this.isAdvertisement = isAdvertisement;
	}

	public String getAdsStartDate() {
		return adsStartDate;
	}

	public void setAdsStartDate(String adsStartDate) {
		this.adsStartDate = adsStartDate;
	}

	public String getAdsEndDate() {
		return adsEndDate;
	}

	public void setAdsEndDate(String adsEndDate) {
		this.adsEndDate = adsEndDate;
	}

	public String getzThumbImgUrl() {
		return zThumbImgUrl;
	}

	public void setzThumbImgUrl(String zThumbImgUrl) {
		this.zThumbImgUrl = zThumbImgUrl;
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

	public void setDescriptionCN(String description) {
		this.descriptionCN = description;
	}

	public String getSpecialCat() {
		return specialCat;
	}

	public void setSpecialCat(String specialCat) {
		this.specialCat = specialCat;
	}

	public String getSpecialCat2() {
        return specialCat2;
    }

    public void setSpecialCat2(String specialCat) {
        this.specialCat2 = specialCat;
    }
    
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSequence() {
		return sequence;
	}

	public void setCompleteMeal(String completeMeal) {
		this.completeMeal = completeMeal;
	}

	public String getCompleteMeal() {
		return completeMeal;
	}

	public String getPromotionsTime() {
        return promotionsTime;
    }

    public void setPromotionsTime(String time) {
        this.promotionsTime = time;
    }
    public String getPromotionsDay() {
        return promotionsDay;
    }

    public void setPromotionsDay(String day) {
        this.promotionsDay = day;
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
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public String getIsPOSCombo() {
		return isPOSCombo;
	}

	public void setIsPOSCombo(String isPOSCombo) {
		this.isPOSCombo = isPOSCombo;
	}
	
	public String getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}

	public String getCompulsoryItem() {
		return compulsoryItem;
	}

	public void setCompulsoryItem(String compulsoryItem) {
		this.compulsoryItem = compulsoryItem;
	}

	public String getMaxSubItem() {
		return maxSubItem;
	}

	public void setMaxSubItem(String maxSubItem) {
		this.maxSubItem = maxSubItem;
	}

	public String getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}
	
	public String getPromotionsStartTime() {
		return promotionsStartTime;
	}

	public void setPromotionsStartTime(String promotionsStartTime) {
		this.promotionsStartTime = promotionsStartTime;
	}

	public String getPromotionsEndTime() {
		return promotionsEndTime;
	}

	public void setPromotionsEndTime(String promotionsEndTime) {
		this.promotionsEndTime = promotionsEndTime;
	}
}
