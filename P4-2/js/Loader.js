document.getElementById('iluminacao').disabled = false;
document.getElementById('objeto').disabled = false;
document.getElementById('plano').disabled = false;

function loadCamera(data) {
alert(data);
}

function loadIluminacao(data) {
  // Se não tiver objeto, vai iluminar o que? o vazio, então pode receber objetos
  // de entrada
  if(!objeto) document.getElementById('objeto').disabled = false;
  iluminacao = {};

  //aqui eu declaro uma variável controladora das entradas

  var a;
  //usando split, eu crio um vetor da entrada, por exemplo,
  //aqui eu tou controlando por espaços
  //se a primeira linha tiver '22 04 6', vai virar um vetor a(22,4,6)

  a = data[0].split(' ');
  //aqui eu pego a posição da fonte em coordenadas de mundo
  var pl = new Ponto3D(a[0], a[1], a[2]);
  // aqui eu pego o coeficiente de reflexão ambiental
  var ka = data[1];

  // aqui eu pego o vetor de cor do ambiente
  var a = data[2].split(' ');
  var ia = new Vetor(a[0], a[1], a[2]);
  // aqui eu pego o coeficiente difuso
  var kd = data[3];

  // aqui eu pego o vetor difuso
  var a = data[4].split(' ');
  var od = new Vetor(a[0], a[1], a[2]);

  // aqui eu pego o coeficiente especular, para onde vai ser refletida a luz
  var ks = data[5];

  // aqui eu pego a cor da fonte de luz
  var a = data[6].split(' ');
  var il = new Vetor(a[0], a[1], a[2]);

  // aqui eu pego o coeficiente de rugosidade
  var n = data[7];

  // crio um novo objeto, onde suas especificações estão no arquivo Models.js
  iluminacao = new Iluminacao(pl, ka, ia, kd, od, ks, il, n);

  // aqui eu faço a adaptação de acordo com o ponto de vista da camera
  iluminacao.pl = iluminacao.pl_pvista_camera.camera_pontoDeVista(camera);

  //NÃO RECEBE MAIS OBJETOS, TÁ BOM, ISSO AKI NÃO É CABARÉ, CARAMBA
  document.getElementById('objeto').disabled = false;
}

function loadObjeto(data) {
alert(data);
}

function loadPlano(data) {
alert(data);
}

function handleFileSelect(evt) {
  var next;
  switch (this.id) {
    case 'camera':
      next = loadCamera;
    break;
    case 'objeto':
      next = loadObjeto;
    break;
    case 'iluminacao':
      next = loadIluminacao;
    break;
    case 'plano':
      next = loadPlano;
    break;
  }
  var file = evt.target.files[0]; // FileList object

  var reader = new FileReader();
  // Closure to capture the file information.
  reader.onload = (function(file) {
    return function(e) {
      var data = this.result.split('\n');
      next(data);
    };
  })(file);
  reader.readAsText(file);
}

document.getElementById('camera').addEventListener('change', handleFileSelect, false);
document.getElementById('iluminacao').addEventListener('change', handleFileSelect, false);
document.getElementById('objeto').addEventListener('change', handleFileSelect, false);
document.getElementById('plano').addEventListener('change', handleFileSelect, false);
