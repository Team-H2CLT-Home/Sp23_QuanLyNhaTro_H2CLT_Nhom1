package h2clt.fpt.quanlynhatro_h2clt_nhom1.model

data class Admin(
    val sdt:String,
    val ten_dang_nhap:String,
    val ho_ten:String,
    val stk: String,
    val ngay_sinh: String,
    val mat_khau:String
) :java.io.Serializable{
    companion object{
        const val TB_NAME="Admin"
        const val CLM_SDT="sdt_admin"
        const val CLM_TEN_DANG_NHAP="ten_dang_nhap"
        const val CLM_HO_TEN="ho_ten"
        const val CLM_STK="stk"
        const val CLM_NGAY_SINH="ngay_sinh"
        const val CLM_MAT_KHAU="mat_khau"

    }
}