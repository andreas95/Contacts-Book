

public class Contact {

	private String firstn;
	private String lastn;
	private String phone;
	private String fix;
	private String mail;
	private String city;
	private String region;
	private String address;
	private String zipcode;
	
	public Contact() {}
	public Contact(String lastn,String firstn,String phone,String fix,String mail,String address,String city,String region,String zipcode) {
		this.lastn=lastn;
		this.firstn=firstn;
		this.phone=phone;
		this.fix=fix;
		this.mail=mail;
		this.address=address;
		this.city=city;
		this.region=region;
		this.zipcode=zipcode;
	}
	public void setFirstn(String firstn) {
		this.firstn=firstn;	}
	public String getFirstn() {
		return firstn;
	}
	public void setLastn(String lastn) {
		this.lastn=lastn;	
	}
	public String getLastn() {
		return lastn;
	}	
	public void setFix(String fix) {
		this.fix=fix;	
	}
	public String getFix() {
		return fix;
	}	
	public void setPhone(String phone) {
		this.phone=phone;	
	}
	public String getPhone() {
		return phone;
	}	
	public void setCity(String city) {
		this.city=city;
	}
	public String getCity() {
		return city;
	}
	public void setRegion(String region) {
		this.region=region;
	}
	public String getRegion() {
		return region;
	}
	public void setZipcode(String zipcode) {
		this.zipcode=zipcode;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setMail(String mail) {
		this.mail=mail;
	}
	public String getMail() {
		return mail;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getAddress() {
		return address;
	}
}
