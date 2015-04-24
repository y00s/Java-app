package myHelpFunc;

public class myCasts {
	
	static public String charStoString(CharSequence c)
	{
		final StringBuilder sb = new StringBuilder(c.length());
		sb.append(c);
		return sb.toString();
	}

}
