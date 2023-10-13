import java.io.*;

public class HeartRateCalculation {

	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str=null;
		int Intensity=50;
		try{
			System.out.println("RestingHR:");
			str=br.readLine();
			double RestingHR=Integer.parseInt(str);
			System.out.println("Age:");
			str=br.readLine();
			double Age=Integer.parseInt(str);
			System.out.println("Intensity|TargetHeartRate");
			System.out.println("---------|---------------");
			while(Intensity<95){
				Intensity+=5;
				System.out.print(Intensity);
				System.out.print("%      |");
				double TargetHeartRate =(((220 - Age) - RestingHR) * Intensity/100.0) + RestingHR;
				System.out.printf("%dbpm\n",(int)(TargetHeartRate+0.5));
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
