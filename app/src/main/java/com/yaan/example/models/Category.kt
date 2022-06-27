package com.yaan.example.models

import org.json.JSONArray
import org.json.JSONObject

//
//data class Category(
//    @SerializedName("id") val id: Int,
//    @SerializedName("name") val name: String,
//    @SerializedName("slug") val slug: String,
//    @SerializedName("parent") val parent: Int?,
//    @SerializedName("description") val description: String,
//    @SerializedName("display") val display: String,
//    @SerializedName("image") val image: String?,
//    @SerializedName("menu_order") val menuOrder: Int,
//    @SerializedName("count") val count: Int,
//    @SerializedName("links") val links: Links?
//) {
//    companion object {
//        fun fromJson(json: String): List<Category> =
//            Gson().fromJson(json, Array<Category>::class.java).toList()
//    }
//
//
//}


data class Category(val id: Int, val name: String) {
    companion object {
//        fun fromJson(json: String):Category{
//            val obj = JSONObject(json)
//            return Category(id = obj.getInt("id"), name = obj.getString("name"))
//        }
        fun fromJson(json: JSONObject):Category{
            return Category(id = json.getInt("id"), name = json.getString("name"))
        }
        fun fromJsonArray(json: JSONArray): List<Category> {
            val list = mutableListOf<Category>()
//            val array = JSONArray(json)
            for (i in 0 until json.length()){
                list.add(fromJson(json.getJSONObject(i)))
            }
            return list
        }
    }
}




