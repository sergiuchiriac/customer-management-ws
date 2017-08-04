package common;

public class APIResponse {

	private Boolean success = null;
	private String errorCode;
	private String message;

	
	public APIResponse() {
	}

	public APIResponse(String errorCode) {
		this.errorCode = errorCode;
	}

	public APIResponse(String errorCode, String message) {
		this.errorCode = errorCode;
		this.setMessage(message);
	}

	public APIResponse(String errorCode, String message, boolean b) {
		this.errorCode = errorCode;
		this.setMessage(message);
		this.setSuccess(b);
	}



	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
