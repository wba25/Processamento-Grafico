package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import API.Util;

public class Iluminacao {
	public static Ponto3D Pl; // - Coordenadas do ponto de luz
	public static Ponto3D Pl0; // - Coordenadas do ponto de luz
	public static double ka; // - reflexao ambiental
	public static Ponto3D Ia; // - vetor cor ambiental
	public static double kd; // - constante difusa
	public static Ponto3D Od; // - vetor difuso
	public static double ks; // - coeficiente especular
	public static Ponto3D Il; // - cor da fonte de luz
	public static double n; // - constante de rugosidade
	
	public static void initIluminacao(String filepath) throws IOException {
		// Ler arquivo
		File iluminacaoEntrada = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(iluminacaoEntrada));

		double[] luz = Util.extract(reader.readLine());
		Iluminacao.Pl0 = new Ponto3D(luz[0],luz[1],luz[2]);
		Iluminacao.ka = Double.parseDouble(reader.readLine());
		double[] cor = Util.extract(reader.readLine());
		Iluminacao.Ia = new Ponto3D(cor[0],cor[1],cor[2]);
		Iluminacao.kd = Double.parseDouble(reader.readLine());
		double[] dif = Util.extract(reader.readLine());
		Iluminacao.Od = new Ponto3D(dif[0],dif[1],dif[2]);
		Iluminacao.ks = Double.parseDouble(reader.readLine());
		cor = Util.extract(reader.readLine());
		Iluminacao.Il = new Ponto3D(cor[0],cor[1],cor[2]);
		Iluminacao.n = Integer.parseInt(reader.readLine());
		reader.close();	
	}
	
	public static void setIluminacao(){
		//fazer a mudan�a de coordenadas para o sistema de vista da posi��o
		//da fonte de luz PL,
		Iluminacao.Pl = Util.convert(Camera.C, Iluminacao.Pl0);
	}
}

