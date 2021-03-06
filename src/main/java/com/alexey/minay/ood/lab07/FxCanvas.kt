package com.alexey.minay.ood.lab07

import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class FxCanvas(
        private val graphicsContext: GraphicsContext
) : ICanvas {

    private var fromX: Double = 0.0
    private var fromY: Double = 0.0
    private var mListX = mutableListOf<Double>()
    private var mListY = mutableListOf<Double>()

    override fun fill(color: RGBAColor) {
        graphicsContext.fill = color.asFxColor()
        graphicsContext.fillPolygon(mListX.toDoubleArray(), mListY.toDoubleArray(), mListX.size)
        mListX.clear()
        mListY.clear()
    }

    override fun fillEllipse(color: RGBAColor, left: Double, top: Double, width: Double, height: Double) {
        graphicsContext.fill = color.asFxColor()
        graphicsContext.fillOval(left, top, width, height)
    }

    override fun moveTo(x: Double, y: Double) {
        fromX = x
        fromY = y
        mListX.add(x)
        mListY.add(y)
    }

    override fun lineTo(x: Double, y: Double) {
        graphicsContext.strokeLine(fromX, fromY, x, y)
        fromX = x
        fromY = y
        mListX.add(x)
        mListY.add(y)
    }

    override fun setLineWidth(lineWidth: Double) {
        graphicsContext.lineWidth = lineWidth
    }

    override fun drawEllipse(left: Double, top: Double, width: Double, height: Double) {
        graphicsContext.strokeOval(left, top, width, height)
    }

    override fun setLineColor(color: RGBAColor) {
        graphicsContext.stroke = color.asFxColor()
    }

    override fun clearRect(left: Double, top: Double, width: Double, height: Double) {
        graphicsContext.clearRect(left, top, width, height)
    }

    companion object {

        private const val MAX_COLOR_VALUE = 255

        private fun RGBAColor.asFxColor() = Color(
                r.asFxColorChannel(),
                g.asFxColorChannel(),
                b.asFxColorChannel(),
                a
        )

        private fun Int.asFxColorChannel() = this.toDouble() / MAX_COLOR_VALUE

    }

}

