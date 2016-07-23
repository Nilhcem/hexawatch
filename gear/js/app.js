(function() {
    var canvasContent,
        canvasSkeleton,
        contextContent,
        contextSkeleton,

        digitsMargin = 12,
        innerRadiusRatio = 0.75,
        isAmbientMode = false,

        marginInteractive = 0,
        marginAmbient = 10,
        margin = marginInteractive,

        strokeWidthInteractive = 3.0,
        strokeWidthAmbient = 2.0,
        strokeWidth = strokeWidthInteractive,

        bgColorInteractive = '#333333',
        bgColorAmbient = '#000000',
        bgColor = bgColorInteractive,

        strokeColor = '#e6e6e6',

        fillColorInteractive = '#b9b9b9',
        fillColorAmbient = '#ffffff',
        fillColor = fillColorInteractive,

        center,
        radius,
        outerRadius,
        hexaRadius,
        outerPoints,
        hexaPoints,
        lastMinute;

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
        canvasContent = document.querySelector('#canvas-content');
        contextContent = canvasContent.getContext('2d');
        canvasSkeleton = document.querySelector('#canvas-skeleton');
        contextSkeleton = canvasSkeleton.getContext('2d');

        canvasContent.width = document.body.clientWidth;
        canvasContent.height = document.body.clientHeight;
        canvasSkeleton.width = document.body.clientWidth;
        canvasSkeleton.height = document.body.clientHeight;

        center = {
            x: document.body.clientWidth / 2,
            y: document.body.clientHeight / 2
        };

        radius = (Math.min(document.body.clientWidth, document.body.clientHeight) - strokeWidth) / 2 - margin;
        outerRadius = radius - strokeWidth / 2;
        hexaRadius = outerRadius * innerRadiusRatio;
        outerPoints = getCirclePoints(center, outerRadius, -90, 12);
        hexaPoints = getCirclePoints(center, hexaRadius, -120, 6);
    }

    function setDefaultEvents() {
        // Add an eventListener for ambientmodechanged
        window.addEventListener("ambientmodechanged", function(e) {
            setAmbient(e.detail.ambientMode);
        });

        // Add an event listener to update the screen immediately when the device wakes up
        document.addEventListener("visibilitychange", function() {
            if (!document.hidden) {
                setAmbient(isAmbientMode);
            }
        });
    }

    function drawWatchContent(forceDraw) {
        var datetime = (typeof tizen === 'undefined') ? new Date() : tizen.time.getCurrentDateTime(),
            minutes = datetime.getMinutes();
        if (forceDraw || minutes != lastMinute) {
            contextContent.clearRect(0, 0, contextContent.canvas.width, contextContent.canvas.height);
            drawBackground(contextContent, center, radius);
            drawMinute(contextContent, outerPoints, hexaPoints, Math.floor(minutes / 10));
            drawDigit(contextContent, center, hexaRadius - strokeWidth, minutes % 10);
            drawHour(contextContent, center, outerRadius, outerPoints, hexaPoints, datetime.getHours() % 12);
        }
        lastMinute = minutes;
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
        if (isAmbientMode) {
            return;
        }

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

    function drawBackground(context, center, radius) {
      context.beginPath();
      context.arc(center.x, center.y, radius, 0, Math.radians(360));
      context.lineWidth = strokeWidth;

      context.fillStyle = bgColor;
      context.fill();
    }

    function drawMinute(context, outerPoints, hexaPoints, minute) {
        context.beginPath();
        moveTo(context, hexaPoints[minute]);
        lineTo(context, outerPoints[minute * 2]);
        lineTo(context, hexaPoints[minute === 5 ? 0 : minute + 1]);
        lineTo(context, hexaPoints[minute]);

        context.lineWidth = strokeWidth
        if (isAmbientMode) {
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
        if (isAmbientMode) {
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
        context.strokeStyle = isAmbientMode ? fillColor : strokeColor;
        context.stroke();
    }

    function setAmbient(inAmbient) {
      isAmbientMode = inAmbient;

      if (inAmbient) {
        margin = marginAmbient;
        strokeWidth = strokeWidthAmbient;
        bgColor = bgColorAmbient;
        fillColor = fillColorAmbient;
      } else {
        margin = marginInteractive;
        strokeWidth = strokeWidthInteractive;
        bgColor = bgColorInteractive;
        fillColor = fillColorInteractive;
      }

      setDefaultVariables();
      drawSkeleton();
      drawWatchContent(true);
    }

    function init() {
        setDefaultVariables();
        setDefaultEvents();

        // Draw the basic layout and the content of the watch at the beginning
        drawWatchContent(true);
        drawSkeleton();

        // Update the content of the watch every second
        setInterval(function() {
            drawWatchContent(false);
        }, 1000);
    }

    window.onload = init;
}());
