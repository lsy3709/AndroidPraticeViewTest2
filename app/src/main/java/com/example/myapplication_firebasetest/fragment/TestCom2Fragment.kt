package com.example.myapplication_firebasetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication_firebasetest.databinding.FragmentTestCom2Binding

class TestCom2Fragment : Fragment() {
    lateinit var binding : FragmentTestCom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTestCom2Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestCom2Binding.inflate(layoutInflater)
        return binding.root
    }

}