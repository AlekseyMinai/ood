package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame

abstract class Shape(
        override val style: IStyle,
        override var frame: Frame?
) : IShape