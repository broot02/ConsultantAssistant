package root.consultantassistant.model;

import java.io.Serializable;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;


@Service
public class Consultant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6768420118396747444L;
	private String firstName;
	private String lastName;
	private String userId;
	private String address;
	private int zip;
	private String city;
	private String state;
	private String email;
	private String phoneNumber;
	private String password;
	public Consultant(){			
	}
	
	public Consultant(final String id, final String password){
		setUserId(userId);
		setPassword(password);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(final String address) {
		this.address = address;
	}

	public int getZip() {
		return zip;
	}
	
	public void setZip(final int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(final String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	public void setState(final String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		EmailValidator ev = EmailValidator.getInstance();
		if(ev.isValid(email)){
			this.email=email;
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(final String phoneNumber) {
		if(phoneNumber.matches("\\([0-9]{3}\\)[0-9]{3}\\-[0-9]{4}")){
			this.phoneNumber = phoneNumber;			
		}
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		if(password.length() > 5){
			this.password = password;
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if(obj instanceof Consultant)
		{
			Consultant cons = (Consultant)obj;
			
			return(firstName.equals(cons.firstName) && lastName.equals(cons.lastName) && zip == cons.zip &&
					userId.equals(cons.userId) && address.equals(cons.address) && city.equals(cons.city) && state.equals(cons.state)
					&& email.equals(cons.email) && phoneNumber.equals(cons.phoneNumber) && password.equals(cons.password));
		}
		return false;
	}
	
	

}
