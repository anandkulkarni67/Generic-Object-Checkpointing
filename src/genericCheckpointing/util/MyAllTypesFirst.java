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
public class MyAllTypesFirst extends SerializableObject {
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;

	public MyAllTypesFirst() {

	}

	public MyAllTypesFirst(int myIntIn, long myLongIn, String myStringIn, boolean myBoolIn) {
		myInt = myIntIn;
		myLong = myLongIn;
		myString = myStringIn;
		myBool = myBoolIn;
	}

	/**
	 * @return the myInt
	 */
	public int getMyInt() {
		return myInt;
	}

	/**
	 * @param myInt
	 *            the myInt to set
	 */
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	/**
	 * @return the myLong
	 */
	public long getMyLong() {
		return myLong;
	}

	/**
	 * @param myLong
	 *            the myLong to set
	 */
	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	/**
	 * @return the myString
	 */
	public String getMyString() {
		return myString;
	}

	/**
	 * @param myString
	 *            the myString to set
	 */
	public void setMyString(String myString) {
		this.myString = myString;
	}

	/**
	 * @return the myBool
	 */
	public boolean isMyBool() {
		return myBool;
	}

	/**
	 * @param myBool
	 *            the myBool to set
	 */
	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
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
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		if (myBool != other.myBool)
			return false;
		if (myInt != other.myInt)
			return false;
		if (myLong != other.myLong)
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myString=" + myString + ", myBool="
				+ myBool + "]";
	}
}
