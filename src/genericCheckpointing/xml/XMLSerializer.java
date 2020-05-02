package genericCheckpointing.xml;

import static genericCheckpointing.util.XMLSerialization.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import genericCheckpointing.util.SerializableObject;

/**
 * <p>
 * This class contains the logic to serialize the object of type
 * SerializableType.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class XMLSerializer implements SerStrategyI {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * <p>
	 * This class contains the logic to serialize the object of type
	 * SerializableType to a string.
	 * </p>
	 */
	@Override
	public String processInput(SerializableObject serializableObject) {
		try {
			@SuppressWarnings("rawtypes")
			Class cls = serializableObject.getClass();
			PropertyDescriptor pd = null;
			Field[] fields = cls.getDeclaredFields();
			StringBuilder serializedObject = new StringBuilder();
			serializedObject.append(HEADER_OPENING_TAG.getPattern() + LINE_SEPARATOR);
			serializedObject.append(String.format(COMPLEXT_TYPE_OPENING_TAG_PATTERN.getPattern() + LINE_SEPARATOR,
					cls.getCanonicalName()));
			for (Field field : fields) {
				pd = new PropertyDescriptor(field.getName(), serializableObject.getClass());
				Method getterMethod = pd.getReadMethod();
				Object value = getterMethod.invoke(serializableObject);
				if (field.getType().getName().equals("java.lang.String")) {
					serializedObject.append(String.format(DATA_MEMBER_PATTERN.getPattern() + LINE_SEPARATOR,
							field.getName(), "string", value, field.getName()));
				} else {
					serializedObject.append(String.format(DATA_MEMBER_PATTERN.getPattern() + LINE_SEPARATOR,
							field.getName(), field.getType().getName(), value, field.getName()));
				}
			}
			serializedObject.append(COMPLEXT_TYPE_CLOSING_TAG.getPattern() + LINE_SEPARATOR);
			serializedObject.append(HEADER_CLOSING_TAG.getPattern() + LINE_SEPARATOR);
			return serializedObject.toString();
		} catch (IntrospectionException exception) {
			System.err.println("Error occured while accessing a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException exception) {
			System.err.println("Error occured while accessing a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (IllegalArgumentException exception) {
			System.err.println("Error occured while accessing a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException exception) {
			System.err.println("Error occured while invoking a method using reflection.");
			exception.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}