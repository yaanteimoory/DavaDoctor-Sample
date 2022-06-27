package com.yaan.example.models

import com.google.gson.annotations.SerializedName


data class Links(

    @SerializedName("self") var self: ArrayList<Self> = arrayListOf(),
    @SerializedName("collection") var collection: ArrayList<Collection> = arrayListOf()

)