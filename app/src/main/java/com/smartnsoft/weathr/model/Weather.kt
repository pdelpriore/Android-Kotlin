package com.smartnsoft.weathr.model

import com.smartnsoft.weathr.R

enum class Weather (val type: String, val picture: Int, val color: Int) {

    CLOUDY("CLOUDY", R.drawable.cloudy, R.color.pink),
    RAINY("RAINY", R.drawable.rainy, R.color.purple),
    STORMY("STORMY", R.drawable.stormy, R.color.turquoise),
    SUNNY("SUNNY", R.drawable.sunny, R.color.yellow),
    SNOWY("SNOWY", R.drawable.snowy, R.color.idontknow);

    companion object {

        fun getPictureByName (type: String?) : Int =

                when (type) {

                    CLOUDY.name -> CLOUDY.picture
                    RAINY.name -> RAINY.picture
                    STORMY.name -> STORMY.picture
                    SUNNY.name -> SUNNY.picture
                    SNOWY.name -> SNOWY.picture

                    else -> -1
                }

        fun getColorByType (type: String?) : Int =

               when (type) {

                   CLOUDY.name -> CLOUDY.color
                   RAINY.name -> RAINY.color
                   STORMY.name -> STORMY.color
                   SUNNY.name -> SUNNY.color
                   SNOWY.name -> SNOWY.color

                   else -> -1
               }
    }
}