
package com.email.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import com.email.model.ComposeMail;
import com.email.services.MessageService;
import com.email.util.Constants;

/**
 * This is a JSF controller class for several different views that deal with the Messages in the databases. 
 * 
 * @author Cybertech
 *
 */
@RequestScoped
@javax.faces.bean.ManagedBean(name="messageList")
public class ManageMessage extends BaseAction implements Serializable {
	
	private Logger LOGGER = Logger.getLogger("com.email.view.MessageList");
	private ComposeMail mail;
	@Inject
	private MessageService messageService;
	private List<ComposeMail> mailList; 
	
	public ComposeMail getMail() {
		if(mail == null){
			mail = new ComposeMail();
		}
		return mail;
	}

	public void setMail(ComposeMail mail) {
		this.mail = mail;
	}

	/**
	 * This method helps to get the message list
	 * @return mailList
	 * @throws Exception
	 */
	public List<ComposeMail> getMailList() throws Exception {
		mailList = messageService.findAllMail();
		return mailList;
	}

	/**
	 * When clicking on send button in the compose mail this method get called.
	 * @param actionEvent
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		
		try {
			LOGGER.info(
					"Start of ManageMessage.sendMail()");
			mail.setSendDate(new Date());
			messageService.save(mail);
			mailList = messageService.findAllMail();
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail send", "Mail send");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			mail = new ComposeMail();
			
			LOGGER.info(
					"End of ManageTemplate.sendMail()");
			
		} catch (Exception e) {
			return manageError(e, "Error while saving Mail. ");
		}
		return "messageList";
	}
	
	/**
	 * when clicking on delete button in the message list page, this method will get called.
	 * 1.get the selected mail and delete mail based on the id
	 * 2.Lists all mail
	 * @param actionEvent
	 * @throws Exception
	 */
	public void delete() throws Exception {
		LOGGER.info(
				"Start of MessageList:delete()");
		try {
			messageService.delete(mail);
			mailList = messageService.findAllMail();
			
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Data is Deleted",
					"Data is Deleted");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
			LOGGER.info(
					"End of MessageList:delete()");	
		} catch (Exception e) {
			manageError(e, "Error while Deleting Mail. ");
		}
	}
	
	/**
	 * This method shall be executed when the user wants to export the emails
	 * @return
	 */
	public String print() {
		LOGGER.info(
				 "Start of ManageMessage:print()");
		try {
			//get root path
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String rootPath = servletContext.getRealPath("");
			mailList = messageService.findAllMail();
			
			//generate jasperprint
			JasperPrint jasperPrint = messageService.messageList(mailList,rootPath);
			String now = new SimpleDateFormat("MM_dd_yyyy").format(new java.util.Date());
			//Export the pdf file for jasperPrint
			JasperExportManager.exportReportToPdfFile(jasperPrint, rootPath	+ "//" + now + ".pdf");
			
			LOGGER.info(
						"End of ManageMessage.print()");
			return "fileDownload";
		} catch (Exception e) {
			return manageError(e, "Error while Exporting Mail. ");
		}
	}
	
	/**
	 * This method is called when user clicks on upload link for uploading the file
	 * @param event
	 * @return
	 */
	public String handleFileUpload(FileUploadEvent event) {
	     
		try {
			LOGGER.info(
					"Start of ManageMessage.handleFileUload()");
			//FacesContext ctx = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String rootPath = servletContext.getRealPath("");
			
			//getting the path from servletContext in which uploaded file need to save
		    //String UploadDirectory = ctx.getExternalContext().getInitParameter("UploadDirectory");
			File result = new File(rootPath +"//reports//"+ event.getFile().getFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(result);
			byte[] buffer = new byte[Constants.BUFFER_SIZE];
			int bulk;
			InputStream inputStream = event.getFile().getInputstream();
			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        LOGGER.info(
					"End of ManageMessage.handleFileUload()"+ event.getFile().getFileName());
	        return "messageList";
			 
		}catch (IOException e) {
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"The files were not uploaded!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
			return manageError(e, "Error while uploading. ");
		}       
	  }
	
	public String logout() throws ServletException {
		LOGGER.info(
				"Start of ManageMessage.logout()");
	    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    request.logout();
	    
	    HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    
	    LOGGER.info(
				"End of ManageMessage.logout()");
	    return "logout"; 
	}

}
