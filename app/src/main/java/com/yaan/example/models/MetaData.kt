package com.yaan.example.models

import com.google.gson.annotations.SerializedName


data class MetaData (

  @SerializedName("id"    ) var id    : Int?    = null,
  @SerializedName("key"   ) var key   : String? = null,
  @SerializedName("value" ) var value : String? = null

)