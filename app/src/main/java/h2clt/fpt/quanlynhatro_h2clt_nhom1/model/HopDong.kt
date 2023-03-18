package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class HopDong(
    val ma_hop_dong:String,
    val thoi_han:Int,
    val ngay_o:String,
    val ngay_hop_dong:String,
    val anh_hop_dong:String,
    val tien_coc:Int,
    val trang_thai_hop_dong:Int,
    val ma_phong:String,
    val ma_nguoi_dung:String){
    companion object{
        const val TB_NAME="hop_dong"
        const val CLM_MA_HOP_DONG="ma_hop_dong"
        const val CLM_THOI_HAN="thoi_han"
        const val CLM_NGAY_O="ngay_o"
        const val CLM_NGAY_HOP_DONG="ngay_hop_dong"
        const val CLM_ANH_HOP_DONG="anh_hop_dong"
        const val CLM_TIEN_COC="tien_coc"
        const val CLM_TRANG_THAI_HOP_DONG="trang_thai_hop_dong"
        const val CLM_MA_PHONG="ma_phong"
        const val CLM_MA_NGUOI_DUNG="ma_nguoi_dung"
    }
}
