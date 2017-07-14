
/* 
    sum2D é a soma das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna  um vetor ou ponto na 2 dimensão
*/
function sum2D(x , y , x0 , y0){
   
    return sum = (parseFloat(x + x0)+","+parseFloat( y +y0));
}
/* 
    sum3D é a soma das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um vetor ou ponto na 3 dimensão
*/

function sum3D(x, x0,  x1 , y, y0, y1 , z, z0, z1){

    return sum = (parseFloat(x+x0+x1)+","+parseFloat(y+y0+y1)+","+parseFloat(z+z0+z1));
    
}
/* 
    prod é a multiplicação de x com x e y com y,logo depois soma os valores das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function prod( x ,  x0 , y , y0){
    return parseFloat(x*x0)+parseFloat(y*y0)
}
/* 
    prod3D é a multiplicação de x com x , y com y e z com z,logo depois soma os valores das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' , 'ys' e 'zs' como parâmetro
    retorna um escalar.
*/
function prod3D( x , y, z, x0 , y0, z0){
    return parseFloat(x*x0)+parseFloat(y*y0)+parseFloat(z*z0);
}
/* 
    mult2D é a multiplicação dos eixos por um escalar 'k' das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um vetor ou ponto na 2 dimensão.
*/
function mult2D( x,  y ,  k){

    return mult = (parseFloat(k*x)+","+parseFloat(k*y));
}
/* 
    mult3D é a multiplicação dos eixos por um escalar 'k' das cordenadas de um vetor ou de um ponto
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um vetor ou ponto na 3 dimensão.
*/
function mult3D( x ,  y ,  z ,  k){

    return (parseFloat(k*x)+","+parseFloat(k*y)+","+parseFloat(k*z));
}
/*
    angleCos é o produto entre dois vetores, divido pela norma dos dois vetores
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna o valor do ângulo.
*/

function angleCos(x,y, x0,y0){
   return parseFloat(prod(x,y,x0,y0)/norm(x,y)*norm(x0,y0));
}
/*
    angleCos é o produto entre dois vetores, divido pela norma dos dois vetores
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna o valor do ângulo.
*/
function angleCos3D(x,y,z,x0,y0,z0){
    return parseFloat(prod3D(x,y,z,x0,y0,z0)/norm3D(x,y,z)*norm3D(x0,y0,z0));
}
/*
    distance é a soma das subtrações dos eixos 'xs' e 'ys' elevado ao quadrado
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function distance( xA ,  yA ,  xB ,  yB){
    var dist = parseFloat((xB-xA)^2)+parseFloat((yB-yA)^2);
    var dist = sqrt(dist);
    return parseFloat(dist);
}
/*
    distance3D é a soma das subtrações dos eixos 'xs' e 'ys' elevado ao quadrado
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function distance3D( xA ,  yA ,zA,  xB ,  yB,zB){
    var dist = parseFloat((xB-xA)^2)+parseFloat((yB-yA)^2)+parseFloat((zB-zA)^2);
    var dist = sqrt(dist);
    return parseFloat(dist);
}
/*
    norm é a raiz da soma dos quadrados das coordenadas 
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function norm( x ,  y){
    return norma = parseFloat(sqrt(x^2+y^2));
}
/*
    norm3D é a raiz da soma dos quadrados das coordenadas 
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function norm3D(x,y,z){
    return parseFloat(sqrt(x^2+y^2+z^2));
}
/*
    productIn é o produto de dois vetores e o angulos destes. 
    Recebe as os 'xs' e 'ys' como parâmetro
    retorna um escalar.
*/
function productIn(x , y , x0 , y0){
  return parseFloat(norm(x,y)) * parseFloat(norm(x0,y0))*parseFloat(angleCos(x,y,x0,y0));
}
/*
    productIn3D é o produto de dois vetores e o angulos destes. 
    Recebe as os 'xs' , 'ys' e 'zs' como parâmetro
    retorna um escalar.
*/
function productIn3D(x , y , z , x0 , y0, z0){
  return parseFloat(norm3D(x,y,z)) * parseFloat(norm3D(x0,y0,z0))*parseFloat(angleCos(x,y,x0,y0));
}
function productVector(){

}


