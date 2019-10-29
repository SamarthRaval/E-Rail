package com.erail.Factory;

import com.erail.service.IBookingService;
import com.erail.service.IClassTrainService;
import com.erail.service.IEmailConfigService;
import com.erail.service.IPasswordValueService;
import com.erail.service.IReportService;
import com.erail.service.IStationService;
import com.erail.service.ITrainService;
import com.erail.service.ITrainStationService;
import com.erail.service.IUserService;
import com.erail.service.Impl.BookingService;
import com.erail.service.Impl.ClassTrainService;
import com.erail.service.Impl.EmailConfigService;
import com.erail.service.Impl.PasswordValueService;
import com.erail.service.Impl.ReportService;
import com.erail.service.Impl.StationService;
import com.erail.service.Impl.TrainService;
import com.erail.service.Impl.TrainStationService;
import com.erail.service.Impl.UserService;

public class ServiceFatory implements IServiceFactory {

	@Override
	public IBookingService createBookingService() {
		return new BookingService();
	}

	@Override
	public IClassTrainService createClassTrainService() {
		return new ClassTrainService();
	}

	@Override
	public IEmailConfigService createEmailConfigService() {
		return new EmailConfigService();
	}

	@Override
	public ITrainStationService createTrainStationService() {
		return new TrainStationService();
	}

	@Override
	public IReportService createReportService() {
		return new ReportService();
	}

	@Override
	public ITrainService createTrainService() {
		return new TrainService();
	}

	@Override
	public IStationService createStationService() {
		return new StationService();
	}

	@Override
	public IUserService createUserService() {
		return new UserService();
	}

	@Override
	public IPasswordValueService createPasswordValueService() {
		return new PasswordValueService();
	}

}
