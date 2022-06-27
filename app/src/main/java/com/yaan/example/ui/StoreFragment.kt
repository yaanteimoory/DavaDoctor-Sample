package com.yaan.example.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.yaan.example.App
import com.yaan.example.adapters.ProductsAdapter
import com.yaan.example.databinding.FragmentStoreBinding
import com.yaan.example.models.MainViewModel


class StoreFragment : Fragment() {

    val mViewModel: MainViewModel by activityViewModels {
        MainViewModel.Factory((activity?.application as App).network)
    }

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductsAdapter()

        binding.apply {
//            lifecycleOwner = viewLifecycleOwner
            rvProducts.adapter = adapter
            tabCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {

                    mViewModel.getProductOfCategory(tab.tag as Int)

                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    mViewModel.getProductOfCategory(tab.tag as Int)
                }
            })
        }

        mViewModel.productList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mViewModel.categoryList.observe(viewLifecycleOwner) { list ->
            list.forEach { category ->
                binding.tabCategories.newTab().apply {
                    tag = category.id
                    text = category.name

                }
//                binding.tabCategories.addTab(tab, false)

            }
        }

        mViewModel.error.observe(viewLifecycleOwner){error->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        mViewModel.loading.observe(viewLifecycleOwner){isLoading->
            binding.rootStore.visibility = if(isLoading) View.GONE else View.VISIBLE
            binding.loading.visibility = if(isLoading) View.VISIBLE else View.GONE

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}