package task2.citys;

/*
 * Exception if was wrong read cities from file
 */
public class DijkstraCitiesException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 134143112L;
	private String exception;

	public DijkstraCitiesException(String exception) {
		super(exception);
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	@Override
	public String toString() {
		return "DijkstraCitiesException [exception=" + exception + "]";
	}

}