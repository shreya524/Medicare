package in.co.online.pharmacy.mgt.bean;

public class AllotedStockBean extends BaseBean {
	
	
	private long allotId;
	private long pharmasticId;
	private String pharmasticName;
	private long medicineId;
	private String medicineName;
	private String quantity;
	
	
	
	
	

	public long getAllotId() {
		return allotId;
	}

	public void setAllotId(long allotId) {
		this.allotId = allotId;
	}

	public long getPharmasticId() {
		return pharmasticId;
	}

	public void setPharmasticId(long pharmasticId) {
		this.pharmasticId = pharmasticId;
	}

	public String getPharmasticName() {
		return pharmasticName;
	}

	public void setPharmasticName(String pharmasticName) {
		this.pharmasticName = pharmasticName;
	}

	public long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
