<%@page import="in.co.online.pharmacy.mgt.controller.BookMedicineCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Medicine</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content text-left">
				<h2>Book Medicine</h2>
				<div class="page_link">
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
					<form class="form-wrap" action="<%=OPSView.BOOK_MEDICINE_CTL%>"
						method="post">
						
						<h3 class="pb-20 text-center mb-20">Book Medicine</h3>
						<h6 class="pb-20 text-center mb-20" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 class="pb-20 text-center mb-20" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h6>
						
						<jsp:useBean id="bean"
							class="in.co.online.pharmacy.mgt.bean.BookMedicineBean" scope="request"></jsp:useBean>
						
						
						<% Long midd=(Long)request.getAttribute("mmId"); %>
						
						<input type="hidden" name="mId" value="<%=midd%>">
						
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
							type="text" class="form-control" name="emailId"
							placeholder="Email Id" value="<%=DataUtility.getStringData(bean.getEmailId())%>">
							
							
							<label>Mobile No.</label> <input type="text"
							class="form-control" name="mobileNo" placeholder="Mobile No." 
							value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
							
							<label>City</label> <input type="text" class="form-control"
							name="city" placeholder="City" value="<%=DataUtility.getStringData(bean.getCity())%>">
							<font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
							
							<label>Address</label>
							<textarea name="address" class="" rows="3" placeholder="Address"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
						
						<div class="text-center">
						<input type="submit" name="operation"
								class="main_btn text-uppercase" value="<%=BookMedicineCtl.OP_SAVE%>"> 
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