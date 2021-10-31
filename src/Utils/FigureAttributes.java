package Utils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class FigureAttributes {
    public int anchorX = 0;
    public int anchorY = 0;
    public int posX=0;
    public int posY=0;
    public boolean display=false;
    public int sizeX=100;
    public int sizeY=100;
    public String lineColor=HexColor.DEFAULT_COLOR.toHexColor();
    public String fillingColor=null;
    public int lineWidth=2;
    public List<Integer> pointsX;
    public List<Integer> pointsY;
    public FigureType figureType = null;
    public FigureAttributes(){
        this.pointsX = new ArrayList<>();
        this.pointsY = new ArrayList<>();
    }

    public FigureAttributes clone(){

        FigureAttributes attr = new FigureAttributes();
        attr.posX=this.posX;
        attr.posY=this.posY;
        attr.display=this.display;
        attr.sizeX=this.sizeX;
        attr.sizeY=this.sizeY;
        attr.lineColor=this.lineColor;
        attr.fillingColor=this.fillingColor;
        attr.lineWidth=this.lineWidth;
        if(this.pointsX.size()>0) {
            attr.pointsX.addAll(this.pointsX);
            attr.pointsY.addAll(this.pointsY);
        }
        attr.figureType=this.figureType;
        return attr;
    }
    public void addPoints(List<Integer> pointsX, List<Integer> pointsY){
        pointsX.forEach(x->{
            this.pointsX.add(x+posX);
        });
        pointsY.forEach(y->{
            this.pointsY.add(y+posY);
        });
        setSize();
    }
    private void setSize(){
        int max=0, min=Integer.MAX_VALUE;
        for(int x : pointsX){
            if(x>max)
                max=x;
            if(x<min)
                min=x;
        }
        sizeX = max-min;
        max=0;
        min=Integer.MAX_VALUE;
        for(int y : pointsY){
            if(y>max)
                max=y;
            if(y<min)
                min=y;
        }
        sizeY = max-min;
    }
    public void setBorder(Border border){
        Int width = border.getWidth();
        HexColor color = border.getColor();
        switch(width.isSigned){
            case Unsigned:{
                this.lineWidth = width.value;
                break;
            }
            case Positive:{
                this.lineWidth += width.value;
                break;
            }
            case Negative:{
                this.lineWidth -= width.value;
                break;
            }
            case Zero:{
                break;
            }
        }
        this.lineColor = color.toHexColor();
    }
    public void setPosition(Pairwise pos){
        if(pos.getVarType() == Variable.VarType.UnsignedPairwise){
            if(pointsX.size()>0) {
                List<Integer> temp = new ArrayList<>();
                pointsX.forEach(x -> {
                    temp.add(x+pos.getOnX()-anchorX);
                });
                this.pointsX.clear();
                this.pointsX.addAll(temp);
                temp.clear();
                pointsY.forEach(y -> {
                    temp.add(y+pos.getOnY()-anchorY);
                });
                this.pointsY.clear();
                this.pointsY.addAll(temp);
            }
            if(pointsX.size()==0)
                System.out.println("before posX: "+this.posX+ " AnchorX:" + this.anchorX);
            this.posX += (pos.getOnX()-this.anchorX);
            this.posY += (pos.getOnY()-this.anchorY);
            this.anchorX=pos.getOnX();
            this.anchorY=pos.getOnY();
            if(pointsX.size()==0)
            System.out.println("after posX: "+this.posX+ " AnchorX:" + this.anchorX);
        }else{
            if(pointsX.size()>0) {
                List<Integer> temp = new ArrayList<>();
                pointsX.forEach(x -> temp.add(x+pos.getOnX()));
                this.pointsX.clear();
                this.pointsX.addAll(temp);
                temp.clear();
                pointsY.forEach(y -> temp.add(y+pos.getOnY()));
                this.pointsY.clear();
                this.pointsY.addAll(temp);
            }
            this.posX+=pos.getOnX();
            this.posY+=pos.getOnY();
        }
    }
    public void setSize(Pairwise size){
        if(size.getVarType()== Variable.VarType.UnsignedPairwise){
            if(pointsX.size()>0){
                List<Integer> temp = new ArrayList<>();
                pointsX.forEach(x ->temp.add(posX+(x-posX)*(size.getOnX())/sizeX));
                this.pointsX.clear();
                this.pointsX.addAll(temp);
                temp.clear();
                pointsY.forEach(y ->temp.add(posY+(y-posY)*(size.getOnY())/sizeY));
                this.pointsY.clear();
                this.pointsY.addAll(temp);
                temp.clear();
                setSize();
            }else{
                sizeX = size.getOnX();
                sizeY = size.getOnY();
            }
        }else{
            if(pointsX.size()>0){
                List<Integer> temp = new ArrayList<>();
                pointsX.forEach(x ->temp.add((int)(posX+(x-posX)*(((float)(sizeX+size.getOnX())/sizeX)))));
                this.pointsX.clear();
                this.pointsX.addAll(temp);

                temp.clear();
                pointsY.forEach(y ->temp.add((int)(posY+(y-posY)*(((float)(sizeY+size.getOnY())/sizeY)))));
                this.pointsY.clear();
                this.pointsY.addAll(temp);
                setSize();
            }else{
                sizeX += size.getOnX();
                sizeY += size.getOnY();
            }
        }
        if(sizeX<=0 || sizeY<=0)
            throw new RuntimeException("Size of figure must be positive!");
    }

    @Override
    public String toString() {
        return "FigureAttributes{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", display=" + display +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", lineColor='" + lineColor + '\'' +
                ", fillingColor='" + fillingColor + '\'' +
                ", lineWidth=" + lineWidth +
                ", pointsX=" + pointsX +
                ", pointsY=" + pointsY +
                ", figureType=" + figureType +
                '}';
    }

    public FigureAttributes setDisplay(boolean display) {
        this.display = display;
        return this;
    }

    private boolean isClosed(){
        if(figureType==FigureType.Polygon){
            return pointsX.get(0).equals(pointsX.get(pointsX.size()-1))
                    && pointsY.get(0).equals(pointsY.get(pointsY.size()-1));
        }else{
            throw new RuntimeException("Cannot check the closeness of this element!");
        }
    }

    public enum FigureType{
        Circle,
        Rectangle,
        Polygon
    }

    public void printElement(Graphics2D g2d){
        g2d.setColor(Color.decode(this.lineColor));
        g2d.setStroke(new BasicStroke((float)this.lineWidth));
        switch(this.figureType){
            case Circle:{
                Shape circle = new Ellipse2D.Double(posX+(float)lineWidth/2,
                        posY+(float)lineWidth/2,
                        sizeX-(float)lineWidth,
                        sizeY-(float)lineWidth);
                g2d.draw(circle);
                if(this.fillingColor!=null){
                    g2d.setColor(Color.decode(this.fillingColor));
                    g2d.fillOval(posX+lineWidth,
                            posY+lineWidth,
                            sizeX-lineWidth*2,
                            sizeY-lineWidth*2);
                }
                break;
            }
            case Rectangle:{
                Shape rect = new Rectangle2D.Double(posX+(float)lineWidth/2,
                        posY+(float)lineWidth/2,
                        sizeX-(float)lineWidth,
                        sizeY-(float)lineWidth);
                g2d.draw(rect);
                if(this.fillingColor!=null){
                    g2d.setColor(Color.decode(this.fillingColor));
                    g2d.fillRect(posX+lineWidth,
                            posY+lineWidth,
                            sizeX-lineWidth*2,
                            sizeY-lineWidth*2);
                }
                break;
            }
            case Polygon:{
                if(this.isClosed()){
                    GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, pointsX.size());
                    polygon.moveTo(pointsX.get(0).doubleValue(), pointsY.get(0).doubleValue());
                    for (int idx = 1; idx < pointsX.size()-1; idx++){
                        polygon.lineTo(pointsX.get(idx).doubleValue(), pointsY.get(idx).doubleValue());
                    }
                    polygon.closePath();
                    //g2d.draw(polygon);
                    if(this.fillingColor!=null){
                        g2d.setColor(Color.decode(fillingColor));
                        g2d.fill(polygon);
                    }
                    g2d.setColor(Color.decode(this.lineColor));
                    g2d.draw(polygon);
                }else{
                    GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, pointsX.size());
                    polyline.moveTo(pointsX.get(0).doubleValue(), pointsY.get(0).doubleValue());
                    for (int idx = 1; idx < pointsX.size(); idx++){
                        polyline.lineTo(pointsX.get(idx).doubleValue(), pointsY.get(idx).doubleValue());
                    }
                    //g2d.draw(polyline);
                    if(this.fillingColor!=null){
                        g2d.setColor(Color.decode(fillingColor));
                        g2d.fill(polyline);
                    }
                    g2d.setColor(Color.decode(this.lineColor));
                    g2d.draw(polyline);
                }
                break;
            }
            default:{
                throw new RuntimeException("Usage of printElement on wrong object!");
            }
        }
    }
}
