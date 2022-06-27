package com.yaan.example.models

import com.google.gson.annotations.SerializedName


data class Dimensions (

  @SerializedName("length" ) var length : String? = null,
  @SerializedName("width"  ) var width  : String? = null,
  @SerializedName("height" ) var height : String? = null

)