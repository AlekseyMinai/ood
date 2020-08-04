package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream

abstract class InputStreamDecorator(
        private val inputStream: IInputStream
) : IInputStream {

    override fun readByte(): Int {
        val byte = inputStream.readByte()
        var decorateByte = decorateByte(byte)
        if (decorateByte == -1) return -1
        while (decorateByte == -2) {
            decorateByte = decorateByte(inputStream.readByte())
        }
        return decorateByte
    }

    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
            return inputStream.readBlock(decorateBlock { if (it != -1) dstBuffer(it) }, size)
    }

    protected abstract fun decorateByte(byte: Int): Int

    protected abstract fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit

}