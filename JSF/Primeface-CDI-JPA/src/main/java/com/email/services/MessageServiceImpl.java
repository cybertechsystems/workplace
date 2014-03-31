package com.email.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.log4j.Logger;

import com.email.dao.MessageDAO;
import com.email.model.ComposeMail;

@Named("messageService")
public class MessageServiceImpl implements MessageService {
	
	private Logger LOGGER = Logger.getLogger("com.email.services.MessageServiceImpl");
	
	@Inject
	private MessageDAO messageDAO;
	
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public void save(ComposeMail mail) throws Exception {
		messageDAO.save(mail);
	}
	
	public List<ComposeMail> findAllMail()  throws Exception{
		return messageDAO.loadAllMail();
	}
	
	public void delete(ComposeMail mail)  throws Exception{
		messageDAO.deleteMail(mail);
	}
	
	/**
	 * This method is user to generate jasperprint from messageList
	 */
	@Override
	public JasperPrint messageList(List<ComposeMail> mailList, String rootPath)
		throws Exception {
		LOGGER.info("============================================"+
				"Start of MessageServiceImpl:messageList()");
		JasperPrint jasperPrint = null;
		if (mailList.size() > 0) {
			Collection<Map<String, Object>> data = getData(mailList);
			JRMapCollectionDataSource jrDataSource = new JRMapCollectionDataSource(data);
			if (jrDataSource.getData().size() > 0) {
				JasperReport report = JasperCompileManager
					.compileReport(rootPath+"//reports//messageReport.jrxml");
				jasperPrint = JasperFillManager.fillReport(report,
						new HashMap(), jrDataSource);
			}
		}
		LOGGER.info("============================================"+
				"End of MessageServiceImpl:messageList()");
		return jasperPrint;
		
	}
	/**
	 * 
	 * @param mailList
	 * @return memberCollection
	 */
	public Collection<Map<String, Object>> getData(List<ComposeMail> mailList) {
		LOGGER.info("============================================"+
				"Start of MessageServiceImpl:getData()");
		Collection<Map<String, Object>> memberCollection = new ArrayList<Map<String, Object>>();
		for (int x = 0; x < mailList.size(); x++) {
			Map<String, Object> messageMap = new HashMap<String, Object>();
			messageMap.put("sendDate",mailList.get(x).getSendDate());
			messageMap.put("message",mailList.get(x).getMessage());
			messageMap.put("toAddress",mailList.get(x).getToAddress());
			memberCollection.add(messageMap);
		}
		LOGGER.info("============================================"+
				"End of MessageServiceImpl:getData()");
		return memberCollection;
	}

}
