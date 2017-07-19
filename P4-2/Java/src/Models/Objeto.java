package Models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Objeto {
	public static ArrayList<Ponto3D> vertices;
	public static ArrayList<Triangulo> triangulos;
	
	public static void setObjeto(String filepath) throws IOException {
		// Declara conjunto de vertices
		vertices = new ArrayList<Ponto3D>();
		// Declata conjunto de triangulos
		triangulos = new ArrayList<Triangulo>();

		// Ler arquivo
		File objeto = new File(filepath);

		// Declara leitor de arquivo
		Scanner scan = new Scanner(objeto);
		scan.useLocale(Locale.ENGLISH);

		// O primeiro inteiro é o número de vértices
		int qtdVertices = scan.nextInt();
		// O segundo inteiro é o número de triangulos
		int qtdTriangulos = scan.nextInt();

		// Mudar de coordenadas mundiais para o coordenadas de vista de todos os vértices do objeto
		for(int i=0; i<qtdVertices; i++){
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			double z = scan.nextDouble();
			Ponto3D p = new Ponto3D(x,y,z);
			p.indice=i;
			
			vertices.add(p);
		}

		// Lendo os triangulos
		for(int i=0; i<qtdTriangulos; i++){
			int v1,v2,v3;
			v1 = scan.nextInt() -1;
			v2 = scan.nextInt() -1;
			v3 = scan.nextInt() -1;
			Triangulo t = new Triangulo(vertices.get(v1),vertices.get(v2),vertices.get(v3),i);
			triangulos.add(t);
		}
		scan.close();
	}
}
