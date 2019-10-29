//Author: Varun
package com.erail.database.DAO.Impl;

import com.erail.database.DAO.IClassTrainDAO;
import com.erail.database.DatabaseConfig;
import com.erail.models.ClassTrain;
import com.mysql.cj.jdbc.CallableStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassTrainDAO implements IClassTrainDAO {
    private static final Logger logger = LogManager.getLogger(ClassTrainDAO.class);

    @Override
    public long save(ClassTrain classTrain) throws Exception {
        logger.info(" in saving classType ");
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        Connection con = dbConfig.getConnection();
        String SQL = "{call createClassTrain (?,?)}";
        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
        cstmt.setString(1, classTrain.getType());
        cstmt.setLong(2, classTrain.getFare());
        long id = cstmt.executeUpdate();
        logger.info("classType saved successfully ");
        return id;

    }

    @Override
    public long update(ClassTrain classTrain) throws Exception {

        logger.info(" in updating classType ");
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        Connection con = dbConfig.getConnection();
        String SQL = "{call updateClassType (?,?,?)}";
        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
        cstmt.setString(1, classTrain.getType());
        cstmt.setLong(2, classTrain.getFare());
        cstmt.setLong(3, classTrain.getId());
        long id = cstmt.executeUpdate();
        logger.info("classType updated successfully ");
        return id;
    }

    @Override
    public List<ClassTrain> getAll() throws Exception {

        logger.info(" get all classType ");
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        Connection con = dbConfig.getConnection();
        String SQL = "{call getAllClassTrain ()}";
        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
        ResultSet rs = cstmt.executeQuery();
        List<ClassTrain> classTrainList = rowMapper(rs);
        return classTrainList;

    }

    @Override
    public ClassTrain getClassTrainByType(String type) throws Exception {
        logger.info(" get  classType by type ");
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        Connection con = dbConfig.getConnection();
        String SQL = "{call getClassTypeByType (?)}";
        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
        cstmt.setString(1, type);
        ResultSet rs = cstmt.executeQuery();
        List<ClassTrain> classTrainList = rowMapper(rs);
        if (classTrainList.size() > 0) {
            logger.info("classType found by type " +type);
            return classTrainList.get(0);
        } else {
            logger.info("classType not found by type " + type);
            return null;
        }


    }

    @Override
    public boolean deleteClassTrain(long id) throws Exception {
        logger.info(" delete classType by id ");
        DatabaseConfig dbConfig = DatabaseConfig.getInstance();
        Connection con = dbConfig.getConnection();
        String SQL = "{call deleteClassType (?)}";
        CallableStatement cstmt = (CallableStatement) con.prepareCall(SQL);
        cstmt.setLong(1, id);
        cstmt.executeQuery();
        logger.info(" deleted successfully classType by id ");
        return true;
    }


    private List<ClassTrain> rowMapper(ResultSet rs) throws Exception {
        List<ClassTrain> classTrainList = new ArrayList<>();
        while (rs.next()) {
            ClassTrain classTrain = new ClassTrain();
            classTrain.setType(rs.getString("type"));
            classTrain.setFare(rs.getLong("fare"));
            classTrain.setId(rs.getLong("id"));
            classTrainList.add(classTrain);
        }
        return classTrainList;
    }
}
