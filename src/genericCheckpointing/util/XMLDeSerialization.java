package genericCheckpointing.util;

/**
 * <p>
 * This class defines patterns which are used while DeSerialization.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public enum XMLDeSerialization {
	COMPLEX_OBJECT_OPENING_TAG_PATTERN("<complexType xsi:type=\"(.+)\">"), DATA_MEMBER_TAG_PATTERN(
			"<(.+) xsi:type=\"xsd:(.+)\">(.+)</(.+)>");

	private String pattern;

	private XMLDeSerialization(String patternIn) {
		pattern = patternIn;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

}
