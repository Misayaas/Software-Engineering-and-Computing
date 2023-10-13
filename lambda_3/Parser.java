import java.util.ArrayList;

public class Parser {
    Lexer lexer;
    public Parser(Lexer l){
        lexer = l;
        lexer.nextToken();
    }

    public AST parse(){//解析入口
        AST ast = term(new ArrayList<>());
        return ast;
    }

    private AST term(ArrayList<String> ctx){
        String paramValue;
        String paramName;
        AST body;

        if(lexer.tokentype.equals(TokenType.LAMBDA)){
            lexer.nextToken();
            paramName = lexer.tokenvalue;
            ctx.add(0,paramName);
            paramValue = "" + ctx.indexOf(paramName);
            lexer.nextToken();
            lexer.nextToken();
            body = term(ctx);
            ctx.remove(paramName);
            return new Abstraction(new Identifier(paramName,paramValue),body);
        }
        else{
            return application(ctx);
        }
    }

    private AST application(ArrayList<String> ctx){

        AST lhs = atom(ctx);
        AST rhs = atom(ctx);
        while( rhs != null ){
            lhs = new Application(lhs,rhs);
            rhs = atom(ctx);
        }
        return lhs;

    }

    private AST atom(ArrayList<String> ctx){
        String paramValue;
        String paramName;
        AST innerTerm;

        if(lexer.nextToken(TokenType.LPAREN)){
            innerTerm = term(ctx);
            if(lexer.nextToken(TokenType.RPAREN))
                return innerTerm;
        }
        else if(lexer.tokentype.equals(TokenType.LCID)){
            paramName = lexer.tokenvalue;
            paramValue = "" + ctx.indexOf(paramName);
            lexer.nextToken(TokenType.LCID);
            return new Identifier(paramName,paramValue);
        }
        return  null;
    }

}
