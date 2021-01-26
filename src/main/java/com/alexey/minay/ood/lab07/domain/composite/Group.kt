package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.LineType
import com.alexey.minay.ood.lab07.domain.RGBAColor
import kotlin.math.max
import kotlin.math.min

class Group(
        override var fillStyle: FillStyle,
        override var lineStyle: LineStyle
) : IGroup {

    private val mShapes = mutableListOf<IShape>()

    override var group: IGroup? = null

    override var frame: Frame
        get() = getCurrentFrame()
        set(value) = resizeFrame(value)

    override fun getShapeCount() = mShapes.count()

    override fun getShapeIndexAt(index: Int) = mShapes[index]

    override fun insertShape(shape: IShape, index: Int) {
        mShapes.add(index, shape)
        shape.group = this
    }

    override fun removeAt(index: Int) {
        mShapes.removeAt(index)
    }

    override fun draw(canvas: ICanvas) {
        drawFrame(canvas)
        mShapes.forEach {
            if (fillStyle.isEnable) it.fillStyle = fillStyle
            if (lineStyle.isEnable) it.lineStyle = lineStyle
            it.draw(canvas)
        }
    }

    private fun resizeFrame(newFrame: Frame) {
        val currentFrame = getCurrentFrame()
        mShapes.forEach {
            it.apply {
                frame = Frame(
                        left = frame.left * newFrame.width / currentFrame.width,
                        right = frame.right * newFrame.width / currentFrame.width,
                        top = frame.top * newFrame.height / currentFrame.height,
                        bottom = frame.bottom * newFrame.height / currentFrame.height
                )
            }
        }
    }

    private fun getCurrentFrame() =
            when {
                mShapes.size == 0 -> Frame(0.0, 0.0, 0.0, 0.0)
                mShapes.size == 1 -> mShapes[0].frame
                else -> {
                    val offset = 10
                    val frame = Frame(
                            right = mShapes[0].frame.right,
                            left = mShapes[0].frame.left,
                            top = mShapes[0].frame.top,
                            bottom = mShapes[0].frame.bottom
                    )
                    mShapes.forEach { shape ->
                        frame.bottom = max(frame.bottom, shape.frame.bottom)
                        frame.top = min(frame.top, shape.frame.top)
                        frame.right = max(frame.right, shape.frame.right)
                        frame.left = min(frame.left, shape.frame.left)
                    }
                    frame.bottom += offset
                    frame.top -= offset
                    frame.right += offset
                    frame.left -= offset
                    frame
                }
            }

    private fun drawFrame(canvas: ICanvas) {
        val currentFrame = frame
        canvas.setLineColor(RGBAColor.GREY_TRANSLUCENT)
        canvas.setLineType(LineType.Frame)
        canvas.moveTo(currentFrame.left, currentFrame.top)
        canvas.lineTo(currentFrame.left, currentFrame.bottom)
        canvas.lineTo(currentFrame.right, currentFrame.bottom)
        canvas.lineTo(currentFrame.right, currentFrame.top)
        canvas.lineTo(currentFrame.left, currentFrame.top)
        canvas.fill(RGBAColor.TRANSPARENT)
    }

}