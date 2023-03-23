package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class PhongDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertPhong(phong: Phong):Long{
        val values=ContentValues()
        values.apply {
            put(Phong.CLM_MA_PHONG,phong.ma_phong)
            put(Phong.CLM_TEN_PHONG,phong.ten_phong)
            put(Phong.CLM_DIEN_TICH,phong.dien_tich)
            put(Phong.CLM_GIA_THUE,phong.gia_thue)
            put(Phong.CLM_SO_NGUOI_O,phong.so_nguoi_o)
            put(Phong.CLM_TRANG_THAI_PHONG,phong.trang_thai_phong)
            put(Phong.CLM_MA_KHU,phong.ma_khu)
        }
        return db.insert(Phong.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInPhongByMaKhu(maKhu:String):List<Phong>{
        val list= mutableListOf<Phong>()
        val sql="""
           select * from ${Phong.TB_NAME} where ${Phong.CLM_MA_KHU} = "$maKhu" 
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()){
            do {
                val phong= Phong(
                    ma_phong = c.getString(c.getColumnIndex(Phong.CLM_MA_PHONG)),
                    ten_phong = c.getString(c.getColumnIndex(Phong.CLM_TEN_PHONG)),
                    dien_tich = c.getInt(c.getColumnIndex(Phong.CLM_DIEN_TICH)),
                    gia_thue = c.getLong(c.getColumnIndex(Phong.CLM_GIA_THUE)),
                    so_nguoi_o = c.getInt(c.getColumnIndex(Phong.CLM_SO_NGUOI_O)),
                    trang_thai_phong = c.getInt(c.getColumnIndex(Phong.CLM_TRANG_THAI_PHONG)),
                    ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU)),
                )
                list+=phong
            }while (c.moveToNext())
        }

        return list
    }
    @SuppressLint("Range")
    fun getPhongById(id:String):Phong?{
        val sql="""
            select * from ${Phong.TB_NAME} where ${Phong.CLM_MA_PHONG}= "$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()) {
            return Phong(
                ma_phong = c.getString(c.getColumnIndex(Phong.CLM_MA_PHONG)),
                ten_phong = c.getString(c.getColumnIndex(Phong.CLM_TEN_PHONG)),
                dien_tich = c.getInt(c.getColumnIndex(Phong.CLM_DIEN_TICH)),
                gia_thue = c.getLong(c.getColumnIndex(Phong.CLM_GIA_THUE)),
                so_nguoi_o = c.getInt(c.getColumnIndex(Phong.CLM_SO_NGUOI_O)),
                trang_thai_phong = c.getInt(c.getColumnIndex(Phong.CLM_TRANG_THAI_PHONG)),
                ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU))
            )
        }

        return null
    }
    @SuppressLint("Range")
    fun getTenPhongById(id:String):String{
        val sql="""
            select ${Phong.CLM_TEN_PHONG} from ${Phong.TB_NAME} where ${Phong.CLM_MA_PHONG}= "$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()) {
            return c.getString(c.getColumnIndex(Phong.CLM_TEN_PHONG))
        }

        return "null"
    }

    @SuppressLint("Range")
    fun getPhongChuaCoHopDong(): List<Phong> {
        val list= mutableListOf<Phong>()
        val sql="""
            SELECT DISTINCT *
            FROM Phong
            WHERE ma_phong NOT IN (SELECT ma_phong FROM Hop_Dong);
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()){
            do {
                val phong= Phong(
                    ma_phong = c.getString(c.getColumnIndex(Phong.CLM_MA_PHONG)),
                    ten_phong = c.getString(c.getColumnIndex(Phong.CLM_TEN_PHONG)),
                    dien_tich = c.getInt(c.getColumnIndex(Phong.CLM_DIEN_TICH)),
                    gia_thue = c.getLong(c.getColumnIndex(Phong.CLM_GIA_THUE)),
                    so_nguoi_o = c.getInt(c.getColumnIndex(Phong.CLM_SO_NGUOI_O)),
                    trang_thai_phong = c.getInt(c.getColumnIndex(Phong.CLM_TRANG_THAI_PHONG)),
                    ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU)),
                    ma_dich_vu = c.getString(c.getColumnIndex(Phong.CLM_MA_DICH_VU))
                )
                list+=phong
            }while (c.moveToNext())
        }

        return list
    }
}