package Utils;

import javafx.util.Pair;

public class Pairwise extends Variable {
    private int onX=0;
    private int onY=0;
    private boolean isRelative=true;
    public Pairwise(String identifier, int onX, int onY, boolean isRelative){
        super(identifier, isRelative?VarType.SignedPairwise:VarType.UnsignedPairwise);
        this.onX = onX;
        this.onY = onY;
        this.isRelative = isRelative;
    }
    public Pairwise(int onX, int onY, boolean isRelative){
        super(isRelative?VarType.SignedPairwise:VarType.UnsignedPairwise);
        this.onX = onX;
        this.onY = onY;
        this.isRelative = isRelative;
    }

    public Pairwise(Int a, Int b){
        super(VarType.SignedPairwise);
        if(a.isSigned== Int.Signed.Unsigned && b.isSigned!= Int.Signed.Unsigned
                || b.isSigned== Int.Signed.Unsigned && a.isSigned!= Int.Signed.Unsigned){
            throw new RuntimeException("Compiler error! Pairwise values not compatible");
        }
        switch(a.isSigned){
            case Negative:{
                this.onX = -a.value;
                break;
            }
            case Zero:{
                this.onX = 0;
                break;
            }
            case Positive:{
                this.onX = a.value;
                break;
            }
            case Unsigned:{
                this.onX = a.value;
                this.isRelative = false;
                this.setVarType(VarType.UnsignedPairwise);
                break;
            }
            default: throw new RuntimeException("Compiler inner error! Unknown Enum Type of SIGNED");
        }
        switch(b.isSigned){
            case Negative:{
                this.onY = -b.value;
                break;
            }
            case Zero:{
                this.onY = 0;
                break;
            }
            case Positive:{
                this.onY = b.value;
                break;
            }
            case Unsigned:{
                this.onY = b.value;
                assert(!this.isRelative);
                break;
            }
            default: throw new RuntimeException("Compiler inner error! Unknown Enum Type of SIGNED");
        }
    }

    public boolean isRelative() {
        return isRelative;
    }
    public int getOnX() {
        return onX;
    }
    public int getOnY() {
        return onY;
    }

    @Override
    public String toString() {
        return super.toString()+"->Pairwise{" +
                "onX=" + onX +
                ", onY=" + onY +
                ", isRelative=" + isRelative +
                '}';
    }

    public Pairwise clone(){
        return new Pairwise(this.onX,this.onY,this.isRelative);
    }
}
