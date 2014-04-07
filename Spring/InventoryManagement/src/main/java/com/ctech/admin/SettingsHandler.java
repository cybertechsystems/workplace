package com.ctech.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctech.admin.beans.Paging;
import com.ctech.admin.beans.menu_item.MenuItem;
import com.ctech.admin.beans.menu_item.Settings;
import com.ctech.admin.util.Util;

/**
 * This controller class has been used for loading the setting page, adding/updating the setting 
 * information into the database. Also, it handles the edit/copy scenarios.
 * @author Cybertech
 *
 */
@Controller
public class SettingsHandler extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(SettingsHandler.class);
	
	public static final String PAGE_SUBMIT_LISTING = "/settinglist";
	public static final String SETTING_ADD = "settingadd";
	public static final String AJAX_SUBMIT_LISTING = "settinglist";
	
	@Autowired
	private DAO dao;
	
	/**
	 * This method has been used for loading the setting page.
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/setting.html", method=RequestMethod.GET)
	public String loadSettingPage(Model m) {
		log.info("Inside SettingsHandler : loadSettingPage.");
		return PAGE_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the setting add page for creating a new 
	 * setting record.
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/settingAdd.html", method=RequestMethod.GET)
	public String loadAddPage(Model m) {
		log.info("Inside SettingsHandler : loadAddPage.");
		m.addAttribute("setting", new Settings());
		return SETTING_ADD;
	}
	
	
	/**
	 * This method has been used for saving the settings data into the database.
	 * 
	 * @param settings
	 * @param m
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/settingSave.html", method=RequestMethod.POST)
	public String saveSettingData(@ModelAttribute Settings settings, Model m) {
		log.info("Start of SettingsHandler : saveSettingData :");
		
		if(settings != null && Util.isNumeric(settings.getsettingID()) 
				&& Integer.parseInt(settings.getsettingID() ) > 0 ) {
			dao.getSqlMapClientTemplate().update("update-setting", settings); 
			log.info("setting updated Successfully : "+settings.toString());
		} else {
			String nextId = (String) dao.getSqlMapClientTemplate().queryForObject("get-next-setting-id");
			if(nextId == null) {
				nextId="1";
			} else {
				nextId = String.valueOf(Integer.parseInt(nextId) + 1);
			}
			log.info("Creating: id=" + nextId);
			settings.setsettingID(nextId);
			 
			dao.getSqlMapClientTemplate().insert("create-setting", settings); 
			log.info("setting saved Successfully : "+settings.toString());
		}
		
	     m.addAttribute("message", "Successfully saved setting: " + settings.toString());
	     
	     loadSettingPage(m);
	     log.info("End of SettingsHandler : saveSettingData :");
	     
	     return AJAX_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for loading the edit settings page based on the selected id
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@RequestMapping(value="/settingEdit.html", method=RequestMethod.GET)
	public String loadEdit(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of SettingsHandler : loadEdit :: selectedId :"+selectedId);
		@SuppressWarnings("deprecation")
		Settings settings = (Settings)dao.getSqlMapClientTemplate().
								queryForObject("retrieve-a-settings", "" + selectedId);
		m.addAttribute("setting", settings);
		
		log.info("End of SettingsHandler : loadEdit :"+settings.toString());
	    
		return SETTING_ADD;
	}
	
	/**
	 * This method has been used for loading the setting page where the input values will match 
	 * with the selected setting record which will be used to create a new record.
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/settingCopy.html", method=RequestMethod.GET)
	public String loadCopy(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of SettingsHandler : loadCopy :: selectedId :"+selectedId);
		
		Settings settings = (Settings)dao.getSqlMapClientTemplate().
								queryForObject("retrieve-a-settings", "" + selectedId);
		settings.setsettingID(null);
		m.addAttribute("setting", settings);
		
		log.info("End of SettingsHandler : loadCopy :"+settings.toString());
		return SETTING_ADD;
	}
	
	/**
	 * This method has been used for deleting the setting record from the database and refreshing
	 * the setting list page.
	 * @param m
	 * @param selectedId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/settingDelete.html", method=RequestMethod.GET)
	public String delete(Model m, @RequestParam("selectedrow") int selectedId) {
		log.info("Start of SettingsHandler : delete :: selectedId :"+selectedId);
		dao.getSqlMapClientTemplate().delete("delete-settings", "" + selectedId);
		loadSettingPage(m);
		
		log.info("End of SettingsHandler : delete");
	    return AJAX_SUBMIT_LISTING;
	}
	
	/**
	 * This method has been used for validating if the entered setting name already exists in the
	 * database or not.
	 * @param settingName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateName.html", method = RequestMethod.GET)
	public @ResponseBody String validate(@RequestParam("selectedname") String settingName) {
		log.info("Start of SettingsHandler : validate :: settingName :"+settingName);
		int settingCount = (Integer) dao.getSqlMapClientTemplate().
				queryForObject("retrieve-setting-names", settingName);
		
		if (settingCount > 0) {
			return "true";
		}
		log.info("End of SettingsHandler : validate :: settingCount :"+settingCount);
		return "false";
	}

	@RequestMapping(value="/settingPageData.html")
	public @ResponseBody String loadPageData(HttpServletRequest req) throws IOException {
	    return managePaging(req);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected List<MenuItem> loadPageRecords(Paging input) {
		return dao.getSqlMapClientTemplate().queryForList("retrieve-settings", input);
	}

	@SuppressWarnings("deprecation")
	protected String getTotalCount(Paging input) {
		return (String) dao.getSqlMapClientTemplate().queryForObject("retrieve-setting-count",input);
	}
}