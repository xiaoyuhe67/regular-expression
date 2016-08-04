package regularexpression;

import java.io.Console;
import java.io.File;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RegexTestHarness {
	
	public static HashSet<String> filenames=new HashSet<String>();
	
	public static HashMap<String,String> filepath=new HashMap<String,String>();

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		if (console == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		while (true) {

			System.out.println("Enter your regex: ");
			Pattern pattern = Pattern.compile(console.nextLine());
			
			System.out.println("Enter your starting path: ");
			String startingpath=console.nextLine();
			
			walkin(new File(startingpath));	
			
			for(String a:filenames)
			{
				Matcher matcher = pattern.matcher(a);
				
				boolean found = false;
				while (matcher.find()) {
					System.out.printf("I found the text %s starting at " + "index %d and ending at index %d\n",
							matcher.group(), matcher.start(), matcher.end());
					
					System.out.println("FileName:" +a);
					System.out.println("Filepath:" +filepath.get(a));
					found = true;
				}
				if (!found) {
					System.out.println("No match found.");
				}
				
				
			}
			

			
		}
	}
	
	public static void walkin(File dir)
	{
		
		File[] listFile=dir.listFiles();
		
		if(listFile!=null)
		{
			for(int i=0;i<listFile.length;i++)
			{
				
				if(listFile[i].isDirectory())
				{
					//System.out.println("Directory: "+listFile[i].getPath());
					//System.out.println("Directory: "+listFile[i].getName());
					
					filenames.add(listFile[i].getName());
					
					filepath.put(listFile[i].getName(), listFile[i].getPath());
					walkin(listFile[i]);
				}
				else
				{
					//System.out.println("           "+listFile[i].getPath());
					
					//System.out.println("Directory: "+listFile[i].getName());
					
					filepath.put(listFile[i].getName(), listFile[i].getPath());
					
					filenames.add(listFile[i].getName());
				}
			}
		}
	}

	public static void RegexExamples() {
		String any = ".*";

		String test1 = "Hello", test2 = "29", test3 = "";

		System.out.println(test1.matches(any));
		System.out.println(test2.matches(any));
		System.out.println(test3.matches(any));

		System.out.println();

		String almostAny = ".+";

		System.out.println(test1.matches(almostAny));
		System.out.println(test2.matches(almostAny));
		System.out.println(test3.matches(almostAny));

		System.out.println();

		String numbersOnly = "\\d*"; // Disallow empty strings by swapping * for
										// +

		System.out.println(test1.matches(numbersOnly));
		System.out.println(test2.matches(numbersOnly));
		System.out.println(test3.matches(numbersOnly));

		System.out.println();

		String colorOptions = "(red|yellow|green)";

		System.out.println("red".matches(colorOptions));
		System.out.println("blue".matches(colorOptions));

		System.out.println();

		String eitherGray = "gr(a|e)y";

		System.out.println("gray".matches(eitherGray));
		System.out.println("grey".matches(eitherGray));
		System.out.println("griy".matches(eitherGray));

		System.out.println();

		String shoe = "shoe(s)?";

		System.out.println("shoe".matches(shoe));
		System.out.println("shoes".matches(shoe));
		System.out.println("shoess".matches(shoe));

	}

}
