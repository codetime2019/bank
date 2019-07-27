package cn.web.pack.exception;

public class MyException extends Exception {

	private String Message = null;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public MyException(String Message) {
		super(Message);
		this.Message = Message;

	}

}
