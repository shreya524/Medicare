package in.co.online.pharmacy.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.pharmacy.mgt.bean.BaseBean;
import in.co.online.pharmacy.mgt.bean.RoleBean;
import in.co.online.pharmacy.mgt.bean.UserBean;
import in.co.online.pharmacy.mgt.exception.ApplicationException;
import in.co.online.pharmacy.mgt.exception.DuplicateRecordException;
import in.co.online.pharmacy.mgt.model.UserModel;
import in.co.online.pharmacy.mgt.util.DataUtility;
import in.co.online.pharmacy.mgt.util.DataValidator;
import in.co.online.pharmacy.mgt.util.PropertyReader;
import in.co.online.pharmacy.mgt.util.ServletUtility;

/**
 * Servlet implementation class PharmacistCtl
 */
@WebServlet(name = "PharmacistCtl", urlPatterns = { "/ctl/PharmacistCtl" })
public class PharmacistCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(PharmacistCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("PharmacistCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} 
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;

		} 
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;

		} 
		
		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid","Mobile No"));
			pass=false;
		} 
			log.debug("PharmacistCtl Method validate Ended");
		return pass;
	}
	
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PharmacistCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean.PHARMASTICS);

		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

			bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
	

	
	
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			
			bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			
			bean.setAddress(DataUtility.getString(request.getParameter("address")));
			
			bean.setCity(DataUtility.getString(request.getParameter("city")));
			
			populateDTO(bean, request);
	
			log.debug("PharmacistCtl Method populatebean Ended");
	
			return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PharmacistCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   UserModel model = new UserModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				UserBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("PharmacistCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PharmacistCtl Method doPost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SAVE.equalsIgnoreCase(op)) {
			
			UserBean bean = (UserBean) populateBean(request);
			try {
			//	System.out.println("in try sign up");
				if(id>0) {
					model.update(bean);
					ServletUtility.setSuccessMessage("Pharmacist Successfully Update", request);
				}else {
				
				long pk = model.registerUser(bean);
				bean.setId(pk);
				ServletUtility.setSuccessMessage("Pharmacist Successfully Addded", request);
				}
				request.getSession().setAttribute("UserBean", bean);
				
				ServletUtility.forward(OPSView.PHARMACIST_VIEW, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OPSView.PHARMACIST_VIEW, request, response);
			return;
		}
		log.debug("PharmacistCtl Method doPost Ended");

	}

	@Override
	protected String getView() {
		return OPSView.PHARMACIST_VIEW;
	}

}
