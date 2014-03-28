package com.zsg.dcvps.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.zsg.dcvps.dao.DetailDao;
import com.zsg.dcvps.entity.Details;
import com.zsg.dcvps.util.LazySorter;

/**
 * This Class has been used for the implementation such as loading the 
 * details records, getting the selected row items.. 
 * @author Cybertech1
 *
 */
public class DetailsDataModel extends LazyDataModel<Details> implements Serializable{
	 
    private List<Details> detailList;
    private DetailDao detailDao;
 
    @Autowired
	private OrderBean orderBean;
    
    //@ManagedProperty("#{orderBean.checkID}")
    private String checkID;
    
    public DetailsDataModel(DetailDao detailDao) {
        this.detailDao = detailDao;
    }
 
    /**
     * This method has been used for loading the details records corresponding to the
     * selected check id.
     */
    @Override
    public List<Details> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
    	detailList = detailDao.findWithCheckId("Details.findDetails", first, first + pageSize, checkID);
    	if(sortField != null) {  
            Collections.sort(detailList, new LazySorter(sortField, sortOrder));  
        } 
    	setPageSize(pageSize);
    	setRowCount(detailDao.countTotalDetails("Details.totalNoOfDetails", checkID));
        return detailList;
    }
 

	@Override
    public Object getRowKey(Details details) {
        return details.getDetailId().toString();
    }
 
	/**
	 * This method has been used for getting the corresponding data for the 
	 * selected row.
	 */
    @Override
    public Details getRowData(String rowKey) {
        if(detailList == null)
            return null;
       for(Details details : detailList) {
           if(details.getDetailId().toString().equals(rowKey))
           return details;
       }
       return null;
    }

	public List<Details> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Details> detailList) {
		this.detailList = detailList;
	}

	public String getCheckID() {
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}
}