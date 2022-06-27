package com.yaan.example.models

import com.google.gson.annotations.SerializedName


data class Images (

  @SerializedName("id"                ) var id              : Int?    = null,
  @SerializedName("date_created"      ) var dateCreated     : String? = null,
  @SerializedName("date_created_gmt"  ) var dateCreatedGmt  : String? = null,
  @SerializedName("date_modified"     ) var dateModified    : String? = null,
  @SerializedName("date_modified_gmt" ) var dateModifiedGmt : String? = null,
  @SerializedName("src"               ) var src             : String? = null,
  @SerializedName("name"              ) var name            : String? = null,
  @SerializedName("alt"               ) var alt             : String? = null

)