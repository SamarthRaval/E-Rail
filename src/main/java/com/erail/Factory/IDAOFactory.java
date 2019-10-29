package com.erail.Factory;

import com.erail.database.DAO.*;

public interface IDAOFactory {

	public IBookingDAO createBookingDAO();

	public IClassTrainDAO createClassTrainDAO();

	public IEmailConfigDAO createEmailConfigDAO();

	public IReportDAO createReportDAO();

	public ITrainDAO createTrainDAO();

	public IStationDAO createStationDAO();

	public IUserDAO createUserDAO();

	public ITrainStationDAO createTrainStationDAO();

	public IPasswordValueDAO createPasswordValueDAO();
}
