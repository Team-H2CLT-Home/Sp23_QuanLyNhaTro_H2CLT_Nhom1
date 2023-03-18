package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.DichVuPhong

class DichVuPhongDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertDichVuPhong(dichVuPhong: DichVuPhong):Long{
        val values=ContentValues()
        values.apply {
            put(DichVuPhong.CLM_MA_DICH_VU,dichVuPhong.ma_dich_vu)
            put(DichVuPhong.CLM_TEN_DICH_VU,dichVuPhong.ten_dich_vu)
        }
      return db.insert(DichVuPhong.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInDichVuPhong():List<DichVuPhong>{
        val list= mutableListOf<DichVuPhong>()
        val sql="select * from ${DichVuPhong.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val dichVuPhong=DichVuPhong(
                    ma_dich_vu = c.getString(c.getColumnIndex(DichVuPhong.CLM_MA_DICH_VU)),
                    ten_dich_vu = c.getString(c.getColumnIndex(DichVuPhong.CLM_TEN_DICH_VU))
                )
                list+=dichVuPhong
            }while (c.moveToNext())
        }
        return list
    }
}