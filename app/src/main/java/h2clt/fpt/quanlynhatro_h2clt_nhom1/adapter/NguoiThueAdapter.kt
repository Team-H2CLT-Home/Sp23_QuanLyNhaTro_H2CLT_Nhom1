package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCapNhatKhachThue
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogChiTietNguoiThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemNguoiThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

const val MA_PHONG_NGUOI_DUNG_KEY="ma phong de lay hoa don"
class NguoiThueViewHolder(
    val binding:LayoutItemNguoiThueBinding

):RecyclerView.ViewHolder(binding.root){
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(nguoiDung: NguoiDung){

        binding.tvTenPhong.text =PhongDao(binding.root.context).getTenPhongById(nguoiDung.ma_phong)
        binding.tvMaNguoiDung.text = nguoiDung.ma_nguoi_dung
        binding.tvSDT.text = "SĐT: "+nguoiDung.sdt_nguoi_dung.toString()
        binding.tvTenNguoiThue.text = "Họ tên: " +nguoiDung.ho_ten_nguoi_dung.toString()
        binding.edTrangThaiO.isChecked = nguoiDung.trang_thai_o==1
//        if(binding.tvTenNguoiThue.text=="Họ tên: "+NguoiDungDao(binding.root.context).getTenNguoiDangOByMaPhong(nguoiDung.ma_phong)){
//            binding.edTrangThaiChuHopDong.isChecked = true
//        }else{
//            binding.edTrangThaiChuHopDong.isChecked = false
//        }

        if(binding.tvMaNguoiDung.text== NguoiDungDao(binding.root.context).getMaNguoiDangOByMaPhong(nguoiDung.ma_phong)){
            binding.edTrangThaiChuHopDong.isChecked = true
        }else{
            binding.edTrangThaiChuHopDong.isChecked = false
        }
//
//        if(binding.tvSDT.text=="SĐT: "+NguoiDungDao(binding.root.context).getSDTNguoiDangOByMaPhong(nguoiDung.ma_phong)){
//            binding.edTrangThaiChuHopDong.isChecked = true
//        }else{
//            binding.edTrangThaiChuHopDong.isChecked = false
//        }
//        if(binding.tvTenNguoiThue.text == NguoiDungDao(binding.root.context).getNguoiDungByTrangThai(nguoiDung.ma_phong,1)){

////  ||binding.tvTenNguoiThue.text=="Họ tên: "+NguoiDungDao(binding.root.context).getTenNguoiDaOByMaPhong(nguoiDung.ma_phong)          Log.d("aaaa", "getNguoiDungByTrangThai: "+NguoiDungDao(binding.root.context).getNguoiDungByTrangThai(nguoiDung.ma_phong,1))
//        Toast.makeText(binding.root.context, NguoiDungDao(binding.root.context).getTenNguoiDaOByMaPhong(nguoiDung.ma_phong), Toast.LENGTH_SHORT).show()
        binding.layoutChuyenChiTietNguoiThue.setOnClickListener {
//            val dialog = DialogChiTietNguoiThueBinding.inflate(LayoutInflater.from(binding.root.context))
//            val builder = AlertDialog.Builder(binding.root.context).create()
            val dialog = DialogChiTietNguoiThueBinding.inflate(LayoutInflater.from(binding.root.context))

            var bottomSheetDialog: BottomSheetDialog
            bottomSheetDialog = BottomSheetDialog(binding.root.context)

            bottomSheetDialog.setContentView(dialog.root)
            dialog.tvChiTietNguoiDungTenPhong.text = "Tên phòng: "+PhongDao(binding.root.context).getTenPhongById(nguoiDung.ma_phong)
            dialog.tvChiTietNguoiThueHoTen.text = nguoiDung.ho_ten_nguoi_dung
            dialog.tvChiTietNguoiThueSDT.text = nguoiDung.sdt_nguoi_dung
            dialog.tvChiTietNguoiThueNgaySinh.text = nguoiDung.nam_sinh
            dialog.tvChiTietNguoiThueCCCD.text = nguoiDung.cccd
            dialog.tvChiTietNguoiThueQueQuan.text = nguoiDung.que_quan
            if(binding.tvTenNguoiThue.text=="Họ tên: "+NguoiDungDao(binding.root.context).getTenNguoiDangOByMaPhong(nguoiDung.ma_phong)){
                dialog.tvLoaiNguoiThue.text = "Là chủ hợp đồng"
            }else{
                dialog.tvLoaiNguoiThue.text = "Là thành viên"
            }
            dialog.btnDongChiTietNguoiThue.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
//            dialog.btnCapNhatChiTietNguoiThue.setOnClickListener {
//                ///cap nhat vao nhe!!!!
////                truyenDuLieu(binding.root.context,nguoiDung.ma_phong)
//                truyenDuLieu(binding.root.context)
//                bottomSheetDialog.dismiss()
//            }

            bottomSheetDialog.show()

        }

    }
    fun truyenDuLieu(context:Context){
        val intent = Intent(context,ActivityCapNhatKhachThue::class.java)
        context.startActivity(intent)
    }
}
class NguoiThueAdapter(
    val listNguoiDung:List<NguoiDung>,val onCLick:KhachThueInterface
):RecyclerView.Adapter<NguoiThueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NguoiThueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemNguoiThueBinding.inflate(inflater,parent,false)
        return NguoiThueViewHolder(binding)
    }

    override fun getItemCount()=listNguoiDung.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NguoiThueViewHolder, position: Int) {
        val user = listNguoiDung[position]
        holder.apply {
            bind(user)
            //updateHopDong(hopDong)
            //fragment.updateDSHopDong(hopDong)
        }
        holder.itemView.setOnClickListener {

            onCLick.OnClickKhachThue(position)
        }
    }
}
