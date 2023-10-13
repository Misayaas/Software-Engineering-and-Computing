import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ResponseTimeCalculation {

	public static void main(String[] args) {
		ArrayList<Integer> times = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter a Number:");
			String s = scanner.nextLine();
			if (s.equals("done"))
				break;
			else {
				times.add(Integer.valueOf(s));
			}
		}

		System.out.printf("Numbers:" + times.stream().map(Object::toString).collect(Collectors.joining(",")) + "\n");
		System.out.printf("The average is %.2f.\n", times.stream().collect(Collectors.averagingDouble((a) -> ((double) a))));
		System.out.printf("The minimum is %d.\n", times.stream().min(Comparator.comparingInt((a) -> (a))).get());
		System.out.printf("The maximum is %d.\n", times.stream().max(Comparator.comparingInt((a) -> (a))).get());
		System.out.printf("The standard deviation is %.2f.\n", Math.sqrt(times.stream().map((Integer x) -> {
			return Math.pow(x - times.stream().collect(Collectors.averagingDouble((a) -> ((double) a))), 2) / (double) times.size();
		}).mapToDouble((a) -> (a)).sum()));
	}

}
