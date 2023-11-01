
package com.example.myapplication_firebasetest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.myapplication_firebasetest.databinding.ActivityImageStorageTestBinding
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class ImageStorageTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageStorageTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageStorageTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //파일올리기
        //방법1
        // 스토리지 객체 얻기
        val storage: FirebaseStorage = Firebase.storage
        // 파일 경로 : images/a.jpg
        val storageRef : StorageReference = storage.reference
        val imgRef: StorageReference = storageRef.child("images/a.jpg")

        //올릴 때 3가지 경우 1) putBytes(), putFile(), putStream()

        // 1) putBytes(), 바이트 배열을 스토리지에 저장시 사용, 뷰의 화면을 바이트로 읽어서 저장하는 경우
        // 뷰의 출력크기와 같은 비트맵 객체를 만들고 이 객체에 뷰의 화면을 그려야 함.
        // 화면을 비트맵 객체에 그리기
        // 뷰의 크기와 같은 빈 Bitmap 객체를 만들고 여기에 Canvas 객체로 뷰의 내용을 그린 후 반환.
        fun getBitmapFromView(view: View) : Bitmap? {
            var bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            var canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        // 이미지를 바이트 값으로 읽기
        // addPicImageView : 읽어올 이미지 뷰
        val bitmap = getBitmapFromView(binding.addPicImageView)
        val baos = ByteArrayOutputStream()
//        bitmap?.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()

        // 이값을 putBytes() 함수로 스토리즈에 저장하는 코드
        var uploadTask = imgRef.putBytes(data)
        uploadTask.addOnFailureListener() {
            Log.d("lsy","upload Fail...")
        }
            .addOnCompleteListener{
                Log.d("lsy","upload Success...")
            }

        // 성공 이후 URL 주소 가져오기.
        val urlTask = uploadTask.continueWithTask {
            task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
        } // 성공시 , 주소 반환
            imgRef.downloadUrl
        }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result // 스토리지에 저장된 파일의 URL
                    Log.d("lsy","upload url : $downloadUri...")
                } else {
                    Log.d("lsy","upload url fail")
                }
            }



        // 2) putFile(),
        // 매개변수로 파일의 경로를 지정하여 업로드
//        val file = Uri.fromFile(File(filePath))
//        val uploadTask2 = imgRef.putFile(file)

        // 3) putStream()
        // 파일을 읽는 Stream 객체를 지정하여 이 Stream 객체에서 전달되는 데이터를 업로드
        // 데이터를 읽는 FileInputStream 객체를 putStream()함수의 매개변수로 지정하여
        // 업로드하는 예

//        val stream = FileInputStream(File(filePath))
//        val uploadTask3 = imgRef.putStream(stream)


        // 업로드 파일 삭제
        val imgRef3 : StorageReference = storageRef.child("image/a.jpg")
        imgRef.delete()
            .addOnFailureListener {
                Log.d("lsy","upload delete fail")
            }
            .addOnCompleteListener {
                Log.d("lsy","upload delete success")
            }


        //파일 내려받기
        //1) getBytes()
        // OOM 예외가 발생할수 있음, 최대 바이트수를 지정해야함.
        // 내려받은 파일의 바이트 값 가져오기
        val storageRef2 : StorageReference = storage.reference
        val imgRef4: StorageReference = storageRef2.child("images/a.jpg")
        val ONE_MEGABYTE: Long = 1024*1024 // 내려 받는 최대 바이트 수 지정
        imgRef4.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it,0,it.size)
            // 다운로드 이미지 뷰 보여주기.
//            binding.downloadImageView.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Log.d("lsy","upload  download Fail")
        }

        // 2) getFile
        // 로컬 저장소에 저장시 유용
        val imgRef5 : StorageReference = storageRef.child("images/a.jpg")
        val localFile = File.createTempFile("images","jpg")
        imgRef5.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//            binding.downloadImageView.setImageBitmap(bitmap)
        }.addOnFailureListener {
            Log.d("lsy","upload  download Fail")
        }

        // downloadUrl() 함수로 URL 얻기
        // URL 받아와서 Glide로 보여주기.
        val imgRef6 : StorageReference = storageRef.child("images/a.jpg")
        imgRef6.downloadUrl.addOnSuccessListener {
            Log.d("lsy", "download uri : $it")
        }.addOnFailureListener {
            Log.d("lsy","upload  download Fail")
        }

        // firebase-ui-storage 라이브러리 이용
        // url 얻고 -> glide 로 이미지 내려받기 한번에
        //
        // 추가
        // plugins { id 'kotlin-kapt' }
        // 추가2      implementation 'com.firebaseui:firebase-ui-storage:8.0.0'


// 이미지 내려받기
        val imgRef7 : StorageReference = storageRef.child("images/a.jpg")
        Glide.with(this)
            .load(imgRef7)
//            .into(binding.downloadImageView)

    }
    // @GlideModule 애너테이션을 적용해서 앱에서 어디에 작성하든지 자동으로 적용.
    @GlideModule
    class MyAppGlideModule: AppGlideModule() {
        override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
                registry.append(
                    StorageReference::class.java, InputStream::class.java,
                FirebaseImageLoader.Factory()
                )
        }
    }


}
