package [PACKAGE];

import java.text.ParseException;

import org.junit.Test;

import Package.Directory.FilenameDateParser;

public class ParseFileDateTest {

	@Test
	
	public void testParseFileDate() throws ParseException
	{
		final String exampleFileName = "abc_07152015.txt";
		FilenameDateParser.DateParser(exampleFileName);
	}
	
}
