package Utils;

public class Variable {
    private String identifier;
    private VarType varType=VarType.Nil;
    private static long VarID = 0;
    public Variable(String identifier, VarType type){
        this.identifier = identifier;
        this.varType = type;
    }
    public Variable(){
        this.identifier = "$$"+Long.toString(VarID++);
    }
    public Variable(VarType type){
        this();
        this.varType = type;
    }
    public boolean checkName(String name){
        if(this.identifier==null)
            throw new RuntimeException("This variable is unset! Invalid usage.");
        return name.trim().equals(this.identifier.trim());
    }
    public String getName(){
        if(this.identifier==null)
            throw new RuntimeException("This variable name is unset! Invalid usage.");
        return this.identifier;
    }
    public void setName(String identifier) {
        this.identifier = identifier;
    }
    public VarType getVarType() {
        return varType;
    }
    public void setVarType(VarType varType) {
        this.varType = varType;
    }
    public Variable clone(){
        return this; //TODO
    }

    @Override
    public String toString() {
        return "Variable{" +
                "identifier='" + identifier + '\'' +
                ", varType=" + varType +
                '}';
    }

    public enum VarType {
        Int,
        SignedInt,
        UnsignedPairwise,
        SignedPairwise,
        UnsignedPairwiseList,
        HexColor,
        Figure,
        Border,
        Nil
    }

}


