package in.co.online.pharmacy.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.pharmacy.mgt.bean.AllotedStockBean;
import in.co.online.pharmacy.mgt.bean.MedicineBean;
import in.co.online.pharmacy.mgt.bean.UserBean;
import in.co.online.pharmacy.mgt.exception.ApplicationException;
import in.co.online.pharmacy.mgt.exception.DatabaseException;
import in.co.online.pharmacy.mgt.exception.DuplicateRecordException;
import in.co.online.pharmacy.mgt.util.JDBCDataSource;

public class AllotedStockModel {
	
	private static Logger log = Logger.getLogger(AllotedStockModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM P_Allot");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public Integer nextAllotId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(AllotId) FROM P_Allot");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public AllotedStockBean findByPk(long id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_Allot WHERE id=?");
		AllotedStockBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AllotedStockBean();
				bean.setId(rs.getLong(1));
				bean.setAllotId(rs.getLong(2));
				bean.setPharmasticId(rs.getLong(3));
				bean.setPharmasticName(rs.getString(4));
				bean.setMedicineId(rs.getLong(5));
				bean.setMedicineName(rs.getString(6));
				bean.setQuantity(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	public AllotedStockBean findByAllotId(long id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM P_Allot WHERE AllotId=?");
		AllotedStockBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AllotedStockBean();
				bean.setId(rs.getLong(1));
				bean.setAllotId(rs.getLong(2));
				bean.setPharmasticId(rs.getLong(3));
				bean.setPharmasticName(rs.getString(4));
				bean.setMedicineId(rs.getLong(5));
				bean.setMedicineName(rs.getString(6));
				bean.setQuantity(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	
	public long add(AllotedStockBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getPharmasticId());
		
		MedicineModel mModel=new MedicineModel();
		MedicineBean mBean=mModel.findByPk(bean.getMedicineId());
		
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO P_Allot VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,nextAllotId());
			pstmt.setLong(3, bean.getPharmasticId());
			pstmt.setString(4, uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(5,bean.getMedicineId());
			pstmt.setString(6, mBean.getName());
			pstmt.setString(7, bean.getQuantity());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	public void delete(AllotedStockBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM P_Allot WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	
	public void update(AllotedStockBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getPharmasticId());
		
		MedicineModel mModel=new MedicineModel();
		MedicineBean mBean=mModel.findByPk(bean.getMedicineId());
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE P_Allot SET AllotId=?,pharmasticId=?,pharmasticName=?,MadicineId=?,madicineName=?,quantity=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1,bean.getAllotId());
			pstmt.setLong(2, bean.getPharmasticId());
			pstmt.setString(3, uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(4,bean.getMedicineId());
			pstmt.setString(5, mBean.getName());
			pstmt.setString(6, bean.getQuantity());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(AllotedStockBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search Role with pagination
     * 
     * @return list : List of Roles
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List search(AllotedStockBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM P_Allot WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getAllotId() > 0) {
                sql.append(" AND Allotid = " + bean.getAllotId());
            }
            if (bean.getPharmasticId() > 0) {
                sql.append(" AND getPharmasticId = " + bean.getPharmasticId());
            }
            if (bean.getPharmasticName() != null && bean.getPharmasticName().length() > 0) {
				sql.append(" AND PHARMASTICNAME LIKE '" + bean.getPharmasticName() + "%'");
            }
            if (bean.getMedicineName() != null
                    && bean.getMedicineName().length() > 0) {
				sql.append(" AND MEDICINENAME LIKE '" + bean.getMedicineName()
                        + "%'");
            }
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new AllotedStockBean();
                bean.setId(rs.getLong(1));
				bean.setAllotId(rs.getLong(2));
				bean.setPharmasticId(rs.getLong(3));
				bean.setPharmasticName(rs.getString(4));
				bean.setMedicineId(rs.getLong(5));
				bean.setMedicineName(rs.getString(6));
				bean.setQuantity(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model search End");
        return list;
    }
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from P_Allot");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AllotedStockBean bean = new AllotedStockBean();
                bean.setId(rs.getLong(1));
                bean.setId(rs.getLong(1));
				bean.setAllotId(rs.getLong(2));
				bean.setPharmasticId(rs.getLong(3));
				bean.setPharmasticName(rs.getString(4));
				bean.setMedicineId(rs.getLong(5));
				bean.setMedicineName(rs.getString(6));
				bean.setQuantity(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
