package com.sunsetgames.bordersecurit.main.util

import kotlin.properties.Delegates

class GameClass {
    lateinit var name: String
    lateinit var category: String
//    var poster by Delegates.notNull<Int>()
     var poster: Int = 0
}