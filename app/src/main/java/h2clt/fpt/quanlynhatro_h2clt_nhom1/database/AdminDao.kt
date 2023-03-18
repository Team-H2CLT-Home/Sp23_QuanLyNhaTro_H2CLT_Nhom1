package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin

class AdminDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertAdmin(admin: Admin):Long{
        val values=ContentValues()
        values.apply {
            put(Admin.CLM_TEN_DANG_NHAP, admin.ten_dang_nhap)
            put(Admin.CLM_SDT, admin.sdt)
            put(Admin.CLM_HO_TEN, admin.ho_ten)
            put(Admin.CLM_MAT_KHAU, admin.mat_khau)

        }
        return db.insert(Admin.TB_NAME,null,values)
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
                    mat_khau = c.getString(c.getColumnIndex(Admin.CLM_MAT_KHAU))
                )
                list+=admin
            }while (c.moveToNext())
        }
        return list
    }


}