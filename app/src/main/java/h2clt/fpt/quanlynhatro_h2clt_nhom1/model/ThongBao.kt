package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class ThongBao(
    val ma_thong_bao:String,
    val tieu_de:String,
    val ma_khu:String,
    val ngay_thong_bao:String,
    val loai_thong_bao:Int,
    val noi_dung:String){
    companion object{
        const val TB_NAME="thong_bao"
        const val CLM_MA_THONG_BAO="ma_thong_bao"
        const val CLM_TIEU_DE="tieu_de"
        const val CLM_NGAY_THONG_BAO="ngay_thong_bao"
        const val CLM_NOI_DUNG="noi_dung"
        const val CLM_MA_KHU="ma_khu_tro"
        const val CLM_LOAI_THONG_BAO="loai_thong_bao"
    }
}
