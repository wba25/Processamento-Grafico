function Ponto2D(x,y){
  this.x = x;
  this.y = y;
}

function Ponto3D(x,y,z){
  this.x = x;
  this.y = y;
  this.z = z;
}

function Vetor(x,y,z){
    this.x = x;
    this.y = y;
    this.z = z;


    Norma = function(){
    sum = 0;
    a = [this.x,this.y,this.z];

    for(i = 0; i<a.lenght; i++){
      sum = a[i]*a[i] + sum;
    }

    norma = Math.sqrt(sum);

    thix.x = this.x/norma;
    thix.y = this.y/norma;
    thix.z = this.z/norma;
    }

    produtoEscalar = function(k){
      return (this.x*v.x + this.y*v.y + this.z*v.z);
    }

    produtoVetorial =  function(v){
      var x = this.y*v.z - this.z*v.y;
      var y = this.z*v.x - this.x*v.z;
      var z = this.x*v.y - this.y*v.x;
      return new Vetor(x, y, z);
    }

  }
