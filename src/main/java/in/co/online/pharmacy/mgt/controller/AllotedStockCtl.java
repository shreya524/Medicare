package in.co.online.pharmacy.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.pharmacy.mgt.bean.AllotedStockBean;
import in.co.online.pharmacy.mgt.bean.BaseBean;
import in.co.online.pharmacy.mgt.bean.MedicineBean;
import in.co.online.pharmacy.mgt.bean.UserBean;
import in.co.online.pharmacy.mgt.exception.ApplicationException;
import in.co.online.pharmacy.mgt.exception.DuplicateRecordException;
import in.co.online.pharmacy.mgt.model.AllotedStockModel;
import in.co.online.pharmacy.mgt.model.MedicineModel;
import in.co.online.pharmacy.mgt.model.UserModel;
import in.co.online.pharmacy.mgt.util.DataUtility;
import in.co.online.pharmacy.mgt.util.DataValidator;
import in.co.online.pharmacy.mgt.util.PropertyReader;
import in.co.online.pharmacy.mgt.util.ServletUtility;

/**
 * Servlet implementation class AllotedStockCtl
 */
@WebServlet(name="AllotedStockCtl",urlPatterns={"/ctl/AllotedStockCtl"})
public class AllotedStockCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(AllotedStockCtl.class);
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("AllotedStockCtl preload method start");
		UserModel model = new UserModel();
		UserBean bean=new UserBean();
		MedicineModel mModel=new MedicineModel();
		try {
			bean.setRoleId(2L);
			List l = model.search(bean);
			System.out.println("Pharmastic List======="+l);
			List l2=mModel.list();
			request.setAttribute("pharList", l);
			request.setAttribute("medicineList", l2);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("AllotedStockCtl preload method end");
	}
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("AllotedStockCtl validate method start");
        boolean pass = true;
        
        System.out.println(request.getParameter("Pharmacist"));

        if ("-----Select Pharmacist -----".equalsIgnoreCase(request.getParameter("Pharmacist"))) {
			request.setAttribute("Pharmacist",
					PropertyReader.getValue("error.require", "Pharmacist"));
			pass = false;
		}
        
        System.out.println(request.getParameter("Madicine"));
        
        if ("-----Select Medicine -----".equalsIgnoreCase(request.getParameter("Medicine"))) {
			request.setAttribute("Medicine",
					PropertyReader.getValue("error.require", "Medicine"));
			pass = false;
		}
        
        if (DataValidator.isNull(request.getParameter("quantity"))) {
            request.setAttribute("quantity",
                    PropertyReader.getValue("error.require", "Quantity"));
            pass = false;
        }

        

        log.debug("AllotedStockCtl validate method end");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("AllotedStockCtl populateBean method start");
		AllotedStockBean bean=new AllotedStockBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPharmasticId(DataUtility.getLong(request.getParameter("Pharmacist")));
		bean.setMedicineId(DataUtility.getLong(request.getParameter("Medicine")));
		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
		populateDTO(bean, request);
		log.debug("AllotedStockCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AllotedStockCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   AllotedStockModel model = new AllotedStockModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				AllotedStockBean bean;
				try {
					bean = model.findByPk(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("AllotedStockCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AllotedStockCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		AllotedStockModel model=new AllotedStockModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			AllotedStockBean bean=(AllotedStockBean)populateBean(request);
			
			
			
				try {
					
					if(DataUtility.getLong(new MedicineModel().findByPk(bean.getMedicineId()).getQuantity())<DataUtility.getLong(bean.getQuantity())) {
						ServletUtility.setErrorMessage("Stock is not available please enter less quantity", request);
						ServletUtility.setBean(bean, request);
						ServletUtility.forward(OPSView.ALLOTED_STOCK_VIEW, request, response);
						return;
					}
					
					if(id>0){
						
					model.update(bean);
					MedicineBean mBean=new MedicineModel().findByPk(bean.getMedicineId());
					mBean.setQuantity(String.valueOf(DataUtility.getLong(mBean.getQuantity())-DataUtility.getLong(bean.getQuantity())));
					new MedicineModel().update(mBean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						MedicineBean mBean=new MedicineModel().findByPk(bean.getMedicineId());
						mBean.setQuantity(String.valueOf(DataUtility.getLong(mBean.getQuantity())-DataUtility.getLong(bean.getQuantity())));
						new MedicineModel().update(mBean);
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
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			AllotedStockBean bean=	(AllotedStockBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OPSView.ALLOTED_STOCK_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OPSView.ALLOTED_STOCK_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OPSView.ALLOTED_STOCK_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("AllotedStockCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OPSView.ALLOTED_STOCK_VIEW;
	}

}
