<%@page import="in.co.online.pharmacy.mgt.controller.PharmacistCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pharmacist</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content text-left">
				<h2>Pharmacist</h2>
				
			</div>
		</div>
	</div>
	</section>
	<section class="appointment-area">
	<div class="container">
		<div
			class="row justify-content-between align-items-center appointment-wrap">
			<div class="col-lg-6 col-md-6 pt-60 pb-60">
				<div class="appointment-right">
					<form class="form-wrap" action="<%=OPSView.PHARMACIST_CTL%>"
						method="post">
						
						<h3 class="pb-20 text-center mb-20">Add Pharmacist</h3>
						<h6 class="pb-20 text-center mb-20" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 class="pb-20 text-center mb-20" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h6>
						
						<jsp:useBean id="bean"
							class="in.co.online.pharmacy.mgt.bean.UserBean" scope="request"></jsp:useBean>

						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

							<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
						 <input type="text" class="form-control"
							name="firstName" placeholder="First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>"> 
							
							
							<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
							<input type="text" class="form-control" name="lastName"
							placeholder="Last Name" value="<%=DataUtility.getStringData(bean.getLastName())%>">
							
							<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
							<input
							type="text" class="form-control" name="login"
							placeholder="Email Id" value="<%=DataUtility.getStringData(bean.getLogin())%>">
							
							<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							 <input
							type="password" class="form-control" name="password"
							placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword())%>"> 
							
							
							
							
							<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
							<input id="datepicker1" name="dob"
							class="dates form-control" placeholder="Date of Birth"
							type="text" value="<%=DataUtility.getDateString(bean.getDob())%>"> 
							
							<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
							 <input type="text"
							class="form-control" name="mobile" placeholder="Mobile No." 
							value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
							
							<font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
							<input type="text" class="form-control"
							name="city" placeholder="City" value="<%=DataUtility.getStringData(bean.getCity())%>">
							
							
						<font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
						<textarea name="address" class="" rows="3" placeholder="Address"> <%=DataUtility.getStringData(bean.getAddress())%></textarea>
						
						<div class="text-center">
						<input type="submit" name="operation"
								class="main_btn text-uppercase" value="<%=PharmacistCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="main_btn text-uppercase"
								value="<%=PharmacistCtl.OP_RESET%>">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>