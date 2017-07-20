package Models;

/**
 * Created by wellington on 20/07/17.
 */
public class Reta {
    public static double coeficienteAngular;

    public Reta(Ponto3D A, Ponto3D B) {
        coeficienteAngular = (B.y - A.y) / (B.x - A.x);
    }

    public void get(){
        //m = yB - yA
        //xB – xA
    }
}
