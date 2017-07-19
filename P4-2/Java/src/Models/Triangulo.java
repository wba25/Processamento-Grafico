package Models;


public class Triangulo implements Comparable<Triangulo>{
	public Ponto3D v1,v2,v3;
	public int indice;
	public double area;
	public boolean isAreaCalculated;

	public Triangulo(Ponto3D v1, Ponto3D v2, Ponto3D v3, int indice){
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
		this.indice=indice;
		this.isAreaCalculated = false;
	}

	public Triangulo(Ponto3D v1, Ponto3D v2, Ponto3D v3){
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
		this.isAreaCalculated = false;
	}

	public double getArea(){
		if (!isAreaCalculated){
			area = v1.x*v2.y + v1.y*v3.x + v2.x*v3.y - v2.y*v3.x - v1.y*v2.x - v1.x*v3.y;
			if (area > 0)
				area /= 2;
			else area /= -2;

			isAreaCalculated = true;
		}
		return area;
	}

	public double[] getBaryCoefs(Ponto3D p) {
		double total, alfa, beta, gama;
		total = getArea();
		//System.out.printf("v1:%s v2:%s v3:%s p:%s\n",v1,v2,v3,p);
		alfa = new Triangulo(p, v3, v2).getArea() / total;
		beta = new Triangulo(p, v3, v1).getArea() / total;
		gama = (double)1 - beta - alfa;
		double[] ret = {alfa, beta, gama};
		return ret;
	}

	public void ordenarX(){
		Ponto3D temp=null;
		if(v1.x<v2.x){
			temp=v1;
			v1=v2;
			v2=temp;
		}
		if(v1.x<v3.x){
			temp=v1;
			v1=v3;
			v3=temp;
		}
		if(v2.x<v3.x){
			temp=v2;
			v2=v3;
			v3=temp;
		}
	}

	public Triangulo ordenarY(){
		Ponto3D v1,v2,v3;
		v1 = this.v1.copy();
		v2 = this.v2.copy();
		v3 = this.v3.copy();
		Ponto3D temp=null;
		if(v1.y<v2.y){
			temp=v1;
			v1=v2;
			v2=temp;
		}
		if(v1.y<v3.y){
			temp=v1;
			v1=v3;
			v3=temp;
		}
		if(v2.y<v3.y){
			temp=v2;
			v2=v3;
			v3=temp;
		}

		return new Triangulo(v1, v2, v3);
	}

	public void ordenarZ(){
		Ponto3D temp=null;
		if(v1.z<v2.z){
			temp=v1;
			v1=v2;
			v2=temp;
		}
		if(v1.z<v3.z){
			temp=v1;
			v1=v3;
			v3=temp;
		}
		if(v2.z<v3.z){
			temp=v2;
			v2=v3;
			v3=temp;
		}
	}

	public int compareTo(Triangulo t) {
		ordenarZ();
		t.ordenarZ();
		if(v1.z>t.v1.z) return 1;
		if(v1.z<t.v1.z) return -1;

		return 0;
	}
}
