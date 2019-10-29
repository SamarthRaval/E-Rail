//Author:Varun
package com.erail.service.Impl;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.service.IEmailConfigService;

import java.util.List;
import java.util.Map;

public class EmailConfigService implements IEmailConfigService {

    private IDAOFactory idaoFactory;

    public EmailConfigService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public List<Map<String, Object>> getEmailConfig() {
        return idaoFactory.createEmailConfigDAO().getEmailConfig();
    }
}
