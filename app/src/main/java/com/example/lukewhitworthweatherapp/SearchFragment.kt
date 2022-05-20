package com.example.lukewhitworthweatherapp

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.lukewhitworthweatherapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment()  {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        setUpSpinner()

        // enable searching if editText has been changed
        binding.etCityInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable) {
                binding.btnSearch.isEnabled = true
            }
        })

        binding.btnSearch.setOnClickListener(View.OnClickListener {
            val cityInput = binding.etCityInput.text.toString()
            val tempInput = binding.spinUnitSelect.selectedItem.toString()

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(com.example.lukewhitworthweatherapp.R.id.main_view, ScrollFragment())
                ?.commit()
        })
        return binding.root
    }

    private fun setUpSpinner() {
        val units = arrayOf("°C", "°F", "K")

        val adapter: ArrayAdapter<String>? = activity?.let { ArrayAdapter<String>(it, R.layout.simple_spinner_item, units) }
        binding.spinUnitSelect.adapter = adapter
    }
}