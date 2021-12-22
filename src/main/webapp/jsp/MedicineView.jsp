<%@page import="in.co.online.pharmacy.mgt.controller.MedicineCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicine</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content text-left">
				<h2>Medicine</h2>
				
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
					<form class="form-wrap" action="<%=OPSView.MEDICINE_CTL%>"
						method="post">
						
						<h3 class="pb-20 text-center mb-20">Add Medicine</h3>
						<h6 class="pb-20 text-center mb-20" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 class="pb-20 text-center mb-20" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h6>
						
						<jsp:useBean id="bean"
							class="in.co.online.pharmacy.mgt.bean.MedicineBean" scope="request"></jsp:useBean>

						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

							<font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
						 <input type="text" class="form-control"
							name="name" placeholder="Drug Name" value="<%=DataUtility.getStringData(bean.getName())%>"> 
							
							
							<font color="red"> <%=ServletUtility.getErrorMessage("catagry", request)%></font>
							<input type="text" class="form-control" name="catagry"
							placeholder="Catagry" value="<%=DataUtility.getStringData(bean.getCatagry())%>">
							
							<font color="red"> <%=ServletUtility.getErrorMessage("company", request)%></font>
							<input
							type="text" class="form-control" name="company"
							placeholder="Company" value="<%=DataUtility.getStringData(bean.getCompany())%>">
							
							
							
							
							<font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%></font>
							 <input type="text"
							class="form-control" name="quantity" placeholder="Quantity" 
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">
							
							<font color="red"> <%=ServletUtility.getErrorMessage("price", request)%></font>
							<input type="text" class="form-control"
							name="price" placeholder="Price" value="<%=DataUtility.getStringData(bean.getPrice())%>">
							
							
						<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
						<textarea name="description" class="" rows="3" placeholder="description"> <%=DataUtility.getStringData(bean.getDescription() )%></textarea>
						
						<div class="text-center">
						<input type="submit" name="operation"
								class="main_btn text-uppercase" value="<%=MedicineCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="main_btn text-uppercase"
								value="<%=MedicineCtl.OP_RESET%>">
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