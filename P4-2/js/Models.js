function Ponto2D(x,y){
  this.x = x;
  this.y = y;
}

function Ponto3D(x,y,z){
  this.x = x;
  this.y = y;
  this.z = z;
}

function Vetor3D(x,y,z){
    this.x = x;
    this.y = y;
    this.z = z;
}

Vetor3D.prototype.norma = function(){
    return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
};

Vetor3D.prototype.produtoEscalar = function(v){
    return (this.x*v.x + this.y*v.y + this.z*v.z);
};

Vetor3D.prototype.produtoVetorial = function(v){
    var x = this.y*v.z - this.z*v.y;
    var y = this.z*v.x - this.x*v.z;
    var z = this.x*v.y - this.y*v.x;
    return new Vetor(x, y, z);
};
