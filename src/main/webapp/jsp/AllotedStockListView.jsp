<%@page import="in.co.online.pharmacy.mgt.model.AllotedStockModel"%>
<%@page import="in.co.online.pharmacy.mgt.model.MedicineModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.pharmacy.mgt.bean.AllotedStockBean"%>
<%@page import="in.co.online.pharmacy.mgt.controller.AllotedStockListCtl"%>
<%@page import="in.co.online.pharmacy.mgt.controller.AllotedStockCtl"%>
<%@page import="in.co.online.pharmacy.mgt.controller.MedicineListCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allot Stock List</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
				<div class="banner_content text-left">
					<h2>Allot Stock List</h2>
				</div>
			</div>
		</div>
</section>
<div class="container">
<form action="<%=OPSView.ALLOTED_STOCK_LIST_CTL%>" method="post">
<div class="section-top-border">
				<h3 class="mb-30 title_color">Allot Stock List</h3>
				<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
				<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
				<div class="progress-table-wrap">
				
					<div class="progress-table">
					
					<div class="table-head" style="width: 50%">
							<input type="text" class="form-control" placeholder="Enter Madicine Name" name="name" 
							value="<%=ServletUtility.getParameter("name", request)%>">&nbsp;&nbsp;
							
							<input type="submit" name="operation" class="btn btn-primary" value="<%=AllotedStockListCtl.OP_SEARCH%>" >
						</div>
						<br>
						
						<div class="table-head">
						
							<div class="serial">S.No.</div>
							<div class="visit">Medicine Name</div>
							<div class="visit">Pharmacist Name</div>
							<div class="visit">Quantity</div>
						</div>
						
						<%
													int pageNo = ServletUtility.getPageNo(request);
																									int pageSize = ServletUtility.getPageSize(request);
																									int index = ((pageNo - 1) * pageSize) + 1;
																									
																									
																									AllotedStockBean bean = null;
																									
																									List list = ServletUtility.getList(request);
																									
																									Iterator<AllotedStockBean> it = list.iterator();
																									
																									while (it.hasNext()) {
																										bean = it.next();
												%>
						
						<div class="table-row">
						
							<div class="serial"><%=index++%></div>
							
							<div class="visit"><%=bean.getMedicineName()%></div>
							<div class="visit"><%=bean.getPharmasticName()%></div>
							<div class="visit"><%=bean.getQuantity()%></div>
							
						</div>
						
				<%
											}
										%>	
						<br>
						<div class="table-row">
						<div class="serial"><input type="submit" name="operation" class="btn btn-info"
						value="<%=AllotedStockListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></div>
						
						
						
						
						
						<%
																			AllotedStockModel model = new AllotedStockModel();
																		%>
					
				<div class="serial">	<input type="submit" name="operation" align="right" class="btn btn-info"
						value="<%=AllotedStockListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>>
						</div></div>
						
						<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
						
					</div>
				</div>
			</div>
			</form>
	</div>
<%@ include file="Footer.jsp" %>
</body>
</html>