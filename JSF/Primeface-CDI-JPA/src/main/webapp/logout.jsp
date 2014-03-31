<% if(session!=null){                    
	session.invalidate(); %>

<% } else{ %>
            Logged Out Successfully....
<% }%>

<% response.sendRedirect("/CDI"); %>