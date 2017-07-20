package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import API.Util;

public class Iluminacao {
	public static Ponto3D Pl; // - Coordenadas do ponto de luz
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

		// Primeira linha do arquivo possue as coordenadas do ponto de luz
		double[] luz = Util.extract(reader.readLine());
		Iluminacao.Pl = new Ponto3D(luz[0],luz[1],luz[2]);
		// Valor da reflexão ambiental
		Iluminacao.ka = Double.parseDouble(reader.readLine());
		// Vetor de cor ambiental
		double[] cor = Util.extract(reader.readLine());
		Iluminacao.Ia = new Ponto3D(cor[0],cor[1],cor[2]);
		// Constante difusa
		Iluminacao.kd = Double.parseDouble(reader.readLine());
		// Constante difusa - ajuda a derterminar a cor do objeto
		double[] difusa = Util.extract(reader.readLine());
		Iluminacao.Od = new Ponto3D(difusa[0],difusa[1],difusa[2]);
		// Valor do coeficiente especular
		Iluminacao.ks = Double.parseDouble(reader.readLine());
		// Vetor da cor da fonte de luz
		cor = Util.extract(reader.readLine());
		Iluminacao.Il = new Ponto3D(cor[0],cor[1],cor[2]);
		// Constante de rugosidade
		Iluminacao.n = Integer.parseInt(reader.readLine());
		// Fecha o leitor
		reader.close();
	}
	
	public static void setIluminacao(){
		//Mudança de coordenadas Mundial para coordenadas de vista de Pl
		Iluminacao.Pl = Util.convert(Camera.C, Iluminacao.Pl);
	}
}

