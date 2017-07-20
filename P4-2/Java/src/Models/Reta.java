package Models;

public class Reta {
    public static Ponto3D v;
    public static Ponto3D p0;

    public Reta(Ponto3D A, Ponto3D B) {
        v = A.subtract(B);
        p0 = B;
    }

    public boolean pertence(Ponto3D p) {
        double a = (p.x - p0.x) / v.x;
        double b = (p.y - p0.y) / v.y;
        double c = (p.z - p0.z) / v.z;
        System.out.println("---->"+a+", "+b+", "+c);
        //return a == b && a == c;
        return a == b;
    }

    public String toString(){
        return "(x - "+p0.x+") / "+v.x+" = "+"(y - "+p0.y+") / "+v.y+" = "+"(z - "+p0.z+") / "+v.z+" = ";
    }
}
