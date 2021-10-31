package Draw2021;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import javax.swing.*;

public class Test extends DrawEngine {

	public Test() {
		super("Java Drawing Test");
	}
	
	public void drawObjects(Graphics2D g2d) {
		//g2d.setBackground(Color.WHITE);
		
		// ADD/REPLACE code in the following to draw
/////////////////////////////////////////////////////////////////////////
//declaration
task52 declaration = new task52();
//shrunk figure
task52 shrunk = declaration.clone();
shrunk.enlargeOrShrink(-100, -100);
shrunk.fillingColor="#27BD67";
GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, shrunk.pointsX.length);
polyline.moveTo(shrunk.pointsX[0], shrunk.pointsY[0]);
for (int idx = 1; idx < shrunk.pointsX.length; idx++){
	polyline.lineTo(shrunk.pointsX[idx], shrunk.pointsY[idx]);
}
g2d.setColor(Color.decode(shrunk.fillingColor));
g2d.fill(polyline);
g2d.setStroke(new BasicStroke(shrunk.lineWidth));
g2d.setColor(Color.decode(shrunk.lineColor));
g2d.draw(polyline);
//enlarged figure
task52 enlarged = declaration.clone();
enlarged.setPosition(50, 150, false);
enlarged.enlargeOrShrink(100, 100);
enlarged.lineColor = "#DF73E5";
enlarged.lineWidth+=10;
polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, enlarged.pointsX.length);
polyline.moveTo(enlarged.pointsX[0], enlarged.pointsY[0]);
for (int idx = 1; idx < enlarged.pointsX.length; idx++){
	polyline.lineTo(enlarged.pointsX[idx], enlarged.pointsY[idx]);
}
g2d.setColor(Color.decode(enlarged.fillingColor));
g2d.fill(polyline);
g2d.setStroke(new BasicStroke(enlarged.lineWidth));
g2d.setColor(Color.decode(enlarged.lineColor));
g2d.draw(polyline);
//moved shrunk figure
shrunk.setPosition(250, 0, true);
shrunk.fillingColor="#ffffff";
polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, shrunk.pointsX.length);
polyline.moveTo(shrunk.pointsX[0], shrunk.pointsY[0]);;
for (int idx = 1; idx < shrunk.pointsX.length; idx++){
	polyline.lineTo(shrunk.pointsX[idx], shrunk.pointsY[idx]);
}
g2d.setColor(Color.decode(shrunk.fillingColor));
g2d.fill(polyline);
g2d.setStroke(new BasicStroke(shrunk.lineWidth));
g2d.setColor(Color.decode(shrunk.lineColor));
g2d.draw(polyline);
//moved enlarged figure
enlarged.setPosition(250, 0, true);
enlarged.fillingColor="#ffffff";
polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, enlarged.pointsX.length);
polyline.moveTo(enlarged.pointsX[0], enlarged.pointsY[0]);
for (int idx = 1; idx < enlarged.pointsX.length; idx++){
	polyline.lineTo(enlarged.pointsX[idx], enlarged.pointsY[idx]);
}
g2d.setColor(Color.decode(enlarged.fillingColor));
g2d.fill(polyline);
g2d.setStroke(new BasicStroke(enlarged.lineWidth));
g2d.setColor(Color.decode(enlarged.lineColor));
g2d.draw(polyline);

/////////////////////////////////////////////////////////////////////////
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {new Test().setVisible(true);}}
		);
	}
class task52{
	int[] pointsX = {0 ,50, 70, 200};int[] pointsY = {0, 90, 200, 50};
	int lineWidth = 10;String lineColor = "#66ccff";
	String fillingColor = "#D34D34";
	int posX = 50;int posY = 50;int sizeX = 0;int sizeY = 0;
	public task52(){
		for(int i=0; i<pointsX.length; i++){
			pointsX[i]+=posX;
			pointsY[i]+=posY;
		}
		setSize();
	}
	public task52(int[] pointsX, int[] pointsY, int lineWidth, String lineColor, int posX, int posY, int sizeX, int sizeY){
		for(int i=0; i<pointsX.length; i++) {
			this.pointsX[i] = pointsX[i];
			this.pointsY[i] = pointsY[i];
		}
		this.lineWidth = lineWidth;
		this.lineColor = new String(lineColor);
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	private void setSize(){
		int max=0, min=Integer.MAX_VALUE;
		for(int x : pointsX){
			if(x>max) max=x;
			if(x<min) min=x;
		}
		sizeX = max-min;
		max=0;	min=Integer.MAX_VALUE;
		for(int y : pointsY){
			if(y>max)	max=y;
			if(y<min)	min=y;
		}
		sizeY = max-min;
	}
	public void setPosition(int x, int y, boolean isMovement){
		if(isMovement) {
			posX+=x;	posY+=y;
			for(int i=0; i<pointsX.length; i++) {pointsX[i]+=x;pointsY[i]+=y;}
		}else{
			for(int i=0; i<pointsX.length; i++){	pointsX[i]=pointsX[i]+x-posX;pointsY[i]=pointsY[i]+y-posY;}
			posX=x;posY=y;
		}
	}
	public void enlargeOrShrink(int x, int y){
		for(int i=0; i<pointsX.length; i++) {
			pointsX[i]=((int) (posX + (pointsX[i] - posX) * (((float) (sizeX + x) / sizeX))));
			pointsY[i]=((int) (posY + (pointsY[i] - posY) * (((float) (sizeY + y) / sizeY))));
		}
		setSize();
	}
	public task52 clone(){
		return new task52(this.pointsX, this.pointsY, this.lineWidth, this.lineColor, this.posX, this.posY, this.sizeX, this.sizeY);
	}
}
}