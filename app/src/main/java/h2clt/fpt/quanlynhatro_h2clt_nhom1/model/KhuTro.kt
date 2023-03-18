package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class KhuTro(
    val ma_khu_tro:String,
    val ten_khu_tro:String,
    val dia_chi:String,
    val so_luong_phong:Int,
    val ten_dang_nhap:String){
    companion object{
        const val TB_NAME="Khu_Tro"
        const val CLM_MA_KHU_TRO="ma_khu_tro"
        const val CLM_TEN_KHU_TRO="ten_khu_tro"
        const val CLM_DIA_CHI="dia_chi"
        const val CLM_SO_LUONG_PHONG="so_luong_phong"
        const val CLM_TEN_DANG_NHAP="ten_dang_nhap"
    }
}
