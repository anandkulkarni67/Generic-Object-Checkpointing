package genericCheckpointing.util;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * This class contains the logic to generate random numbers of different types.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class RandomValueGenerator {

	private Random randomValueGenerator = null;
	private int intRange = Integer.MAX_VALUE;
	private short shortRange = Short.MAX_VALUE;

	public RandomValueGenerator() {
		randomValueGenerator = new Random();
	}

	/**
	 * <p>
	 * This method generates a random integer value.
	 * </p>
	 * 
	 * @return a generated value.
	 */
	public int getRandomInteger() {
		return randomValueGenerator.nextInt(intRange);
	}

	/**
	 * <p>
	 * This method generates a random character from 'A' to 'Z'.
	 * </p>
	 * 
	 * @return a generated character.
	 */
	public char getRandomCharacter() {
		char character = (char) (randomValueGenerator.nextInt(26) + 'A');
		return character;
	}

	/**
	 * <p>
	 * This method generates a random boolean value. (either true ot false).
	 * </p>
	 * 
	 * @return a generated value.
	 */
	public boolean getRandomBoolean() {
		return randomValueGenerator.nextBoolean();
	}

	/**
	 * <p>
	 * This method generates a random long value.
	 * </p>
	 * 
	 * @return a generated value.
	 */
	public long getRandomLong() {
		return randomValueGenerator.nextLong();
	}

	/**
	 * <p>
	 * This method generates random double value.
	 * </p>
	 * 
	 * @return a generated value.
	 */
	public double getRandomDouble() {
		return randomValueGenerator.nextDouble();
	}

	/**
	 * <p>
	 * This method generates a random float value.
	 * </p>
	 * 
	 * @return a generated float value.
	 */
	public float getRandomFloat() {
		return randomValueGenerator.nextFloat();
	}

	/**
	 * <p>
	 * This method generates a random String by using a UUID library.
	 * </p>
	 * 
	 * @return a generated string.
	 */
	public String getRandomString() {
		return UUID.randomUUID().toString();
	}

	/**
	 * <p>
	 * This method generates a random short value.
	 * </p>
	 * 
	 * @return a generated value.
	 */
	public short getRandomShort() {
		return (short) randomValueGenerator.nextInt(shortRange);
	}
}
