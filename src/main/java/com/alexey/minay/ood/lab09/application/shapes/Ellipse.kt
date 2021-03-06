package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICanvas
import com.alexey.minay.ood.lab09.application.Style
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.pow

class Ellipse(
    override var frame: AppFrame,
    override val uid: Long
) : IAppShape {

    private val leftTop: AppPoint
        get() = frame.leftTop
    private val horizontalDiameter: Double
        get() = frame.rightTop.x - frame.leftTop.x
    private val verticalDiameter: Double
        get() = frame.rightBottom.y - frame.rightTop.y

    override fun draw(canvas: ICanvas) = with(canvas) {
        setStyle(Style.SHAPE)
        fillEllipse(leftTop, horizontalDiameter, verticalDiameter)
        drawEllipse(leftTop, horizontalDiameter, verticalDiameter)
    }

    override fun isIncluding(point: AppPoint) =
        (point.x - frame.center.x).pow(2) +
                (point.y - frame.center.y).pow(2) <= (verticalDiameter / 2).pow(2)

}