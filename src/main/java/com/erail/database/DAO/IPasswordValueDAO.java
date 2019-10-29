//Author:Samarth 
package com.erail.database.DAO;

import java.util.List;

import com.erail.models.PasswordRule;

public interface IPasswordValueDAO {
	public List<PasswordRule> getPasswordInfo() throws Exception;
}
