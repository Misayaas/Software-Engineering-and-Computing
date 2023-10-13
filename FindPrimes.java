import java.util.Scanner;

public class FindPrimes
{
	public static  boolean Is_prime(int num)
	{
		if(num==2)
			return true;

		for(int i=2;i<num;i++)
		{
			if(num%i==0)
				return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();

		for(int i=0;i<num;i++)
		{

			int whole,prime1=0,prime2=0;
			whole=input.nextInt();
			for(int j=2;j<whole;j++)
			{
				if(Is_prime(j))
				{
					if(Is_prime(whole-j))
					{
						prime1=j;
						prime2=whole-j;
						break;
					}
				}
			}
			System.out.printf("%d %d\n",prime1,prime2);
		}
	}

}
