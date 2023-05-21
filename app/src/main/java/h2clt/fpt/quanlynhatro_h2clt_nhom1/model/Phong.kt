package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class Phong(
    val ma_phong:String,
    val ten_phong:String,
    val dien_tich:Int,
    val gia_thue:Long,
    val so_nguoi_o:Int,
    val trang_thai_phong:Int,
    val ma_khu:String
):java.io.Serializable{
    companion object{
        const val TB_NAME="phong"
        const val CLM_MA_PHONG="ma_phong"
        const val CLM_TEN_PHONG="ten_phong"
        const val CLM_DIEN_TICH="dich_tich"
        const val CLM_GIA_THUE="gia_thue"
        const val CLM_SO_NGUOI_O="so_nguoi_o"
        const val CLM_TRANG_THAI_PHONG="trang_thai_phong"
        const val CLM_MA_KHU="ma_khu_tro"

    }
}
