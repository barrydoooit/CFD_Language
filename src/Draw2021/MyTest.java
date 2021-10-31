package Draw2021;

import drawlang.Compiler;

import java.io.*;

import java.awt.*;
import javax.swing.*;

public class MyTest extends DrawEngine {

	public MyTest() {
		super("Drawing Demo");
	}
	
	public void drawObjects(Graphics2D g2d) {
		Compiler compiler = new Compiler();
		try {
			compiler.compile(new BufferedReader(new FileReader("input files/Test5_3_musicalnotes.txt")), 1024);
		}catch(FileNotFoundException f){
			System.out.println("Error! File [ input.txt ] not found!");
		}
		System.out.println("entered drawing");
		compiler.getSemanticAnalyzer().figuresToPrint.forEach(f->{
			f.printElement(g2d);
		});

	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {new MyTest().setVisible(true);}}
		);
	}


}