package com.ctech.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ctech.admin.beans.Paging;
import com.ctech.admin.util.Util;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This controller has been used as a template for the implementations such as manage the paging for different sections
 * and saving the image.
 * 
 * @author Cybertech
 *
 */
public abstract class BaseController {
	
	protected String managePaging(HttpServletRequest req) throws IOException {
		
		int start = Util.convertToNumber(req.getParameter("iDisplayStart"));
		int length = Util.convertToNumber(req.getParameter("iDisplayLength"));
		Paging paging = new Paging();
		paging.setStart(start);
		paging.setSize(length);
		
		String search = req.getParameter("sSearch");
		if(search != null && search.trim().length() > 0) {
			paging.setSearch("%"+search+"%");
		}
		
		String orderCol = req.getParameter("iSortCol_0");
		if(orderCol != null && orderCol.trim().length() > 0) {
			paging.setOrderCol(Util.convertToNumber(orderCol) + 1);
		} else {
			paging.setOrderCol(99);
		}
		
		String orderBy = req.getParameter("sSortDir_0");
		if(orderBy != null && orderBy.trim().length() > 0) {
			paging.setOrderBy(orderBy);
		} else {
			paging.setOrderBy("desc");
		}
		
		HttpSession session = req.getSession();
		String totalCount = (String)session.getAttribute("totalCount");
		totalCount = (totalCount == null || start == 0) ? getTotalCount(paging): totalCount;
		session.setAttribute("totalCount", totalCount);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sEcho", req.getParameter("sEcho"));
		data.put("iTotalRecords", new Integer(length));
		data.put("iTotalDisplayRecords", totalCount);
		data.put("aaData", loadPageRecords(paging));
		
		JsonFactory factory = new JsonFactory();
		StringWriter out = new StringWriter();
		JsonGenerator g = factory.createGenerator(out);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(g, data);
		String output = out.toString();
		out.flush();
		
		return output;
		
	}

	abstract protected Object loadPageRecords(Paging input);

	abstract protected String getTotalCount(Paging input) ;
	
	protected String saveImage(MultipartFile rawImage, String name, String path) throws IOException {
		
		if(rawImage != null && rawImage.getOriginalFilename().trim().length() > 0) {
			int start = rawImage.getOriginalFilename().indexOf(".");
			String fileName = rawImage.getOriginalFilename().substring(0,start) +"_" + name + 
									rawImage.getOriginalFilename().substring(start,rawImage.getOriginalFilename().length());
			
			FileCopyUtils.copy(rawImage.getBytes(),	new FileOutputStream(path + fileName));
			return fileName; 
		} 
		
		return null;
	}

}
