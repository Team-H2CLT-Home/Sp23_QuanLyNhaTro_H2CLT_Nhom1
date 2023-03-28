package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class AdminDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertAdmin(admin: Admin):Long{
        val values=ContentValues()
        values.apply {
            put(Admin.CLM_TEN_DANG_NHAP, admin.ten_dang_nhap)
            put(Admin.CLM_SDT, admin.sdt)
            put(Admin.CLM_HO_TEN, admin.ho_ten)
            put(Admin.CLM_STK, admin.stk)
            put(Admin.CLM_NGAY_SINH, admin.ngay_sinh)
            put(Admin.CLM_HO_TEN, admin.ho_ten)
            put(Admin.CLM_MAT_KHAU, admin.mat_khau)

        }
        return db.insert(Admin.TB_NAME,null,values)
    }
    fun updateAdmin(admin: Admin):Int{
        val values=ContentValues()
        values.apply {
            put(Admin.CLM_TEN_DANG_NHAP, admin.ten_dang_nhap)
            put(Admin.CLM_SDT, admin.sdt)
            put(Admin.CLM_HO_TEN, admin.ho_ten)
            put(Admin.CLM_STK, admin.stk)
            put(Admin.CLM_NGAY_SINH, admin.ngay_sinh)
            put(Admin.CLM_HO_TEN, admin.ho_ten)
            put(Admin.CLM_MAT_KHAU, admin.mat_khau)

        }
        return db.update(Admin.TB_NAME,values,"${Admin.CLM_TEN_DANG_NHAP} =?", arrayOf(admin.ten_dang_nhap))
    }

    @SuppressLint("Range")
    fun getAllInAdmin():List<Admin>{
        val list= mutableListOf<Admin>()
        val sql="select * from ${Admin.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val admin= Admin(
                    ten_dang_nhap =c.getString(c.getColumnIndex(Admin.CLM_TEN_DANG_NHAP)),
                    sdt= c.getString(c.getColumnIndex(Admin.CLM_SDT)),
                    ho_ten = c.getString(c.getColumnIndex(Admin.CLM_HO_TEN)),
                    stk = c.getString(c.getColumnIndex(Admin.CLM_STK)),
                    ngay_sinh = c.getString(c.getColumnIndex(Admin.CLM_NGAY_SINH)),
                    mat_khau = c.getString(c.getColumnIndex(Admin.CLM_MAT_KHAU))
                )
                list+=admin
            }while (c.moveToNext())
        }
        return list
    }
    fun checkLogin(username:String, password:String):Boolean{
        val sql="""
            select *from ${Admin.TB_NAME} where ${Admin.CLM_TEN_DANG_NHAP}= "$username"
            and ${Admin.CLM_MAT_KHAU} = "$password"
        """.trimIndent()
        return try {
            val cursor=db.rawQuery(sql, null)
            cursor.moveToFirst()
        }catch (e:java.lang.Exception ){
            false
        }

    }


    @SuppressLint("Range")
    fun getHoTenAdmin():String{
        val sql="""
           select ${Admin.TB_NAME}.${Admin.CLM_HO_TEN} from ${Admin.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(Admin.CLM_HO_TEN))

        }
        return "null"
    }
    @SuppressLint("Range")
    fun getSDTAdmin():String{
        val sql="""
           select ${Admin.TB_NAME}.${Admin.CLM_SDT} from ${Admin.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(Admin.CLM_SDT))

        }
        return "null"
    }
    @SuppressLint("Range")
    fun getSTKAdmin():String{
        val sql="""
           select ${Admin.TB_NAME}.${Admin.CLM_STK} from ${Admin.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(Admin.CLM_STK))

        }
        return "null"
    }
    @SuppressLint("Range")
    fun getNSAdmin():String{
        val sql="""
           select ${Admin.TB_NAME}.${Admin.CLM_NGAY_SINH} from ${Admin.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(Admin.CLM_NGAY_SINH))

        }
        return "null"
    }
}