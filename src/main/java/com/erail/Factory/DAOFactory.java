package com.erail.Factory;

import com.erail.database.DAO.IBookingDAO;
import com.erail.database.DAO.IClassTrainDAO;
import com.erail.database.DAO.IEmailConfigDAO;
import com.erail.database.DAO.IPasswordValueDAO;
import com.erail.database.DAO.IReportDAO;
import com.erail.database.DAO.IStationDAO;
import com.erail.database.DAO.ITrainDAO;
import com.erail.database.DAO.ITrainStationDAO;
import com.erail.database.DAO.IUserDAO;
import com.erail.database.DAO.Impl.BookingDAO;
import com.erail.database.DAO.Impl.ClassTrainDAO;
import com.erail.database.DAO.Impl.EmailConfigDAO;
import com.erail.database.DAO.Impl.PasswordValueDAO;
import com.erail.database.DAO.Impl.ReportDAO;
import com.erail.database.DAO.Impl.StationDAO;
import com.erail.database.DAO.Impl.TrainDAO;
import com.erail.database.DAO.Impl.TrainStationDAO;
import com.erail.database.DAO.Impl.UserDAO;

public class DAOFactory implements IDAOFactory {

	@Override
	public IBookingDAO createBookingDAO() {
		return new BookingDAO();
	}

	@Override
	public IClassTrainDAO createClassTrainDAO() {
		return new ClassTrainDAO();
	}

	@Override
	public IEmailConfigDAO createEmailConfigDAO() {
		return new EmailConfigDAO();
	}

	@Override
	public ITrainStationDAO createTrainStationDAO() {
		return new TrainStationDAO();
	}

	@Override
	public IReportDAO createReportDAO() {
		return new ReportDAO();
	}

	@Override
	public ITrainDAO createTrainDAO() {
		return new TrainDAO();
	}

	@Override
	public IStationDAO createStationDAO() {
		return new StationDAO();
	}

	@Override
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}

	@Override
	public IPasswordValueDAO createPasswordValueDAO() {
		return new PasswordValueDAO();
	}
}
