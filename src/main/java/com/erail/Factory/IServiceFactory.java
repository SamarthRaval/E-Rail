package com.erail.Factory;

import com.erail.service.*;

public interface IServiceFactory {

	public IBookingService createBookingService();

	public IClassTrainService createClassTrainService();

	public IEmailConfigService createEmailConfigService();

	public ITrainStationService createTrainStationService();

	public IReportService createReportService();

	public ITrainService createTrainService();

	public IStationService createStationService();

	public IUserService createUserService();

	public IPasswordValueService createPasswordValueService();

}
