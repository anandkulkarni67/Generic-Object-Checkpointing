package genericCheckpointing.driver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.Iterator;
import java.util.Vector;

import genericCheckpointing.handler.proxyHandler;
import genericCheckpointing.io.FileProcessor;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.Mode;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.RandomValueGenerator;
import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This class contains the main method.
 * </p>
 * 
 * @author Anand Kulkarni
 */
public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		if (!driver.validateArguments(args)) {
			System.exit(1);
		}
		try {
			int noObjectsOfAKind = Integer.parseInt(args[1]);
			ProxyCreator pc = new ProxyCreator();
			FileProcessorI fileProcessor = new FileProcessor(args[2]);
			InvocationHandler handler = new proxyHandler(fileProcessor);
			StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
					handler);
			switch (Mode.getMode(args[0])) {
			case SERDESER:
				Iterator<SerializableObject> serializedObjects = driver.writeObjects(cpointRef, noObjectsOfAKind);
				Iterator<SerializableObject> deSerializedObjects = driver.readObjects(cpointRef, 2 * noObjectsOfAKind);
				int noMismatchedObjects = 0;
				Vector<SerializableObject> misMatchedObjects = new Vector<SerializableObject>();
				SerializableObject serializedObject = null;
				SerializableObject deSerializedObject = null;
				while (serializedObjects.hasNext() && deSerializedObjects.hasNext()) {
					serializedObject = serializedObjects.next();
					deSerializedObject = deSerializedObjects.next();
					if (!(serializedObject.equals(deSerializedObject))) {
						noMismatchedObjects++;
						misMatchedObjects.add(serializedObject);
					}
				}
				System.out.println(noMismatchedObjects + " mismatched objects found.");
				driver.displaySerializedObjects(misMatchedObjects.iterator());
				break;
			case DESER:
				Iterator<SerializableObject> deserializedObjects = driver.readObjects(cpointRef, noObjectsOfAKind);
				driver.displaySerializedObjects(deserializedObjects);
				break;
			default:
				System.err.println("UnSupported Mode.");
				System.exit(1);
			}
			fileProcessor.close();
		} catch (IllegalArgumentException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * <p>
	 * This method is used to display objects of type SerializableObject.
	 * </p>
	 * 
	 * @param misMatchedObjects
	 *            List of objects to displayed.
	 */
	private void displaySerializedObjects(Iterator<SerializableObject> misMatchedObjects) {
		while (misMatchedObjects.hasNext()) {
			System.out.println(misMatchedObjects.next());
		}
	}

	/**
	 * <p>
	 * This method uses a proxy to write SerializableObjects to the file.
	 * </p>
	 * 
	 * @param cpointRef
	 *            proxy reference used to call write method.
	 * @param noObjectsOfAKind
	 *            No of objects to be written.
	 * @return Iterator of objects written using proxy reference.
	 */
	private Iterator<SerializableObject> writeObjects(StoreRestoreI cpointRef, int noObjectsOfAKind) {
		Vector<SerializableObject> serializedObjects = new Vector<SerializableObject>();
		RandomValueGenerator randomValueGenerator = new RandomValueGenerator();
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;
		for (int i = 0; i < noObjectsOfAKind; i++) {
			myFirst = new MyAllTypesFirst(randomValueGenerator.getRandomInteger(), randomValueGenerator.getRandomLong(),
					randomValueGenerator.getRandomString(), randomValueGenerator.getRandomBoolean());
			mySecond = new MyAllTypesSecond(randomValueGenerator.getRandomDouble(),
					randomValueGenerator.getRandomFloat(), randomValueGenerator.getRandomShort(),
					randomValueGenerator.getRandomCharacter());
			serializedObjects.add(myFirst);
			serializedObjects.add(mySecond);
			((StoreI) cpointRef).writeObj(myFirst, "XML");
			((StoreI) cpointRef).writeObj(mySecond, "XML");
		}
		return serializedObjects.iterator();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param cpointRef
	 * @param noObjectsOfAKind
	 * @return
	 */
	private Iterator<SerializableObject> readObjects(StoreRestoreI cpointRef, int noObjectsOfAKind) {
		Vector<SerializableObject> deSerializedObjects = new Vector<SerializableObject>();
		SerializableObject myRecordRet;
		for (int j = 0; j < noObjectsOfAKind; j++) {
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			deSerializedObjects.add(myRecordRet);
		}
		return deSerializedObjects.iterator();
	}

	/**
	 * <p>
	 * This method is used to validate command line arguments provided from the
	 * command line.
	 * </p>
	 * 
	 * @param args
	 *            array of command line arguments.
	 * @return Returns true if all command arguments are valid, false otherwise.
	 */
	private boolean validateArguments(String[] args) {
		try {
			if (args.length != 3) {
				System.err.println("Please enter valid number of arguments. ");
				System.exit(1);
			}
			Mode.getMode(args[0]);
			int num = Integer.parseInt(args[1]);
			validateFileName(args[2]);
		} catch (NumberFormatException exception) {
			System.err.println("Please enter an argument in valid format. " + exception);
			exception.printStackTrace();
			System.exit(1);
		} catch (IllegalArgumentException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			System.exit(1);
		}
		return true;
	}

	/**
	 * <p>
	 * This method validates the file and creates a new file if it already does
	 * not exists.
	 * </p>
	 * 
	 * @param fileName
	 *            name of the file to be validated.
	 */
	private void validateFileName(String fileName) {
		try {
			File file = new File(fileName);
			file.createNewFile();
		} catch (IOException exception) {
			System.err.println("Error occured while creating an output file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}
}