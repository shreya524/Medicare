package in.co.online.pharmacy.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.pharmacy.mgt.bean.BaseBean;
import in.co.online.pharmacy.mgt.bean.BookMedicineBean;
import in.co.online.pharmacy.mgt.bean.MedicineBean;
import in.co.online.pharmacy.mgt.exception.ApplicationException;
import in.co.online.pharmacy.mgt.exception.DuplicateRecordException;
import in.co.online.pharmacy.mgt.model.BookMedicineModel;
import in.co.online.pharmacy.mgt.model.MedicineModel;
import in.co.online.pharmacy.mgt.util.DataUtility;
import in.co.online.pharmacy.mgt.util.DataValidator;
import in.co.online.pharmacy.mgt.util.PropertyReader;
import in.co.online.pharmacy.mgt.util.ServletUtility;

/**
 * Servlet implementation class BookMedicineCtl
 */
@WebServlet(name = "BookMedicineCtl", urlPatterns = { "/BookMedicineCtl" })
public class BookMedicineCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(BookMedicineCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("BookMedicineCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("firstName"))) {
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("emailId"))) {
            request.setAttribute("emailId",
                    PropertyReader.getValue("error.require", "Email Id"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("city"))) {
            request.setAttribute("city",
                    PropertyReader.getValue("error.require", "City"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("address"))) {
            request.setAttribute("address",
                    PropertyReader.getValue("error.require", "Address"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo",
                    PropertyReader.getValue("error.require", "Mobile No"));
            pass = false;
        }

        log.debug("BookMedicineCtl validate method end");
		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookMedicineCtl populateBean method start");
		BookMedicineBean bean=new BookMedicineBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setMedicineId(DataUtility.getLong(request.getParameter("mId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		populateDTO(bean, request);
		log.debug("BookMedicineCtl populateBean method end");
		return bean;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookMedicineCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   BookMedicineModel model = new BookMedicineModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			
			long miId = DataUtility.getLong(request.getParameter("mdId"));
			System.out.println("MdId=========="+miId);
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				BookMedicineBean bean;
				try {
					bean = model.findByPk(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
			
			request.setAttribute("mmId",miId);
			ServletUtility.forward(getView(), request, response);
			log.debug("BookMedicineCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookMedicineCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		BookMedicineModel model=new BookMedicineModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			BookMedicineBean bean=(BookMedicineBean)populateBean(request);
				try {
					if(id>0){
						
					/*model.update(bean);*/
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(OPSView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}
			
		}/*else if (OP_DELETE.equalsIgnoreCase(op)) {
		MedicineBean bean=	(MedicineBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OPSView.MEDICINE_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}*//*else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OPSView, request, response);
			return;
	}*/else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OPSView.BOOK_MEDICINE_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("BookMedicineCtl doPost method end");
	}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OPSView.BOOK_MEDICINE_VIEW;
	}

}
