package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class LoaiDichVu(
    val ma_loai_dich_vu:String,
    val ten_loai_dich_vu:String,
    val gia_dich_vu:Int,
    val trang_thai_loai_dich_vu:Int,
    val ma_dich_vu:String){
    companion object{
        const val TB_NAME="loai_dich_vu"
        const val CLM_MA_LOAI_DICH_VU="ma_loai_dich_vu"
        const val CLM_TEN_LOAI_DICH_VU="ten_loai_dich_vu"
        const val CLM_GIA_DICH_VU="gia_dich_vu"
        const val CLM_TRANG_THAI_LOAI_DICH_VU="trang_thai_loai_dich_vu"
        const val CLM_MA_DICH_VU="ma_dich_vu"
    }
}
