<%@page import="in.co.online.pharmacy.mgt.controller.AllotedStockCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.pharmacy.mgt.util.DataUtility"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allot Stock</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content text-left">
				<h2>Allot Stock</h2>
				
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
					<form class="form-wrap" action="<%=OPSView.ALLOTED_STOCK_CTL%>"
						method="post">
						
						<h3 class="pb-20 text-center mb-20">Allot Stock</h3>
						<h6 class="pb-20 text-center mb-20" style="color: red"><%=ServletUtility.getErrorMessage(request)%></h6>
						<h6 class="pb-20 text-center mb-20" style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h6>
						
						<jsp:useBean id="bean"
							class="in.co.online.pharmacy.mgt.bean.AllotedStockBean" scope="request"></jsp:useBean>

						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="createdBy"
							value="<%=bean.getCreatedBy()%>"> <input type="hidden"
							name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
							type="hidden" name="createdDatetime"
							value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
						<input type="hidden" name="modifiedDatetime"
							value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
							
							<%
								List pharList=(List)request.getAttribute("pharList");
								List mediList=(List)request.getAttribute("medicineList");
							%>

							<font color="red"> <%=ServletUtility.getErrorMessage("Pharmacist", request)%></font>
						<%=HTMLUtility.getList("Pharmacist", String.valueOf(bean.getPharmasticId()),pharList)%> 
							
							
							<font color="red"> <%=ServletUtility.getErrorMessage("Medicine", request)%></font>
						<%=HTMLUtility.getList("Medicine", String.valueOf(bean.getMedicineId()),mediList)%>
							
							
							
							
							<font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%></font>
							 <input type="text"
							class="form-control" name="quantity" placeholder="Quantity" 
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">
							
							
						
						<div class="text-center">
						<input type="submit" name="operation"
								class="main_btn text-uppercase" value="<%=AllotedStockCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="main_btn text-uppercase"
								value="<%=AllotedStockCtl.OP_RESET%>">
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