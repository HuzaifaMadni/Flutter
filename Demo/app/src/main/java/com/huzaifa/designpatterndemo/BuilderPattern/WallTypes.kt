package com.huzaifa.designpatterndemo.BuilderPattern

class WallTypes {

    enum class WallType(val type: String) {

        Stone("Stone"),
        Cement("Cement"),
        Brick("Brick"),

    }

    companion object {

        fun getStone(): String {
            return WallType.Stone.type
        }

        fun getCement(): String {
            return WallType.Cement.type
        }

        fun getBrick(): String {
            return WallType.Brick.type
        }
    }
}