/**
 * 
 */
package genericCheckpointing.util;

/**
 * <p>
 * This class defines an object that will be stored and read from the file.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class MyAllTypesSecond extends SerializableObject {
	double myDoubleT;
	float myFloatT;
	short myShortT;
	char myCharT;

	public MyAllTypesSecond() {

	}

	public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn) {
		myDoubleT = myDoubleTIn;
		myFloatT = myFloatTIn;
		myShortT = myShortTIn;
		myCharT = myCharTIn;
	}

	/**
	 * @return the myDoubleT
	 */
	public double getMyDoubleT() {
		return myDoubleT;
	}

	/**
	 * @param myDoubleT
	 *            the myDoubleT to set
	 */
	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	/**
	 * @return the myFloatT
	 */
	public float getMyFloatT() {
		return myFloatT;
	}

	/**
	 * @param myFloatT
	 *            the myFloatT to set
	 */
	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	/**
	 * @return the myShortT
	 */
	public short getMyShortT() {
		return myShortT;
	}

	/**
	 * @param myShortT
	 *            the myShortT to set
	 */
	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	/**
	 * @return the myCharT
	 */
	public char getMyCharT() {
		return myCharT;
	}

	/**
	 * @param myCharT
	 *            the myCharT to set
	 */
	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		result = prime * result + myShortT;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
				+ ", myCharT=" + myCharT + "]";
	}
}
