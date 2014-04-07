package com.ctech.admin.beans.menu_item;

import java.io.Serializable;

public class ItemDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String zPk;
	private String zProductId;
	private String zName;
	private String description;
	private String subCategories;
	private String primaryCategory;
	private String compulsoryItem;
	private String maxSubItem;
	private String sel1Name;
	private String sel1Max;
	private String sel1Prompt;
	private String sel2;
	private String sel2Price;
	private String sel2Name;
	private String sel2Max;
	private String sel2Prompt;
	private String sel3;
	private String sel3Price;
	private String sel3Name;
	private String sel3Max;
	private String sel3Prompt;
	private String sel4;
	private String sel4Price;
	private String sel4Name;
	private String sel4Max;
	private String sel4Prompt;
	private String sel5;
	private String sel5Price;
	private String sel5Name;
	private String sel5Max;
	private String sel5Prompt;
	private String isPOSCombo;
	private String allowChangeCompleteMealQty; 

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubCategories() {
		return subCategories;
	}

	public String getPrimaryCategory() {
        return primaryCategory;
    }
	
	public String getCompulsoryItem() {
		return compulsoryItem;
	}
	
	public String getMaxSubItem() {
		return maxSubItem;
	}
	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}

	public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }
    
    public void setCompulsoryItem(String compulsoryItem) {
    	this.compulsoryItem = compulsoryItem;
    }
	
	public void setMaxSubItem(String maxSubItem) {
		this.maxSubItem = maxSubItem;
	}
	public String getSel1Name() {
		return sel1Name;
	}

	public void setSel1Name(String sel1Name) {
		this.sel1Name = sel1Name;
	}

	public String getSel1Max() {
		return sel1Max;
	}

	public void setSel1Max(String sel1Max) {
		this.sel1Max = sel1Max;
	}

	public String getSel1Prompt() {
		return sel1Prompt;
	}

	public void setSel1Prompt(String sel1Prompt) {
		this.sel1Prompt = sel1Prompt;
	}

	public String getSel2() {
		return sel2;
	}

	public String getSel2Price() {
        return sel2Price;
    }
	
	public void setSel2(String sel2) {
		this.sel2 = sel2;
	}

	public void setSel2Price(String sel2Price) {
        this.sel2Price = sel2Price;
    }
	
	public String getSel2Name() {
		return sel2Name;
	}

	public void setSel2Name(String sel2Name) {
		this.sel2Name = sel2Name;
	}

	public String getSel2Max() {
		return sel2Max;
	}

	public void setSel2Max(String sel2Max) {
		this.sel2Max = sel2Max;
	}

	public String getSel2Prompt() {
		return sel2Prompt;
	}

	public void setSel2Prompt(String sel2Prompt) {
		this.sel2Prompt = sel2Prompt;
	}

	public String getSel3() {
		return sel3;
	}

	public String getSel3Price() {
        return sel3Price;
    }
	
	public void setSel3(String sel3) {
		this.sel3 = sel3;
	}

	public void setSel3Price(String sel3Price) {
        this.sel3Price = sel3Price;
    }
	
	public String getSel3Name() {
		return sel3Name;
	}

	public void setSel3Name(String sel3Name) {
		this.sel3Name = sel3Name;
	}

	public String getSel3Max() {
		return sel3Max;
	}

	public void setSel3Max(String sel3Max) {
		this.sel3Max = sel3Max;
	}

	public String getSel3Prompt() {
		return sel3Prompt;
	}

	public void setSel3Prompt(String sel3Prompt) {
		this.sel3Prompt = sel3Prompt;
	}

	public String getSel4() {
		return sel4;
	}
	
	public String getSel4Price() {
        return sel4Price;
    }
	

	public void setSel4(String sel4) {
		this.sel4 = sel4;
	}
	
	public void setSel4Price(String sel4Price) {
        this.sel4Price = sel4Price;
    }

	public String getSel4Name() {
		return sel4Name;
	}

	public void setSel4Name(String sel4Name) {
		this.sel4Name = sel4Name;
	}

	public String getSel4Max() {
		return sel4Max;
	}

	public void setSel4Max(String sel4Max) {
		this.sel4Max = sel4Max;
	}

	public String getSel4Prompt() {
		return sel4Prompt;
	}

	public void setSel4Prompt(String sel4Prompt) {
		this.sel4Prompt = sel4Prompt;
	}

	public String getSel5() {
		return sel5;
	}

	public String getSel5Price() {
        return sel5Price;
    }
	
	public void setSel5(String sel5) {
		this.sel5 = sel5;
	}

	public void setSel5Price(String sel5Price) {
        this.sel5Price = sel5Price;
    }
	
	public String getSel5Name() {
		return sel5Name;
	}

	public void setSel5Name(String sel5Name) {
		this.sel5Name = sel5Name;
	}

	public String getSel5Max() {
		return sel5Max;
	}

	public void setSel5Max(String sel5Max) {
		this.sel5Max = sel5Max;
	}

	public String getSel5Prompt() {
		return sel5Prompt;
	}

	public void setSel5Prompt(String sel5Prompt) {
		this.sel5Prompt = sel5Prompt;
	}

	public String getIsPOSCombo() {
        return isPOSCombo;
    }

    public void setIsPOSCombo(String flag) {
        this.isPOSCombo = flag;
    }
    
    public String getAllowChangeCompleteMealQty() {
        return allowChangeCompleteMealQty;
    }

    public void setAllowChangeCompleteMealQty(String flag) {
        this.allowChangeCompleteMealQty = flag;
    }
}
