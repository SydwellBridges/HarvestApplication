package com.example.harvest.navigate

data class Survey(val id: String ?= "", val location: String ?= "", val size: Int ?= 0, val land: String ?= "",
                  val slope: String ?= "", val distance: Int ?= 0, val shade: String ?= "", var soiltype: String ?= "",
                  var phlevel: Int ?= 0, var soiltexture: String ?= "", var soilcolour: String ?= "", var soilPorosity: String ?= ""){

    constructor():this( "","", 0, "", "", 0, "", "", 0, "", "", ""){}

}