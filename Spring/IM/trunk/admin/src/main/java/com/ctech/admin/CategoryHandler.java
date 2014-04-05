package com.ctech.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
 * This controller class has been used for loading the Category page, adding/updating the category 
 * information into the database. Also, it handles the edit/copy scenarios.
 * 
 * @author Cybertech
 *
 */
@Controller
public class CategoryHandler extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(CategoryHandler.class);
	public static final String PAGE_SUBMIT_LISTING = "/categorylist";
	public static final String CATEGORY_ADD = "categoryadd";
	public static final String AJAX_SUBMIT_LISTING = "categorylist";
	
	@Value("#{systemProps['image.folder.path']}")
	private String imageFolder;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(value="/category.html", method=RequestMethod.GET)
	public String loadCategoryPage(Model m) {
		log.info("Inside CategoryHandler : loadCategoryPage");
		return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the category page where the end-user can enter 
	 * the category information.
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/categoryAdd.html", method=RequestMethod.GET)
	public String loadAddPage(Model m) {
		log.info("Start of CategoryHandler : loadAddPage");
		
		Category category = new Category();
		category.setSequence("98");
		m.addAttribute("category", category);
		
		log.info("End of CategoryHandler : loadAddPage");
		return CATEGORY_ADD;
	}
	
	/**
	 * This method has been used for saving(in case of a new category record) or updating(in case of editing
	 * an existing category record) the category data into the database. This will return the category list page once 
	 * the above operations have been done. 
	 * 
	 * @param category
	 * @param m
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/categorySave.html", method=RequestMethod.POST)
	public String saveCategoryData(@ModelAttribute Category category, Model m) throws IOException {
		log.info("Start of CategoryHandler : saveCategoryData : ");
		if(StringUtils.isEmpty(category.getSequence())) {
			category.setSequence("98");
		}
		
		String imgName = null;
		if(category != null && Util.isNumeric(category.getCategoryID()) 
				&& Integer.parseInt(category.getCategoryID()) > 0 ) {

			imgName = saveImage(category.getImageFile(), category.getCategoryID(),
								context.getRealPath("/") +"/"+ imageFolder);
			if(category.getisShown() == null) {
				category.setisShown("0");
			}
			//if imgName is present the object will be save after the else condition
			if(imgName == null) {
				dao.getSqlMapClientTemplate().update("update-category", category);
			}
			
		} else {
			String nextId = (String) dao.getSqlMapClientTemplate().queryForObject("get-next-category-id");
			if(nextId == null) {
				nextId="1";
			} else {
				nextId = String.valueOf(Integer.parseInt(nextId) + 1);
			}
			log.info("Creating: id=" + nextId);
			category.setCategoryID(nextId);
			 
			dao.getSqlMapClientTemplate().insert("create-category", category); 
			imgName = saveImage(category.getImageFile(), category.getCategoryID(), 
								context.getRealPath("/") +"/"+ imageFolder);
		}
		
		//process images.
		if(imgName != null) {
			category.setimgUrl(imageFolder+ imgName);
			dao.getSqlMapClientTemplate().update("update-category", category); 
		}
		
	    m.addAttribute("message", "Successfully saved category: " + category.toString());
	    log.info("End of CategoryHandler : saveCategoryData :: category : "+category.toString());
	    
	    loadCategoryPage(m);
	    return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading an existing category record for editing purpose. This will take the 
	 * input value as selected row value in the form of selected id.
	 * 
	 * @param m
	 * @param selectedId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/categoryEdit.html", method=RequestMethod.GET)
	public String loadEdit(Model m, @RequestParam("selectedrow") int selectedId) throws UnsupportedEncodingException {
		log.info("Start of CategoryHandler : loadEdit :: selectedId : "+selectedId);
		Category category = (Category)dao.getSqlMapClientTemplate().
								queryForObject("retrieve-a-category", "" + selectedId);
		m.addAttribute("category", category);
		log.info("End of CategoryHandler : loadEdit :: category : "+category.toString());
		return CATEGORY_ADD;
	}
	
	/**
	 * This method has been used for taking the category id as an input value and will use this id to delete
	 * the category record from the database. On complete of delete operation the list page will be loaded
	 * and returned.
	 * 
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/categoryDelete.html", method=RequestMethod.GET)
	public String delete(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of CategoryHandler : delete ");
		dao.getSqlMapClientTemplate().delete("delete-category", "" + selectedId);
		loadCategoryPage(m);
		log.info("Start of CategoryHandler : delete ");
	    return AJAX_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for taking the category id to fetch the corresponding category record 
	 * from the database and will return the save page containing the corresponding datas which can be re-used 
	 * for a new category.
	 *  
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/categoryCopy.html", method=RequestMethod.GET)
	public String loadCopy(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of CategoryHandler : loadCopy : "+selectedId);
		Category category = (Category)dao.getSqlMapClientTemplate().
								queryForObject("retrieve-a-category", "" + selectedId);
		category.setCategoryID(null);
		m.addAttribute("category", category);
	    
		log.info("End of CategoryHandler : loadCopy : category"+category.toString());
		return CATEGORY_ADD;
	}
	
	/**
	 * This method has been used for updating a ipod only flag. If the flag is false then it will make it to 
	 * true else otherwise.
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/changeHideShow.html", method = RequestMethod.GET)
	public @ResponseBody String updateIpodOnlyFlag(@RequestParam("selectedid") String id) {
		Category category = (Category)dao.getSqlMapClientTemplate().
				queryForObject("retrieve-shown-value", id);
		String value=category.getisShown();
		if("1".equalsIgnoreCase(value)){
			category.setisShown("0");
			dao.getSqlMapClientTemplate().update("update-shown-value", category);
		}else{
			category.setisShown("1");
			dao.getSqlMapClientTemplate().update("update-shown-value", category);
		}
		return "true";
	}
	
	@RequestMapping(value="/categoryPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<MenuItem> loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-category", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-category-count", input);
	}
	
	/**
	 * This method has been used for validating if the description is present in the database for the 
	 * corresponding category.
	 *  
	 * @param description
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateDescription.html", method = RequestMethod.GET)
	public @ResponseBody String validate(@RequestParam("selecteddesc") String description) {
		int descriptionCount = (Integer) dao.getSqlMapClientTemplate().
				queryForObject("retrieve-category-desc", description);
		
		if (descriptionCount > 0) {
			return "true";
		}
		return "false";
	}
}