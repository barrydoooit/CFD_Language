package Utils;

public class Int extends Variable{
    int value;
    Signed isSigned;
    public static final String POS_SIGN = "+";
    private static long IntId = 0;
    public Int(String identifier, int value, Signed isSigned){
        super(identifier, isSigned==Signed.Unsigned?VarType.Int:VarType.SignedInt);
        this.value = value;
        this.isSigned = isSigned;
    }
    public Int(String identifier, int value){
        super(identifier, VarType.Int);
        this.value = value;
        this.isSigned = Signed.Unsigned;
    }
    public Int(int value, Signed isSigned){
        super(isSigned==Signed.Unsigned?VarType.Int:VarType.SignedInt);
        this.value = value;
        this.isSigned = isSigned;
    }
    public Int(int value){
        super(VarType.Int);
        this.value = value;
        this.isSigned = Signed.Unsigned;
    }

    public enum Signed{
        Positive,
        Negative,
        Unsigned,
        Zero
    }

    public Int clone(){
        return new Int(value, isSigned);
    }
}
