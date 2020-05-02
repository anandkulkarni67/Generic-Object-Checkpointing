/**
 * 
 */
package genericCheckpointing.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import genericCheckpointing.driver.FileProcessorI;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xml.DeSerStrategyI;
import genericCheckpointing.xml.SerStrategyI;
import genericCheckpointing.xml.XMLDeSerializer;
import genericCheckpointing.xml.XMLSerializer;

/**
 * <p>
 * This class acts as a proxy handler and intercepts calls to methods from
 * StoreRestoreI interface and provides the implementation for these methods.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class proxyHandler implements InvocationHandler {

	private FileProcessorI fileProcessor;

	public proxyHandler(FileProcessorI fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}

	/**
	 * <p>
	 * Provides an implementation to invoke method from InvocationHandler class.
	 * </p>
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		switch (methodName) {
		case "writeObj":
			Object object = args[0];
			if (object instanceof SerializableObject) {
				SerializableObject serializableObject = (SerializableObject) object;
				String serializedObject = serializeData(serializableObject, new XMLSerializer());
				fileProcessor.write(serializedObject);
			} else {
				System.err.println("Type : " + object.getClass().getName() + " is not serializable.");
				System.exit(1);
			}
			break;
		case "readObj":
			String line = null;
			List<String> lines = new ArrayList<String>();
			while (((line = fileProcessor.read()) != null) && !line.trim().equals("</DPSerialization>")) {
				lines.add(line);
			}
			if (!lines.isEmpty()) {
				return deSerializeObject(lines, new XMLDeSerializer());
			}
			break;
		default:
			System.err.println("'" + methodName + "' Method not supported yet");
			System.exit(1);
		}
		return null;
	}

	/**
	 * <p>
	 * This method is used to apply the strategy pattern serialize an object.
	 * </p>
	 * 
	 * @param serializableObject
	 *            object to be serialized.
	 * @param serStrategy
	 *            strategy to be used for serialization.
	 * @return serialized object.
	 */
	private String serializeData(SerializableObject serializableObject, SerStrategyI serStrategy) {
		return serStrategy.processInput(serializableObject);
	}

	/**
	 * <p>
	 * This method is used to apply the strategy pattern deSerialize an object.
	 * </p>
	 * 
	 * @param lines
	 *            string contents to be deSerialized.
	 * @param deSerializer
	 *            concrete implementation to be used for deSerialization.
	 * @return a deSerialized object.
	 */
	private SerializableObject deSerializeObject(List<String> lines, DeSerStrategyI deSerializer) {
		return deSerializer.processInput(lines);
	}
}
