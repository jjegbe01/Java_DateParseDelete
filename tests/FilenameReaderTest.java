package [PACKAGE];
import org.junit.Test;

import package.path.FilenameReader;

public class FilenameReaderTest {

	@Test
	
	public void testReadFileNames()
	{
		final String testFilePath = "C:\\TARGET\\DIRECTORY\\GOES\\HERE";
		//final String testFilePath = "C:\\Users\\xbm2kbt\\Documents\\Project Documentation\\Performance Analysis\\June25th";
		FilenameReader.WriteFilenames(testFilePath);
	}
	
	
	
	
}
