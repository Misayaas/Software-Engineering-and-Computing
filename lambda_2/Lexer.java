public class Lexer {
    private String source;
    public String tokenvalue;
    int index;

    /**
     * Lexer 类内构造函数
     *
     * @param s
     */
    public Lexer(String s) {
        source = s;
        index = 0;
    }

    /**
     * 解析函数，本次作业主任务
     *
     * @return TokenType 当前合法的token的tokenType
     */
    public TokenType nextToken() {
        TokenType result = getTokenAndNext();
        System.out.println(result.toString());
        return result;
    }

    public TokenType getTokenAndNext() {
        while (index < source.length() && currentChar() == ' ')
            index++;

        tokenvalue = new String();
        if (index >= source.length())
            return TokenType.EOF;


        if (currentChar() == '.') {
            index++;
            tokenvalue += ".";
            return TokenType.DOT;
        }

        if (currentChar() == '\\') {
            index++;
            tokenvalue += "\\";
            return TokenType.LAMBDA;
        }

        if (currentChar() == '(') {
            index++;
            tokenvalue += "(";
            return TokenType.LPAREN;
        }

        if (currentChar() == ')') {
            index++;
            tokenvalue += ")";
            return TokenType.RPAREN;
        }

        if (Character.isLowerCase(currentChar())) {
            tokenvalue += currentChar();
            index++;
            while (Character.isLowerCase(currentChar()) || Character.isUpperCase(currentChar())) {
                tokenvalue += currentChar();
                index++;
            }
            return TokenType.LCID;
        }

        return TokenType.EOF;
    }

    private char currentChar() {
        return source.charAt(index);
    }

    /**
     * check token == t 检查类型
     *
     * @param t
     * @return 类型是否匹配
     */
    public boolean nextIsMatched(TokenType t) {
        if (t == TokenType.EOF) return index == source.length();


        // skip space
        int temp_index = index;
        char tested_char = currentChar();
        while (temp_index < source.length() && tested_char == ' ') {
            temp_index++;
            tested_char = source.charAt(temp_index);
        }

        switch (t) {
            case DOT:
                return tested_char == '.';
            case LAMBDA:
                return tested_char == '\\';
            case LPAREN:
                return tested_char == '(';
            case RPAREN:
                return tested_char == ')';
            case LCID:
                return Character.isLowerCase(tested_char);
        }
        return false;
    }

    /**
     * 保证当前token的类型与传入的t相同，并解析下一个符合此法规则的token
     * 如果解析到不同于t的类型，则退出并报错
     *
     * @param t
     */
    public void checkAndNext(TokenType t) throws Exception {
        if (!nextIsMatched(t))
            throw new Exception("Not expected token");
        nextToken();
    }

    /**
     * 跳过当前TokenType t，并解析下一个符合此法规则的token
     *
     * @param t
     * @return 是否skip成功
     */
    public boolean skipThisType(TokenType t) {
        return false;
    }
}