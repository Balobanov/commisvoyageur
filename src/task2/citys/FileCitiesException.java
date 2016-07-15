package task2.citys;

/*
 * Exception if was wrong file format
 */
public class FileCitiesException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 153;
	private String exception;

	public FileCitiesException(String exception) {
		super(exception);
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	@Override
	public String toString() {
		return "FileCitiesException [exception=" + exception + "]";
	}

}