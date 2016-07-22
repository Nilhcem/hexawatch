(function() {
    var canvasBackground,
        canvasContent,
        canvasSkeleton,
        contextBackground,
        contextContent,
        contextSkeleton,

        digitsMargin = 12,
        innerRadiusRatio = 0.75,
        ambientMode = false,

        strokeWidthInteractive = 3.0,
        strokeWidthAmbient = 1.0,
        strokeWidth = strokeWidthInteractive,

        bgColorInteractive = '#333333',
        bgColorAmbient = '#000000',
        bgColor = bgColorInteractive,

        strokeColorInteractive = '#e6e6e6',
        strokeColorAmbient = '#505050',
        strokeColor = strokeColorInteractive,

        fillColorInteractive = '#b9b9b9',
        fillColorAmbient = '#dddddd',
        fillColor = fillColorInteractive,

        center,
        radius,
        outerRadius,
        hexaRadius,
        outerPoints,
        hexaPoints;

    Math.radians = function(degrees) {
        return degrees * Math.PI / 180;
    };

    function moveTo(context, point) {
        context.moveTo(point.x, point.y);
    }

    function lineTo(context, point) {
        context.lineTo(point.x, point.y);
    }

    function setDefaultVariables() {
        canvasBackground = document.querySelector('#canvas-background');
        contextBackground = canvasBackground.getContext('2d');
        canvasContent = document.querySelector('#canvas-content');
        contextContent = canvasContent.getContext('2d');
        canvasSkeleton = document.querySelector('#canvas-skeleton');
        contextSkeleton = canvasSkeleton.getContext('2d');

        canvasBackground.width = document.body.clientWidth;
        canvasBackground.height = document.body.clientHeight;
        canvasContent.width = document.body.clientWidth;
        canvasContent.height = document.body.clientHeight;
        canvasSkeleton.width = document.body.clientWidth;
        canvasSkeleton.height = document.body.clientHeight;

        center = {
            x: document.body.clientWidth / 2,
            y: document.body.clientHeight / 2
        };

        radius = (Math.min(document.body.clientWidth, document.body.clientHeight) - strokeWidth) / 2;
        outerRadius = radius - strokeWidth / 2;
        hexaRadius = outerRadius * innerRadiusRatio;
        outerPoints = getCirclePoints(center, outerRadius, -90, 12);
        hexaPoints = getCirclePoints(center, hexaRadius, -120, 6);
    }

    function setDefaultEvents() {
        // add eventListener to update the screen immediately when the device wakes up
        document.addEventListener('visibilitychange', function() {
            if (!document.hidden) {
                drawWatchContent();
            }
        });
    }

    function drawBackground() {
      contextBackground.beginPath();
      contextBackground.arc(center.x, center.y, radius, 0, Math.radians(360));
      contextBackground.lineWidth = strokeWidth;

      contextBackground.fillStyle = bgColor;
      contextBackground.fill();
    }

    function drawWatchContent() {
        var datetime = (typeof tizen === 'undefined') ? new Date() : tizen.time.getCurrentDateTime(),
            hour = datetime.getHours() % 12,
            minute = Math.floor(datetime.getMinutes() / 10),
            digit = datetime.getMinutes() % 10;

        contextContent.clearRect(0, 0, contextContent.canvas.width, contextContent.canvas.height);
        drawMinute(contextContent, outerPoints, hexaPoints, minute);
        drawDigit(contextContent, center, hexaRadius - strokeWidth, digit);
        drawHour(contextContent, center, outerRadius, outerPoints, hexaPoints, hour);
    }

    function getCirclePoints(center, radius, rotation, dividingPoints) {
        var points = [],
            angle;

        for (var i = 0; i < dividingPoints; i++) {
            angle = i * (360 / dividingPoints) + rotation;
            points[i] = {
                x: center.x + radius * Math.cos(Math.radians(angle)),
                y: center.y + radius * Math.sin(Math.radians(angle))
            };
        }
        return points;
    }

    function drawSkeleton() {
        contextSkeleton.clearRect(0, 0, contextSkeleton.canvas.width, contextSkeleton.canvas.height);

        // Outer circle
        contextSkeleton.beginPath();
        contextSkeleton.arc(center.x, center.y, radius, 0, Math.radians(360));

        // Minutes triangles
        var i;
        for (i = 0; i < 6; i++) {
            moveTo(contextSkeleton, hexaPoints[i]);
            lineTo(contextSkeleton, outerPoints[i * 2]);
            lineTo(contextSkeleton, hexaPoints[i === 5 ? 0 : i + 1]);
            lineTo(contextSkeleton, hexaPoints[i]);
        }

        // Hours separators
        for (i = 0; i < 6; i++) {
            moveTo(contextSkeleton, hexaPoints[i]);
            lineTo(contextSkeleton, outerPoints[i === 0 ? 11 : i * 2 - 1]);
        }

        contextSkeleton.lineWidth = strokeWidth;
        contextSkeleton.strokeStyle = strokeColor;
        contextSkeleton.stroke();
    }

    function drawMinute(context, outerPoints, hexaPoints, minute) {
        context.beginPath();
        moveTo(context, hexaPoints[minute]);
        lineTo(context, outerPoints[minute * 2]);
        lineTo(context, hexaPoints[minute === 5 ? 0 : minute + 1]);
        lineTo(context, hexaPoints[minute]);

        context.lineWidth = strokeWidth
        if (ambientMode) {
          context.strokeStyle = fillColor;
          context.stroke();
        } else {
          context.fillStyle = fillColor;
          context.fill();
        }
    }

    function drawHour(context, center, radius, outerPoints, hexaPoints, hour) {
        context.beginPath();

        var innerIdx = (hour % 2 + hour) / 2;
        innerIdx = innerIdx === 6 ? 0 : innerIdx;

        var nextInnerIdx = Math.floor(((hour + 1) % 2 + hour) / 2 + 1);
        nextInnerIdx = nextInnerIdx === 6 ? 0 : nextInnerIdx;

        var arcStartCoord = hour * Math.radians(30) - Math.radians(90);

        context.arc(center.x, center.y, radius, arcStartCoord, arcStartCoord - Math.radians(30), true);
        lineTo(context, hexaPoints[innerIdx]);
        lineTo(context, outerPoints[hour]);

        context.arc(center.x, center.y, radius, arcStartCoord, arcStartCoord + Math.radians(30), false);
        lineTo(context, hexaPoints[nextInnerIdx]);
        lineTo(context, outerPoints[hour]);

        context.lineWidth = strokeWidth
        if (ambientMode) {
          context.strokeStyle = fillColor;
          context.stroke();
        } else {
          context.fillStyle = fillColor;
          context.fill();
        }
    }

    function drawDigit(context, center, radius, digit) {
        var innerNumbersPoints = getCirclePoints(center, radius - digitsMargin, -120, 6);
        var coords;
        if (digit === 0) {
            coords = [0, 1, 2, 3, 4, 5, 0];
        } else if (digit === 1) {
            coords = [1, 2, 3];
        } else if (digit === 2) {
            coords = [0, 1, 2, 5, 4, 3];
        } else if (digit === 3) {
            coords = [0, 1, 2, 5, 2, 3, 4];
        } else if (digit === 4) {
            coords = [0, 5, 2, 1, 2, 3];
        } else if (digit === 5) {
            coords = [1, 0, 5, 2, 3, 4];
        } else if (digit === 6) {
            coords = [1, 0, 5, 4, 3, 2, 5];
        } else if (digit === 7) {
            coords = [0, 1, 2, 3];
        } else if (digit === 8) {
            coords = [2, 5, 0, 1, 2, 3, 4, 5];
        } else {
            coords = [2, 5, 0, 1, 2, 3, 4];
        }

        drawPathFromCoords(context, innerNumbersPoints, coords);
    }

    function drawPathFromCoords(context, points, coords) {
        // Outer circle
        context.beginPath();
        moveTo(context, points[coords[0]]);
        for (var i = 1; i < coords.length; i++) {
            lineTo(context, points[coords[i]]);
        }

        context.lineWidth = strokeWidth;
        context.strokeStyle = ambientMode ? fillColor : strokeColor;
        context.stroke();
    }

    function setAmbient(inAmbient) {
      ambientMode = inAmbient;

      if (inAmbient) {
        strokeWidth = strokeWidthAmbient;
        bgColor = bgColorAmbient;
        strokeColor = strokeColorAmbient;
        fillColor = fillColorAmbient;
        canvasContent.style.zIndex = "3";
        canvasSkeleton.style.zIndex = "2";
      } else {
        strokeWidth = strokeWidthInteractive;
        bgColor = bgColorInteractive;
        strokeColor = strokeColorInteractive;
        fillColor = fillColorInteractive;
        canvasContent.style.zIndex = "2";
        canvasSkeleton.style.zIndex = "3";
      }

      setDefaultVariables();
      drawBackground();
      drawSkeleton();
      drawWatchContent();
    }

    function init() {
        setDefaultVariables();
        setDefaultEvents();

        // Draw the basic layout and the content of the watch at the beginning
        drawBackground();
        drawWatchContent();
        drawSkeleton();

        // Update the content of the watch every second
        setInterval(function() {
            drawWatchContent();
        }, 1000);
    }

    window.onload = init;
}());
