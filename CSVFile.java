import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CSVFile
{
	public static void main(String[] args)
	{
		String filePath = CSVFile.class.getClassLoader().getResource("data.txt").getPath();
		printCSVFile(filePath);
	}
	
	public static void printCSVFile(String filePath)
	{
		System.out.println("Last    First    Salary");
		try
		{
			FileReader reader = new FileReader(filePath);
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNext())
			{
				String[] cols = scanner.nextLine().split(",");
				System.out.println(String.join("    ", cols));
			}
		}
		catch(Exception e)
		{

		}
	}
	

}
