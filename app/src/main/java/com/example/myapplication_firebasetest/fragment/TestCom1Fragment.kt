package com.example.myapplication_firebasetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_firebasetest.adpater.RecyclerViewTest
import com.example.myapplication_firebasetest.adpater.ViewPageAdapterTest
import com.example.myapplication_firebasetest.databinding.FragmentTestCom1Binding

class TestCom1Fragment : Fragment() {
    lateinit var binding : FragmentTestCom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTestCom1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestCom1Binding.inflate(layoutInflater)
        val viewpagerF1Test1 = binding.viewpagerF1Test1

        viewpagerF1Test1.adapter = activity?.let { ViewPageAdapterTest(it) }

        // 리사이클러뷰 붙이기
        // 준비물) 1) 리사이클러뷰 어댑터 2)목록요소의 아이템 뷰 3) 더미 데이터
        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("더미 데이터 추가 번호 $i")
        }
        // 출력 방향, 리니어 나란히 수직으로
        val layoutManager = LinearLayoutManager(activity)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

//        val layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)


        // 리사이클러뷰 속성 옵션에 출력 옵션 붙이기.
        binding.recyclerViewTest1.layoutManager = layoutManager
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest1.adapter = customAdapter


        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        // 리사이클러뷰 속성 옵션에 출력 옵션 붙이기.
        binding.recyclerViewTest2.layoutManager = layoutManager2
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter2 = RecyclerViewTest(datas)
        binding.recyclerViewTest2.adapter = customAdapter2

        return binding.root
    }

}