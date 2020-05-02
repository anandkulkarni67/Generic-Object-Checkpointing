/**
 * 
 */
package genericCheckpointing.xml;

import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This interface defines methods required to Serialize an object.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface SerStrategyI {

	public String processInput(SerializableObject serializableObject);

}
