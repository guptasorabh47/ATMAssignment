package ie.assignment.exception;

public class AtmException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100000L;

	public AtmException() {
		super();

	}

	public AtmException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

	public AtmException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public AtmException(String arg0) {
		super(arg0);

	}

	public AtmException(Throwable arg0) {
		super(arg0);

	}

}
