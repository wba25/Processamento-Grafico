package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import Models.Camera;
import Models.Plano;
import Models.Ponto3D;
import Models.Triangulo;

public class Phong extends JFrame{

	public double[][] z_buffer;
	public ArrayList<Triangulo> t;
	public ArrayList<Triangulo> t2;
	public int ResX = 640;
	public int ResY = 480;
	int qtdPontos =0;
	BufferedImage objeto;

	public Phong(int x, int y, int resX, int resY){
		super("Sombra no plano");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, resX, resY);
		this.ResX = resX;
		this.ResY = resY;

		// Declara z-buffer
		z_buffer = new double[ResX+1][ResY+1];
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(objeto, 0, 0, null);
	}

	public void scanLine3D(){
		objeto = new BufferedImage(ResX+1, ResY+1, BufferedImage.TYPE_INT_ARGB); 

		this.t = Camera.triangulosConvertidos;
		this.t2 = Camera.triangulos2D;

		// Inicia z-buffer
		for(double[] row: z_buffer)
			Arrays.fill(row, Double.MAX_VALUE);

		for(int i=0;i<t2.size()-2;i++){
			pinte(Camera.intervalos.get(i),t.get(i).indice, i);
		}

		pintePlano(Camera.intervalos.get(t2.size()-1),t2.get(t2.size()-1).indice);
		pintePlano(Camera.intervalos.get(t2.size()-2),t2.get(t2.size()-2).indice);

		fix();
	}

	private void pintePlano(Ponto3D[][] intervalos, int indice) {
		for(int i=0;i<intervalos.length;i++){
			if(intervalos[i][0]!=null){
				for(int j= (int) intervalos[i][0].x;j<=intervalos[i][1].x;j++){
					Ponto3D pixel = new Ponto3D(j, intervalos[i][0].y);

					int x = (int) pixel.x;
					int y = (int) pixel.y;

					if(Plano.existIntersecao(pixel)){
						// É sombra:
						// Pintar apenas a componente ambiental
						if(x>=0 && x<=ResX && y>=0 && y<=ResY ){
							double[] bary = t2.get(indice).getBaryCoefs(pixel);
							if (bary[0] < 0 || bary[1] < 0 || bary[2] < 0) continue;

							Ponto3D v1 = t.get(indice).v1;
							Ponto3D v2 = t.get(indice).v2;
							Ponto3D v3 = t.get(indice).v3;
							Ponto3D p = v1.multiply(bary[0]).add(v2.multiply(bary[1])).add(v3.multiply(bary[2]));

							if(x <= ResX && y <= ResY && z_buffer[x][y]>p.z && p.z>0){
								z_buffer[x][y] = p.z;
								qtdPontos++;
								int rgb = new Color(0,0,0).getRGB();
								objeto.setRGB(x, y, rgb);
							}
						}
					}
					else {
						// Não é sombra
						// Pinta-se com a equação de iluminação completa
						if(x>=0 && x<=ResX && y>=0 && y<=ResY ){
							double[] bary = t2.get(indice).getBaryCoefs(pixel);
							if (bary[0] < 0 || bary[1] < 0 || bary[2] < 0) continue;

							Ponto3D v1 = t.get(indice).v1;
							Ponto3D v2 = t.get(indice).v2;
							Ponto3D v3 = t.get(indice).v3;
							Ponto3D p = v1.multiply(bary[0]).add(v2.multiply(bary[1])).add(v3.multiply(bary[2]));

							if(x <= ResX && y <= ResY && z_buffer[x][y]>p.z && p.z>0){
								z_buffer[x][y] = p.z;
								qtdPontos++;
								int rgb = new Color(255,255,255).getRGB();
								objeto.setRGB(x, y, rgb);
							}
						}
					}
					System.out.println("Um pixel do plano pintado...");
				}
			}
		}
	}

	private void pinte(Ponto3D[][] intervalos, int indice, int k){
		for(int i=0;i<intervalos.length;i++){
			if(intervalos[i][0]!=null){
				//System.out.println("x_min: "+ret[i][0].x+" x_max:"+ret[i][1].x+" y: "+ret[i][0].y);
				for(int j= (int) intervalos[i][0].x;j<=intervalos[i][1].x;j++){
					Ponto3D pixel = new Ponto3D(j, intervalos[i][0].y);

					int x = (int) pixel.x;
					int y = (int) pixel.y;

					if(x>=0 && x<=ResX && y>=0 && y<=ResY ){
						double[] bary = t2.get(indice).getBaryCoefs(pixel);
						if (bary[0] < 0 || bary[1] < 0 || bary[2] < 0) continue;

						Ponto3D v1 = t.get(indice).v1;
						Ponto3D v2 = t.get(indice).v2;
						Ponto3D v3 = t.get(indice).v3;
						Ponto3D p = v1.multiply(bary[0]).add(v2.multiply(bary[1])).add(v3.multiply(bary[2]));

						//System.out.println("x: "+pixel.x+" y: "+ pixel.y);
						if(x <= ResX && y <= ResY && z_buffer[x][y]>p.z && p.z>0){
							z_buffer[x][y] = p.z;
							Ponto3D I = p.getColor();
							int r,g,b;
							r = (int) Math.round(I.x); 
							g = (int) Math.round(I.y); 
							b = (int) Math.round(I.z); 
							qtdPontos++;
							int rgb = new Color(r,g,b).getRGB();
							objeto.setRGB(x, y, rgb);
							Plano.pxlsPintados.add(pixel);
						}
					}
				}
			}
		}
	}

	private void fix() {
		for (int x=1;x<ResX;x++){
			for (int y=1;y<ResY;y++){
				if (z_buffer[x][y] == Double.MAX_VALUE && z_buffer[x][y+1] != Double.MAX_VALUE && z_buffer[x][y-1] != Double.MAX_VALUE){
					int r,g,b,r2,g2,b2;
					Color color;
					int rgb;

					rgb= objeto.getRGB(x, y+1);
					color = new Color(rgb);
					r2 = color.getRed()/2;
					g2 = color.getGreen()/2;
					b2 = color.getBlue()/2;

					rgb = objeto.getRGB(x, y-1);
					color = new Color(rgb);
					r2 += color.getRed()/2;
					g2 += color.getGreen()/2;
					b2 += color.getBlue()/2;

					rgb = objeto.getRGB(x, y);
					r = color.getRed();
					g = color.getGreen();
					b = color.getBlue();

					//if (z_buffer[x][y] == Double.MAX_VALUE || Math.abs(r-r2) > 60 || Math.abs(g-g2) > 60 || Math.abs(b-b2) > 60){
						color = new Color(r2,g2,b2);
						rgb = color.getRGB();
					//}

					objeto.setRGB(x, y, rgb);
				}
			}
		}
	}
}