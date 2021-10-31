package Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FigureObject extends Variable{
    public static final String VISIBLE_SIGN = ">";
//也许可以给分三种，points指定， size指定，容器类来讨论。
    private boolean isContainer = false;
    private ArrayList<FigureObject> contentList;
    private boolean visible;
    private List<FigureAttributes> decodedAttributes = new ArrayList<>();

    public FigureObject(String name, boolean visible, ArrayList<FigureObject> contentList, FigureAttributes.FigureType figureType){
        super(name, VarType.Figure);
        this.contentList = new ArrayList<>();
        if(contentList!=null) {
            this.contentList.addAll(contentList);
        }
        else if(figureType!=null){
            this.decodedAttributes.add(new FigureAttributes());
            this.decodedAttributes.get(0).figureType = figureType;
        }
        this.visible = visible;
    }

    public FigureObject(boolean visible, ArrayList<FigureObject> contentList){
        super(VarType.Figure);
        this.contentList = new ArrayList<>();
        if(contentList!=null)
            this.contentList.addAll(contentList);
        this.visible = visible;
    }

    public FigureObject(){
        super(VarType.Figure);
        this.contentList = new ArrayList<>();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void addContent(FigureObject newContent){
        this.contentList.add(newContent);
    }

    public ArrayList<FigureObject> getContentList() {
        return contentList;
    }
    public void setAsContainer(){
        this.isContainer=true;
    }
    public boolean isContainer() {
        return isContainer;
    }
    private FigureObject objectClass=null;
    private HexColor fillingHexColor=null;
    private Border border=null;
    private Pairwise position=null;
    private Pairwise size=null;
    private PairwiseList points=null;

    public FigureObject getObjectClass() {
        return objectClass;
    }
    public void setObjectClass(FigureObject obj){
        this.objectClass=obj;
    }
    public void setFillingColor(HexColor color){
        this.fillingHexColor = color;
    }
    public void setBorder(Border border){
        this.border = border;
    }
    public void setPosition(Pairwise pos){
        this.position = pos;
    }
    public void setSize(Pairwise size){
       this.size = size;
    }
    public void setPoints(PairwiseList pList){
        this.points = pList;
    }
    public void computeParameters(){
        if(this.isContainer) {
            if(this.objectClass.isContainer()){
                for (FigureObject obj : this.getContentList()) {
                    obj.getDecodedAttributes().forEach(a -> {
                        this.decodedAttributes.add(a.clone().setDisplay(this.objectClass.isVisible() && a.display));
                    });
                }
            }else {
                for (FigureObject obj : this.getContentList()) {
                    obj.getDecodedAttributes().forEach(a -> {
                        this.decodedAttributes.add(a.clone().setDisplay(a.display));
                    });
                }
            }
        }else{
            if(this.objectClass==null)
                throw new RuntimeException("Error! Object Class remains unset!");
            if(this.objectClass==POLYGON && this.points.size()<=1)
                throw new RuntimeException("Error! Polygon needs points designated!!");
            for (FigureAttributes attr: this.objectClass.getDecodedAttributes()) {
                //System.out.println(attr);
                if(this.objectClass.isContainer()){
                    this.decodedAttributes.add(attrTransform(attr).setDisplay(this.isVisible()&&attr.display));
                }else {
                    this.decodedAttributes.add(attrTransform(attr).setDisplay(this.isVisible()));
                }
            }
        }
    }

    public List<FigureAttributes> getDecodedAttributes() {
        return decodedAttributes;
    }

    private FigureAttributes attrTransform(FigureAttributes attr){
        FigureAttributes newAttr = attr.clone();
        if(this.fillingHexColor!=null){
            newAttr.fillingColor = fillingHexColor.toHexColor();
        }
        if(this.border!=null){
            newAttr.setBorder(this.border);
        }
        if(this.objectClass == POLYGON && this.points!=null){
            newAttr.addPoints(this.points.valuesOnX, this.points.valuesOnY);
        }
        if(this.position!=null){
            newAttr.setPosition(this.position);
        }
        if(this.size!=null){
            newAttr.setSize(this.size);
        }
        newAttr.figureType = attr.figureType;
        //System.out.println("After decode: ");
        //System.out.println(newAttr);
        return newAttr;
    }

    public static final String CircleKeyword = "Circle";
    public static final String RectKeyword = "Rectangle";
    public static final String PolygonKeyword = "Polygon";
    public static final FigureObject CIRCLE=new FigureObject(CircleKeyword, false, null, FigureAttributes.FigureType.Circle);
    public static final FigureObject RECTANGLE=new FigureObject(RectKeyword , false, null, FigureAttributes.FigureType.Rectangle);
    public static final FigureObject POLYGON=new FigureObject(PolygonKeyword , false, null,  FigureAttributes.FigureType.Polygon);

    public FigureObject clone(){
        return this;
    }

    @Override
    public String toString() {
        return "FigureObject{"+getName()+"}";
    }

}
