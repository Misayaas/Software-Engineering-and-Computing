
import java.util.ArrayList;
import java.util.regex.*;

public class Lexer{
    public String source;
    public int index;
    public TokenType tokentype;
    public String tokenvalue ;
    public char tokenchar;//用于辅助wholeToken函数；
    public ArrayList<TokenType> result = new ArrayList<TokenType>();        //①
    public ArrayList<String> wholeTokenValue = new ArrayList<String>();
    /**
     * Lexer 类内构造函数
     * @param s
     */
    public Lexer(String s){
        index = 0;
        source = s;
        wholeToken();
        print(result);
    }

    public TokenType wholeToken(){
        //write your code here
        while(index < source.length()){
            tokenchar = source.charAt(index);
            switch(tokenchar) {
                case '\\':
                    index++;
                    result.add(TokenType.LAMBDA);
                    wholeTokenValue.add("\\");
                    break;
                case '(':
                    index++;
                    result.add(TokenType.LPAREN);
                    wholeTokenValue.add("(");
                    break;
                case ')':
                    index++;
                    result.add(TokenType.RPAREN);
                    wholeTokenValue.add(")");
                    break;
                case '.':
                    index++;
                    result.add(TokenType.DOT);
                    wholeTokenValue.add(".");
                    break;
                case ' ':
                    index++;
                    break;
                default:
                    String temptoken="";
                    while ((65 <= tokenchar && tokenchar <= 90) || (97 <= tokenchar && tokenchar <= 122)) {
                        temptoken = temptoken + tokenchar;
                        index++;
                        if (index < source.length()) {
                            tokenchar = source.charAt(index);
                        } else break;
                    }
                    result.add( TokenType.LCID);
                    wholeTokenValue.add(temptoken);
                    break;
            }
        }
        result.add(TokenType.EOF);
        wholeTokenValue.add("\0");
        index = 0;

        return null;
    }
    /**
     * 解析函数
     * @return TokenType 当前合法的token的tokenType
     */
    public TokenType nextToken(){
        index++;
        tokentype =  result.get(index-1);
        tokenvalue = wholeTokenValue.get(index-1);
        return tokentype;
    }

    public boolean nextToken(TokenType type){
        if(tokentype.equals(type)) {
            index++;
            tokentype = result.get(index - 1);
            tokenvalue = wholeTokenValue.get(index - 1);
            return true;
        }
        else return false;
    }

    public static void print(ArrayList<TokenType> tokenList){
        for(TokenType token: tokenList){
            System.out.println(token.toString());
        }
    }
}
