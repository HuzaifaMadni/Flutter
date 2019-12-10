package com.huzaifa.designpatterndemo.BuilderPattern



class HouseTypes {

    enum class HouseType(val type: String) {

        House("House"),
        Villa("Villa"),
        Mansion("Mansion"),
    }

    companion object {

        fun getHouse(): String {
            return HouseType.House.type
        }

        fun getVilla():String {
            return HouseType.Villa.type
        }

        fun getMansion(): String {
            return HouseType.Mansion.type
        }
    }
}