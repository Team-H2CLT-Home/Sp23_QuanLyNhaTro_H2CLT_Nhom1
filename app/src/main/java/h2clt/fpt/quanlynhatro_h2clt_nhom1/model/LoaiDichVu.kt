package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class LoaiDichVu(
    val ma_loai_dich_vu:String,
    val ten_loai_dich_vu:String,
    val gia_dich_vu:Int,
    val trang_thai_loai_dich_vu:Int,
    val ma_khu_tro:String,
    val ma_phong:String){
    companion object{
        const val TB_NAME="loai_dich_vu"
        const val CLM_MA_LOAI_DICH_VU="ma_loai_dich_vu"
        const val CLM_TEN_LOAI_DICH_VU="ten_loai_dich_vu"
        const val CLM_GIA_DICH_VU="gia_dich_vu"
        const val CLM_TRANG_THAI_LOAI_DICH_VU="trang_thai_loai_dich_vu"
        const val CLM_MA_PHONG="ma_phong"
        const val CLM_MA_KHU_TRO="ma_khu_tro"
    }
}
