package com.erail.service;

import java.util.List;

import com.erail.models.PasswordRule;

public interface IPasswordValueService {

	public List<PasswordRule> getPasswordInfo() throws Exception;
}
