package com.alexey.minay.ood.lab3.streams.output

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class MemoryOutputStreamTest {

    private val mMemory = mutableListOf<Byte>()
    private val mMemoryOutputStream = MemoryOutputStream(mMemory)

    @Test
    fun shouldWriteByte() {
        mMemoryOutputStream.writeByte('t'.toInt())
        assertEquals('t'.toInt(), mMemory[0].toInt())
    }

    @Test
    fun shouldWriteBlock() {
        val bytes = intArrayOf('t'.toInt(), 'e'.toInt(), 's'.toInt(), 't'.toInt())
        mMemoryOutputStream.writeBlock(bytes)
        assertEquals('e'.toInt(), mMemory[1].toInt())
    }

    @After
    fun clear() {
        mMemory.clear()
    }

}