document.getElementById('iluminacao').disabled = false;
document.getElementById('objeto').disabled = false;
document.getElementById('plano').disabled = false;

function loadCamera(data) {
alert(data);
}

function loadIluminacao(data) {
alert(data);
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
