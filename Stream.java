import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Stream
{
	// 使用命令式范式实现
	public static String getNamesStringImperatively(List<String> nameList)
	{
		List<String> filteredNames = new ArrayList<>();
		for(String name : nameList)
		{
			if(name.length() > 1)
			{
				filteredNames.add(Character.toUpperCase(name.charAt(0)) + name.substring(1));
			}
		}
		String result = String.join(",",filteredNames);
		return result;
	}

	// 使用函数式范式实现
	public static String getNamesStringFunctionally(List<String> nameList)
	{
		String result = nameList.stream()
				.filter(name -> name.length() > 1)
				.map(name -> Character.toUpperCase(name.charAt(0)) + name.substring(1))
				.collect(Collectors.joining(","));
		return result;
	}
}
