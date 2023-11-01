package com.example.myapplication_firebasetest

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_firebasetest.adpater.RecyclerViewTest
import com.example.myapplication_firebasetest.adpater.ViewPageAdapterTest2
import com.example.myapplication_firebasetest.databinding.ActivityTestTabComBinding
import com.google.android.material.tabs.TabLayoutMediator

class TestTabComActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestTabComBinding
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestTabComBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tab , 뷰페이저 연동
        val tabLayout = binding.tabs

        val viewPager = binding.viewpagerAdd




        viewPager.adapter= ViewPageAdapterTest2(this)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

        binding.mainDrawerView.setNavigationItemSelectedListener {
                it ->
            if (it.title == "로그인") {
                Toast.makeText(this@TestTabComActivity,"로그인 화면 이동", Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "로그아웃") {
                Toast.makeText(this@TestTabComActivity,"로그아웃 화면 이동", Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "메인가기") {
                Toast.makeText(this@TestTabComActivity,"메인가기 화면 이동", Toast.LENGTH_SHORT).show()
            }
            true
        }

        setSupportActionBar(binding.toolbar)


        // 드로워 화면에 액션 버튼 클릭시 -> 드로워 화면에 나오게.
        toggle = ActionBarDrawerToggle(this@TestTabComActivity,
            binding.drawer, R.string.open, R.string.close)
        // 화면에 붙이는 작업, 적용하기.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 버튼 클릭시, 동기화, 드러워 화면을 열어주는 기능.
        toggle.syncState()

        //4) floating action button
        // 이벤트 추가하기.
        binding.floatingActionButton.setOnClickListener {
            when(binding.floatingActionButton.isExtended) {
                true -> binding.floatingActionButton.shrink()
                false -> binding.floatingActionButton.extend()
            }
            Toast.makeText(this@TestTabComActivity,"floatingActionButton 클릭됨", Toast.LENGTH_SHORT).show()
        }


        // 리사이클러뷰 붙이기
        // 준비물) 1) 리사이클러뷰 어댑터 2)목록요소의 아이템 뷰 3) 더미 데이터
        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("더미 데이터 추가 번호 $i")
        }
        // 출력 방향, 리니어 나란히 수직으로
        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

//        val layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)


        // 리사이클러뷰 속성 옵션에 출력 옵션 붙이기.
        binding.recyclerViewTest.layoutManager = layoutManager
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest.adapter = customAdapter
        //onCreate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        // 버튼을 열때 이용되는 이벤트 핸들러 부분.
        if(toggle.onOptionsItemSelected(item)){
            return true
            // 오버 플로우 메뉴의 클릭시 이벤트를 ,
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@TestTabComActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@TestTabComActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@TestTabComActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        return super.onOptionsItemSelected(item)
    }

    // 검색 이벤트 핸들러 추가하는 부분.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 만약, 다른 메뉴를 만들어서 교체 작업, 밑에 부분으로 교체.
//        menuInflater.inflate(R.menu.menu_main,menu)
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        // SearchView -> 자동 임포트 주의 -> 제트팩이 아닌 일반 android 로 임포트 하면 안됨.
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("lsy","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@TestTabComActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}