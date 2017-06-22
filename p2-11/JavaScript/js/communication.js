//Arquivo para troca de mensagens entre o bonsaijs e o resto do codigo
function sendEvaluation() {
    var x = document.getElementById("evaluation").value
    bezier.sendMessage('receiveEvaluation', {
        data: x
    });
}

/*
function sendReduction() {
  var x = 1
  movie.sendMessage('receiveReduction', {
    data: x
  });
}
*/

function selectControlPoint() {
    var x = document.getElementById("controlPoint").checked
    bezier.sendMessage('receiveControlPoint', {
        data: x
    });
}

function selectPolygonControl() {
    var x = document.getElementById("polygonControl").checked
    bezier.sendMessage('receivePolygonControl', {
        data: x
    });
}

function selectCurveControl() {
    var x = document.getElementById("curveControl").checked
    bezier.sendMessage('receiveCurveControl', {
        data: x
    });
}

function receiveCurvature(data) {
    grafico.sendMessage('makeGraphic', {
        data: data.data
    });
}

















