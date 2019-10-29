//Author:Samarth
package com.erail.service.Impl;

import java.util.List;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.models.PasswordRule;
import com.erail.service.IPasswordValueService;

public class PasswordValueService implements IPasswordValueService {

	private IDAOFactory idaoFactory;

	public PasswordValueService() {
		idaoFactory = new DAOFactory();
	}

	@Override
	public List<PasswordRule> getPasswordInfo() throws Exception {
		return idaoFactory.createPasswordValueDAO().getPasswordInfo();
	}

}
