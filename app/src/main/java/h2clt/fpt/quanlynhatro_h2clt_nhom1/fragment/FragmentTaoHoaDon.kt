package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding


class FragmentTaoHoaDon : AppCompatActivity() {
    private lateinit var binding: FragmentTaoHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTaoHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}