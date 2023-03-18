package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class DichVuPhong(
    val ma_dich_vu:String,
    val ten_dich_vu:String){
    companion object{
        const val TB_NAME="dich_vu_phong"
        const val CLM_MA_DICH_VU="ma_dich_vu"
        const val CLM_TEN_DICH_VU="ten_dich_vu"
    }
}
