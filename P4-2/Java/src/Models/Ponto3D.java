package Models;

public class Ponto3D {
	public double x,y,z;
	public int indice;
	public Ponto3D normal;
	public boolean is3D;
	public Ponto3D color;
	public Ponto3D(){}

	// Construtor 3D
	public Ponto3D(double x, double y, double z, Ponto3D normal, Ponto3D color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.normal=normal;
		this.is3D = true;
		this.color = color;
	}
	
	public Ponto3D(double x, double y, double z) {
		this(x,y,z,null,null);
	}
	
	// Construtor 2D
	public Ponto3D(double x, double y) {
		this(x,y,0,null,null);
		this.is3D = false;
	}
	
	public double[] cordsToArray(){
		double[] ans = new double[3];
		ans[0] = x;
		ans[1] = y;
		ans[2] = z;
		return ans;
	}

	public Ponto3D copy(){
		Ponto3D p = new Ponto3D(x,y,z,normal,color);
		p.indice = this.indice;
		return p;
	}

	public Ponto3D normalize(){
		Ponto3D vn=this.divide(this.norma());
		vn.indice = this.indice;
		return vn;
	}

	public double norma(){
		return Math.sqrt(this.dotProduct(this));
	}
	
	public Ponto3D add(Ponto3D p2){
		double x,y,z;
		Ponto3D p, normal, color;

		x = this.x + p2.x;
		y = this.y + p2.y;
		z = this.z + p2.z;
		
		if(this.normal==null){
			normal=p2.normal;
		}else if(p2.normal==null){
			normal=this.normal;
		}else{
			normal = this.normal.add(p2.normal);
		}
		
		if(this.color==null){
			color=p2.color;
		}else if(p2.color==null){
			color=this.color;
		}else{
			color = this.color.add(p2.color);
		}
		
		//if (color != null) color.trucateColor();

		p = new Ponto3D(x, y, z, normal,color);
		return p;
	}

	public Ponto3D subtract(Ponto3D p2){
		return this.add(p2.multiply(-1));
	}

	public Ponto3D multiply(double k){
		double x,y,z;
		Ponto3D p,normal;

		x = this.x * k;
		y = this.y * k;
		z = this.z * k;

		if(this.normal==null){
			normal=null;
		}else{
			normal = this.normal.multiply(k);
		}

		p = new Ponto3D(x, y, z, normal, this.color);
		p.indice = this.indice;
		return p;
	}

	public Ponto3D divide(double k){
		return this.multiply(1/k);
	}
	
	public Ponto3D multiplyWithColor(double k) {
		if (this.color == null){ 
			this.color = getColor();
		}
		Ponto3D p = multiply(k);
		
		p.color = p.color.multiply(k);
		p.color.truncateXYZ();
		return p;
	}

	// Produto interno ou escalar
	public double dotProduct(Ponto3D p2){

		double ret = x*p2.x + y*p2.y + z*p2.z;

		return ret;
	}

	// crossProduct
	public Ponto3D produtoVetorial(Ponto3D N){

		double a1=x,a2=y,a3=z,b1=N.x, b2=N.y, b3=N.z;
		Ponto3D det = new Ponto3D(a2*b3 - a3*b2, a3*b1 - a1*b3, a1*b2 - a2*b1);
		return det;	
	}

	public Ponto3D kronecker(Ponto3D p2){
		Ponto3D p = p2.copy();
		p.x = this.x*p2.x;
		p.y = this.y*p2.y;
		p.z = this.z*p2.z;
		return p;
	}
	
	public String toString(){
		return String.format("(%f||%f||%f)", x,y,z);
	}

	public Ponto3D getColor(){
		Ponto3D p = this;
		Ponto3D N = p.normal.normalize();
		Ponto3D VdoPonto = p.multiply(-1).normalize(); // V = - P
		double VpN = N.dotProduct(VdoPonto);
		if (VpN < 0) N = N.multiply(-1);
		Ponto3D L = Iluminacao.Pl.subtract(p).normalize(); // L = Pl - P
		Ponto3D R = N.multiply(2).multiply(N.dotProduct(L)).subtract(L).normalize();
		double LpN = L.dotProduct(N);
		double RpV = R.dotProduct(VdoPonto);
		
		if(LpN<0) LpN=0;
		if(RpV<0) RpV=0;
		
		Ponto3D Id = Iluminacao.Il.multiply(LpN*Iluminacao.kd).kronecker(Iluminacao.Od);
		Ponto3D Ie = Iluminacao.Il.multiply(Math.pow(RpV,Iluminacao.n)*Iluminacao.ks);
		
		Ponto3D I = Iluminacao.Ia.multiply(Iluminacao.ka).add(Id).add(Ie);//.multiply(255);
		
		I.truncateXYZ();
		
		return I;
	}
	
	public void truncateXYZ(){
		Ponto3D I = this;
		
		I.x = Math.min(255, I.x);
//		I.x = Math.max(0, I.x);
		
		I.y = Math.min(255, I.y);
//		I.y = Math.max(0, I.y);
		
		I.z = Math.min(255, I.z);
//		I.z = Math.max(0, I.z);

	}
	


}
