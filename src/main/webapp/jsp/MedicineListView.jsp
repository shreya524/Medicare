<%@page import="java.util.Iterator"%>
<%@page import="in.co.online.pharmacy.mgt.bean.MedicineBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.pharmacy.mgt.model.MedicineModel"%>
<%@page import="in.co.online.pharmacy.mgt.controller.MedicineListCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicine List</title>

<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
				<div class="banner_content text-left">
					<h2>Medicine List</h2>
				</div>
			</div>
		</div>
</section>
<div class="container">
<form action="<%=OPSView.MEDICINE_LIST_CTL%>" method="post">
<div class="section-top-border">
				<h3 class="mb-30 title_color">Medicine List</h3>
				<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
				<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
				<div class="progress-table-wrap">
				
					<div class="progress-table">
					
					<div class="table-head" style="width: 50%">
							<input type="text" class="form-control" placeholder="Enter  Name" name="name" 
							value="<%=ServletUtility.getParameter("name", request)%>">&nbsp;&nbsp;
							
							<input type="submit" name="operation" class="btn btn-primary" value="<%=MedicineListCtl.OP_SEARCH%>" >
						</div>
						<br>
						
						<div class="table-head">
						<div class="serial">Select</div>
							<div class="serial">S.No.</div>
							<div class="visit">Name</div>
							<div class="visit">Catagry</div>
							<div class="visit">Company</div>
							<div class="visit">Price</div>
							<div class="visit">Quantity</div>
							<div class="visit">Description</div>
							<div class="visit">Edit</div>
						</div>
						
						<%
													int pageNo = ServletUtility.getPageNo(request);
															int pageSize = ServletUtility.getPageSize(request);
															int index = ((pageNo - 1) * pageSize) + 1;
															
															
															MedicineBean bean = null;
															
															List list = ServletUtility.getList(request);
															
															Iterator<MedicineBean> it = list.iterator();
															
															while (it.hasNext()) {
																bean = it.next();
												%>
						
						<div class="table-row">
						<div class="serial"><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"></div>
							<div class="serial"><%=index++%></div>
							
							<div class="visit"><%=bean.getName()%></div>
							<div class="visit"><%=bean.getCatagry()%></div>
							<div class="visit"><%=bean.getCompany()%></div>
							<div class="visit"><%=bean.getPrice()%></div>
							<div class="visit"><%=bean.getQuantity()%></div>
							<div class="visit"><%=bean.getDescription()%></div>
							<div class="visit"><a class="btn btn-info" href="MedicineCtl?id=<%=bean.getId()%>">Edit</a></div>
							
						</div>
						
				<%
											}
										%>	
						<br>
						<div class="table-row">
						<div class="serial"><input type="submit" name="operation" class="btn btn-info"
						value="<%=MedicineListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></div>
						
						
						<div class="serial"><input type="submit" name="operation" class="btn btn-info"
						value="<%=MedicineListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></div>
						
						
						<%
																			MedicineModel model = new MedicineModel();
																		%>
					
				<div class="serial">	<input type="submit" name="operation" align="right" class="btn btn-info"
						value="<%=MedicineListCtl.OP_NEXT%>"
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