//Author:Dhruvi
package com.erail.service.Impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erail.Factory.DAOFactory;
import com.erail.Factory.IDAOFactory;
import com.erail.database.DAO.IReportDAO;
import com.erail.service.IReportService;
@Service
public class ReportService implements IReportService
{

	private IDAOFactory idaoFactory;

	public ReportService(){
		 idaoFactory = new DAOFactory();
	}

	
	@Override
	public Map<String, Integer> getBookingCountByYear(String Year) throws Exception {
		return idaoFactory.createReportDAO().getBookingCountByYear(Year);
	}

	@Override
	public Map<String, Integer> getBookingCountByMonthYear(String Year, String Month) throws Exception{
		return idaoFactory.createReportDAO().getBookingCountByMonthYear(Year,Month);
	}

}