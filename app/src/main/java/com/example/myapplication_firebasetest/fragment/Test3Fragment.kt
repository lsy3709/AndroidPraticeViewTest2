package com.example.myapplication_firebasetest.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication_firebasetest.databinding.FragmentTest3Binding

class Test3Fragment : Fragment() {
    lateinit var binding: FragmentTest3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTest3Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTest3Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}