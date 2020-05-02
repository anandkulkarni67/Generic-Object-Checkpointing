/**
 * 
 */
package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This interface defines all methods used to read objects from a file.
 * <p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface RestoreI extends StoreRestoreI {

	/**
	 * <p>
	 * This method is used to read objects from a file.
	 * </p>
	 * 
	 * @param wireFormat
	 *            a format in which an object should be written.
	 * @return returns an object read from the file.
	 */
	SerializableObject readObj(String wireFormat);
}
