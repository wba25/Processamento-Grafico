function Ponto(){
    this.x;
    this.y;
    this.z;
}

function Vetor(modulo, direcao){
    this.modulo = modulo;
    this.direcao = direcao;
}

Vetor.prototype.Norma= function(a){
  sum = 0;
  for(i = 0; i<a.lenght; i++){
    sum = a[i]*a[i] + sum;
  }
  return Math.sqrt(sum);
}
