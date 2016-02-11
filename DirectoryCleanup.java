package [Package path goes here];

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.net.ftp.FTPClient;

public class DirectoryCleanup{

	private static Calendar cal = Calendar.getInstance();
	private static DateFormat dfSecondary = new SimpleDateFormat("yyyyMMdd");
	private Date fileDate;
	private String tempDate;
	private static Date limitDate;
	private File[] listOfFiles;
	private int startIndex;
	private int lastIndex;
	Exception exPrimary = null;
	Exception exSecondary = null;
	
	public DirectoryCleanup(){ }
	
	private void establishDate(int retentionLength){
		cal.add(Calendar.DATE, -retentionLength);
		limitDate = cal.getTime();
	}

	public void deleteFiles(String filepath, int retentionLength) throws ParseException, FileNotFoundException {
		establishDate(retentionLength);
		System.out.println("The cutoff date as of today is: " + limitDate.toString());
		File folder = new File(filepath);
		int countedOutdatedFiles = 0;
		int countedNewFiles = 0;
		int countedInvalidFiles = 0;
		if (folder.exists() == true)
		{
			if (folder.listFiles().length > 0) 
			{
				listOfFiles = folder.listFiles();
				for (File file : listOfFiles) {
					startIndex = file.getName().lastIndexOf("_") + 1;
					lastIndex = file.getName().lastIndexOf(".");
					if (startIndex > 0 && lastIndex > 0) {
						try {
							tempDate = file.getName().substring(startIndex, lastIndex);
							fileDate = dfSecondary.parse(tempDate);
							dfSecondary.format(limitDate);
						} catch (Exception ex) {
							exPrimary = ex;
							System.err.println("Caught exception: " + exPrimary.getMessage());
							System.err.println("File " + file.getName() + " does not have a valid filename.");
							System.err.println("File " + file.getName() + " will be ignored.");
							countedInvalidFiles += 1;
							continue;
						}
						if (fileDate.compareTo(limitDate) <= 0) {
							 System.out.println(file.getName() + " is older than 30 days and will be deleted.");
							 file.delete();
							 System.out.println(file.getName() + " was deleted.");
							 countedOutdatedFiles += 1;
						}
						else if (fileDate.compareTo(limitDate) > 0) {
							System.out.println(file.getName() + " is not out of date.");
							countedNewFiles += 1;
						}
					}		
				}
				System.out.println("- - - - - - - - - - - - - - - - - - - -\nWithin the " + filepath.toString() + " directory, there were this many of each file type:");
				System.out.println("Outdated files: " + countedOutdatedFiles);
				System.out.println("Unparsable files: " + countedInvalidFiles);
				System.out.println("Files less than 30 days old: " + countedNewFiles);
			}
			else
			{
				System.out.println("Specified directory is empty.");
			}
		}
		else
		{
			System.out.println("Specified directory does not exist: " + filepath.toString());
		}
	
	}
	
	public void identifyFiles(String filepath, String retentionLength) throws ParseException {
	establishDate(Integer.parseInt(retentionLength));
	System.out.println("The cutoff date as of today is: " + limitDate.toString());
	File folder = new File(filepath);
	System.out.println();
	int countedOutdatedFiles = 0;
	int countedNewFiles = 0;
	int countedInvalidFiles = 0;
	if(folder.exists() == true)
	{
		if (folder.listFiles().length > 0) 
		{
			listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0) {
					try {
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = dfSecondary.parse(tempDate);
						dfSecondary.format(limitDate);
					} catch (Exception ex) {
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						countedInvalidFiles += 1;
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0 || fileDate.compareTo(limitDate) < 0) {
						System.out.println(file.getName() + " is older than 30 days.");
						countedOutdatedFiles += 1;
					}
					else if (fileDate.compareTo(limitDate) > 0) {
						System.out.println(file.getName() + " is not out of date.");
						countedNewFiles += 1;
					}
				}		
			}
			//Report results of scan
			System.out.println("- - - - - - - - - - - - - - - - - - - -\nWithin the " + filepath.toString() + " directory, there were this many of each file type:");
			System.out.println("Outdated files: " + countedOutdatedFiles);
			System.out.println("Unparsable files: " + countedInvalidFiles);
			System.out.println("Files less than 30 days old: " + countedNewFiles);
		}
		else
		{
			System.out.println("Specified directory is empty.");
		}
	}
	else
	{
	System.out.println("Specified directory does not exist: " + filepath.toString());	
	}
}
	
	
	/**Outdated methods, delete later.**/
	/*public void getFTPProperties()
	{
		String ftpPropertiesInput = "Path to properties file goes here";
		Properties prop = new Properties();
		ftpConfigProperties = new FTPConfigProperties();
		InputStream input = null;
		try
		{
			input = new FileInputStream(ftpPropertiesInput);
			prop.load(input);
			ftpConfigProperties.setFtpHostName(prop.getProperty("dps.ftp.hostname"));
			ftpConfigProperties.setFtpPort(prop.getProperty("dps.ftp.port"));
			ftpConfigProperties.setFtpUsername(prop.getProperty("dps.ftp.username"));
			ftpConfigProperties.setFtpPassword(prop.getProperty("dps.ftp.password"));
			ftpConfigProperties.setFtpExtension(prop.getProperty("dps.ftp.file.extension"));
			ftpConfigProperties.setFtpSqlDirPath(prop.getProperty("dps.sql.dirPath"));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}*/
	
	/*public FTPClient getFTPClient(FtpUtils ftpUtils)
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
	/*	public void DeleteFTPFiles (FTPFile[] ftpFiles, FTPClient ftpClient) throws ParseException, IOException
	{
		establishDate();
		System.out.println("The cutoff date as of today is: " + limitDate.toString());
		if(ftpFiles.length > 0)
		{
			for (FTPFile file : ftpFiles)
			{
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0)
				{
					try
					{
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = dfSecondary.parse(tempDate);
						dfSecondary.format(limitDate);
					}
					catch (Exception ex)
					{
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						countedInvalidFiles += 1;
						continue;
					}
					if(fileDate.compareTo(limitDate) == 0 || fileDate.compareTo(limitDate) < 0)
					{
						System.out.println(file.getName() + " is older than 30 days.");
						System.out.println(file.getName() + " will be deleted.");
						fileToDelete = ftpClient.printWorkingDirectory() + "/" + file.getName();
						ftpClient.deleteFile(fileToDelete);
						System.out.println(file.getName() + " was deleted.");
						countedOutdatedFiles += 1;
					}
					else
					{
						System.out.println(file.getName() + " is not out of date.");
						countedNewFiles += 1;
					}
				}
				
			}
			//Report results of scan
			System.out.println("\n- - - - - - - - - - - - - - - - - - - -\nWithin the " + ftpClient.printWorkingDirectory() + " directory: ");
			System.out.println(countedOutdatedFiles + " Outdated files were identified and deleted.");
			System.out.println(countedInvalidFiles + " files with unparsable filenames were found.");
			System.out.println(countedNewFiles + " files were found that are younger than 30 days.");
			countedOutdatedFiles = 0;
			countedInvalidFiles = 0;
			countedNewFiles = 0;
		}
		else
		{
			System.out.println("The target directory is empty.");
		}
		ftpClient.logout();
		System.out.println("Logged out of FTP server.");
	}*/
	
	/*public void identifyFTPFiles (FTPFile[] ftpFiles, FTPClient ftpClient) throws ParseException, IOException
	{
		establishDate();
		System.out.println("The cutoff date as of today is: " + limitDate.toString());
		System.out.println("- - - - - - - - - - - - - - - - - - - -\n");
		if(ftpFiles.length > 0)
		{
			for (FTPFile file : ftpFiles)
			{
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0)
				{
					try
					{
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = dfSecondary.parse(tempDate);
						dfSecondary.format(limitDate);
					} catch (Exception ex) {
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						countedInvalidFiles += 1;
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0 || fileDate.compareTo(limitDate) < 0)
					{
						System.out.println(file.getName() + " is older than 30 days.");
						countedOutdatedFiles += 1;
					}
					else if (fileDate.compareTo(limitDate) > 0)
					{
						System.out.println(file.getName() + " is not out of date.");
						countedNewFiles += 1;
					}
				}
			}
			//Resets calendar pointer to current date after scanning each directory
			cal.add(Calendar.DATE, 31);
			//Report results of scan
			System.out.println("\n- - - - - - - - - - - - - - - - - - - -\nWithin the " + ftpClient.printWorkingDirectory() + " directory, there were this many of each file type:");
			System.out.println("Outdated files: " + countedOutdatedFiles);
			System.out.println("Unparsable files: " + countedInvalidFiles);
			System.out.println("Files less than 30 days old: " + countedNewFiles);
			countedOutdatedFiles = 0;
			countedInvalidFiles = 0;
			countedNewFiles = 0;
		}
		else
		{
			System.out.println("The target directory is empty");
		}
		ftpClient.logout();
		System.out.println("Logged out of FTP server.");
	}*/
}
