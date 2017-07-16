

public class Matrix {
	public float[] reflect(float[] v) {
		float[] r=new float[3];
		for(int i=0;i<v.length;i++) {
			r[i]=v[i]*-1;
		}
		return r;
	}
	public float[] multscalar(float[] v, float d)
	{	
		float[] r=new float[3];
		for(int i=0;i<r.length;i++) {
			r[i]=v[i]*d;
		}
		return r;
	}
	public float[][] changeCoordinateSystem(float[][] m, float ox, float oy) {
		/**origem 300, 300,0*/
		
		float[] a1,a2,a3=new float[3];
		
		a1=m[0];
		a2=reflect(m[1]);
		a3=m[2];
		
		a1[0]=a1[0]+ox;
		a1[1]=a1[1]+ox;
		a1[2]=a1[2]+ox;
		
		a2[0]=a2[0]+oy;
		a2[1]=a2[1]+oy;
		a2[2]=a2[2]+oy;
		
		float[][] a= {a1,a2,a3};
		
		return a;
	}
	
	public float[][] matrixProjection(float[][] m, float d){
		
		float[][] p=null;
		return null;
	}
	
}
