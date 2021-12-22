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
import in.co.online.pharmacy.mgt.bean.UserBean;
import in.co.online.pharmacy.mgt.exception.ApplicationException;
import in.co.online.pharmacy.mgt.model.AllotedStockModel;
import in.co.online.pharmacy.mgt.model.UserModel;
import in.co.online.pharmacy.mgt.util.DataUtility;
import in.co.online.pharmacy.mgt.util.PropertyReader;
import in.co.online.pharmacy.mgt.util.ServletUtility;

/**
 * Servlet implementation class AllotedStockList
 */
@WebServlet(name = "AllotedStockListCtl", urlPatterns = { "/ctl/AllotedStockListCtl" })
public class AllotedStockListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(AllotedStockListCtl.class);
    
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("AllotedStockList populateBean method start");
		AllotedStockBean bean = new AllotedStockBean();

		bean.setMedicineName(DataUtility.getString(request.getParameter("name")));

		bean.setPharmasticName(DataUtility.getString(request.getParameter("pharmacist")));

		log.debug("AllotedStockList populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AllotedStockListCtl doGet Start");
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		AllotedStockBean bean = (AllotedStockBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list

		String[] ids = request.getParameterValues("ids");

		AllotedStockModel model = new AllotedStockModel();
		try {
			
			UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setPharmasticId(uBean.getId());
			}
			
			list = model.search(bean, pageNo, pageSize);

			
		
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("AllotedStockListCtl doPOst End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
log.debug("AllotedStockListCtl doPost Start");
		
		
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		AllotedStockBean bean = (AllotedStockBean) populateBean(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		
		String[] ids = request.getParameterValues("ids");
		
		AllotedStockModel model = new AllotedStockModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OPSView.ALLOTED_STOCK_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					AllotedStockBean deletebean = new AllotedStockBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OPSView.ALLOTED_STOCK_LIST_CTL, request, response);
				return;

			}
			
			
			UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setPharmasticId(uBean.getId());
			}
			list = model.search(bean, pageNo, pageSize);
			
			
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("AllotedStockListCtl doGet End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OPSView.ALLOTED_STOCK_LIST_VIEW;
	}

}
