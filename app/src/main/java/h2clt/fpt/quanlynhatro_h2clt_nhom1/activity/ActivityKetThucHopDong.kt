package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityKetThucHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogGiaHanHdBinding


class ActivityKetThucHopDong : AppCompatActivity() {
    private lateinit var binding: ActivityKetThucHopDongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKetThucHopDongBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.sapHetHopDong.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog  = DialogGiaHanHdBinding.inflate(LayoutInflater.from(this))
            builder.setView(dialog.root)

            builder.show()
        }
    }
}