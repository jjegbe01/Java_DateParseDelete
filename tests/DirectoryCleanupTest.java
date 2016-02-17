package [PACKAGE NAME];

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.Test;

import [Package name];
import [Package name];

public class DirectoryCleanupTest {
	
	/*FTPConfigProperties ftpConfigProperties;
	FTPClient ftpClient = null;
	String targetFilePath = null;
	DirectoryCleanup dirClean = new DirectoryCleanup();
	FTPFile[] listOfFiles;*/
	
	String filepath = "[EXAMPLE FILE PATH]";
	String prefixDPSOne = "Prefix1";
	String prefixDPSTwo = "Prefix2";
	String prefixDPSThree = "Prefix3";
	String prefixDPSFour = "Prefix4";
	String prefixCurrency = "Prefix5";
	String retentionLength = "[Enter file retention length here]";
	
	@Test
	public void testFileIdentifyDelete() throws ParseException
	{
		DirectoryCleanup dc = new DirectoryCleanup();
		/*dc.deleteFiles(dc.identifyFiles(filepath, retentionLength, prefixDPSOne));
		dc.deleteFiles(dc.identifyFiles(filepath, retentionLength, prefixDPSThree));
		dc.deleteFiles(dc.identifyFiles(filepath, retentionLength, prefixDPSTwo));
		dc.deleteFiles(dc.identifyFiles(filepath, retentionLength, prefixDPSFour));
		dc.deleteFiles(dc.identifyFiles(filepath, retentionLength, prefixCurrency));*/
		
		dc.listFileArray(dc.identifyFiles(filepath, retentionLength, prefixDPSOne));
		dc.listFileArray(dc.identifyFiles(filepath, retentionLength, prefixDPSThree));
		dc.listFileArray(dc.identifyFiles(filepath, retentionLength, prefixDPSTwo));
		dc.listFileArray(dc.identifyFiles(filepath, retentionLength, prefixDPSFour));
		dc.listFileArray(dc.identifyFiles(filepath, retentionLength, prefixCurrency));
	}
	
	/*@Test
	public void testPrefixCheck() throws ParseException
	{
		String filenameDPSOne = "filename.txt";
		String filenameDPSTwo = "filename.txt";
		String filenameDPSThree = "filename.txt";
		String filenameDPSFour = "filename.txt";
		String filenameCurrency = "filename.txt";
		String filenameMiscOne = "filename.txt";
		String filenameMiscTwo = "filename.txt";
		
		String prefixDPSOne = "Prefix";
		String prefixDPSTwo = "Prefix";
		String prefixDPSThree = "Prefix";
		String prefixDPSFour = "Prefix";
		String prefixCurrency = "Prefix";
		
		String[] listOfFilenames = new String[7];
		listOfFilenames[0] = filenameDPSOne;
		listOfFilenames[1] = filenameDPSTwo;
		listOfFilenames[2] = filenameDPSThree;
		listOfFilenames[3] = filenameDPSFour;
		listOfFilenames[4] = filenameCurrency;
		listOfFilenames[5] = filenameMiscOne;
		listOfFilenames[6] = filenameMiscTwo;
		
		FilenameParserUtilities filenameParseUtils = new FilenameParserUtilities();
		for (String filename : listOfFilenames)
		{
			System.out.println(filenameParseUtils.PrefixCheck(filename, prefixDPSOne));
			System.out.println(filenameParseUtils.PrefixCheck(filename, prefixDPSTwo));
			System.out.println(filenameParseUtils.PrefixCheck(filename, prefixDPSThree));
			System.out.println(filenameParseUtils.PrefixCheck(filename, prefixDPSFour));
			System.out.println(filenameParseUtils.PrefixCheck(filename, prefixCurrency));
		}
		
		
	}*/
	
	/*@Test
	public void testFileDateParseDelete() throws ParseException, FileNotFoundException
	{
		//Replace with a directory containing test files
		
		final String exampleFilePath = "filepath";
		DirectoryCleanup fdOne = new DirectoryCleanup();
		//fdOne.DeleteFiles(exampleFilePath);
		fdOne.identifyFiles(exampleFilePath, "-31");
		//fdOne.moveFiles(exampleFilePath);
	}*/
	
	
	/*@Test
	public void testDirectoryDetection()
	{
		File invalidDir = new File("filepath");
		File outdatedDir = new File("filepath");
		if(!invalidDir.isDirectory())
		{
			System.out.println("The Invalid folder does not exist in that directory.");
			invalidDir.mkdir();
			System.out.println("A new directory for invalid files has been created at " + invalidDir.toString());
			
		}
		else
		{
			System.out.println("The Invalid folder already exists in that directory.");
		}
		
		if(!outdatedDir.isDirectory())
		{
			System.out.println("The Outdated folder does not exist in that directory.");
			outdatedDir.mkdir();
			System.out.println("A new directory for outdated files has been created at " + outdatedDir.toString());
			
		}
		else
		{
			System.out.println("The Outdated folder already exists in that directory.");
		}
	}*/
	
	/*@Test
	public void testDirectoryReading()
	{
		
		File fileOne = new File("filepath");
		File fileTwo = new File("filepath");
		
		String absolutePathOne = fileOne.getAbsolutePath();
		String absolutePathTwo = fileTwo.getAbsolutePath();
		
		System.out.println(absolutePathOne);
		System.out.println(absolutePathTwo);
	}*/
	
	/*private void getFTPProperties()
	{
		String ftpPropertiesInput = "filepath";
		Properties prop = new Properties();
		ftpConfigProperties = new FTPConfigProperties();
		InputStream input = null;
		try
		{
			
			input = new FileInputStream(ftpPropertiesInput);
			prop.load(input);
		  //Call get methods here
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}*/
	
	/*private FTPClient getFTPClient(FtpUtils ftpUtils)
	{
		getFTPProperties();
		try
		{
			String hostname = ftpConfigProperties.getFtpHostName();
			int port = Integer.parseInt(ftpConfigProperties.getFtpPort());
			String username = ftpConfigProperties.getFtpUsername();
			String password = ftpConfigProperties.getFtpPassword();
			if (port != 21)
			{
				port = 21;
			}
			ftpClient = ftpUtils.connect(hostname, port, username, password);
			ftpClient.setRemoteVerificationEnabled(false);
			ftpClient.enterLocalPassiveMode();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return ftpClient;
	}*/
	
	/*@Test
	public void testFTPProperties()
	{
		getFTPProperties();
		System.out.println(ftpConfigProperties.getFtpHostName().toString());
		System.out.println(ftpConfigProperties.getFtpPort().toString());
		System.out.println(ftpConfigProperties.getFtpUsername().toString());
		System.out.println(ftpConfigProperties.getFtpPassword().toString());
		System.out.println(ftpConfigProperties.getFtpExtension());
		System.out.println(ftpConfigProperties.getFtpSqlDirPath().toString());
	}*/
	
	
	/*@Test
	public void testFTPConnection()
	{	
		FtpUtils ftpUtils = new FtpUtils();
		getFTPClient(ftpUtils);
		try {
			System.out.println("Successfully logged into FTP server");
			//Move to specified directory before scanning
			ftpClient.changeWorkingDirectory("/DPS-WW");
			System.out.println("The current working directory is: " + ftpClient.printWorkingDirectory());
			//System.out.println(ftpClient.listFiles());
			listOfFiles = ftpClient.listFiles();
			try 
			{
				dirClean.identifyFTPFiles(listOfFiles, ftpClient);
				//dirClean.DeleteFTPFiles(listOfFiles, ftpClient);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			//Connect to and scan the RR Directory
			ftpClient.changeWorkingDirectory("/");
			System.out.println(ftpClient.printWorkingDirectory());
			ftpClient.changeWorkingDirectory("/DPS-RR");
			System.out.println(ftpClient.printWorkingDirectory());
			listOfFiles = ftpClient.listFiles();
			try
			{
				dirClean.identifyFTPFiles(listOfFiles, ftpClient);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
