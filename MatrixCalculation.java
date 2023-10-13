package edu.nju;
import java.util.Scanner;
/**
 * 实现矩阵的加法、乘法以及控制台输出
 * 其中加法和乘法需要有两种实现方式
 * 1.传入一个矩阵进行2个矩阵的操作
 * 2.从控制台（console）读入一个矩阵，再进行操作
 * 所有的数据均为int型
 * 输入数据均默认为正确数据，不需要对输入数据进行校验
 * @author Ray Liu & Qin Liu
 */
public class MatrixCalculation
{
	
	/**
	 * 实现矩阵加法，返回一个新的矩阵
	 * @return result matrix = A + B
	 */
	public int[][] plus(int[][] A, int[][] B)
	{
		if(A.length == 0||B.length == 0)
		{
			int [][] C = new int[0][0];
			return C;
		}

		int m = A.length;
		int n = A[0].length;
		int [][] res = new int [m][n];
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n; j++)
			{
				res[i][j] = A[i][j] + B[i][j];
			}
		}
		return res;
	}
	
	/**
	 * 实现矩阵乘法，返回一个新的矩阵
	 * @return result matrix = A * B
	 */
	public int[][] times(int[][] A, int[][] B)
	{
		if(A.length == 0||B.length == 0)
		{
			int [][] C = new int[0][0];
			return C;
		}

		int m = A.length;
		int n = B[0].length;
		int k = A[0].length;
		int [][] res = new int [m][n];
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n; j++)
			{
				int sum = 0;
				for(int p = 0; p < k; p++)
				{
					sum += A[i][p] * B[p][j];
				}
				res[i][j] = sum;
			}
		}
		return res;
	}
	
	/**
	 * 从控制台读入矩阵数据，进行矩阵加法，读入数据格式如下：
	 * m n
	 * m * n 的数据方阵，以空格隔开
	 * 连续读入2个矩阵数据
	 * example:
	 * 4 3
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 4 3
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 1 2 3
	 * 返回一个新的矩阵
	 */
	public int [][] plusFromConsole()
	{
		Scanner input = new Scanner(System.in);
		int [][] A = readMatrix(input);
		int [][] B = readMatrix(input);

		return plus(A,B);
	}

	/**
	 * 输入格式同上方法相同
	 * 实现矩阵的乘法
	 * 返回一个新的矩阵
	 */
	public int[][] timesFromConsole()
	{
		Scanner input = new Scanner(System.in);
		int [][] A = readMatrix(input);
		int [][] B = readMatrix(input);

		return times(A,B);
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
	public void print(int[][] A)
	{
		int m = A.length;
		int n = A[0].length;

		System.out.println();
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n - 1; j++)
			{
				System.out.print(A[i][j] + " ");
			}
			System.out.println(A[i][n-1]);
		}
	}

	private int [][] readMatrix(Scanner scanner)
	{
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int [][] matrix = new int [m][n];
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n; j++)
			{
				matrix[i][j] = scanner.nextInt();
			}
		}
		return matrix;
	}
}
