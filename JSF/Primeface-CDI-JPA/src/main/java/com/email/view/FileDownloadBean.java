package com.email.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="fileDownloadBean")
@RequestScoped
public class FileDownloadBean extends BaseAction {
	
	private Logger LOGGER = Logger.getLogger("com.email.view.FileDownloadBean");
	private StreamedContent file;
	
    public void setFile(StreamedContent file) {
		this.file = file;
	}
    
    public StreamedContent getFile() {  
        return file;  
    }

    /**
     * This method help to download the messageList
     * get the root path and download generated file from the root path
     * @throws FileNotFoundException
     */
	public FileDownloadBean() throws FileNotFoundException { 
		try{
			LOGGER.info(
					"Start of FileDownloadBean()");
			
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext(); 
			String rootPath = servletContext.getRealPath("");
		    String now = new SimpleDateFormat("MM_dd_yyyy").format(new java.util.Date());
			InputStream stream = new FileInputStream(new File(rootPath + "//"+now+".pdf"));
	        file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_"+now+".pdf");  
	       
	        LOGGER.info(
					"End of FileDownloadBean()");
		}catch (Exception e) {
			manageError(e, "Error while downloading File. ");
		}
    }  
}
