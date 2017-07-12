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

// Calcula valor da norma do vetor
Vetor3D.prototype.norma = function(){
    return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
};

// Calcula produto interno
Vetor3D.prototype.produtoInterno = function(v){
    return (this.x*v.x + this.y*v.y + this.z*v.z);
};

// Calcula vetor resultado do produto vetorial
Vetor3D.prototype.produtoVetorial = function(v){
    var i = this.y*v.z - this.z*v.y;
    var j = this.z*v.x - this.x*v.z;
    var k = this.x*v.y - this.y*v.x;
    return new Vetor3D(i, j, k);
};

// Valor do cosseno de 2 vetores
Vetor3D.prototype.cosAngulo = function(v){
    console.log(this.produtoInterno(v));
    console.log(this.norma());
    console.log(v.norma());
    return this.produtoInterno(v) / (this.norma() * v.norma());
} 

// Ângulo entre 2 vetores em graus
Vetor3D.prototype.angulo = function(v){
    return Math.acos(this.cosAngulo(v))*(180/Math.PI);
} 