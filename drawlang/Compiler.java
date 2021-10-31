package drawlang;

import drawlang.lexer.*;
import drawlang.node.*;
import drawlang.parser.*;

import java.io.*;

public class Compiler {
    private SemanticAnalyzer semanticAnalyzer;

    public Compiler(){
        semanticAnalyzer = new SemanticAnalyzer();
    }
    public void compile(Reader reader, int size) {
        try {
            Lexer lexer = new Lexer(
                    new PushbackReader(reader, size));
                            /*new BufferedReader(
                                    new FileReader("input.txt")), 1024));*/
            // Create a Parser instance.
            Parser p = new Parser(lexer);
            System.out.println( "starting.." );
            // Parse the input.
            Start tree = p.parse();
            tree.apply(this.semanticAnalyzer);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public SemanticAnalyzer getSemanticAnalyzer() {
        return semanticAnalyzer;
    }

    public static void main(String[] args){
        try {
            new Compiler().compile(new BufferedReader(new FileReader("input files/input.txt")), 1024);
        }catch(FileNotFoundException f){
            System.out.println("Error! File [ input.txt ] not found!");
        }
    }

}
//java -jar lib/sablecc.jar grammar.txt