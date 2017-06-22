var stageObjects = []
var circles = []
var points = []
var controlPoints = []
var curve = null
var controlLines = []
var pontosDivisao = [[], []]
var derivada1 = []
var derivada2 = []

var showControlPolygon = true
var showControlPoints = true
var showCurve = true
var evaluation = 1000
var valoresX = []
var valoresY = []

var curvature = null

var myText = new Text().addTo(stage);

stage.on('message:makeGraphic', function (data) {
    //controlLines = data.data
    //myText.attr('text', data.data);
    curvature = []
    curvature = data.data
    
    stageObjects = []
    stageObjects.push(new Path(curvature).stroke('purple', 1))
    stage.children(stageObjects)
});