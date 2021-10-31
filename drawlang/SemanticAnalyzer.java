package drawlang;

import Utils.*;
import drawlang.node.*;
import drawlang.analysis.*;
import java.util.*;

import static Utils.Int.POS_SIGN;


public class SemanticAnalyzer extends DepthFirstAdapter {
    private static final String ASSERT_ERROR = "ERROR! Should have been tackled by syntax analysis but not!";
    List<List<Variable>> runtimeStack;
    public SemanticAnalyzer(){
        super();
        this.runtimeStack = new ArrayList<>();
        this.runtimeStack.add(new ArrayList<>());
    }

    /*Numeric attribute*/
    public void caseASignedNumeric(ASignedNumeric node) {
        node.variable=new Int(Integer.parseInt(node.getNumber().getText().trim()), node.getSign().toString().trim().equals(POS_SIGN)? Int.Signed.Positive: Int.Signed.Negative);
    }
    public void caseAUnsignedNumeric(AUnsignedNumeric node) {
        node.variable=new Int(Integer.parseInt(node.getNumber().getText().trim()));
    }

    /*Visible signs*/
    public void caseTOpdot(TOpdot node) {
        if (node.parent() instanceof PDisplayop){
            ((FigureObject)getLastOfFormerSymbolScope()).setVisible(false);
        }
    }
    public void caseTOpleftangle(TOpleftangle node) {
        if (node.parent() instanceof PDisplayop){
            ((FigureObject)getLastOfFormerSymbolScope()).setVisible(true);
        }
    }

    /*Terminals*/
    public void caseTHexcolor(THexcolor node) {
        node.variable=new HexColor(node.getText().trim());
    }
    public void caseTIdentifier(TIdentifier node) {
        Variable temp=findIdentifierInScope(node.getText(), getCurrentSymbolScope());
        /*Two left value situations */
        if (node.parent() instanceof PObjectidentifier){
            if(temp==null){
                String figureName = node.getText();
                getLastOfFormerSymbolScope().setName("$$"+figureName); //Indicate that the object would not be called before its construction done
            }else{
                throw new RuntimeException("Redefinition of object" + node.getText()+ " in current scope");
            }
        }else if(node.parent() instanceof PCommand) {
            if (node == ((ACommand) (node.parent())).getIdentifier()) {
                if(temp!=null) {
                    throw new RuntimeException("Redefinition of variable " + node.getText()+ " in current scope");
                } //no support for redefinition in same scope
            }else {/* Right value situation in command*/
                if (temp != null) {
                    if (temp instanceof FigureObject)
                        throw new RuntimeException("Does not support assignment of figure object to variable");
                    node.variable=temp.clone();
                } else{
                    throw new RuntimeException(node.getText() + " has not been defined!");
                }
            }
        }else{ /* All other right value situations */
            temp=findIdentifier(node.getText());
            if (temp != null) {
                node.variable=temp.clone();
            } else {
                throw new RuntimeException(node.getText() + " has not been defined!");
            }
        }
    }
    public void caseTKeywordcircle(TKeywordcircle node) {
        node.variable=FigureObject.CIRCLE.clone();
    }
    public void caseTKeywordpoly(TKeywordpoly node) {
        node.variable=FigureObject.POLYGON.clone();
    }
    public void caseTKeywordrect(TKeywordrect node) {
        node.variable=FigureObject.RECTANGLE.clone();
    }

    /*From keywordclasstype candidates to keywordclasstype*/
    public void outAKeywordcircleKeywordclasstype(AKeywordcircleKeywordclasstype node) {
        inconsistentError(node.getKeywordcircle().getNodeType(), new Variable.VarType[]{Variable.VarType.Figure});//assert
        node.variable=node.getKeywordcircle().variable;
    }
    public void outAKeywordrectKeywordclasstype(AKeywordrectKeywordclasstype node) {
        inconsistentError(node.getKeywordrect().getNodeType(), new Variable.VarType[]{Variable.VarType.Figure});//assert
        node.variable=node.getKeywordrect().variable;
    }
    public void outAKeywordpolyKeywordclasstype(AKeywordpolyKeywordclasstype node) {
        inconsistentError(node.getKeywordpoly().getNodeType(), new Variable.VarType[]{Variable.VarType.Figure});//assert
        node.variable=node.getKeywordpoly().variable;
    }

    /*Assign types to the single values in Pairwise */
    public void outAVariablepairvaluePairvalue(AVariablepairvaluePairvalue node) {
        node.variable=node.getIdentifier().variable.clone();
    }
    public void outANumericpairvaluePairvalue(ANumericpairvaluePairvalue node) {
        node.variable=node.getNumeric().variable;
    }
    public void outAEmptyPairvalue(AEmptyPairvalue node) {
        node.variable=new Int(0, Int.Signed.Zero);
    }

    /*Assign type and values to a Pairwise*/
    public void outAPairwise(APairwise node) {
        PPairvalue first = node.getFirst();
        PPairvalue second = node.getSecond();
        try {
            inconsistentError(first.getNodeType(), new Variable.VarType[]{second.getNodeType()});//type check
        }catch(RuntimeException e){
            throw new RuntimeException("Pairwise values have inconsistent types! Maybe your are typing 0 rather than {empty} along with some signed int? ");
        }
        if(first.getNodeType()== Variable.VarType.SignedInt){
            node.variable=new Pairwise((Int)first.variable, (Int)second.variable);
        }else if (first.getNodeType()== Variable.VarType.Int){
            node.variable=new Pairwise((Int)first.variable, (Int)second.variable);
        }else{
            throw new RuntimeException("Compiler error! Pairwise values not compatible");
        }
    }

    /*From attributevalue candidates to attributevalue*/
    public void outAHexcolorvalueAttributevalue(AHexcolorvalueAttributevalue node) {
        inconsistentError(node.getHexcolor().getNodeType(), new Variable.VarType[]{Variable.VarType.HexColor});//assert
        node.variable=node.getHexcolor().variable;
    }
    public void outANumericvalueAttributevalue(ANumericvalueAttributevalue node) {
        inconsistentError(node.getNumeric().getNodeType(), new Variable.VarType[]{Variable.VarType.Int, Variable.VarType.SignedInt});//assert
        node.variable=node.getNumeric().variable;
    }
    public void outAVariablevalueAttributevalue(AVariablevalueAttributevalue node) {
        node.variable=node.getIdentifier().variable.clone();
    }
    public void outAKeywordvalueAttributevalue(AKeywordvalueAttributevalue node) {
        inconsistentError(node.getKeywordclasstype().getNodeType(), new Variable.VarType[]{Variable.VarType.Figure});//assert
        node.variable=node.getKeywordclasstype().variable;
    }
    public void outAPairwisevalueAttributevalue(APairwisevalueAttributevalue node) {
        inconsistentError(node.getPairwise().getNodeType(), new Variable.VarType[]{Variable.VarType.UnsignedPairwise, Variable.VarType.SignedPairwise});//assert
        node.variable=node.getPairwise().variable;
    }

    /*Assign type from attributevalue list and attributevalue to attributevalues*/
    public void outAAttributelistAttributevalues(AAttributelistAttributevalues node) {
        switch(node.getAttributevalues().getNodeType()){
            case Int:
            case SignedInt:{
                if(node.getAttributevalue().getNodeType() == Variable.VarType.HexColor){
                    node.variable=new Border((Int)node.getAttributevalues().variable, (HexColor)node.getAttributevalue().variable);
                }
                else{
                    throw new RuntimeException("Int object cannot be followed by "+node.getAttributevalue().toString()+" with type "+node.getAttributevalue().getNodeType().name());
                }
                break;
            }
            case SignedPairwise:{
                throw new RuntimeException("SignedPairwise object cannot be followed with anything");
            }
            case UnsignedPairwise: {
                if(node.getAttributevalue().getNodeType()== Variable.VarType.UnsignedPairwiseList) {
                    node.variable = new PairwiseList().append((Pairwise)(node.getAttributevalues().variable)).appendAll((PairwiseList)(node.getAttributevalue().variable));
                }
                else if(node.getAttributevalue().getNodeType()== Variable.VarType.UnsignedPairwise ){
                    node.variable = new PairwiseList().append((Pairwise)(node.getAttributevalues().variable)).append((Pairwise)(node.getAttributevalue().variable));
                }else{
                    throw new RuntimeException("Unsigned Pairwise object cannot be followed with "+node.getAttributevalue().toString()+" with type "+node.getAttributevalue().getNodeType().name());
                }
                break;
            }
            case UnsignedPairwiseList: {
                if(node.getAttributevalue().getNodeType()== Variable.VarType.UnsignedPairwiseList) {
                    node.variable = new PairwiseList().appendAll((PairwiseList)(node.getAttributevalues().variable)).appendAll((PairwiseList)(node.getAttributevalue().variable));
                }
                else if(node.getAttributevalue().getNodeType()== Variable.VarType.UnsignedPairwise ){
                    node.variable = new PairwiseList().appendAll((PairwiseList)(node.getAttributevalues().variable)).append((Pairwise)(node.getAttributevalue().variable));
                }else{
                    throw new RuntimeException("Pairwise/PairwiseList object cannot be followed with "+node.getAttributevalue().toString()+" with type "+node.getAttributevalue().getNodeType().name());
                }
                break;
            }
            case HexColor:{
                if(node.getAttributevalue().getNodeType()== Variable.VarType.Int
                        || node.getAttributevalue().getNodeType()== Variable.VarType.SignedInt){
                    node.variable = new Border((Int)(node.getAttributevalues().variable), (HexColor)(node.getAttributevalue().variable));
                }
                else {
                    throw new RuntimeException("HexColor object cannot be followed with " + node.getAttributevalue().toString()+" with type "+node.getAttributevalue().getNodeType().name());
                }
                break;
            }
            case Figure:{
                throw new RuntimeException("Figure object cannot be followed by anything");
            }
            case Border:{
                throw new RuntimeException("Border object cannot be followed by anything");
            }
            case Nil:{
                throw new RuntimeException("Compiler error! Unsupported datatype in "+ node.getAttributevalues().toString());
            }
        }
    }
    public void outAAttributevalueAttributevalues(AAttributevalueAttributevalues node) {
        switch(node.getAttributevalue().getNodeType()){
            case Int:
            case SignedInt:
            case SignedPairwise:
            case UnsignedPairwise:
            case UnsignedPairwiseList:
            case HexColor:
            case Border:
            case Figure:{
                node.variable=node.getAttributevalue().variable;
                break;
            }
            case Nil:{
                throw new RuntimeException("Compiler error! Unsupported datatype in "+ node.getAttributevalue().toString());
            }
        }
    }

    /*Assign attributes to figure*/
    public void outAAttribute(AAttribute node) {
        Variable belongingFigure;
        if(!((belongingFigure= getLastOfFormerSymbolScope()) instanceof FigureObject)) {
            throw new RuntimeException("Stack Corrupted Due to Unknown Problem!");
        }
        FigureObject curFigure = (FigureObject) belongingFigure;
        PAttributename attributeName = node.getAttributename();
        switch(attributeName.toString().trim()){
            case("class"): {
                if (node.getAttributevalues().getNodeType() != Variable.VarType.Figure)
                    throw new RuntimeException("[Class] field received incompatible attribute type: "+node.getAttributevalues().getNodeType());
                curFigure.setObjectClass((FigureObject)node.getAttributevalues().variable);
                break;
            }
            case("color"):{
                if (node.getAttributevalues().getNodeType() != Variable.VarType.HexColor)
                    throw new RuntimeException("[Color] field received incompatible attribute type: "+node.getAttributevalues().getNodeType());
                curFigure.setFillingColor((HexColor)node.getAttributevalues().variable.clone());
                break;
            }
            case("border"):{
                if(node.getAttributevalues().getNodeType()== Variable.VarType.Int
                        || node.getAttributevalues().getNodeType()== Variable.VarType.SignedInt ){
                    curFigure.setBorder(new Border((Int)node.getAttributevalues().variable));
                }else if(node.getAttributevalues().getNodeType()== Variable.VarType.HexColor){
                    curFigure.setBorder(new Border((HexColor) node.getAttributevalues().variable));
                }
                else if (node.getAttributevalues().getNodeType() != Variable.VarType.Border) {
                    throw new RuntimeException("[Border] field received incompatible attribute type: " + node.getAttributevalues().getNodeType());
                }else{
                    curFigure.setBorder((Border)node.getAttributevalues().variable.clone());
                }
                break;
            }
            case("position"): {
                if (node.getAttributevalues().getNodeType() != Variable.VarType.UnsignedPairwise
                        && node.getAttributevalues().getNodeType() != Variable.VarType.SignedPairwise)
                    throw new RuntimeException("[Position] field received incompatible attribute type: " + node.getAttributevalues().getNodeType());
                curFigure.setPosition((Pairwise)node.getAttributevalues().variable.clone());
                break;
            }
            case("size"):{
                if (node.getAttributevalues().getNodeType() != Variable.VarType.UnsignedPairwise
                        && node.getAttributevalues().getNodeType() != Variable.VarType.SignedPairwise)
                    throw new RuntimeException("[Size] field received incompatible attribute type: "+node.getAttributevalues().getNodeType());
                curFigure.setSize((Pairwise) node.getAttributevalues().variable.clone());
                break;
            }
            case("points"):{
                if (node.getAttributevalues().getNodeType() != Variable.VarType.UnsignedPairwiseList)
                    throw new RuntimeException("[Points] field received incompatible attribute type: "+node.getAttributevalues().getNodeType());
                curFigure.setPoints((PairwiseList) node.getAttributevalues().variable.clone());
                break;
            }
            default:
                throw new RuntimeException("Assertion failed: lexer took in incorrect attribute name: "+attributeName.toString().trim());
        }

    }

    /* Type check and value assign in a assign command */
    public void inACommand(ACommand node) {
    }
    public void outACommand(ACommand node) {
        ArrayList<Variable> curScope = (ArrayList<Variable>) getCurrentSymbolScope();
        PAttributevalues  values = node.getAttributevalues();
        switch(values.getNodeType()) {
            case Int:
            case SignedInt:
            case SignedPairwise:
            case UnsignedPairwise:
            case UnsignedPairwiseList:
            case HexColor:
            case Figure:
            case Border: {
                curScope.add(values.variable.clone());
                getLastOfCurrentSymbolScope().setName(node.getIdentifier().getText().trim());
                break;
            }
            case Nil: {
                throw new RuntimeException("Compiler error in OutACommand! Unsupported datatype in " + node.getAttributevalues().toString());
            }
        }

    }

    public void inAObject(AObject node) {
        Variable outerObject = getLastOfFormerSymbolScope();
        this.runtimeStack.get(this.runtimeStack.size()-1).add(new FigureObject());
        if(outerObject!=null) {
            ((FigureObject) outerObject).setAsContainer();
            ((FigureObject) outerObject).addContent((FigureObject) getLastOfCurrentSymbolScope());
        }
        this.runtimeStack.add(new ArrayList<>());
        //printSymbolTable();
    }
    public void outAObject(AObject node){
        clearLastSymbolScope();
        FigureObject thisObject = (FigureObject)getLastOfCurrentSymbolScope();
        if(runtimeStack.size()>1) {
            ((FigureObject) getLastOfFormerSymbolScope()).setObjectClass(thisObject);
        }

        thisObject.setName(thisObject.getName().substring(2));
        thisObject.computeParameters();
        //print("Out object");
        //printSymbolTable();
    }
//
    private void clearLastSymbolScope(){
        this.runtimeStack.remove(this.runtimeStack.size()-1);
    }

    private List<Variable> getFormerSymbolScope(){
        return this.runtimeStack.get(this.runtimeStack.size()-2);
    }

    private Variable getLastOfFormerSymbolScope(){
        if(this.runtimeStack.size()<2)
            return null;
        return this.runtimeStack.get(this.runtimeStack.size()-2).get(this.runtimeStack.get(this.runtimeStack.size()-2).size()-1);
    }
    private Variable getLastOfCurrentSymbolScope(){
        return this.runtimeStack.get(this.runtimeStack.size()-1).get(this.runtimeStack.get(this.runtimeStack.size()-1).size()-1);
    }
    private List<Variable> getCurrentSymbolScope() {
        return this.runtimeStack.get(this.runtimeStack.size()-1);
    }
    private void inconsistentError(Variable.VarType a, Variable.VarType[] b){
        boolean noError = false;
        for(Variable.VarType type : b) {
            if (a == type) {
                noError = true;
                break;
            }
        }
        if (!noError) {
            throw new RuntimeException(ASSERT_ERROR);
        }
    }
    private Variable findIdentifier(String identifier){
        List<Variable> scopedSymbolTable;
        for (ListIterator<List<Variable>> itr = this.runtimeStack.listIterator(this.runtimeStack.size());
             itr.hasPrevious();) {
            scopedSymbolTable=itr.previous();
            if(scopedSymbolTable.size()==0)
                continue;
            for(Variable var : scopedSymbolTable){
                if(var.checkName(identifier)){
                    return var;
                }
            }
        }
        return null;
    }
    private Variable findIdentifierInScope(String identifier, List<Variable> scopedSymbolTable){
            for(Variable var : scopedSymbolTable){
                try {
                    if (var.checkName(identifier)) {
                        return var;
                    }
                }catch(RuntimeException ignored){}
            }
        return null;
    }
    private void print(Object str){System.out.println(str);}

    private void printSymbolTable(){
        List<Variable> scopedSymbolTable;
        if (this.runtimeStack.size() == 0) {
        } else {
            for (ListIterator<List<Variable>> itr = this.runtimeStack.listIterator(this.runtimeStack.size());
                 itr.hasPrevious(); ) {
                scopedSymbolTable = itr.previous();
                if (scopedSymbolTable.size() == 0) {
                    System.out.println("[ {EMPTY} ]");
                    continue;
                }
                scopedSymbolTable.forEach(s -> {
                    System.out.print("[" + s.getName() + " " + ((s instanceof FigureObject) ? (((FigureObject) s).isVisible() ? "(Visible)" : "(Hidden)") : "") + " ]");
                });
                System.out.println();
            }
            System.out.println("***Stack Printed");
        }
    }

    public List<List<Variable>> getRuntimeStack() {
        return runtimeStack;
    }

    public void inAListProgram(AListProgram node) {
        this.figuresToPrint = new ArrayList<>();
    }
    public void outAListProgram(AListProgram node) {
        setFiguresToPrint();
        //System.out.println(figuresToPrint.size());
        //figuresToPrint.forEach(System.out::println);
    }

    public List<FigureAttributes> figuresToPrint = new ArrayList<>();
    public void setFiguresToPrint(){
        runtimeStack.get(0).forEach(s->{
            if(s instanceof FigureObject && ((FigureObject) s).isVisible()){
               ((FigureObject) s).getDecodedAttributes().forEach(attr->{
                   if(attr.display)
                       figuresToPrint.add(attr);
               });
            }
        });
    }
}