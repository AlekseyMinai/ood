package com.alexey.minay.ood.lab05

class Menu {

    private val mItems = mutableMapOf<String, Item>()
    private var mExit = false

    fun addItem(shortcut: String, description: String, command: (List<String>) -> Unit) {
        mItems[shortcut] = Item(description, command)
    }

    fun run(message: (String) -> Unit, readCommand: () -> String?) {
        while (!mExit) {
            val input = readCommand() ?: continue
            try {
                input.split(" ").also {
                    mItems[it[0]]?.command
                        ?.invoke(it)
                        ?: message("Unknown command")
                }
            } catch (e: Exception) {
                message(e.message ?: "")
            }
        }
    }

    fun showInstructions(output: (String) -> Unit) {
        mItems.forEach {
            output("- ${it.key} - ${it.value.description}")
        }
    }

    fun exit() {
        mExit = true
    }

    private inner class Item(
        val description: String,
        val command: (List<String>) -> Unit
    )

}