package in.co.online.pharmacy.mgt.bean;

public class MedicineBean extends BaseBean {
	
	
	private String name;
	private String description;
	private String price;
	private String catagry;
	private String company;
	private String quantity;
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCatagry() {
		return catagry;
	}

	public void setCatagry(String catagry) {
		this.catagry = catagry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
		return id+ "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
