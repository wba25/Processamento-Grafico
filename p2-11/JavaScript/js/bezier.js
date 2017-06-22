var circles = []
var points = []
var controlPoints = []
var curvature = null
var curve = null
var controlLines = []
var pontosDivisao = [] // matriz de todos os ponto usadas na sub divisão
var derivada1 = []
var derivada2 = []
var stageObjects = []
var seeControlPolygon = true
var seeControlPoints = true
var seeCurve = true
var avaliacoes = 1000

//Comunicação entre html e bonsai

stage.on('message:goGrafico', function () {
    getGraphic()
});

stage.on('message:receiveEvaluation', function (data) {
    avaliacoes = data.data
    getCurve()
    renderObjects()
});

//mensagens para mostrar ou não os pontos,o polígono e a curva
stage.on('message:receiveControlPoint', function (data) {
    seeControlPoints = data.data
    renderObjects()
});

stage.on('message:receivePolygonControl', function (data) {
    seeControlPolygon = data.data
    renderObjects()
});

stage.on('message:receiveCurveControl', function (data) {
    seeCurve = data.data
    if (circles.length > 2) {
        getCurve()
    }
    renderObjects()
});

function interpolation(t, p1, p2) {
    var x = (1 - t) * p1.x + t * p2.x
    var y = (1 - t) * p1.y + t * p2.y
    return new Point(x, y)
}

function calcDerivadas(n, t) {
    // Se t está além da metade, a primeira metade é a maior e vice versa.
    // Na subdivisão, considera-se o ponto da subdivisão o inicial.
    if (t > 0.5) {
        derivada1[n] = subPoint(pontosDivisao[points.length - 2][0], pontosDivisao[points.length - 1][0]);
        derivada1[n + 1] = subPoint(pontosDivisao[points.length - 3][0], pontosDivisao[points.length - 2][0]);
        derivada2[n] = subPoint(derivada1[n + 1], derivada1[n]);
    } else {
        //console.log(subPoint(pontosDivisao[points.length - 2][1], pontosDivisao[points.length - 1][0]).x + ", "                    +subPoint(pontosDivisao[points.length - 2][1], pontosDivisao[points.length - 1][0]).y)
        derivada1[n] = subPoint(pontosDivisao[points.length - 2][1], pontosDivisao[points.length - 1][0]);
        derivada1[n + 1] = subPoint(pontosDivisao[points.length - 3][2], pontosDivisao[points.length - 2][1]);
        derivada2[n] = subPoint(derivada1[n + 1], derivada1[n]);
    }
    //console.log("calculou a derivada " + n)
}

// subtrai 2 pontos
function subPoint(ptA, ptB) {
    var x = (ptA.x - ptB.x)
    var y = (ptA.y - ptB.y)
    return new Point(x, y)
}

//vai obter os pontos da curva de acordo  com o Algoritmo de de Casteljau
function getCurve() {
    if (points.length > 2) {
        //para os pontos da primeira subdivisão salva-se os pontos de controle
        pontosDivisao[0] = points;
        curve = []
        n = 0
        //gerará avaliacoes pontos de curva
        for (var t = 0; t <= 1; t += 1 / avaliacoes) {
            var pointsTemp = []
            pointsTemp = points
            a = 1
            while (pointsTemp.length > 1) {
                var pLenght = pointsTemp.length
                var temp2 = []
                pontosDivisao[a] = []
                    // vai calcular um novo ponto para cada par de pontos n e n+1
                for (var i = 0; i < pLenght - 1; i++) {
                    var newPoint = interpolation(t, pointsTemp[i], pointsTemp[i + 1])
                    temp2.push(newPoint)
                    pontosDivisao[a].push(newPoint) // salva-se todos os ponto encontrados durante a sub-divisão
                }
                //sempre que sai do for, pointsTemp tem um ponto a menos do que tinha anteriormente
                pointsTemp = temp2
                a++
            }
            //quando sai do while, pointsTemp tem apenas 1 ponto, que será o ponto da curva para um certo t
            //adiciona-se esse ponto na curva
            curve.push(pointsTemp[0].x)
            curve.push(pointsTemp[0].y)
                //o primeiro for será executado novamente, com um t diferente para gerar um outro ponto da curva
            if (n < avaliacoes) {
                calcDerivadas(n, t)
                n++
            }
        }
        getGraphic()
    } else {
        curve = null
    }
}

//Pega pontos da curvatura do grafico segundo o procedimento descrito em:
// -> http://www.math24.net/curvature-of-plane-curves.html
// Formula:  k = (x'y'' - y'x'') / ((x')^2 + (y')^2)^(3/2)
function getGraphic() {
    if (points.length > 2) {
        curvature = []
        var K = []
        var maiorAbs = 0
        var num
        var den
        var N = avaliacoes

        // Para cada avaliação da curva, considera-se sua subdivisão nesse ponto e se calcula sua curvatura considerando suas derivadas, segundo a fórmula.
        for (var i = 0; i < N - 2; i++) {
            num = derivada1[i].x * derivada2[i].y - derivada1[i].y * derivada2[i].x;
            den = (derivada1[i].x * derivada1[i].x) + (derivada1[i].y * derivada1[i].y);
            den = Math.pow(den, 3);
            den = Math.sqrt(den);
            K[i] = num / den;
            maiorAbs = Math.max(maiorAbs, Math.abs(K[i]));
        }

        var x, y;

        // Pega pontos da cruva e normaliza os pontos para caber na tela
        //console.log(window.screen.availHeight+ "x" +window.screen.availWidth)
        for (var i = 0; i < N - 2; i++) {
            x = 0.2 + (4.0 * i / (N - 2));
            y = 2.2 + (K[i] / maiorAbs);

            x /= 4;
            x *= 600;

            y /= 4;
            y *= 600;
            /*
            if(y<0){
                y = Math.abs(y);
            }
            */

            // Adiciona ponto para a curvatura
            curvature.push(x)
            curvature.push(y)
            
        }

        stage.sendMessage('sendCurvature', {
            data: curvature
        });
    }
}

//Renderiza os pontos, paths,...
function renderObjects() {
    stageObjects = []
    for (var i = 0; i < controlPoints.length; i++) {
        stageObjects.push(new Circle(controlPoints[i].x, controlPoints[i].y, 3).attr('fillColor', 'blue'))
    }
    if (seeCurve && curve) {
        stageObjects.push(new Path(curve).stroke('red', 1))
    }
    if (seeControlPolygon) {
        for (var i = 0; i < controlLines.length; i++) {
            stageObjects.push(controlLines[i])
        }
    }
    if (seeControlPoints) {
        for (var i = 0; i < circles.length; i++) {
            stageObjects.push(circles[i])
        }
    }
    stage.children(stageObjects)

    /*
    // Atualizar grafico
    stage.sendMessage('updateGrafico', {
        data: [valoresX, valoresY]
    });
    //console.log("render")
    */
}


stage.on('click', function (e) {
    controlLines = []
    var isExistedCircle = false
    //console.log("Get: x=" + e.x + " ,y=" + e.y)
    for (var i = 0; i < circles.length; i++) {
        if (circles[i] == e.target) {
            points[i].x = e.x
            points[i].y = e.y
            isExistedCircle = true
        }
    }
    if (!isExistedCircle) {
        circles.push(new Circle(e.x, e.y, 3).attr('fillColor', color('rgb(29, 91, 145)')).on('drag', function (evt) {
            this.attr({
                x: evt.x,
                y: evt.y
            });
        }));
        points.push(new Point(e.x, e.y))
    }
    if (circles.length > 1) {
        for (var i = 0; i < circles.length - 1; i++) {
            controlLines.push(new Path([points[i].x, points[i].y, points[i + 1].x, points[i + 1].y]).stroke(color('rgb(91, 192, 222)'), 0.9))
        }
        getCurve()
    }
    renderObjects()
});

//deleta os pontos
stage.on('doubleclick', function (e) {
    controlLines = []
    for (var i = 0; i < circles.length; i++) {
        if (circles[i] == e.target) {
            circles.splice(i, 1);
            points.splice(i, 1)
            getCurve()
        }
    }
    if (circles.length > 1) {
        for (var i = 0; i < circles.length - 1; i++) {
            controlLines.push(new Path([points[i].x, points[i].y, points[i + 1].x, points[i + 1].y]).stroke(color('rgb(91, 192, 222)'), 0.9))
        }
    }
    renderObjects()
});