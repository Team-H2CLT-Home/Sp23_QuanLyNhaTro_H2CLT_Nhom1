package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.FragmentCaNhanBinding

class FragmentCaNhan:Fragment() {
    private lateinit var binding: FragmentCaNhanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaNhanBinding.inflate(inflater,container,false)
        return binding.root
    }
}