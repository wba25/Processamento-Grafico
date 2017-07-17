function Ponto2D(x,y){
  this.x = x;
  this.y = y;
}

function Ponto3D(x,y,z){
  this.x = x;
  this.y = y;
  this.z = z;
}

//dada a camera, o ponto de vista do observador, sacou?
//é como se por exemplo, você tirar uma foto de lado ou de frente
// se for de lado, o ponto de vista é geometricamente de lado
//caso contrário, vai ser de frente kkkkk
// exemplo bosta mas vai dá pra entender.

Ponto3D.prototype.camera_pontoDeVista = function(camera) {
  var a = this.clone();
  var b = a.sub(camera.c);
  var r = b.multiplicarMatrix(camera.alfa);
  return r;
};


function Vetor3D(x,y,z){
    this.x = x;
    this.y = y;
    this.z = z;
}

// Calcula valor da norma do vetor
Vetor3D.prototype.norma = function(){
    return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
};

// Retorna um vetor unitario de mesmo sentido e direção do vetor original
Vetor3D.prototype.normaliza = function(){
    var norma = this.norma();
    return new Vetor3D(this.x/norma,this.y/norma,this.z/norma);
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


//pl - coordenadas originais
//ka - reflexao ambiental
//Ia - vetor cor ambiental
//kd - constante difusa
//od - vetor difuso
//ks - parte especular
//Il - cor da fonte de luz
//n  - constante de rugosidade

function Iluminacao(pl, ka, ia, kd, od, ks, il, n) {
  this.pl = pl; //posições originais da fonte de luz em coordenadas de mundo
  this.pl_pvista_camera = pl; //adapta as posiçoes de entrada para o ponto de vista da camera
  this.ka = ka; // reflexão de luz ambiente (quanto maior, mais reflexível é)
  this.ia = ia; // intensidade da luz ambiente (quanto maior, mais iluminado kkk)
  this.kd = kd; // constante difusa, o quão espalhável é essa luz
  this.od = od; // vetor difuso, usado para simplesmente espalhar kkk
  this.ks = ks; // parte especular, como a luz vai ser refletida no objeto
  this.il = il; // cor da fonte de luz
  this.n = n; // constante de rugosidade, trata irregularidades, ruídos, enfim, frescura.
}

//DEPOIS VOU TRATAR A COLORAÇÃO DO OBJETO DE ACORDO COM A ILUMINAÇÃO, PERAÍ
