package genericCheckpointing.xml;

import static genericCheckpointing.util.XMLDeSerialization.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.driver.FileProcessorI;
import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This class contains the logic to deSerialize the XML file into java objects
 * of type SerializableObject.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class XMLDeSerializer implements DeSerStrategyI {

	public XMLDeSerializer() {

	}

	public XMLDeSerializer(FileProcessorI fileProcessor) {
		super();
	}

	/**
	 * <p>
	 * This method is used to process the list of strings and de-serialize them
	 * into a java object.
	 * </p>
	 */
	@Override
	public SerializableObject processInput(final List<String> lines) {
		try {
			final Pattern complexObjectPattern = Pattern.compile(COMPLEX_OBJECT_OPENING_TAG_PATTERN.getPattern());
			final Pattern dataMemberTagPattern = Pattern.compile(DATA_MEMBER_TAG_PATTERN.getPattern());
			final Matcher complexObjectPatternMatcher = complexObjectPattern.matcher(lines.get(1));
			Method setterMethod = null;
			PropertyDescriptor dataMemberDescriptor = null;
			complexObjectPatternMatcher.find();
			String className = complexObjectPatternMatcher.group(1);
			@SuppressWarnings("rawtypes")
			Class cls = Class.forName(className);
			SerializableObject deSerializedObject = (SerializableObject) cls.newInstance();
			for (int i = 2; i < lines.size() - 1; i++) {
				final Matcher dataMemberTagPatternMatcher = dataMemberTagPattern.matcher(lines.get(i));
				dataMemberTagPatternMatcher.find();
				dataMemberDescriptor = new PropertyDescriptor(dataMemberTagPatternMatcher.group(1),
						deSerializedObject.getClass());
				setterMethod = dataMemberDescriptor.getWriteMethod();
				switch (dataMemberTagPatternMatcher.group(2)) {
				case "int":
					setterMethod.invoke(deSerializedObject, Integer.parseInt(dataMemberTagPatternMatcher.group(3)));
					break;
				case "long":
					setterMethod.invoke(deSerializedObject, Long.parseLong(dataMemberTagPatternMatcher.group(3)));
					break;
				case "double":
					setterMethod.invoke(deSerializedObject, Double.parseDouble(dataMemberTagPatternMatcher.group(3)));
					break;
				case "short":
					setterMethod.invoke(deSerializedObject, Short.parseShort(dataMemberTagPatternMatcher.group(3)));
					break;
				case "float":
					setterMethod.invoke(deSerializedObject, Float.parseFloat(dataMemberTagPatternMatcher.group(3)));
					break;
				case "boolean":
					setterMethod.invoke(deSerializedObject, Boolean.parseBoolean(dataMemberTagPatternMatcher.group(3)));
					break;
				case "char":
					setterMethod.invoke(deSerializedObject,
							Character.valueOf(dataMemberTagPatternMatcher.group(3).charAt(0)));
					break;
				case "string":
					setterMethod.invoke(deSerializedObject, dataMemberTagPatternMatcher.group(3));
					break;
				}
			}
			return deSerializedObject;
		} catch (IntrospectionException exception) {
			System.err.println("Error occured while accessing/invoking a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			System.err.println("Error occured while accessing/invoking a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (InstantiationException exception) {
			System.err.println("Error occured while accessing a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException exception) {
			System.err.println("Error occured while loading a class.");
			exception.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
