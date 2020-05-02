package genericCheckpointing.xml;

import java.util.List;

import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This interface defines methods required to DeSerialize an object.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface DeSerStrategyI {
	/**
	 * <p>
	 * This method reads the object from the file and de-serializes it to a
	 * SerializableObject.
	 * </p>
	 * 
	 * @param lines
	 *            List of lines containing the object from the file.
	 * @return A DeSerialized object.
	 */
	public SerializableObject processInput(List<String> lines);
}
