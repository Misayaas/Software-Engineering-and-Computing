import java.util.Scanner;
/**
 * 矩阵类，实现矩阵的加法，矩阵乘法，点乘以及转置方法
 * 其中加法和点乘方法需要有两种实现方式
 * 1.传入一个MyMatrix对象进行2个矩阵的操作
 * 2.从控制台（console）读入一个矩阵数据，再进行操作
 * 所有的数据均为int型
 * 输入数据均默认为正确数据，不需要对输入数据进行校验
 * @author Ray Liu & Qin Liu
 *
 */
public class MyMatrix {
	private int[][] data;
	
	/**
	 * 构造函数，参数为2维int数组
	 * a[i][j]是矩阵中的第i+1行，第j+1列数据
	 * @param a
	 */
	public MyMatrix(int[][] a){
		this.data = a;
	}

	public int[][] getData() {
		return data;
	}

	/**
	 * 实现矩阵加法，返回一个新的矩阵
	 * @param B
	 * @return
	 */
	public MyMatrix plus(MyMatrix B)
	{
		int [][] a = this.getData();
		int [][] b = B.getData();

		int [][] result = new int [a.length][a[0].length];
		for(int i = 0;i < a.length;i++)
		{
			for(int j = 0;j < a[0].length;j++)
			{
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		return new MyMatrix(result);
	}

	
	/**
	 * 实现矩阵乘法，返回一个新的矩阵
	 * @param B
	 * @return
	 */
	public MyMatrix times(MyMatrix B)
	{
		int [][] a = this.getData();
		int [][] b = B.getData();

		int [][] result = new int [a.length][b[0].length];
		for(int i = 0;i < a.length;i++)
		{
			for(int j = 0;j < b[0].length;j++)
			{
				for(int k = 0;k < a[0].length;k++)
				{
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return new MyMatrix(result);

	}
	
	/**
	 * 实现矩阵的点乘，返回一个新的矩阵
	 * @param b
	 * @return
	 */
	public MyMatrix times(int b)
	{
		int[][] a = this.getData();

		int[][] result = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				result[i][j] = a[i][j] * b;
			}
		}

		return new MyMatrix(result);
	}
	
	/**
	 * 实现矩阵的转置，返回一个新的矩阵
	 * @return
	 */
	public MyMatrix transpose()
	{
		int[][] a = this.getData();

		int[][] result = new int[a[0].length][a.length];
		for (int i = 0; i < a[0].length; i++) {
			for (int j = 0; j < a.length; j++) {
				result[i][j] = a[j][i];
			}
		}

		return new MyMatrix(result);
	}
	
	/**
	 * 从控制台读入矩阵数据，进行矩阵加法，读入数据格式如下：
	 * m n
	 * m * n 的数据方阵，以空格隔开
	 * example:
	 * 4 3
	 * 1 2 3 
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 返回一个新的矩阵
	 * @return
	 */
	public MyMatrix plusFromConsole()
	{
		Scanner input = new Scanner(System.in);
		int [][] a = null;

		int m = input.nextInt();
		int n = input.nextInt();

		a = new int [m][n];
		for(int i = 0;i < m;i++)
		{
			for(int j = 0;j < n;j++)
			{
				a[i][j] = input.nextInt();
			}
		}

		MyMatrix A = new MyMatrix(a);
		return this.plus(A);
	}
	
	/**
	 * 输入格式同上方法相同
	 * 实现矩阵的乘法
	 * 返回一个新的矩阵
	 * @return
	 */
	public MyMatrix timesFromConsole()
	{
		Scanner input = new Scanner(System.in);

		int[][] a = null;
		int[][] b = null;

		int m = input.nextInt();
		int n = input.nextInt();

		a = new int[m][n];
		for(int i = 0;i < m;i++)
		{
			for(int j = 0;j < n;j++)
			{
				a[i][j] = input.nextInt();
			}
		}
		MyMatrix A = new MyMatrix(a);
		return this.times(A);
	}
	
	/**
	 * 打印出该矩阵的数据
	 * 起始一个空行，结束一个空行
	 * 矩阵中每一行数据呈一行，数据间以空格隔开
	 * example：
	 * 
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 
	 */
	public void print()
	{
		int[][] a = this.getData();

		System.out.println();
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[0].length; j++)
			{
				if(j != a[0].length - 1)
				{
					System.out.print(a[i][j] + " ");
				}
				else
				{
					System.out.print(a[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
