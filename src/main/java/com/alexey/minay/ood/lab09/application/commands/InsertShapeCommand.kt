package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand

class InsertShapeCommand(
    private val model: ApplicationDocument,
    private val shape: IAppShape
) : ICommand {

    override fun execute() {
        model.insertShapeAt(model.shapeCount, shape)
    }

    override fun unexecute() {
        model.removeShapeAt(model.shapeCount)
    }
}