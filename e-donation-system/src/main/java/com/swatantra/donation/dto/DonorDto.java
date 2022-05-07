package com.swatantra.donation.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class DonorDto {

	private Long donarId;
	
	@NotNull
	private Long ngoId;

	@NotBlank
	@Length(min = 5, max = 30)
	private String donarName;

	@NotBlank
	@Length(min = 5, max = 30)
	private String userName;

	@NotBlank
	@Length(min = 3, max = 25)
	private String password;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@Min(value = 1000000000L)
	@Max(value = 9999999999L)
	private Long phoneNumber;

	@NotBlank
	@Length(min = 5, max = 100)
	private String address;

	public Long getDonarId() {
		return donarId;
	}

	public void setDonarId(Long donarId) {
		this.donarId = donarId;
	}

	public String getDonarName() {
		return donarName;
	}

	public void setDonarName(String donarName) {
		this.donarName = donarName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getNgoId() {
		return ngoId;
	}

	public void setNgoId(Long ngoId) {
		this.ngoId = ngoId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, donarId, donarName, email, ngoId, password, phoneNumber, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonorDto other = (DonorDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(donarId, other.donarId)
				&& Objects.equals(donarName, other.donarName) && Objects.equals(email, other.email)
				&& Objects.equals(ngoId, other.ngoId) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(userName, other.userName);
	}

}
