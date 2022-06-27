package com.yaan.example.models

import android.util.Log
import androidx.lifecycle.*
import com.yaan.example.network.volly.NetworkUtility
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel(private val networkUtil: NetworkUtility) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _categoryList = MutableLiveData<List<Category>>(listOf())
    val categoryList : LiveData<List<Category>> get() = _categoryList

    private val _productList = MutableLiveData<List<Product>>(listOf())
    val productList:LiveData<List<Product>> get() = _productList

    private val _selectedProduct = MutableLiveData<Product?>(null)
    val selectedProduct:LiveData<Product?> get() = _selectedProduct

    init {
        getData()
    }


    private suspend fun getCategories(): List<Category> {

        val json = networkUtil.getCategories()
        return try {
            Category.fromJsonArray(JSONArray(json))
        } catch (ex: Exception) {
            _error.value = json
//            Log.d("teeeeeeest", "getCategories: Json: $json")
            Log.d("teeeeeeest", "getCategories: exception: error: $ex")
            listOf()
        }

    }

    private suspend fun getProducts(): List<Product> {

        val json = networkUtil.getProducts()
        return try {
            Product.fromJsonArray(JSONArray(json))
        } catch (ex: Exception) {
            _error.value = json
            Log.d("teeeeeeest", "getProducts: Json: $json")
            Log.d("teeeeeeest", "getProducts: exception: error: $ex")
            listOf()
        }

    }

    private fun getData() {
        viewModelScope.launch {
            _loading.value = true
            _productList.value = getProducts()
            _categoryList.value = getCategories()
            _loading.value = false

        }
    }


    fun getProductOfCategory(categoryId: Int) {
        _loading.value = true
        viewModelScope.launch {
            val json = networkUtil.getCategoryProducts(categoryId)
            try {
                _productList.value = Product.fromJsonArray(JSONArray(json))
            } catch (ex: Exception) {
                _error.value = json
                Log.d("teeeeeeest", "getProductOfCategory: Json: $json")
                Log.d("teeeeeeest", "getProductOfCategory: exception: error: $ex")
            } finally {
                _loading.value = false
            }
        }
    }

    fun getProductById(id: Int) {
        _loading.value = true
        viewModelScope.launch {
            val json = networkUtil.getProductById(id)
            try {
                _selectedProduct.value = Product.fromJson(JSONObject(json))
            } catch (ex: Exception) {
                _error.value = json
                Log.d("teeeeeeest", "getProductById: Json: $json")
                Log.d("teeeeeeest", "getProductById: exception: error: $ex")
            } finally {
                _loading.value = false
            }
        }
    }
    fun unBindProduct(){
        _selectedProduct.value = null
    }


    class Factory(private val networkUtil: NetworkUtility) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java))
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(networkUtil) as T

            throw IllegalArgumentException("Unexpected class")
        }

    }
}