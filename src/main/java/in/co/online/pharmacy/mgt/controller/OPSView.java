package in.co.online.pharmacy.mgt.controller;

public interface OPSView {
	
	public String APP_CONTEXT = "/OnlinePharmacyMgt";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	public String PHARMACIST_VIEW = PAGE_FOLDER + "/PharmacistView.jsp";	
	public String PHARMACIST_LIST_VIEW = PAGE_FOLDER + "/PharmacistListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String MEDICINE_VIEW = PAGE_FOLDER + "/MedicineView.jsp";	
	public String MEDICINE_LIST_VIEW = PAGE_FOLDER + "/MedicineListView.jsp";
	
	public String ALLOTED_STOCK_VIEW = PAGE_FOLDER + "/AllotedStockView.jsp";	
	public String ALLOTED_STOCK_LIST_VIEW = PAGE_FOLDER + "/AllotedStockListView.jsp";
		
	
	public String BOOK_MEDICINE_VIEW = PAGE_FOLDER + "/BookMedicineView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	public String INDEX_VIEW ="/index.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	
	public String ALLOTED_STOCK_CTL = APP_CONTEXT + "/ctl/AllotedStockCtl";
	public String ALLOTED_STOCK_LIST_CTL = APP_CONTEXT + "/ctl/AllotedStockListCtl";
	
	public String PHARMACIST_CTL = APP_CONTEXT + "/ctl/PharmacistCtl";
	public String PHARMACIST_LIST_CTL = APP_CONTEXT + "/ctl/PharmacistListCtl";
	
	public String MEDICINE_CTL = APP_CONTEXT + "/ctl/MedicineCtl";
	public String MEDICINE_LIST_CTL = APP_CONTEXT + "/ctl/MedicineListCtl";
	
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String INDEX_CTL = APP_CONTEXT + "/IndexCtl";
	
	public String BOOK_MEDICINE_CTL = APP_CONTEXT + "/BookMedicineCtl";

}
