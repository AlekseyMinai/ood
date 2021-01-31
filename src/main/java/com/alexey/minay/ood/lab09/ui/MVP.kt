package com.alexey.minay.ood.lab09.ui

import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.application.ResizableState
import java.io.File

interface MVP {

    interface ICanvasView {
        fun updateCursor(cursorState: ResizableState)
    }

    interface ICanvasPresenter {
        fun onViewCreated(view: ICanvasView)
        fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double)
        fun onMoveShape(newPosition: Point, parentWidth: Double, parentHeight: Double)
        fun onMouseMoved(mousePosition: Point)
        fun onMouseClicked(mousePosition: Point)
        fun onMousePressed(pressedPoint: Point)
        fun onDeleteShape()
        fun onMouseReleased()
        fun onStyleModified()
        fun onUndo()
        fun onRedo()
    }

    interface IFileTabView

    interface IFileTabPresenter {
        fun onViewCreated(view: IFileTabView)
        fun onSave(file: File)
        fun onSaveAs(file: File)
        fun onOpen(file: File)
    }

}