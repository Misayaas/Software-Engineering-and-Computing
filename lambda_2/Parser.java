import java.util.ArrayList;

public class Parser {
    Lexer lexer;

    public Parser(Lexer l) {
        lexer = l;
    }

    public AST parse() {//解析入口
        AST ast = parseAsTerm(new ArrayList<>());
        return ast;
    }

    /**
     * 解析 term
     *
     * @param ctx
     * @return
     */
    private AST parseAsTerm(ArrayList<String> ctx) {
        if (lexer.nextIsMatched(TokenType.LAMBDA)) {
            lexer.nextToken();

            if (lexer.nextIsMatched(TokenType.LCID)) {
                lexer.nextToken();
                ctx.add(lexer.tokenvalue);
                Identifier id = new Identifier(lexer.tokenvalue, findIndexFor(lexer.tokenvalue, ctx));

                if (lexer.nextToken() != TokenType.DOT) return null;

                AST term = parseAsTerm(ctx);
                if (term == null) return null;

                Abstraction ast = new Abstraction(id, term);
                ctx.remove(ctx.size() - 1);
                return ast;
            }
        }

        AST app;
        if ((app = parseAsApplication(ctx)) != null) {
            return app;
        }
        return null;
    }

    /**
     * 解析 application
     *
     * @param ctx
     * @return
     */
    private AST parseAsApplication(ArrayList<String> ctx) {
        AST result = parseAsAtom(ctx);
        while (true) {
            AST atom = parseAsAtom(ctx);
            if (atom != null) {
                result = new Application(result, atom);
            } else {
                break;
            }
        }

        return result;
    }

    /**
     * 解析 atom
     *
     * @param ctx
     * @return
     */
    private AST parseAsAtom(ArrayList<String> ctx) {
        // Identifier
        if (lexer.nextIsMatched(TokenType.EOF))
            return null;
        if (lexer.nextIsMatched(TokenType.LCID)) {
            lexer.nextToken();
            return new Identifier(lexer.tokenvalue, findIndexFor(lexer.tokenvalue, ctx));
        }

        //'('
        if (lexer.nextIsMatched(TokenType.LPAREN)) {

            lexer.nextToken();
            AST ast = parseAsTerm(ctx);
            if (ast == null) return null;

            // ')'
            if (!lexer.nextIsMatched(TokenType.RPAREN)) return null;
            lexer.nextToken();

            return ast;
        }


        return null;
    }

    private String findIndexFor(String id, ArrayList<String> ctx) {
        for (int i = ctx.size() - 1; i >= 0; i--) {
            if (ctx.get(i).equals(id)) {
                return String.valueOf(ctx.size() - 1 - i);
            }
        }

        return "-1";
    }
}
