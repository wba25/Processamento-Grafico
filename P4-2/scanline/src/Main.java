import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	public static final int CANVAS_WIDTH  = 600;
	public static final int CANVAS_HEIGHT = 600;
	public static final int PIXEL_SIZE=2;
	
	public static final int ORIGIN_X_AXIS = 300;
	public static final int ORIGIN_Y_AXIS = 300;
	
	private DrawCanvas canvas;
	
	public Main(){
		canvas = new DrawCanvas();
	    canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	    Container cp = getContentPane();
	    cp.add(canvas);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    pack();              
	    setTitle("P4-2");  
	    setVisible(true);    
	}
	
	private class DrawCanvas extends JPanel {
		
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);
	         setBackground(Color.WHITE);
	         
	         drawOrigin(g);
	         scanline(g);
	         //drawCube(g);
	         
	      }
	      public void drawOrigin(Graphics g) {
	    	  	g.setColor(Color.BLUE);
	    	  	g.fillRect(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 5, 5);
	      }
	      public void drawCube(Graphics g) {
	    	  	Point3D a, b, c, d;
	    	  	
	    	  	
	    	  	a=new Point3D(0,100,0);
	    	  	b=new Point3D(100,100,0);
	    	  	c=new Point3D(0,0,0);
	    	  	d=new Point3D(100,0,0);
	    	  	
	    	  	projection(g, a, 80);
	    	  	projection(g, b, 80);
	    	  	projection(g, c, 80);
	    	  	projection(g, d, 80);
	    	  	
	      }
	      public void drawPoint(Graphics g, Point2D p) {
	    	  	
	    	  	g.setColor(Color.RED);
	    	  	g.fillRect(p.x, p.y, PIXEL_SIZE, PIXEL_SIZE);
	      
	      }
	      public void drawPoint(Graphics g, int x, int y) 
	      {
	    	  	
	    	  	g.setColor(Color.BLACK);
	    	  	g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
	    	  	
	      }
	      public void scanline(Graphics g) {
	  		
	  		float[] x= {0, 100, 200};
	  		float[] y= {0, 100, 20};
	  		float[] z= {0, 0, 100};
	  		
	  		float[][] a= {x, y, z};
	  		
	  		Matrix m= new Matrix();
	  		
	  		a=m.changeCoordinateSystem(a, ORIGIN_X_AXIS, ORIGIN_Y_AXIS);
	  		
	  		plot(g, a);
	  		
	  	  }
	      public void plot(Graphics g,float[][] m) {
	    	  	float[] x=m[0];
	    	  	float[] y=m[1];
	    	  	float[] z=m[2];
	    	  	
	    	  	Point2D p=null;
	    	  	ArrayList<Point2D> vertex=new ArrayList<Point2D>();
	    	  	for(int i=0; i<3;i++) {
	    	  		
	    	  		p=new Point2D((int)x[i],(int)y[i]);
	    	  		vertex.add(p);
	    	  		drawPoint(g,p);
	    	  	}
	    	  	for(int i=0;i<vertex.size();i++) {
	    	  		if(i-1>=0) {
	    	  			drawRect(g, vertex.get(i-1), vertex.get(i));
	    	  		}
	    	  	}
	    	  	drawRect(g, vertex.get(0), vertex.get(2));
	    	  	
	      }
	      public Point2D projection(Graphics g, Point3D p, double distance) {
	    	  	double xp, yp;	    	  	
	    	  	Point2D projPoint=null;
	    	  	if(p.z!=0) {
	    	  		xp=(p.x/p.z)*distance;
	    	  		yp=(p.y/p.z)*distance;
	    	  		
	    	  	}
	    	  	else {
	    	  		xp=p.x;
	    	  		yp=p.y;
	    	  	}
	    	  	
	    	  	yp= yp*(-1);
	    	  	xp=xp+ORIGIN_X_AXIS;
	    	  	yp=yp+ORIGIN_Y_AXIS;
	    	  	
	    	  	projPoint=new Point2D((int)xp,(int)yp);
	    	  	drawPoint(g, projPoint);
	    	  	
	    	  	return projPoint;
	      }
	      public void drawRect(Graphics g, Point2D p1, Point2D p2) {
	    	  		
	    	  		drawPoint(g, p1);
	    	  		drawPoint(g, p2);
	    	  		
	    	  		Point2D[] arg= {p1, p2};
	    	  		
	    	  		Point2D p=null;
	    	  		
	    	  		int begin=0;
	    	  		int end=0;
	    	  		
	    	  		if(p1.x<p2.x) {
	    	  			p=p1;
	    	  			end=p2.x;
	    	  			begin=p1.x;
	    	  		}else {
	    	  			p=p2;
	    	  			end=p1.x;
	    	  			begin=p2.x;
	    	  		}
	    	  		
	    	  		if(p1.x-p2.x==0) {
	    	  			begin=Math.min(p1.y, p2.y);
	    	  			end=Math.max(p1.y, p2.y);
	    	  			
	    	  			for(int y=begin; y<end;y=y+PIXEL_SIZE) {
	    	  				drawPoint(g, p1.x, y);
	    	  			}
	    	  		}else if(p1.y-p2.y==0) {
	    	  			begin=Math.min(p1.x, p2.x);
	    	  			end=Math.max(p1.x, p2.x);
	    	  			for(int x=begin; x<end;x=x+PIXEL_SIZE) {
	    	  				drawPoint(g, x, p1.y);
	    	  			}
	    	  			
	    	  		}else {
		    	  		int y=0;
		    	  		for(int x=begin; x<end;x=x+PIXEL_SIZE) {
		    	  			System.out.println("begin: "+begin+", end: "+end+". "+x+" "+f(x,arg));
		    	  			y=(int)f(x, arg);
		    	  			drawPoint(g, x,  y);
		    	  		}
		    	  		
		    	  		
	    	  		}
	      }
	      
	      
	      public double f(int x, Point2D arg[]) {
	    	  
	    	  	double y, a, b;
	  		
	    	  	double x1, y1, x2, y2;
	    	  	x1=arg[0].x;
	    	  	y1=arg[0].y;
	    	  	x2=arg[1].x;
	    	  	y2=arg[1].y;
	    	  	a=(y2-y1)/(x2-x1);
	    	  	b=(y1-a*x1);
	    	  	
	    	  	y=a*x+b;
	  		
	  		return y;
	  	}
	}
	
	public static void main(String[] args) {
		Main frame= new Main();
		frame.setVisible(true);
	}
}
class Point2D{
	int x;
	int y;
	public Point2D(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
}
class Point3D{
	int x;
	int y;
	int z;
	public Point3D(int x, int y, int z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
}
