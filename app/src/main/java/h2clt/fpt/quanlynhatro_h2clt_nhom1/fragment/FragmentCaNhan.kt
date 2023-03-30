package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentCaNhanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin


class FragmentCaNhan:Fragment() {
    private lateinit var binding: FragmentCaNhanBinding
    private var username = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaNhanBinding.inflate(inflater,container,false)
//        val tenDao = AdminDao(binding.root.context).getHoTenAdmin()
//        val sdtDao = AdminDao(binding.root.context).getSDTAdmin()
//        binding.tvTenChuNha.text = tenDao
//        binding.tvSDT.text = "SĐT: "+sdtDao
        val pref  = binding.root.context.getSharedPreferences(
            THONG_TIN_DANG_NHAP,
            AppCompatActivity.MODE_PRIVATE
        )
        username =pref.getString(USERNAME_KEY,"")!!
        val admin  = AdminDao(binding.root.context).getAdmin(username)
        binding.tvTenChuNha.setText(""+admin?.ho_ten)
        binding.tvSDT.setText(""+admin?.sdt)
        binding.tvDangXuat.setOnClickListener {
            val bundle = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            bundle.setTitle("Thông Báo")
            bundle.setMessage("Bạn có chắc chắn muốn đăng xuất không?")
            bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(activity, ActivityDangNhap::class.java)
                activity?.finish()
                startActivity(intent)
            })
            bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            bundle.show()
        }
        binding.tvThongTinChuNha.setOnClickListener {
            val intent = Intent(activity, ActivityThongTinChuNha::class.java)
            intent.putExtra("admin",admin)
            startActivity(intent)
        }
        binding.tvCapNhat.setOnClickListener {
            val intent = Intent(activity, ActivityCapNhatThongTinChuNha::class.java)
            intent.putExtra("admin",admin)
            startActivity(intent)
        }
        return binding.root
    }
}