<%@page import="in.co.online.pharmacy.mgt.model.MedicineModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.pharmacy.mgt.bean.MedicineBean"%>
<%@page import="in.co.online.pharmacy.mgt.controller.IndexCtl"%>
<%@page import="in.co.online.pharmacy.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<%@ include file="jsp/Header.jsp"%>
	<!--================Header Menu Area =================-->

	<!--================ Home Banner Area =================-->
	<section class="home_banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div class="banner_content row">
				<div class="col-lg-12">
					<h1>We Care for Your Health Every Moment</h1>
					<p>If you are looking at blank cassettes on the web, you may be
						very confused at the difference in price You may see some for as
						low as each.</p>
					<a class="main_btn mr-10" href="#">get started</a>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--================ End Home Banner Area =================-->


	<div class="container">
		<form action="<%=OPSView.INDEX_CTL%>" method="post">
			<div class="section-top-border">
				<h3 class="mb-30 title_color">Medicine List</h3>
				<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
				<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
				<div class="progress-table-wrap">

					<div class="progress-table">

						<div class="table-head" style="width: 50%">
							<input type="text" class="form-control" placeholder="Enter  Name"
								name="name"
								value="<%=ServletUtility.getParameter("name", request)%>">&nbsp;&nbsp;

							<input type="submit" name="operation" class="btn btn-primary"
								value="<%=IndexCtl.OP_SEARCH%>">
						</div>
						<br>

						<div class="table-head">
							<div class="serial">Select</div>
							<div class="serial">S.No.</div>
							<div class="visit">Name</div>
							<div class="visit">Catagory</div>
							<div class="visit">Company</div>
							<div class="visit">Price</div>
							<div class="visit">Quantity</div>
							<div class="visit">Description</div>
							<div class="visit">Book</div>
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
							<div class="serial">
								<input type="checkbox" class="case" name="ids"
									value="<%=bean.getId()%>">
							</div>
							<div class="serial"><%=index++%></div>

							<div class="visit"><%=bean.getName()%></div>
							<div class="visit"><%=bean.getCatagry()%></div>
							<div class="visit"><%=bean.getCompany()%></div>
							<div class="visit"><%=bean.getPrice()%></div>
							<div class="visit"><%=bean.getQuantity()%></div>
							<div class="visit"><%=bean.getDescription()%></div>
							<div class="visit">
								<a class="btn btn-info"
									href="<%=OPSView.BOOK_MEDICINE_CTL%>?mdId=<%=bean.getId()%>">Book</a>
							</div>

						</div>

						<%
						}
						%>
						<br>
						<div class="table-row">
							<div class="serial">
								<input type="submit" name="operation" class="btn btn-info"
									value="<%=IndexCtl.OP_PREVIOUS%>"
									<%=(pageNo == 1) ? "disabled" : ""%>>
							</div>


							<%
							MedicineModel model = new MedicineModel();
							%>

							<div class="serial">
								<input type="submit" name="operation" align="right"
									class="btn btn-info" value="<%=IndexCtl.OP_NEXT%>"
									<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>>
							</div>
						</div>

						<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
							type="hidden" name="pageSize" value="<%=pageSize%>">

					</div>
				</div>
			</div>
		</form>
	</div>

	<!--================ start footer Area =================-->
	<%@ include file="jsp/Footer.jsp"%>
</body>
</html>