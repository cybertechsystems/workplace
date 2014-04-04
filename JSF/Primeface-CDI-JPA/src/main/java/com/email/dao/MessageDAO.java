package com.email.dao;

import java.util.List;

import com.email.model.ComposeMail;
import com.email.util.ApplicationException;

public interface MessageDAO {

	/* Is called when the user create the Mail. */
	public void save(ComposeMail mail) throws ApplicationException;
	
	/* Is called for fetching all mail. */
	public List<ComposeMail> loadAllMail() throws Exception;
	
	/* Is called when the user delete the mail. */
	public void deleteMail(ComposeMail mail) throws ApplicationException;

}
