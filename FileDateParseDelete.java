package com.ups.ttg.brokerage.tradeability.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class FileDateParseDelete {

	private static Calendar cal = Calendar.getInstance();
	private static DateFormat df = new SimpleDateFormat("MMddyyyy");
	private Date fileDate;
	private String tempDate;
	private static Date limitDate;
	private File[] listOfFIles;
	private int startIndex;
	private int lastIndex;
	private String invalidRenameToTarget;
	private String validRenameToTarget;
	Exception exPrimary = null;
	
	Properties prop = new Properties();
	InputStream input = null;
	
	/*
	 * This method assumes that each filename in a directory has the following
	 * naming conventions:
	 * 
	 * file_MMddyyyy.extension
	 * 
	 * [Example] : abc_01012015.txt
	 */
	
	public void DeleteFIles(String filepath) throws ParseException {
		cal.add(Calendar.DATE, -31);
		limitDate = cal.getTime();
		File folder = new File(filepath);
		if (folder.listFiles().length > 0) {
			listOfFIles = folder.listFiles();
			for (File file : listOfFIles) {
				startIndex = file.getName().lastIndexOf("_") + 1;
				lastIndex = file.getName().lastIndexOf(".");
				if (startIndex > 0 && lastIndex > 0) {
					try
					{
						//TODO: Needs to be made into a relative file path
						input = new FileInputStream("D:\\UPSData\\TradeAbility\\trunk\\tradeability-utils\\src\\main\\resources\\filedateparsedelete.properties");
						prop.load(input);
						validRenameToTarget = prop.getProperty("deleter.valid.renameto.target");
						invalidRenameToTarget = prop.getProperty("deleter.invalid.renameto.target");
					}
					catch (IOException ex)
					{
						ex.printStackTrace();
					}
					
					try
					{
						tempDate = file.getName().substring(startIndex, lastIndex);
						fileDate = df.parse(tempDate);
						df.format(limitDate);
					}
					
					catch(Exception ex)
					{
						exPrimary = ex;
						System.err.println("Caught exception: " + exPrimary.getMessage());
						System.err.println("File " + file.getName()+ " does not have a filename of a valid format.");
						System.err.println("File " + file.getName()+ " has been moved to a different directory.");
						file.renameTo(new File(invalidRenameToTarget + file.getName()));
						continue;
					}
					if (fileDate.compareTo(limitDate) == 0 || fileDate.compareTo(limitDate) < 0) {
						
						
						/**
						 * Use this code if you want the console to only
						 * identify the status of each file * without deleting
						 * or moving it. *
						 */
						System.out.println(file.getName()
								+ " is older than 30 days.");
						
						/**
						 * Use this code if you want outdated files to be
						 * deleted. *
						 */
						
						// System.out.println(file.getName() +
						// " is older than 30 days and will be deleted.");
						// file.delete(); /*Uncomment to test outdated file
						// deletion.*/
						// System.out.println(file.getName() + " was deleted.");

						/**
						 * Use this code if you want outdated files to be moved
						 * into a different directory.* The filepath on line 2
						 * of this code block must be changed as needed.
						 */
						 //System.out.println(file.getName() +
						 //" is older than 30 days and will be moved.");
						 //file.renameTo(new
						 //File(validRenameToTarget
						 //+ file.getName()));
						 //System.out.println(file.getName() + " was moved.");
					}
					
					else if (fileDate.compareTo(limitDate) > 0) {
						System.out.println(file.getName() + "is not out of date.");
					}
				}
			}
		}
		
		else {
			System.out.println("Specified directory is empty.");
		}
		
	}
	
}
