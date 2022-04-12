package com.etg.gateway.common;

public final class Constants {

	public static final String JSON_API = "json_api";
	public static final String XML_API = "xml_api";
	public static final String UTC = "UTC";

	public static final String DUPLICATED_REQUEST_MESSAGE = "This request has been already excecuted !";
	public static final String NO_RESOURCE_FOUND_MESSAGE = "No resourse found for  ";
	public static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error! ";
	public static final String CONTACT_SERVICE_MESSAGE = "Please contact the service";
	public static final String SCHEDULED_ERROR_MESSAGE = "Exception in @Scheduled task! ";
	public static final String CLIENT_ID_NOT_NULL_MESSAGE = "Client id can not be empty !";
	public static final String REQUEST_ID_NOT_NULL_MESSAGE = "Request id can not be empty !";
	public static final String PERIOD_POSITIVE_ERROR_MESSAGE = "The period represents hours, it's value must be greater or equal to 0 !";
	public static final String CURRENCY_NOT_SUPPORTED_MESSAGE = "The specified currency is not supported";

	public static final String CURRENCY_REGEX = "^[A-Z]{3}$";

	private Constants() {
	}
}
