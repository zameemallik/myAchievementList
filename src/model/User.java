package model;

import java.io.Serializable;

//　ユーザーの情報を保持するためのJavaBeansクラス
public class User implements Serializable {
	private String address;
	private String name;
	private String pass;


	public User() {}

	public User(String address, String name, String pass) {
		this.address = address;
		this.name = name;
		this.pass = pass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
