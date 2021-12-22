<%@page import="in.co.online.pharmacy.mgt.controller.LoginCtl"%>
<%@page import="in.co.online.pharmacy.mgt.bean.UserBean"%>
<%@page import="in.co.online.pharmacy.mgt.controller.OPSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/OnlinePharmacyMgt/css/bootstrap.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/linericon/style.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/css/font-awesome.min.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/owl-carousel/owl.carousel.min.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/lightbox/simpleLightbox.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/nice-select/css/nice-select.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/animate-css/animate.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/vendors/jquery-ui/jquery-ui.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/css/style.css">
	<link rel="stylesheet" href="/OnlinePharmacyMgt/css/responsive.css">
	<script src="/OnlinePharmacyMgt/js/jquery-3.2.1.min.js"></script>
	<script src="/OnlinePharmacyMgt/js/popper.js"></script>
	<script src="/OnlinePharmacyMgt/js/bootstrap.min.js"></script>
	<script src="/OnlinePharmacyMgt/vendors/nice-select/js/jquery.nice-select.min.js"></script>
	<script src="/OnlinePharmacyMgt/vendors/isotope/isotope-min.js"></script>
	<script src="/OnlinePharmacyMgt/vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="/OnlinePharmacyMgt/js/jquery.ajaxchimp.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"></script>
	<script src="/OnlinePharmacyMgt/js/mail-script.js"></script>
	<script src="/OnlinePharmacyMgt/js/custom.js"></script>
</head>
<body>
<header class="header_area">

<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="<%=OPSView.WELCOME_CTL%>">
						Online Pharmacy Management System
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
					 aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<div class="row ml-0 w-100">
							<div class="col-lg-12 pr-0">
								<ul class="nav navbar-nav center_nav pull-right">
									<li class="nav-item active">
										<a class="nav-link" href="<%=OPSView.WELCOME_CTL%>">Home</a>
									</li>
									
									
									 <%
      						 			 if (userLoggedIn) {
  									  %>
  									  
  									  <%if(userBean.getRoleId()==1){ %>
									<li class="nav-item submenu dropdown">
										<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pharmacist</a>
										<ul class="dropdown-menu">
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.PHARMACIST_LIST_CTL%>">View Pharmacist</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.PHARMACIST_CTL%>">Add Pharmacist</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.ALLOTED_STOCK_CTL%>">Allot Stock</a>
											</li>
										</ul>
									</li>
									<li class="nav-item submenu dropdown">
										<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Medicine</a>
										<ul class="dropdown-menu">
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.MEDICINE_CTL%>">Add Medicine</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.MEDICINE_LIST_CTL%>">View Stock</a>
											</li>
										</ul>
									</li>
									<%}else if(userBean.getRoleId()==2){%>
									
										<li class="nav-item submenu dropdown">
										<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Stock Detail</a>
										<ul class="dropdown-menu">
											<li class="nav-item active">
										<a class="nav-link" href="<%=OPSView.ALLOTED_STOCK_LIST_CTL%>">Alloted Stock</a>
									</li>
								
										</ul>
									</li>
									
									<%}else if(userBean.getRoleId()==3){ %>
									
									<%} %>
									
									<li class="nav-item submenu dropdown">
										<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=welcomeMsg%></a>
										<ul class="dropdown-menu">
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.MY_PROFILE_CTL%>">My Profile</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="<%=OPSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
											</li>
										</ul>
									</li>
									
									<%}else{	
									%>
									
									<li class="nav-item ">
										<a class="nav-link" href="<%=OPSView.INDEX_CTL%>">Book Medicine</a>
									</li>
									<li class="nav-item ">
										<a class="nav-link" href="<%=OPSView.LOGIN_CTL%>">SignIn</a>
									</li>
									
									<%} %>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</header>
</body>
</html>