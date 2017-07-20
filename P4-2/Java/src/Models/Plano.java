package Models;

import javafx.scene.shape.Line;

import java.util.Collections;

public class Plano {
    public static Ponto3D[] pontos;
    public static Triangulo[] triangulos;
    public static double base;
    public static double altura;

    public Plano() {
        setup();
    }

    public static void setup(){
        pontos = new Ponto3D[4];
        triangulos = new Triangulo[2];
    }

    public void getEquacaoPlano() {

    }

    public void getNormal() {

    }

    public void setPontos(Ponto3D p1, Ponto3D p2, Ponto3D p3, Ponto3D p4){
        pontos[0] = p1;
        pontos[1] = p2;
        pontos[2] = p3;
        pontos[3] = p4;
    }

    public void setTriangulos(Triangulo t1, Triangulo t2){
        triangulos[0] = t1;
        triangulos[1] = t2;
    }

    public static void setPlano(String planoBase, String planoAltura) {
        setup();
        base = Double.parseDouble(planoBase);
        altura = Double.parseDouble(planoAltura);
        Ponto3D ptnMaisBaixo = ptnmenorY();
        //if (ptnMaisBaixo.z>1000) ptnMaisBaixo.z = 100;
        System.out.println("Ponto com menor Y: "+ptnMaisBaixo.x+", "+ptnMaisBaixo.y+", "+ptnMaisBaixo.z);
        int index = Objeto.vertices.size() -1;

        pontos[0] = new Ponto3D(ptnMaisBaixo.x-(base/2),ptnMaisBaixo.y,ptnMaisBaixo.z-(altura/2));
        pontos[0].indice = index;
        System.out.println("A: "+pontos[0].x+", "+pontos[0].y+", "+pontos[0].z+". Indice: "+index);
        index += 1;
        pontos[1] = new Ponto3D(ptnMaisBaixo.x-(base/2),ptnMaisBaixo.y,ptnMaisBaixo.z+(altura/2));
        pontos[1].indice = index;
        System.out.println("B: "+pontos[1].x+", "+pontos[1].y+", "+pontos[1].z+". Indice: "+index);
        index += 1;

        // C é simetrico à A em relação ao ptnMaisBaixo. C = 2*ptnMaisBaixo - A
        pontos[2] = new Ponto3D((2*ptnMaisBaixo.x)-pontos[0].x,ptnMaisBaixo.y,(2*ptnMaisBaixo.z)-pontos[0].z);
        pontos[2].indice = index;
        System.out.println("C: "+pontos[2].x+", "+pontos[2].y+", "+pontos[2].z+". Indice: "+index);
        index += 1;

        // D é simetrico à B em relação ao ptnMaisBaixo. D = 2*ptnMaisBaixo - B
        pontos[3] = new Ponto3D((2*ptnMaisBaixo.x)-pontos[1].x,ptnMaisBaixo.y,(2*ptnMaisBaixo.z)-pontos[1].z);
        pontos[3].indice = index;
        System.out.println("D: "+pontos[3].x+", "+pontos[3].y+", "+pontos[3].z+". Indice: "+index);

        //pontos[0] = new Ponto3D((2*ptnMaisBaixo.x)-pontos[1].x,ptnMaisBaixo.y,(2*ptnMaisBaixo.z)-pontos[1].z);

        // Adiciona Pontos do plano ao Objeto
        Collections.addAll(Objeto.vertices, pontos);

        // Primeiro triangulo
        Triangulo t = new Triangulo(Objeto.vertices.get(index-1),Objeto.vertices.get(index-2),Objeto.vertices.get(index+1),Objeto.triangulos.size());
        //t.v1 = new Ponto3D((2*Objeto.vertices.get(index).x)-t.v1.x,Objeto.vertices.get(index).y,(2*Objeto.vertices.get(index).z)-t.v1.z);
        Objeto.triangulos.add(t);

        //System.out.println("----->"+Objeto.vertices.get(index).x+", "+Objeto.vertices.get(index).y+" ,"+Objeto.vertices.get(index).z);

        System.out.println("Primeiro Triangulo: ");
        System.out.println("V1: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.z);
        System.out.println("V2: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.z);
        System.out.println("V3: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.z);

        //Segundo triangulo
        t = new Triangulo(Objeto.vertices.get(index-1),Objeto.vertices.get(index+1),Objeto.vertices.get(index),Objeto.triangulos.size());
        Objeto.triangulos.add(t);

        System.out.println("Segundo Triangulo: ");
        System.out.println("V1: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v1.z);
        System.out.println("V2: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v2.z);
        System.out.println("V3: "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.x+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.y+", "+Objeto.triangulos.get(Objeto.triangulos.size()-1).v3.z);
    }

    private static Ponto3D ptnmenorY() {
        Ponto3D ptn = new Ponto3D(0,0,Double.MAX_VALUE);
        for (Ponto3D vertice : Objeto.vertices) {
            if(vertice.y < ptn.y) ptn = vertice;
        }
        return ptn;
    }

    public static boolean existIntersecao(Ponto3D pixel) {
        Reta l = new Reta(Iluminacao.Pl, pixel);

        


        return false;
    }
}
