package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityThemPhong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTatCaPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong


class FragmentTatCaPhong : Fragment() {
    private lateinit var binding: FragmentTatCaPhongBinding
    var listPhong= listOf<Phong>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTatCaPhongBinding.inflate(LayoutInflater.from(context))
        binding.imgAddPhong.setOnClickListener {
            val intent= Intent(activity, ActivityThemPhong::class.java)
            startActivity(intent)

        }
        return binding.root
    }
}