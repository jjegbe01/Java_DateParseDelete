package [PACKAGE DIRECTORY GOES HERE];

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class FilenameDateParser {

	private static Calendar calOne = Calendar.getInstance();
	private static Calendar calTwo = Calendar.getInstance();
	private static DateFormat df = new SimpleDateFormat("MMddyyyy");
	
	private static Date today;
	private static Date fileDate;
	private static String tempDate;
	private static Date limitDate;
	
	/*This method assumes that each filename in a directory
	 * has the following naming conventions:
	 * 
	 * file_MMDDYYYY.extension
	 * 
	 * [Example] : abc_01012015.txt
	 * 
	 * */
	public static void DateParser (String fileName) throws ParseException
	{
		//Set todays date
		today = calOne.getTime();
		//Set calTwo to point at the date 31 days from today
		calTwo.add(Calendar.DATE, -31);
		//Set limitDate equal to calTwo
		limitDate = calTwo.getTime();
		
		int startIndex = fileName.lastIndexOf("_") + 1;
		int lastIndex = fileName.lastIndexOf(".");
		
		tempDate = fileName.substring(startIndex, lastIndex);
		fileDate = df.parse(tempDate);
		
		System.out.println("TODAYS unformatted date is: " + today);
		System.out.println("TODAYS formatted date is: " + df.format(today));
		System.out.println("The unparsed FILENAME date string is: " + tempDate);
		System.out.println("The unformatted parsed FILENAME date string is: " + fileDate);
		System.out.println("The formatted parsed FILENAME date string is: " + df.format(fileDate));
		System.out.println("The unformatted date 31 days ago was: " + limitDate );
		System.out.println("The formatted date 31 days ago was: " + df.format(limitDate));
	}
	
}
