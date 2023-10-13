public class Interpreter {
    Parser parser;
    AST astAfterParser;

    public Interpreter(Parser p){
        parser = p;
        astAfterParser = p.parse();
    }

    private  boolean isAbstraction(AST ast){
        return ast instanceof Abstraction;
    }
    private  boolean isApplication(AST ast){
        return ast instanceof Application;
    }
    private  boolean isIdentifier(AST ast){
        return ast instanceof Identifier;
    }

    public AST eval(){
        return evalAST(astAfterParser);
    }


    private  AST evalAST(AST ast){
        if (isIdentifier(ast)) {
            return ast;
        }

        else if (isAbstraction(ast)) {
            ((Abstraction) ast).body = evalAST(((Abstraction) ast).body);
            return ast;
        }

        else if (isApplication(ast)) {
            //左树化简:
            if (isIdentifier(((Application) ast).lhs)) {
            }

            else if (isAbstraction(((Application) ast).lhs)) {
                ((Application) ast).lhs = evalAST(((Application) ast).lhs);
            }

            else if (isApplication(((Application) ast).lhs)) {
                if (!(((Application) ((Application) ast).lhs).lhs instanceof Identifier && ((Application) ((Application) ast).lhs).rhs instanceof Identifier)) {
                    ((Application) ast).lhs = evalAST(((Application) ast).lhs);
                }
            }


            //右树化简:
            if (((Application) ast).rhs != null) {
                if (isIdentifier(((Application) ast).rhs)) {
                }

                else if (isAbstraction(((Application) ast).rhs)) {
                    (((Abstraction) ((Application) ast).rhs)).body = evalAST((((Abstraction) ((Application) ast).rhs)).body);
                }

                else if (isApplication(((Application) ast).rhs)) {
                    if (!(((Application) ((Application) ast).rhs).lhs instanceof Identifier && ((Application) ((Application) ast).rhs).rhs instanceof Identifier)) {
                        ((Application) ast).rhs = evalAST(((Application) ast).rhs);
                    }
                }
            }

            //左右树代换求值:
            if (isIdentifier(((Application) ast).lhs)) {
                return ast;
            }

            else if (isAbstraction(((Application) ast).lhs)) {
                if(((Application) ast).rhs == null){
                    return ((Application) ast).lhs;
                }
                else if (((Application) ast).rhs != null) {
                    ast = substitute(((Abstraction) ((Application) ast).lhs).body, ((Application) ast).rhs);
                    ast = evalAST(ast);//代换之后，还得化简！
                    return ast;
                }
            }

            else if (isApplication(((Application) ast).lhs)) {
                return ast;
            }
        }
        return ast;
    }

    private AST substitute(AST node,AST value){
        return shift(-1,subst(node,shift(1,value,0),0),0);
    }

    private AST subst(AST node, AST value, int depth){
        if(isApplication(node)){
            return new Application(
                    subst(((Application)node).lhs, value, depth),
                    subst(((Application)node).rhs, value, depth)
            );
        }
        else if(isAbstraction(node)){
            return new Abstraction(
                    ((Abstraction)node).param,
                    subst(((Abstraction)node).body, value, depth+1)
            );
        }
        else if(isIdentifier(node)){
            if(Integer.parseInt(((Identifier)node).value) == depth){
                return shift(depth, value, 0);
            }
        }
        return node;
    }

    private AST shift(int by, AST node,int from){
        if(isApplication(node)){
            ((Application)node).lhs = shift(by, ((Application)node).lhs, from);
            ((Application)node).rhs = shift(by, ((Application)node).rhs, from);
        }
        else if(isAbstraction(node)){
            ((Abstraction)node).body = shift(by, ((Abstraction)node).body, from+1);
        }
        else if(isIdentifier(node)){
            int int_IdValue = Integer.parseInt(((Identifier)node).value);
            if(int_IdValue >= from){
                return new Identifier(((Identifier)node).name, ""+ (int_IdValue + by));
            }
        }
        return node;
    }

    static String ZERO = "(\\f.\\x.x)";
    static String SUCC = "(\\n.\\f.\\x.f (n f x))";
    static String ONE = app(SUCC, ZERO);
    static String TWO = app(SUCC, ONE);
    static String THREE = app(SUCC, TWO);
    static String FOUR = app(SUCC, THREE);
    static String FIVE = app(SUCC, FOUR);
    static String PLUS = "(\\m.\\n.((m "+SUCC+") n))";
    static String POW = "(\\b.\\e.e b)";
    static String PRED = "(\\n.\\f.\\x.n(\\g.\\h.h(g f))(\\u.x)(\\u.u))";
    static String SUB = "(\\m.\\n.n"+PRED+"m)";
    static String TRUE = "(\\x.\\y.x)";
    static String FALSE = "(\\x.\\y.y)";
    static String AND = "(\\p.\\q.p q p)";
    static String OR = "(\\p.\\q.p p q)";
    static String NOT = "(\\p.\\a.\\b.p b a)";
    static String IF = "(\\p.\\a.\\b.p a b)";
    static String ISZERO = "(\\n.n(\\x."+FALSE+")"+TRUE+")";
    static String LEQ = "(\\m.\\n."+ISZERO+"("+SUB+"m n))";
    static String EQ = "(\\m.\\n."+AND+"("+LEQ+"m n)("+LEQ+"n m))";
    static String MAX = "(\\m.\\n."+IF+"("+LEQ+" m n)n m)";
    static String MIN = "(\\m.\\n."+IF+"("+LEQ+" m n)m n)";

    private static String app(String func, String x){
        return "(" + func + x + ")";
    }
    private static String app(String func, String x, String y){
        return "(" +  "(" + func + x +")"+ y + ")";
    }
    private static String app(String func, String cond, String x, String y){
        return "(" + func + cond + x + y + ")";
    }

    public static void main(String[] args) {

        String[] sources = {
                ZERO,//0
                ONE,//1
                TWO,//2
                THREE,//3
                app(PLUS, ZERO, ONE),//4
                app(PLUS, TWO, THREE),//5
                app(POW, TWO, TWO),//6
                app(PRED, ONE),//7
                app(PRED, TWO),//8
                app(SUB, FOUR, TWO),//9
                app(AND, TRUE, TRUE),//10
                app(AND, TRUE, FALSE),//11
                app(AND, FALSE, FALSE),//12
                app(OR, TRUE, TRUE),//13
                app(OR, TRUE, FALSE),//14
                app(OR, FALSE, FALSE),//15
                app(NOT, TRUE),//16
                app(NOT, FALSE),//17
                app(IF, TRUE, TRUE, FALSE),//18
                app(IF, FALSE, TRUE, FALSE),//19
                app(IF, app(OR, TRUE, FALSE), ONE, ZERO),//20
                app(IF, app(AND, TRUE, FALSE), FOUR, THREE),//21
                app(ISZERO, ZERO),//22
                app(ISZERO, ONE),//23
                app(LEQ, THREE, TWO),//24
                app(LEQ, TWO, THREE),//25
                app(EQ, TWO, FOUR),//26
                app(EQ, FIVE, FIVE),//27
                app(MAX, ONE, TWO),//28
                app(MAX, FOUR, TWO),//29
                app(MIN, ONE, TWO),//30
                app(MIN, FOUR, TWO),//31
        };

        for (int i = 0; i < sources.length; i++) {

            String source = sources[i];

            System.out.println(i + ":" + source);

            Lexer lexer = new Lexer(source);

            Parser parser = new Parser(lexer);

            Interpreter interpreter = new Interpreter(parser);

            AST result = interpreter.eval();

            System.out.println(i + ":" + result.toString());

        }
    }
}