package com.yaan.example.models

import org.json.JSONArray
import org.json.JSONObject


//data class Product(
//
//    @SerializedName("id") val id: Int? = null,
//    @SerializedName("name") val name: String? = null,
//    @SerializedName("slug") val slug: String? = null,
//    @SerializedName("permalink") val permalink: String? = null,
//    @SerializedName("date_created") val dateCreated: String? = null,
//    @SerializedName("date_created_gmt") val dateCreatedGmt: String? = null,
//    @SerializedName("date_modified") val dateModified: String? = null,
//    @SerializedName("date_modified_gmt") val dateModifiedGmt: String? = null,
//    @SerializedName("type") val type: String? = null,
//    @SerializedName("status") val status: String? = null,
//    @SerializedName("featured") val featured: Boolean? = null,
//    @SerializedName("catalog_visibility") val catalogVisibility: String? = null,
//    @SerializedName("description") val description: String? = null,
//    @SerializedName("short_description") val shortDescription: String? = null,
//    @SerializedName("sku") val sku: String? = null,
//    @SerializedName("price") val price: String? = null,
//    @SerializedName("regular_price") val regularPrice: String? = null,
//    @SerializedName("sale_price") val salePrice: String? = null,
//    @SerializedName("date_on_sale_from") val dateOnSaleFrom: String? = null,
//    @SerializedName("date_on_sale_from_gmt") val dateOnSaleFromGmt: String? = null,
//    @SerializedName("date_on_sale_to") val dateOnSaleTo: String? = null,
//    @SerializedName("date_on_sale_to_gmt") val dateOnSaleToGmt: String? = null,
//    @SerializedName("on_sale") val onSale: Boolean? = null,
//    @SerializedName("purchasable") val purchasable: Boolean? = null,
//    @SerializedName("total_sales") val totalSales: Int? = null,
//    @SerializedName("virtual") val virtual: Boolean? = null,
//    @SerializedName("downloadable") val downloadable: Boolean? = null,
//    @SerializedName("downloads") val downloads: ArrayList<String> = arrayListOf(),
//    @SerializedName("download_limit") val downloadLimit: Int? = null,
//    @SerializedName("download_expiry") val downloadExpiry: Int? = null,
//    @SerializedName("external_url") val externalUrl: String? = null,
//    @SerializedName("button_text") val buttonText: String? = null,
//    @SerializedName("tax_status") val taxStatus: String? = null,
//    @SerializedName("tax_class") val taxClass: String? = null,
//    @SerializedName("manage_stock") val manageStock: Boolean? = null,
//    @SerializedName("stock_quantity") val stockQuantity: String? = null,
//    @SerializedName("backorders") val backorders: String? = null,
//    @SerializedName("backorders_allowed") val backordersAllowed: Boolean? = null,
//    @SerializedName("backordered") val backordered: Boolean? = null,
//    @SerializedName("low_stock_amount") val lowStockAmount: String? = null,
//    @SerializedName("sold_individually") val soldIndividually: Boolean? = null,
//    @SerializedName("weight") val weight: String? = null,
//    @SerializedName("dimensions") val dimensions: Dimensions? = Dimensions(),
//    @SerializedName("shipping_required") val shippingRequired: Boolean? = null,
//    @SerializedName("shipping_taxable") val shippingTaxable: Boolean? = null,
//    @SerializedName("shipping_class") val shippingClass: String? = null,
//    @SerializedName("shipping_class_id") val shippingClassId: Int? = null,
//    @SerializedName("reviews_allowed") val reviewsAllowed: Boolean? = null,
//    @SerializedName("average_rating") val averageRating: String? = null,
//    @SerializedName("rating_count") val ratingCount: Int? = null,
//    @SerializedName("upsell_ids") val upsellIds: ArrayList<String> = arrayListOf(),
//    @SerializedName("cross_sell_ids") val crossSellIds: ArrayList<String> = arrayListOf(),
//    @SerializedName("parent_id") val parentId: Int? = null,
//    @SerializedName("purchase_note") val purchaseNote: String? = null,
//    @SerializedName("categories") val categories: ArrayList<Category> = arrayListOf(),
//    @SerializedName("tags") val tags: ArrayList<String> = arrayListOf(),
//    @SerializedName("images") val images: ArrayList<Images> = arrayListOf(),
//    @SerializedName("attributes") val attributes: ArrayList<String> = arrayListOf(),
//    @SerializedName("default_attributes") val defaultAttributes: ArrayList<String> = arrayListOf(),
//    @SerializedName("variations") val variations: ArrayList<String> = arrayListOf(),
//    @SerializedName("grouped_products") val groupedProducts: ArrayList<String> = arrayListOf(),
//    @SerializedName("menu_order") val menuOrder: Int? = null,
//    @SerializedName("price_html") val priceHtml: String? = null,
//    @SerializedName("related_ids") val relatedIds: ArrayList<Int> = arrayListOf(),
//    @SerializedName("meta_data") val metaData: ArrayList<MetaData> = arrayListOf(),
//    @SerializedName("stock_status") val stockStatus: String? = null,
//    @SerializedName("has_options") val hasOptions: Boolean? = null,
//    @SerializedName("_links") val Links: Links? = Links()
//
//) {
//    companion object {
//        fun fromJsonArray(json: String): List<Product> =
//            Gson().fromJson(json, Array<Product>::class.java).toList()
//
//        fun fromJson(json: String): Product = Gson().fromJson(json, Product::class.java)
//    }
//}

data class Product(
    val id: Int,
    val name: String,
    val salePrice: String,
    val description: String,
    val categories: List<Category>,
    val images: List<String>
) {
    companion object {
//        fun fromJson(json: String): Product {
//            val obj = JSONObject(json)
//            return Product(
//                id = obj.getInt("id"),
//                name = obj.getString("name"),
//                salePrice = obj.getString("sale_price"),
//                description = obj.getString("description"),
//                categories = Category.fromJsonArray(obj.getJSONArray("categories").toString()),
//                images = retrieveImagesLink(obj.getJSONArray("images"))
//                )
//        }
        fun fromJson(json: JSONObject): Product {

            return Product(
                id = json.getInt("id"),
                name = json.getString("name"),
                salePrice = json.getString("sale_price"),
                description = json.getString("description"),
                categories = Category.fromJsonArray(json.getJSONArray("categories")),
                images = retrieveImagesLink(json.getJSONArray("images"))
            )
        }

        fun fromJsonArray(json:JSONArray):List<Product>{
            val list = mutableListOf<Product>()
//            val array = JSONArray(json)
            for (i in 0 until json.length()){
                list.add(fromJson(json.getJSONObject(i)))
            }
            return list
        }


    }
}


private fun retrieveImagesLink(jsonArray: JSONArray):List<String>{
    val list = mutableListOf<String>()
    for (i in 0 until jsonArray.length()){
        list.add(jsonArray.getJSONObject(i).getString("src"))
    }
    return  list
}

