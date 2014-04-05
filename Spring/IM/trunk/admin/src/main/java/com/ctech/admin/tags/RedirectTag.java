package com.ctech.admin.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RedirectTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletResponse response = (HttpServletResponse) pageContext
				.getResponse();
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

}
