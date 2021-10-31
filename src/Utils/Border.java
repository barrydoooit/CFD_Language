package Utils;

public class Border extends Variable{
    private Int width;
    private HexColor color;
    private boolean isRelative = false;
    private static final Int DEFAULT_WIDTH = new Int(2);
    public Border(String identifier, int width, Int.Signed isSigned, HexColor color){
        super(identifier, VarType.Border);
        this.width = new Int(width, isSigned);
        this.color = HexColor.copy(color);
    }
    public Border(Int width, HexColor color){
        super(VarType.Border);
        this.width=(Int)width.clone();
        this.color=(HexColor)color.clone();
    }
    public Border(Int width){
        this(width, HexColor.DEFAULT_COLOR);
    }
    public Border(HexColor color){
        this(DEFAULT_WIDTH, color);
    }
    @Override
    public Border clone(){
        return new Border(this.width.clone(), this.color.clone());
    }

    public Int getWidth() {
        return width;
    }

    public HexColor getColor() {
        return color;
    }
}
