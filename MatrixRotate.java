import java.io.*;

public class MatrixRotate {

    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=null;
        int[][] a=new int[100][100];
        try{
            str=br.readLine();
            String[] the_split=str.split(" ");
            int m=Integer.parseInt(the_split[0]);
            int n=Integer.parseInt(the_split[1]);
            for(int i=0;i<m;++i){
                str=br.readLine();
                the_split=str.split(" ");
                for(int j=0;j<n;++j){
                    a[i][j]=Integer.parseInt(the_split[j]);
                }
            }
            str=br.readLine();
            int role=Integer.parseInt(str);
            role%=360;
            if(role==0){
                for(int i=0;i<m;++i){
                    System.out.print(a[i][0]);
                    for(int j=1;j<n;++j)System.out.printf(" %d",a[i][j]);
                    System.out.println();
                }
            }
            else if(role==90){
                for(int j=0;j<n;++j){
                    System.out.print(a[m-1][j]);
                    for(int i=m-2;i>=0;--i)System.out.printf(" %d",a[i][j]);
                    System.out.println();
                }
            }
            else if(role==180){
                for(int i=m-1;i>=0;--i){
                    System.out.print(a[i][n-1]);
                    for(int j=n-2;j>=0;--j)System.out.printf(" %d",a[i][j]);
                    System.out.println();
                }
            }
            else {
                for(int j=n-1;j>=0;--j){
                    System.out.print(a[0][j]);
                    for(int i=1;i<m;++i)System.out.printf(" %d",a[i][j]);
                    System.out.println();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
