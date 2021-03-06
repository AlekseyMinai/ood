package com.alexey.minay.ood.lab05.commands

import java.io.File

open class FileHelper {

    fun copyFile(donorFilePath: String, newPath: String, newFilePath: String) {
        val file = File(donorFilePath)
        val tempPathDir = File(newPath)
        if (!tempPathDir.exists()) tempPathDir.mkdir()
        val tempFile = File(newFilePath)
        tempFile.writeBytes(file.readBytes())
    }

    fun deleteFile(imagePath: String) {
        File(imagePath).delete()
    }

}