package com.dao;

public interface UserOperator {
	public Object getByUsername(String username);
	public Object getByUsernameAndPassword(String username, String password);
}
