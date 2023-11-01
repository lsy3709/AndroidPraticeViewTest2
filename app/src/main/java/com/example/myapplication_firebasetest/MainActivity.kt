package com.example.myapplication_firebasetest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_firebasetest.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding : ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.testBtn.setOnClickListener {
            val mapUser=mapOf("name" to "lsy","email" to "lsy@naver.com")
//방법1
//            val colRef : CollectionReference = db.collection("users")
//            val docRef : Task<DocumentReference> = colRef.add(mapUser)
//            docRef.addOnSuccessListener{
//                    documentReference->
//                Log.d("lsy","DocumentSnapshotaddedwithID:${documentReference.id}")
//            }
//            docRef.addOnFailureListener{
//                    e->Log.w("lsy","Erroraddingdocument",e)
//            }
            //방법2
//            db.collection("users2").add(mapUser)
//                .addOnSuccessListener {
//                        documentReference->
//                    Log.d("lsy","DocumentSnapshotaddedwithID:${documentReference.id}")
//                }
//                .addOnFailureListener{
//                        e->Log.w("lsy","Erroraddingdocument",e)
//                }
            //방법3
//            val user2 = User("lsy","lsy3@naver.com",20,true)
//            db.collection("users4").add(user2)

            //방법4
//            val user3 = User("lsy","lsy4@naver.com",20,true)
//            db.collection("users4").document("ID01").set(user3)

            //업데이트 , 삭제
            // 업데이트 1개
//            db.collection("users4").document("ID01").update("email","변경@test.com")
            // 업데이트 여러개
//            db.collection("users4").document("ID01").update(
//                mapOf("name" to "변경이름",
//                "email" to " 변경이메일@test.com"))

            //특정 필드 삭제
//            db.collection("users4").document("ID01").update(mapOf("name" to FieldValue.delete()))

            //문서 전체 삭제
//            db.collection("users4").document("ID01").delete()

            //get()  컬렉션 전체 문서 가져오기.
//            db.collection("users4").get().addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("lsy","${document.id} => ${document.data}")
//                }
//
//            }
//                .addOnFailureListener { exception ->
//                    Log.d("lsy","errer",exception)
//                }
        // 단일 문서 가져오기.
//            val docRef = db.collection("users4").document("ID01")
//            docRef.get()
//                .addOnSuccessListener {
//                    document ->
//                    if (document != null) {
//                        Log.d("lsy","datas : ${document.data}")
//                    } else {
//                        Log.d("lsy","No such document")
//                    }
//                }
//                .addOnFailureListener {
//                    exception -> Log.d("lsy", "error",exception)
//                }

            // 가져온 문서를 객체 담아서 사용시 toObject() 이용
            // 자동으로 매핑 해줌.
            // 주의, 매개변수가 없는 생성자가 있어야함
            // 클래스의 프로퍼티가 public 게터 함수를 가져야 함.
//            class User2 {
//                var name: String? =null
//                var email: String? = null
//                var avg: Int = 0
//            }
//            val docRef = db.collection("users4").document("ID01")
//            docRef.get().addOnSuccessListener {
//                documentSnapshot ->
//                val selectUser = documentSnapshot.toObject(User2::class.java)
//                Log.d("lsy","name: ${selectUser?.name}")
//            }

            //조건에 맞는 문서 가져오기
            // 예) whereGreaterThan(), whereIn(), whereArrayContains(), whereLessThan(), whereNotEqualto(), whereNotIn() 등
            db.collection("users4").whereEqualTo("name","lsy").get()
                .addOnSuccessListener {
                    documents ->  for (document in documents) {
                        Log.d("lsy","${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener {
                    exception ->
                    Log.w("lsy", "error",exception)
                }
        }
        }

}