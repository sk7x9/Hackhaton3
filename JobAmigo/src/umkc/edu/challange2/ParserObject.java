package umkc.edu.challange2;

public class ParserObject {

	String img;
	String name;
	String desc;
	String title;
	String emp_type;
	String educ;
	String exp;
	String details;
	String loc;
	String pay;
	String did;
	
	public ParserObject(String img, String name, String desc, String title, String emp_type, String educ, String exp,
			String details, String loc, String pay,String did) {
		this.img = img;
		this.name = name;
		this.desc = desc;
		this.title = title;
		this.emp_type = emp_type;
		this.educ = educ;
		this.exp = exp;
		this.details = details;
		this.loc = loc;
		this.pay = pay;
		this.did=did;
	}


	
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getDid() {
		return did;
	}

	public String getTitle() {
		return title;
	}

	public String getEmpType() {
		return emp_type;
	}

	public String getEduc() {
		return educ;
	}
	
	public String getExp() {
		return exp;
	}
	
	public String getPay() {
		return pay;
	}

	public String getImage() {
		return img;
	}



	public CharSequence getloc() {
		// TODO Auto-generated method stub
		return loc;
	}



	public CharSequence getdetails() {
		// TODO Auto-generated method stub
		return details;
	}
}
