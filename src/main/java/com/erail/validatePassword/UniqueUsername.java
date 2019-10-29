//Author:Samarth
package com.erail.validatePassword;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.erail.database.DAO.IUserDAO;
import com.erail.database.DAO.Impl.UserDAO;

public class UniqueUsername {

	IUserDAO iUserDAO;

	public boolean uniqueUsername(String username) throws Exception {
		iUserDAO = new UserDAO();

		List<String> usernames = iUserDAO.getAllUsername();

		for (int i = 0; i < usernames.size(); i++) {
			if (usernames.get(i).equals(username)) {
				return false;
			}
		}
		return true;
	}

	public boolean validateUsername(String username) {

		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(username);
		return matcher.find();

	}
}
