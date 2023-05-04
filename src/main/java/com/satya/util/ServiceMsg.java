package com.satya.util;

public enum ServiceMsg {
	EMPTY_FIELD("Fields Can not be Empty.."),
	REG_SUCCESS("Registered Successfully.."),
	ACCOUNT_EXISTS("An User Already Exists with this Email.."),
	NOUSER_FOUND("Account Not Found With This Email..Please Register First.."),
	LOGIN_SUCCESS("Login Successfull.."),
	INCORRECT_PWD("Incorrect Password.."),
	POST_ADDED("Your Post Has Been Successfully Added.."),
	NOT_LOGGEDIN("Login First To view Your Posts.."),
	LOGGED_IN("Your Are Logged In..");

	private String msg;

	ServiceMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}
}
