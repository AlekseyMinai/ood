package com.alexey.minay.ood.lab3.coffee

fun main() {
    val coffee: IBeverage = IceCube(Cinnamon(Cappuccino(Cappuccino.Portion.DOUBLE)), 3, IceCubeType.DRY)
    println(coffee.getCost())
}