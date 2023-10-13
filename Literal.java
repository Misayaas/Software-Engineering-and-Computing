import java.util.Scanner;
public class Literal
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();

        if(s.equals("true") || s.equals("false"))
        {
            System.out.println("boolean");
        }
        else if (s.contains(".") || s.contains("e") || s.contains("E"))
        {
            if (s.endsWith("f") || s.endsWith("F"))
            {
                System.out.println("float");
            }
            else
            {
                System.out.println("double");
            }
        }
        else if (s.matches("\\d+"))
        {
            System.out.println("integer");
        }
        else if (s.endsWith("l") || s.endsWith("L"))
        {
            System.out.println("long");
        }

        else
        {
            try
            {
                Long.parseLong(s);
                System.out.println("long");
            }
            catch (NumberFormatException e)
            {
                try
                {
                    Double.parseDouble(s);
                    System.out.println("double");
                }
                catch (NumberFormatException e1)
                {
                    System.out.println("char");
                }
            }
        }
    }
}
