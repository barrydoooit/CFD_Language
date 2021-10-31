package Utils;

public class HexColor extends Variable{

    int value=-1;
    public HexColor(String identifier, String hexValue){
        super(identifier, VarType.HexColor);
        this.value = hexToInt(hexValue);
    }
    public HexColor(String hexValue){
        super(VarType.HexColor);
        this.value = hexToInt(hexValue);
    }
    public static HexColor copy(HexColor color){
        return new HexColor(color.getName(), color.toHexColor());
    }
    public int hexToInt(String hexValue){
        return Integer.parseInt(hexValue.substring(1,7),16);
    }

    public String toHexColor(){
        if(this.value>=0){
            return String.format("#%1$06X",this.value);
        }else
            throw new NullPointerException("Color object has not been initialized!");
    }

    public static final HexColor DEFAULT_COLOR = new HexColor("$DEFAULT_COLOR", "#000000");
    public HexColor clone(){
        return new HexColor(this.toHexColor());
    }
}
