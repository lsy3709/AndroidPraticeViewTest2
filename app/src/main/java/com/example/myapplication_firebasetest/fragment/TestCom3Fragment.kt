package com.example.myapplication_firebasetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication_firebasetest.databinding.FragmentTestCom3Binding

class TestCom3Fragment : Fragment() {
    lateinit var binding : FragmentTestCom3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTestCom3Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestCom3Binding.inflate(layoutInflater)
        return binding.root
    }

}