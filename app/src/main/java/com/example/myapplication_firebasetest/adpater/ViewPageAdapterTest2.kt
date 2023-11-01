package com.example.myapplication_firebasetest.adpater


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication_firebasetest.fragment.TestCom1Fragment
import com.example.myapplication_firebasetest.fragment.TestCom2Fragment
import com.example.myapplication_firebasetest.fragment.TestCom3Fragment

class ViewPageAdapterTest2(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    //각 프래그먼트들을 담을 리스트
    var testFragment : List<Fragment>

    init {
        testFragment = listOf(TestCom1Fragment(), TestCom2Fragment(), TestCom3Fragment())
    }

    override fun getItemCount(): Int {
        return testFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        val returnFragment : Fragment = testFragment[position]
        return returnFragment
    }

}