function Ponto(){
    this.x;
    this.y;
}

function Vetor(modulo, direcao){
    this.modulo = modulo;
    this.direcao = direcao;
}

function Norma(a){
  sum = 0;
  for(i = 0; i<a.lenght; i++){
    sum = a[i]*a[i] + sum;
  }
  return Math.sqrt(sum);
}
