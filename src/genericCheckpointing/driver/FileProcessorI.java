package genericCheckpointing.driver;

/**
 * <p>
 * This interface declares all methods required to read and write contents to a file.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface FileProcessorI {

	/**
	 * <p>
	 * This method reads a part of the file (a line or block depending on the
	 * implementation).
	 * </p>
	 * 
	 * @return String content or null if EOF is reached.
	 */
	public String read();

	/**
	 * /**
	 * <p>
	 * This method writes text contents to the file.
	 * </p>
	 * 
	 * @param serializedContents
	 *            content to be written to the file.
	 */
	public void write(String serializedContents);

	/**
	 * <p>
	 * This method is used to close the input and/or output resources.
	 * </p>
	 */
	public void close();

}
