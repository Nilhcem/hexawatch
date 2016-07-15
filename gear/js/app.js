/*
 * Copyright (c) 2016 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

(function() {
    var canvasLayout,
        canvasContent,
        ctxLayout,
        ctxContent,
        center,
        watchRadius;

    /**
     * Renders a circle with specific center, radius, and color
     * @private
     * @param {object} context - the context for the circle to be placed in
     * @param {number} radius - the radius of the circle
     * @param {string} color - the color of the circle
     */
    function renderCircle(context, center, radius, color) {
        context.save();
        context.beginPath();
        context.fillStyle = color;
        context.arc(center.x, center.y, radius, 0, 2 * Math.PI);
        context.fill();
        context.closePath();
        context.restore();
    }

    /**
     * Renders a needle with specific center, angle, start point, end point, width and color
     * @private
     * @param {object} context - the context for the needle to be placed in
     * @param {number} angle - the angle of the needle (0 ~ 360)
     * @param {number} startPoint - the start point of the needle (-1.0 ~ 1.0)
     * @param {number} startPoint - the end point of the needle (-1.0 ~ 1.0)
     * @param {number} width - the width of the needle
     * @param {string} color - the color of the needle
     */
    function renderNeedle(context, angle, startPoint, endPoint, width, color) {
        var radius = context.canvas.width / 2,
            centerX = context.canvas.width / 2,
            centerY = context.canvas.height / 2,
            dxi = radius * Math.cos(angle) * startPoint,
            dyi = radius * Math.sin(angle) * startPoint,
            dxf = radius * Math.cos(angle) * endPoint,
            dyf = radius * Math.sin(angle) * endPoint;

        context.save();
        context.beginPath();
        context.lineWidth = width;
        context.strokeStyle = color;
        context.moveTo(centerX + dxi, centerY + dyi);
        context.lineTo(centerX + dxf, centerY + dyf);
        context.stroke();
        context.closePath();
        context.restore();
    }

    /**
     * Renders text at a specific center, radius, and color
     * @private
     * @param {object} context - the context for the text to be placed in
     * @param {string} text - the text to be placed
     * @param {number} x - the x-coordinate of the text
     * @param {number} y - the y-coordinate of the text
     * @param {number} textSize - the size of the text in pixel
     * @param {string} color - the color of the text
     */
    function renderText(context, text, x, y, textSize, color) {
        context.save();
        context.beginPath();
        context.font = textSize + "px Courier";
        context.textAlign = "center";
        context.textBaseline = "middle";
        context.fillStyle = color;
        context.fillText(text, x, y);
        context.closePath();
        context.restore();
    }

    /**
     * Draws the basic layout of the watch
     * @private
     */
    function drawWatchLayout() {
        var grd,
            angle,
            i,
            j;

        // Clear canvas
        ctxLayout.clearRect(0, 0, ctxLayout.canvas.width, ctxLayout.canvas.height);

        // Draw the background circle
        renderCircle(ctxLayout, center, watchRadius, "#000000");
        grd = ctxLayout.createLinearGradient(0, 0, watchRadius * 2, 0);
        grd.addColorStop(0, "#000000");
        grd.addColorStop(0.5, "#454545");
        grd.addColorStop(1, "#000000");
        ctxLayout.fillStyle = grd;
        renderCircle(ctxLayout, center, watchRadius * 0.945, grd);
        renderCircle(ctxLayout, center, watchRadius * 0.7, "#000000");

        // Draw the dividers
        // 60 unit divider
        for (i = 1; i <= 60; i++) {
            angle = (i - 15) * (Math.PI * 2) / 60;
            renderNeedle(ctxLayout, angle, 0.95, 1.0, 1, "#c4c4c4");
        }

        // 12 unit divider
        for (j = 1; j <= 12; j++) {
            angle = (j - 3) * (Math.PI * 2) / 12;
            renderNeedle(ctxLayout, angle, 0.7, 0.945, 10, "#c4c4c4");
        }

        renderText(ctxLayout, "HEXAWATCH", center.x, center.y - (watchRadius * 0.4), 25, "#999999");
    }

    /**
     * Draws the content of the watch
     * @private
     */
    function drawWatchContent() {
        var datetime = tizen.time.getCurrentDateTime(),
            hour = datetime.getHours(),
            minute = datetime.getMinutes(),
            second = datetime.getSeconds(),
            date = datetime.getDate();

        // Clear canvas
        ctxContent.clearRect(0, 0, ctxContent.canvas.width, ctxContent.canvas.height);

        // Draw the hour needle
        renderNeedle(ctxContent, Math.PI * (((hour + minute / 60) / 6) - 0.5), 0, 0.50, 3, "#454545");

        // Draw the minute needle
        renderNeedle(ctxContent, Math.PI * (((minute + second / 60) / 30) - 0.5), 0, 0.70, 3, "#454545");

        // Draw the minute/hour circle
        renderCircle(ctxContent, center, 8, "#454545");

        // Draw the second needle
        ctxContent.shadowOffsetX = 4;
        ctxContent.shadowOffsetY = 4;
        renderNeedle(ctxContent, Math.PI * ((second / 30) - 0.5), -0.10, 0.85, 1, "#c4c4c4");

        // Draw the second circle
        ctxContent.shadowOffsetX = 0;
        ctxContent.shadowOffsetY = 0;
        renderCircle(ctxContent, center, 5, "#c4c4c4");

        // Draw the center circle
        renderCircle(ctxContent, center, 2, "#454545");

        // Draw the text for date
        renderText(ctxContent, date, center.x, center.y + (watchRadius * 0.5), 25, "#999999");
    }

    /**
     * Set default variables
     * @private
     */
    function setDefaultVariables() {
        canvasLayout = document.querySelector("#canvas-layout");
        ctxLayout = canvasLayout.getContext("2d");
        canvasContent = document.querySelector("#canvas-content");
        ctxContent = canvasContent.getContext("2d");

        // Set the canvases square
        canvasLayout.width = document.body.clientWidth;
        canvasLayout.height = canvasLayout.width;
        canvasContent.width = document.body.clientWidth;
        canvasContent.height = canvasContent.width;

        center = {
            x: document.body.clientWidth / 2,
            y: document.body.clientHeight / 2
        };

        watchRadius = canvasLayout.width / 2;
    }

    /**
     * Set default event listeners
     * @private
     */
    function setDefaultEvents() {
        // add eventListener to update the screen immediately when the device wakes up
        document.addEventListener("visibilitychange", function() {
            if (!document.hidden) {
                // Draw the content of the watch
                drawWatchContent();
            }
        });
    }

    /**
     * Initiates the application
     * @private
     */
    function init() {
        setDefaultVariables();
        setDefaultEvents();

        // Draw the basic layout and the content of the watch at the beginning
        drawWatchLayout();
        drawWatchContent();

        // Update the content of the watch every second
        setInterval(function() {
            drawWatchContent();
        }, 1000);
    }

    window.onload = init;
}());
