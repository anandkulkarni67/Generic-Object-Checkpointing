package genericCheckpointing.util;

/**
 * <p>
 * This class is used define different modes that the application can handle.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public enum Mode {
	SERDESER("serdeser"), DESER("deser");

	private String mode;

	Mode(String modeIn) {
		mode = modeIn;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * <p>
	 * This method is used validate if a given string has mode associated with
	 * it.
	 * </p>
	 * 
	 * @param mode
	 *            mode value to validated.
	 * @return if a valid mode exists, returns it, otherwise throws
	 *         IllegalArgumentException.
	 * @throws IllegalArgumentException
	 */
	public static Mode getMode(String mode) throws IllegalArgumentException {

		switch (mode) {
		case "serdeser":
			return Mode.SERDESER;
		case "deser":
			return Mode.DESER;
		default:
			throw new IllegalArgumentException("'" + mode + "' mode is not supported.");
		}
	}
}
