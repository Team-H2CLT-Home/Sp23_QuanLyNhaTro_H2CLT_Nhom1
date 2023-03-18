package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung

class NguoiDungDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertNguoiDung(nguoiDung: NguoiDung):Long{
        val values=ContentValues()
        values.apply {
            put(NguoiDung.CLM_MA_NGUOI_DUNG,nguoiDung.ma_nguoi_dung)
            put(NguoiDung.CLM_HO_TEN_NGUOI_DUNG,nguoiDung.ho_ten_nguoi_dung)
            put(NguoiDung.CLM_CCCD,nguoiDung.cccd)
            put(NguoiDung.CLM_SDT_NGUOI_DUNG,nguoiDung.sdt_nguoi_dung)
            put(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG,nguoiDung.trang_thai_chu_hop_dong)
            put(NguoiDung.CLM_TRANG_THAI_O,nguoiDung.trang_thai_o)
            put(NguoiDung.CLM_MA_PHONG,nguoiDung.ma_phong)
        }
        return db.insert(NguoiDung.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInNguoiDung():List<NguoiDung>{
        val list= mutableListOf<NguoiDung>()
        val sql="select * from ${NguoiDung.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val nguoiDung=NguoiDung(
                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))

                )
                list+=nguoiDung
            }while (c.moveToNext())
        }

        return list
    }
}