package [PACKAGE GOES HERE];

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DirectoryCleanup{

	private static Calendar cal = Calendar.getInstance();
	private static DateFormat df = new SimpleDateFormat("MMddyyyy");
	private Date fileDate;
	private String tempDate;
	private static Date limitDate;
	private File[] listOfFiles;
	private int startIndex;
	private int lastIndex;
	private String invalidRenameToTarget;
	private String validRenameToTarget;
	Exception exPrimary = null;
	Exception exSecondary = null;
	
	Properties prop = new Properties();
	InputStream input = null;

	/*
	 * All methods in this class assume that each filename in a directory has the following naming convention:
	 * file_MMddyyyy.extension
	 * [Example] : abc_01012015.txt
	 */

	private void establishDate()
	{
		cal.add(Calendar.DATE, -31);
		limitDate = cal.getTime();
	}
	//TODO: Externalize the process of retrieving the date from each filename
	
	//Delete's all files in a target directory that are older than 30 days
	public void DeleteFiles(String filepath) throws ParseException, FileNotFoundException {
		establishDate();
		File folder = new File(filepath);
		if (folder.listFiles().length > 0) 
		{
			listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0) {
					try 
					{
						//TODO: Needs to be made into a relative file path
						//Change this before implementation
						input = new FileInputStream("D:\\Properties\\File\\Goes\\Here\\directorycleanup.properties");
						prop.load(input);
						validRenameToTarget = prop.getProperty("deleter.valid.renameto.target");
						invalidRenameToTarget = prop.getProperty("deleter.invalid.renameto.target");
					} 
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
					try {
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = df.parse(tempDate);
						df.format(limitDate);
					} catch (Exception ex) {
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						System.err.println("File " + file.getName() + " has been moved to a different directory.");
						file.renameTo(new File(invalidRenameToTarget + file.getName()));
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0
							|| fileDate.compareTo(limitDate) < 0) {
						 System.out.println(file.getName() + " is older than 30 days and will be deleted.");
						 file.delete();
						 System.out.println(file.getName() + " was deleted.");
					}
					else if (fileDate.compareTo(limitDate) > 0) {
						System.out.println(file.getName() + " is not out of date.");
					}
				}		
			}
		}
		else
		{
			System.out.println("Specified directory is empty.");
		}
	}
	
	//Identifies all files in a target directory that are older than 30 days
	public void identifyFiles(String filepath) throws ParseException {
		establishDate();
		File folder = new File(filepath);
		if (folder.listFiles().length > 0) 
		{
			listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0) {
					try {
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = df.parse(tempDate);
						df.format(limitDate);
					} catch (Exception ex) {
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0
							|| fileDate.compareTo(limitDate) < 0) {
						System.out.println(file.getName() + " is older than 30 days.");
					}
					else if (fileDate.compareTo(limitDate) > 0) {
						System.out.println(file.getName() + " is not out of date.");
					}
				}		
			}
		}
		else
		{
			System.out.println("Specified directory is empty.");
		}
	}
	
	//Moves all files that are older than 30 days from the target directory to another directory
	public void moveFiles(String filepath) throws ParseException {
		establishDate();
		File folder = new File(filepath);
		if (folder.listFiles().length > 0) 
		{
			listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0) {
					try 
					{
						//TODO: Needs to be made into a relative file path
						input = new FileInputStream("D:\\Properties\\File\\Goes\\Here\\directorycleanup.properties");
						prop.load(input);
						validRenameToTarget = prop.getProperty("deleter.valid.renameto.target");
						invalidRenameToTarget = prop.getProperty("deleter.invalid.renameto.target");
					} 
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
					try {
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = df.parse(tempDate);
						df.format(limitDate);
					} catch (Exception ex) {
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName() + " does not have a valid filename.");
						System.err.println("File " + file.getName() + " has been moved to a different directory.");
						file.renameTo(new File(invalidRenameToTarget + file.getName()));
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0
							|| fileDate.compareTo(limitDate) < 0) {
						 System.out.println(file.getName() + " is older than 30 days and will be moved.");
						 file.renameTo(new
						 File(validRenameToTarget + file.getName()));
						 System.out.println(file.getName() + " was moved to ." + validRenameToTarget.toString());
					}
					else if (fileDate.compareTo(limitDate) > 0) {
						System.out.println(file.getName()+ " is not out of date.");
					}
				}		
			}
		}
		else
		{
			System.out.println("Specified directory is empty.");
		}
	}	
}
