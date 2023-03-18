package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class Admin(
    val sdt:String,
    val ten_dang_nhap:String,
    val ho_ten:String,
    val mat_khau:String
) {
    companion object{
        const val TB_NAME="Admin"
        const val CLM_SDT="sdt_admin"
        const val CLM_TEN_DANG_NHAP="ten_dang_nhap"
        const val CLM_HO_TEN="ho_ten"
        const val CLM_MAT_KHAU="mat_khau"

    }
}