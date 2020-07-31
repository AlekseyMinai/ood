package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.commands.*
import com.alexey.minay.ood.lab05.document.DocumentPrinter
import com.alexey.minay.ood.lab05.document.HTMLDocument
import com.alexey.minay.ood.lab05.document.IDocument
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    val document = HTMLDocument()
    val receiver = Receiver()
    val documentPrinter = DocumentPrinter(document)
    println("введите команду:")
    println("Help - помощь")
    while (true) {
        val input = readLine() ?: continue
        val command = CommandHandler.newCommand(document, documentPrinter, input)
        if (command == null) {
            println("Incorrect command, print \"Help\"..")
            continue
        }
        try {
            receiver.add(command)
            println("Command executed..")
        }catch (e: RuntimeException){
            println(e.localizedMessage)
        }catch (e: FileNotFoundException){
            println(e.localizedMessage)
        }
        if (command is CloseDocumentCommand) return
    }
}

object CommandHandler {

    fun newCommand(document: IDocument, documentPrinter: DocumentPrinter, input: String): ICommand? {
        val splittedCommand = input.split(" ")
        if (splittedCommand.isEmpty()) return null
        val commandName = splittedCommand[0]
        return when (commandName) {
            "InsertParagraph" -> createInsertParagraphCommand(document, splittedCommand)
            "InsertImage" -> createInsertImageCommand(document, splittedCommand)
            "SetTitle" -> createSetTitleCommend(document, splittedCommand)
            "List" -> PrintDocumentCommand(documentPrinter)
            "ReplaceText" -> createReplaceTextCommand(document, splittedCommand)
            "ResizeImage" -> createResizeImageCommand(document, splittedCommand)
            "Help" -> HelpCommand()
            "Undo" -> UndoCommand(document)
            "Redo" -> RedoCommand(document)
            "Save" -> RedoCommand(document)
            "Close" -> CloseDocumentCommand(document)
            else -> null
        }
    }

    private fun createInsertParagraphCommand(document: IDocument, splittedCommand: List<String>): ICommand? {
        if (splittedCommand.size < 3) return null
        val text = splittedCommand.subList(2, splittedCommand.size).joinToString()
        if (splittedCommand[1] == "end") {
            return InsertParagraphCommand(document, text)
        }
        val position: Int = splittedCommand[1].toIntOrNull() ?: return null
        return InsertParagraphCommand(document, text, position)
    }

    private fun createInsertImageCommand(document: IDocument, splittedCommand: List<String>): ICommand? {
        if (splittedCommand.size != 5) return null
        val width: Int = splittedCommand[2].toIntOrNull() ?: return null
        val height: Int = splittedCommand[3].toIntOrNull() ?: return null
        val path: String = splittedCommand[4]
        if (splittedCommand[1] == "end") {
            return InsertImageCommand(document, path, width, height)
        }
        val position: Int = splittedCommand[1].toIntOrNull() ?: return null
        return InsertImageCommand(document, path, width, height, position)
    }

    private fun createSetTitleCommend(document: IDocument, splittedCommand: List<String>): ICommand? {
        if (splittedCommand.size < 2) return null
        return SetTitleCommand(document, splittedCommand.subList(1, splittedCommand.size).joinToString())
    }

    private fun createReplaceTextCommand(document: IDocument, splittedCommand: List<String>): ICommand? {
        if (splittedCommand.size < 3) return null
        val position: Int = splittedCommand[1].toIntOrNull() ?: return null
        val text = splittedCommand.subList(2, splittedCommand.size).joinToString()
        return ReplaceTextCommand(document, text, position)
    }

    private fun createResizeImageCommand(document: IDocument, splittedCommand: List<String>): ICommand? {
        if (splittedCommand.size != 4) return null
        val position: Int = splittedCommand[1].toIntOrNull() ?: return null
        val width: Int = splittedCommand[2].toIntOrNull() ?: return null
        val height: Int = splittedCommand[3].toIntOrNull() ?: return null
        return ResizeImageCommand(document, position, width, height)
    }

}

