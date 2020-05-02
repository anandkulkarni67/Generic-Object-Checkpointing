package genericCheckpointing.util;

/**
 * <p>
 * This class defines patterns which are used while Serialization.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public enum XMLSerialization {
	HEADER_OPENING_TAG("<DPSerialization>", 0), HEADER_CLOSING_TAG("</DPSerialization>",
			0), COMPLEXT_TYPE_OPENING_TAG_PATTERN("<complexType xsi:type=\"%s\">", 1), COMPLEXT_TYPE_CLOSING_TAG(
					"</complexType>", 0), DATA_MEMBER_PATTERN("<%s xsi:type=\"xsd:%s\">%s</%s>", 4);

	private String pattern;
	private int noOfMatches;

	private XMLSerialization(String patternIn, int noOfMatchesIn) {
		pattern = patternIn;
		noOfMatches = noOfMatchesIn;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the noOfMatches
	 */
	public int getNoOfMatches() {
		return noOfMatches;
	}
}
