
import java.util.regex.*;

public class Lexer{

    private int pos = 0;
    private String s;
    private char currentChar;
    /**
     * Lexer 类内构造函数
     * @param s
     */
    public Lexer(String s){
        this.s = s;
        currentChar = s.charAt(pos);
    }

    //分析完成后往后推一位
    private void next() {
        pos++;
        if (pos > s.length() - 1) {
            currentChar = 0;
        } else {
            currentChar = s.charAt(pos);
        }
    }

    //跳过空白符
    private void skipWhitespace() {
        while (currentChar != 0 && Character.isWhitespace(currentChar)) {
            next();
        }
    }

    // 读取一个LCID类型的token
    private String getId() {
        StringBuilder result = new StringBuilder();
        while (currentChar != 0 && Character.isLetterOrDigit(currentChar) && Character.isLowerCase(currentChar)) {
            result.append(currentChar);
            next();
        }
        return result.toString();
    }
    /**
     * 解析函数，本次作业主任务
     * @return TokenType 当前合法的token的tokenType
     */
    public TokenType nextToken() {
        while (currentChar != 0) {

            // 处理空格等无用字符
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            // 处理LCID类型的token
            if (Character.isLetter(currentChar)) {
                next();
                System.out.println("LCID");
                return TokenType.LCID;
            }

            // 处理lambda符号
            if (currentChar == '\\') {
                next();
                System.out.println("LAMBDA");
                return TokenType.LAMBDA;
            }

            // 处理左括号
            if (currentChar == '(') {
                next();
                System.out.println("LPAREN");
                return TokenType.LPAREN;
            }

            // 处理右括号
            if (currentChar == ')') {
                next();
                System.out.println("RPAREN");
                return TokenType.RPAREN;
            }

            // 处理点符号
            if (currentChar == '.') {
                next();
                System.out.println("DOT");
                return TokenType.DOT;
            }
            break;
        }
        // 处理已结束的输入
        System.out.println("EOF");
        return TokenType.EOF;
    }
    /**
     * check token == t 检查类型
     * @param t
     * @return 类型是否匹配
     */
    public boolean nextIsMatched(TokenType t)
    {
        return t == nextToken();
    }

    /**
     * 保证当前token的类型与传入的t相同，并解析下一个符合此法规则的token
     * 如果解析到不同于t的类型，则退出并报错
     * @param t
     */
    public void checkAndNext(TokenType t){
        //TODO
    }

    /**
     * 跳过当前TokenType t，并解析下一个符合此法规则的token
     * @param t
     * @return 是否skip成功
     */
    public boolean skipThisType(TokenType t){
       //TODO
        return false;
    }
}
