package com.entity;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
@Entity
public class Shops implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String Susername;
	private String Sname;
	private String Spassword;
	private String Ssex;
	private String Sphone;
	private String Sshopname;
	private String Slocal;
	
	public Shops() {}
	
	public Shops(String Susername, String Sname, String Spassword,
			String Ssex, String Sphone, String Sshopname, String Slocal) {
		this.Susername = Susername;
		this.Sname = Sname;
		this.Spassword = Spassword;
		this.Ssex = Ssex;
		this.Sphone = Sphone;
		this.Sshopname = Sshopname;
		this.Slocal = Slocal;
	}

	public String getSusername() {
		return Susername;
	}

	public void setSusername(String susername) {
		Susername = susername;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSpassword() {
		return Spassword;
	}

	public void setSpassword(String spassword) {
		Spassword = spassword;
	}

	public String getSsex() {
		return Ssex;
	}

	public void setSsex(String ssex) {
		Ssex = ssex;
	}

	public String getSphone() {
		return Sphone;
	}

	public void setSphone(String sphone) {
		Sphone = sphone;
	}

	public String getSshopname() {
		return Sshopname;
	}

	public void setSshopname(String sshopname) {
		Sshopname = sshopname;
	}

	public String getSlocal() {
		return Slocal;
	}

	public void setSlocal(String slocal) {
		Slocal = slocal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
