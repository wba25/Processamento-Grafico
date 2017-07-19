package API;

import Models.Ponto3D;

public class ProjecaoPontos extends MatrixUtil{
	
	public static Ponto3D projetar2D(Ponto3D p, double d, double Hx, double  Hy){
		
		double x = (d/Hx)*(p.x/p.z);
		double y = (d/Hy)*(p.y/p.z);
		
		Ponto3D p2 = new Ponto3D(x,y);
		p2.indice = p.indice;
		
		return p2;
	}
	
	public static Ponto3D map2Screen(Ponto3D p, int resX, int resY){
		Ponto3D p2  = null;
		p2 = new Ponto3D(0,0);
		p2.x = ((p.x+1)/2)*resX;
		p2.y = ((1-p.y)/2) *resY;
		p2.indice = p.indice;
		
		return p2;
	}
	
	//metodo inutil VV
	public static boolean isOnWindow(Ponto3D p, double d, double hx, double hy){
		//fiz uma mudan�a de acordo com romero sobre a janela de visualiza��o
		return (p.x<=hx && p.x>=-hx && p.y<=hy && p.y>=-hy);
	}
	
	
	
	
}
