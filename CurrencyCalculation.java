import java.util.Scanner;
public class CurrencyCalculation
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("How many euros are you exchanging?");
		double start = sc.nextDouble();
		System.out.println("What is the exchange rate?");
		double rate = sc.nextDouble();
		System.out.printf("%.2f euros at an exchange rate of %.2f is %.2f U.S. dollars.",start,rate,start*rate/100);
	}

}
