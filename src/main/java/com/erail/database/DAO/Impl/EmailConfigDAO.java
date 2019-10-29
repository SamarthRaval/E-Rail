//Author: Varun
package com.erail.database.DAO.Impl;

import com.erail.database.DAO.IEmailConfigDAO;
import com.erail.database.DatabaseConfig;
import com.mysql.cj.jdbc.CallableStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailConfigDAO  implements IEmailConfigDAO {

    private static final Logger logger = LogManager.getLogger(EmailConfigDAO.class);

    @Override
    public List<Map<String, Object>> getEmailConfig() {
        try {
            logger.info(" getting email configuration ");
            DatabaseConfig dbConfig = DatabaseConfig.getInstance();
            Connection con = dbConfig.getConnection();
            String SQL = "{call getEmailConfig ()}";
            CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
            List<Map<String,Object>>responseList = new ArrayList<>();
            ResultSet rs = cstmt.executeQuery();

            while(rs.next()){
                Map<String,Object> result = new HashMap<>();
                result.put("emailType", rs.getString("emailType"));
                result.put("email",rs.getString("email"));
                result.put("password", rs.getString("pasword"));
                result.put("body",rs.getString("body"));
                responseList.add(result);

            }
            return  responseList;
        } catch (Exception ex) {
            logger.error(" Error in saving classType");
            ex.printStackTrace();
        }

        return null;
    }
}
