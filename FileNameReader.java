package [PACKAGE DIRECTORY];

import java.io.File;

public class FilenameReader {
	
	public static void WriteFilenames(String filepath)
	{
		File folder = new File (filepath);
		if(folder.listFiles() != null)
		{
			File[] listOfFiles = folder.listFiles();
			
			for(File file : listOfFiles)
			{
				if(file.isFile())
				{
					System.out.println(file.getName());
				}
			}
		}
		
	}
	
}
	
