package h2clt.fpt.quanlynhatro_h2clt_home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class ManHinhChao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_man_hinh_chao)
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this,DangNhap::class.java)
            startActivity(intent)
        },2000)

    }
}