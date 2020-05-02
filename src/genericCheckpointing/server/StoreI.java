package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * <p>
 * This interface defines methods for each object to be written to the
 * file.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface StoreI extends StoreRestoreI {

	/**
	 * <p>
	 * This method is used to write object of type MyAllTypesFirst to the file.
	 * </p>
	 * 
	 * @param aRecord
	 *            an object to be written.
	 * @param wireFormat
	 *            a format for serialization.
	 */
	void writeObj(MyAllTypesFirst aRecord, String wireFormat);

	/**
	 * <p>
	 * This method is used to write object of type MyAllTypesSecond to the file.
	 * </p>
	 * 
	 * @param aRecord
	 *            an object to be written.
	 * @param wireFormat
	 *            a format for serialization.
	 */
	void writeObj(MyAllTypesSecond aRecord, String wireFormat);

}
