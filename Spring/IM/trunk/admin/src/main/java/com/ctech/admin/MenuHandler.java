package com.ctech.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctech.admin.beans.Paging;
import com.ctech.admin.beans.menu_item.Category;
import com.ctech.admin.beans.menu_item.MenuItem;
import com.ctech.admin.util.Util;

/**
 * This controller class has been used for loading the Menu page, adding/updating the menu 
 * information into the database. Also, it handles the edit/copy scenarios.
 * 
 * @author Cybertech
 *
 */
@Controller
public class MenuHandler extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(MenuHandler.class);
	public static final String PAGE_SUBMIT_LISTING = "/menulist";
	public static final String MENU_ADD = "menuadd";
	public static final String AJAX_SUBMIT_LISTING = "menulist";
	
	@Value("#{systemProps['menu.image.folder.path']}")
	private String imageFolder;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value="/menu.html", method=RequestMethod.GET)
	public String loadMenuPage(Model m) {
		log.info("Inside MenuHandler : loadMenuPage");
		return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the menu page where the end-user can enter 
	 * the corresponding menu information.
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/menuAdd.html", method=RequestMethod.GET)
	public String loadAddPage(Model m) {
		log.info("Start of MenuHandler : loadAddPage");
		MenuItem menuitem = new MenuItem();
		m.addAttribute("promotionsDayList",loadDays());
		m.addAttribute("menuitem", menuitem);
		m.addAttribute("isPromotions", false);
		
		m.addAttribute("categoryList", loadCategories());
		m.addAttribute("subCategoryList", loadSubCategories(menuitem.getCategoryId()));
		m.addAttribute("compulsoryItemList", loadCompulsoryItem(menuitem.getCategoryId()));
		
		log.info("End of MenuHandler : loadAddPage");
		return MENU_ADD;
	}
	
	/**
	 * This method has been used for saving(in case of a new menu record) or updating(in case of editing
	 * an existing menu record) the menu data into the database. This will return the menu list page once 
	 * the above operations have been done.
	 * 
	 * @param menuitem
	 * @param m
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/menuSave.html", method=RequestMethod.POST)
	public String saveMenuData(@ModelAttribute MenuItem menuitem, Model m,HttpServletRequest req) throws IOException {
		log.info("Start of MenuHandler : saveMenuData : ");
		
		if(StringUtils.isEmpty(menuitem.getStock())) {
			menuitem.setStock("999");
		}
		if(StringUtils.isEmpty(menuitem.getSequence())) {
			menuitem.setSequence("99");
		}
		if(StringUtils.isEmpty(menuitem.getMaxSubItem())) {
			menuitem.setMaxSubItem("99");
		}
		if(StringUtils.isEmpty(menuitem.getUsualPrice())) {
			menuitem.setUsualPrice(null);
		}
		if(StringUtils.isEmpty(menuitem.getBasePrice())) {
			menuitem.setBasePrice(null);
		}
		if(StringUtils.isEmpty(menuitem.getTakeawayPrice())) {
			menuitem.setTakeawayPrice(null);
		}
		if(StringUtils.isEmpty(menuitem.getAdsStartDate())) {
			menuitem.setAdsStartDate(null);
		}
		if(StringUtils.isEmpty(menuitem.getAdsEndDate())) {
			menuitem.setAdsEndDate(null);
		}
		if(StringUtils.isEmpty(menuitem.getPromotionsDay())) {
			menuitem.setPromotionsDay("Mo,Tu,We,Th,Fr,Sa,Su");
		}
		if(StringUtils.isEmpty(req.getParameter("isAdvertise"))) {
			menuitem.setIsAdvertisement("0");
		} else {
			menuitem.setIsAdvertisement(req.getParameter("isAdvertise"));
		}
		if(StringUtils.isEmpty(req.getParameter("isPOSComboo"))) {
			menuitem.setIsPOSCombo("1");
		} else {
			menuitem.setIsPOSCombo(req.getParameter("isPOSComboo"));
		}
		if(StringUtils.isEmpty(req.getParameter("iPodOnlybtn"))) {
			menuitem.setiPodOnly("0");
		} else {
			menuitem.setiPodOnly(req.getParameter("iPodOnlybtn"));
		}
		if(StringUtils.isEmpty(req.getParameter("iPadOnlybtn"))) {
			menuitem.setiPadOnly("0");
		} else {
			menuitem.setiPadOnly(req.getParameter("iPadOnlybtn"));
		}
		
		if(!StringUtils.isEmpty(menuitem.getSubCategories())) {
			if(menuitem.getSubCategories().startsWith("|")) {
				menuitem.setSubCategories(req.getParameter("subCategories").substring(1));
			}else{
				menuitem.setSubCategories(req.getParameter("subCategories"));
			}
		}
		/*if(!StringUtils.isEmpty(menuitem.getPrimaryCategory())) {
			menuitem.setPrimaryCategory(menuitem.getPrimaryCategory().replaceAll(",", ""));
		}*/
		if(StringUtils.isEmpty(menuitem.getPromotionsTime())) {
			menuitem.setPromotionsTime(menuitem.getPromotionsStartTime().replaceAll(",", "")+"-"+menuitem.getPromotionsEndTime().replaceAll(",", ""));
		}
		String imgName = null;
		if(menuitem != null && Util.isNumeric(menuitem.getzPk()) 
				&& Integer.parseInt(menuitem.getzPk()) > 0 ) {

			imgName = saveImage(menuitem.getImageFile(), menuitem.getzPk(),
								context.getRealPath("/") +"/"+ imageFolder);
			
			//if imgName is present the object will be save after the else condition
			if(imgName == null) {
				dao.getSqlMapClientTemplate().update("update-menu-item", menuitem);
			}
			
		} else {
			String nextId = (String) dao.getSqlMapClientTemplate().queryForObject("get-next-menu-item-id");
			String nextProductId = (String) dao.getSqlMapClientTemplate().queryForObject("get-next-menu-product-id");
			if(nextId == null) {
				nextId="1";
				nextProductId="1";
			} else {
				nextId = String.valueOf(Integer.parseInt(nextId) + 1);
				nextProductId = String.valueOf(Integer.parseInt(nextProductId) + 1);
			}
			log.info("Creating: id=" + nextId);
			menuitem.setzPk(nextId);
			menuitem.setzProductId(nextProductId);
			 
			dao.getSqlMapClientTemplate().insert("create-menu-item", menuitem); 
			imgName = saveImage(menuitem.getImageFile(), menuitem.getCategoryId(), 
								context.getRealPath("/") +"/"+ imageFolder);
			if(imgName == null) {
				dao.getSqlMapClientTemplate().update("update-menu-item", menuitem);
				log.info("Menu-item updated successfully.");
			}
		}
		//process images.
		if(imgName != null) {
			menuitem.setzThumbImgUrl(imageFolder+ imgName);
			dao.getSqlMapClientTemplate().update("update-menu-item", menuitem); 
			log.info("Menu-item updated successfully.");
		}
	    m.addAttribute("message", "Successfully saved menuItem: " + menuitem.toString());
	    log.info("End of MenuHandler : saveMenuData :: menuItem : "+menuitem.toString());
	    return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading an existing menu record for editing purpose. This will take the 
	 * input value as selected row value in the form of selected id.
	 * 
	 * @param m
	 * @param selectedId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/menuEdit.html", method=RequestMethod.GET)
	public String loadEdit(Model m, @RequestParam("selectedrow") String selectedId) throws UnsupportedEncodingException {
		log.info("Start of MenuHandler : loadEdit :: selectedId : "+selectedId);
		
		MenuItem menuitem = (MenuItem)dao.getSqlMapClientTemplate().
								queryForList("retrieve-a-menu-item", "" + selectedId).get(0);
		
		m.addAttribute("promotionsDayList",loadDays());
		if("1".equalsIgnoreCase(menuitem.getIsAdvertisement())){
			m.addAttribute("isPromotions", true);
		} else {
			m.addAttribute("isPromotions", false);
		}
		
		m.addAttribute("menuitem", menuitem);
		
		m.addAttribute("categoryList", loadCategories());
		if(menuitem.getSubCategories() != null){
			m.addAttribute("subCatList", getSubCategoryList(menuitem.getSubCategories()));
			m.addAttribute("subCategoryList", getAvailCategoryList(menuitem.getCategoryId(),menuitem.getSubCategories()));
		} else{
			m.addAttribute("subCategoryList", loadSubCategories(menuitem.getCategoryId()));
		}
		m.addAttribute("compulsoryItemList", loadCompulsoryItem(menuitem.getPrimaryCategory()));
		
		log.info("End of MenuHandler : loadEdit :: menuitem : "+menuitem.toString());
		return MENU_ADD;
	}
	
	/**
	 * This method has been used for taking the sub-category values in the form a single string
	 * and will return the sub-category list after splitting it.
	 *  
	 * @param subCategories
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	private List<Category> getSubCategoryList(String subCategories) {
		log.info("Start of MenuHandler : getSubCategoryList :: subCategories : "+subCategories);
		String [] temp = subCategories.split("[\\|\\s]+"); 
		List<String> names = new ArrayList<String>(); 
		for(String str : temp) {
			names.add(str);
		}
		log.info("End of MenuHandler : getSubCategoryList ");
		return dao.getSqlMapClientTemplate().queryForList("retrieve-subcategory-details", names);
	}
	
	/**
	 * This method has been used for getting the available sub-category values based on a category.
	 * This method is taking the category id as an input and returning the list of sub-categories as alist
	 * 
	 * @param categoryId
	 * @param subCategories
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	private List<Category> getAvailCategoryList(String categoryId,String subCategories) {
		log.info("Start of MenuHandler : getAvailCategoryList :: categoryId : "+categoryId+ " subCategories : "+subCategories);
		String [] temp = subCategories.split("[\\|\\s]+");
		List<String> names = new ArrayList<String>(); 
		names.add(categoryId);
		for(String str : temp){
			names.add(str);
		}
		log.info("End of MenuHandler : getAvailCategoryList ");
		return (List<Category>) dao.getSqlMapClientTemplate().queryForList("retrieve-availcategory-details", names);
	}

	/**
	 * This method has been used for taking the menu id as an input value and will use this id to delete
	 * the menu record from the database. On complete of delete operation the list page will be loaded and returned.
	 *
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/menuDelete.html", method=RequestMethod.GET)
	public String delete(Model m, @RequestParam("selectedrow") String selectedId) {
		log.info("Start of MenuHandler : delete ");
		dao.getSqlMapClientTemplate().delete("delete-menu-item", selectedId);
		
		log.info("End of MenuHandler : delete ");
	    return AJAX_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for taking the menu id to fetch the corresponding menu record 
	 * from the database and will return the save page containing the corresponding datas which can be re-used 
	 * for a new menu-item.
	 * 
	 * @param m
	 * @param selectedId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/menuCopy.html", method=RequestMethod.GET)
	public String loadCopy(Model m, @RequestParam("selectedrow") String selectedId) throws UnsupportedEncodingException  {
		log.info("Start of MenuHandler : loadCopy : "+selectedId);
		
		MenuItem menuitem = (MenuItem)dao.getSqlMapClientTemplate().
								queryForList("retrieve-a-menu-item", "" + selectedId).get(0);
		menuitem.setzPk(null);
		m.addAttribute("menuitem", menuitem);
		m.addAttribute("promotionsDayList",loadDays());
		if("1".equalsIgnoreCase(menuitem.getIsAdvertisement())){
			m.addAttribute("isPromotions", true);
		} else {
			m.addAttribute("isPromotions", false);
		}
		m.addAttribute("categoryList", loadCategories());
		if(menuitem.getSubCategories() != null){
			m.addAttribute("subCatList", getSubCategoryList(menuitem.getSubCategories()));
			m.addAttribute("subCategoryList", getAvailCategoryList(menuitem.getCategoryId(),menuitem.getSubCategories()));
		} else{
			m.addAttribute("subCategoryList", loadSubCategories(menuitem.getCategoryId()));
		}
		m.addAttribute("compulsoryItemList", loadCompulsoryItem(menuitem.getPrimaryCategory()));
		
		log.info("End of MenuHandler : loadCopy : menuitem"+menuitem.toString());
		return MENU_ADD;
	}
	
	/**
	 * This method has been used for loading all the categories from the database and each category details have been 
	 * put into a map, which is returned once all the category objects have been placed.
	 * 
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected Map<String, String> loadCategories() {
		List<Category> categoryList = null;
		categoryList = dao.getSqlMapClientTemplate().queryForList("retrieve-category-names");
		Map<String,String> map = new HashMap<String,String>();
		try {
	    	for (Category category : categoryList){
	    		map.put(category.getCategoryID(), category.getDescription());
	    	}
	    	return map;
		} catch (Exception e){
			e.printStackTrace();
			return map;
		}
	}
	
	/**
	 * This method has been used for loading all the sub-categories for a corresponding category.
	 * The category id has been passed as an input value and it will return a list containing the sub category values. 
	 * 
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<Category> loadSubCategories(String categoryId) {
		List<Category> subCategoryList = null;
		
		if(!StringUtils.isEmpty(categoryId)) {
			subCategoryList = dao.getSqlMapClientTemplate().queryForList("retrieve-subcategory-names", categoryId);
		} else {
			subCategoryList = dao.getSqlMapClientTemplate().queryForList("retrieve-category-names");
		}
		return subCategoryList;
	}
	
	/**
	 * This method has been used for loading the compulsory item associated to the corresponding category.
	 * which has been passed as an input.
	 * 
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<MenuItem> loadCompulsoryItem(String categoryId) {
		List<MenuItem> compulsoryItemList = null;
		
		if(!StringUtils.isEmpty(categoryId)) {
			compulsoryItemList = dao.getSqlMapClientTemplate().queryForList("retrieve-compulsory-items", categoryId);
		}
		return compulsoryItemList;
	}
	
	/**
	 * This method has been used for fetching a list containing the week days which will be used
	 * as a drop-down.
	 * 
	 * @return
	 */
	protected List<String> loadDays() {
		List<String> dayList = new ArrayList<String>();
		dayList.add("Mo");
		dayList.add("Tu");
		dayList.add("We");
		dayList.add("Th");
		dayList.add("Fr");
		dayList.add("Sa");
		dayList.add("Su");
		
		return dayList;
	}
	
	/**
	 * This method has been used for validating the menu name if it is present in the database. if the 
	 * corresponding menu name is present in the database, then the flag is being set accordingly.
	 * @param name
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateMenuName.html", method = RequestMethod.GET)
	public @ResponseBody String validate(@RequestParam("selectedname") String name) {
		String recordCount = (String) dao.getSqlMapClientTemplate().
				queryForObject("retrieve-a-menu-count", name);
		
		if (!recordCount.equalsIgnoreCase("0")) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * This method has been used for updating the Ipod-only flag value for the corresponding menu-item
	 * for which the flag value is being checked from the database and based on the result the flag value
	 * has been set to the alternate.
	 * Here the menu-item id has been passed as an input value.
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/changeIpodOnlyFlag.html", method = RequestMethod.GET)
	public @ResponseBody String updateIpodOnlyFlag(@RequestParam("selectedid") String id) {
		MenuItem menuitem = (MenuItem)dao.getSqlMapClientTemplate().
				queryForObject("retrieve-a-ipod-value", id);
		String value=menuitem.getiPodOnly();
		if("1".equalsIgnoreCase(value)){
			menuitem.setiPodOnly("0");
			dao.getSqlMapClientTemplate().update("update-ipod-value", menuitem);
		}else{
			menuitem.setiPodOnly("1");
			dao.getSqlMapClientTemplate().update("update-ipod-value", menuitem);
		}
		return "true";
	}
	
	/**
	 * This method has been used for populating the primary category value based on a category. The primary 
	 * category value will be returned as a string so that it can be used in the select option field.
	 * 
	 * @param categoryID
	 * @return
	 */
	@RequestMapping(value = "/populatePrimaryCategory.html", method = RequestMethod.GET)
	public @ResponseBody String populatePrimaryCategory(@RequestParam("selectedid") String categoryID) {
		String dropDownStr = "<option value='0'>Select Category</option>";
		List<Category> subCategoryList= loadSubCategories(categoryID);
		for(Category category : subCategoryList) {
			dropDownStr += "<option value='"+category.getCategoryID()+"'>"+category.getDescription()+"</option>";
		}
		return dropDownStr;
	}
	
	/**
	 * This method has been used for populating the compulsory item values based on a category id which is being passed
	 * as an input value.
	 * 
	 * @param categoryID
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@RequestMapping(value = "/populateCompulsoryItem.html", method = RequestMethod.GET)
	public @ResponseBody String populateCompulsoryItem(@RequestParam("selectedid") String categoryID) {
		String dropDownStr = "<option value='0'>Select Compulsory Item</option>";
		List<MenuItem> menuList= dao.getSqlMapClientTemplate().queryForList("retrieve-compulsory-items", categoryID);
		for(MenuItem menuItem : menuList) {
			dropDownStr += "<option value='"+menuItem.getzProductId()+"'>"+menuItem.getzName()+"</option>";
		}
		return dropDownStr;
	}
	
	@RequestMapping(value="/menuPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<MenuItem> loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-menu-items", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-menu-count", input);
	}
	
}