package com.yaan.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.yaan.example.App
import com.yaan.example.databinding.FragmentProductBinding
import com.yaan.example.models.MainViewModel

class ProductFragment : Fragment() {

    private val mViewModel:MainViewModel by activityViewModels{
        MainViewModel.Factory((activity?.application as App).network)
    }

//    private val args : Pro by navArgs()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mViewModel.getProductById(args.productId)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = mViewModel

        mViewModel.error.observe(viewLifecycleOwner){error->
            Toast.makeText(requireContext(), error,Toast.LENGTH_SHORT).show()
        }
        mViewModel.loading.observe(viewLifecycleOwner){isLoading->
            binding.rootProduct.visibility = if(isLoading) View.GONE else View.VISIBLE
            binding.loading.visibility = if(isLoading) View.VISIBLE else View.GONE

        }


    }

    override fun onDestroyView() {
        _binding=null
        mViewModel.unBindProduct()
        super.onDestroyView()

    }

}