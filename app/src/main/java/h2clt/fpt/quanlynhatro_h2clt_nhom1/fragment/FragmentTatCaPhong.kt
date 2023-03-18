package h2clt.fpt.quanlynhatro_h2clt_home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityThemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.FragmentTatCaPhongBinding

class FragmentTatCaPhong : Fragment() {
    private lateinit var binding: FragmentTatCaPhongBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTatCaPhongBinding.inflate(LayoutInflater.from(context))
        binding.imgAddPhong.setOnClickListener {
            val build = AlertDialog.Builder(context)
            val dialog = ActivityThemPhongBinding.inflate(LayoutInflater.from(context))
            build.setView(dialog.root)

            build.show()
        }
        return binding.root
    }
}