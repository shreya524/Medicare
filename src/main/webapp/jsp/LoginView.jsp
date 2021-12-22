<%@page import="in.co.online.pharmacy.mgt.controller.LoginCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>

<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
				<div class="banner_content text-left">
					<h2>Login</h2>
					
				</div>
			</div>
		</div>
</section>
<section class="appointment-area">
		<div class="container">
			<div class="row justify-content-between align-items-center appointment-wrap">
				<div class="col-lg-6 col-md-6 pt-60 pb-60">
					<div class="appointment-right">
						<form class="form-wrap" action="<%=OPSView.LOGIN_CTL%>" method="post">
							<h3 class="pb-20 text-center mb-20">Login</h3>
							<h6 class="pb-20 text-center mb-20" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h6>
							<h6 class="pb-20 text-center mb-20" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h6>
						
						<jsp:useBean id="bean" class="in.co.online.pharmacy.mgt.bean.UserBean"
            scope="request"></jsp:useBean>
            <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
               <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
							
							<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							<input type="text" class="form-control" name="login" placeholder="Login Id" 
							value="<%=DataUtility.getStringData(bean.getLogin())%>" >
							
							<font
                       			 color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							<input type="password" class="form-control" name="password" placeholder="Password" 
							value="<%=DataUtility.getStringData(bean.getPassword()) %>">
							
						
							<div class="text-center">
								<input type="submit" name="operation" class="main_btn text-uppercase" value="<%=LoginCtl.OP_SIGN_IN %>">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="Footer.jsp" %>
</body>
</html>