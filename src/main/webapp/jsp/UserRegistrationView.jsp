<%@page import="in.co.online.pharmacy.mgt.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@page import="in.co.online.pharmacy.mgt.controller.OPSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content text-left">
				<h2>Registration</h2>
				<div class="page_link">
					<a href="index.html">Home</a> <a href="about.html">About</a>
				</div>
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
					<form class="form-wrap" action="<%=OPSView.USER_REGISTRATION_CTL%>"
						method="post">
						
						<h3 class="pb-20 text-center mb-20">User Registration</h3>
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


						<label>First Name</label> <input type="text" class="form-control"
							name="firstName" placeholder="First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>"> 
							<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
							
							<label>Last	Name</label> 
							<input type="text" class="form-control" name="lastName"
							placeholder="Last Name" value="<%=DataUtility.getStringData(bean.getLastName())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
							
							 <label>Email Id</label> <input
							type="text" class="form-control" name="login"
							placeholder="Email Id" value="<%=DataUtility.getStringData(bean.getLogin())%>">
							
							 <label>Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword())%>"> 
							<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							
							<label>Confirm Password</label> <input
							type="password" class="form-control" name="confirmPassword"
							placeholder="Confirm Password" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							
							<label>Date Of	Birth</label> 
							<input id="datepicker1" name="dob"
							class="dates form-control" placeholder="Date of Birth"
							type="text" value="<%=DataUtility.getDateString(bean.getDob())%>"> 
							<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
							
							<label>Mobile No.</label> <input type="text"
							class="form-control" name="mobile" placeholder="Mobile No." 
							value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
							
							<label>City</label> <input type="text" class="form-control"
							name="city" placeholder="City" value="<%=DataUtility.getStringData(bean.getCity())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
							
						<label>Address</label>
						<textarea name="address" class="" rows="3" placeholder="Address"> <%=DataUtility.getStringData(bean.getAddress())%></textarea>
						
						<div class="text-center">
						<input type="submit" name="operation"
								class="main_btn text-uppercase" value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
								type="submit" name="operation" class="main_btn text-uppercase"
								value="<%=UserRegistrationCtl.OP_RESET%>">
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