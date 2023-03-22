package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong

class HopDongDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertHopDong(hopDong: HopDong):Long{
        val values=ContentValues()
        values.apply {
            put(HopDong.CLM_MA_HOP_DONG,hopDong.ma_hop_dong)
            put(HopDong.CLM_THOI_HAN,hopDong.thoi_han)
            put(HopDong.CLM_NGAY_O,hopDong.ngay_o)
            put(HopDong.CLM_NGAY_HOP_DONG,hopDong.ngay_hop_dong)
            put(HopDong.CLM_ANH_HOP_DONG,hopDong.anh_hop_dong)
            put(HopDong.CLM_TIEN_COC,hopDong.tien_coc)
            put(HopDong.CLM_TRANG_THAI_HOP_DONG,hopDong.trang_thai_hop_dong)
            put(HopDong.CLM_MA_PHONG,hopDong.ma_phong)
            put(HopDong.CLM_MA_NGUOI_DUNG,hopDong.ma_nguoi_dung)
        }
        return db.insert(HopDong.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInHopDongbyID(id:String):HopDong?{
        val sql="""
            select * from ${HopDong.TB_NAME} where ${HopDong.CLM_MA_HOP_DONG}= "$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
                return HopDong(
                    ma_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_MA_HOP_DONG)),
                    thoi_han = c.getInt(c.getColumnIndex(HopDong.CLM_THOI_HAN)),
                    ngay_o = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_O)),
                    ngay_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_HOP_DONG)),
                    anh_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_ANH_HOP_DONG)),
                    tien_coc = c.getInt(c.getColumnIndex(HopDong.CLM_TIEN_COC)),
                    trang_thai_hop_dong = c.getInt(c.getColumnIndex(HopDong.CLM_TRANG_THAI_HOP_DONG)),
                    ma_phong = c.getString(c.getColumnIndex(HopDong.CLM_MA_PHONG)),
                    ma_nguoi_dung = c.getString(c.getColumnIndex(HopDong.CLM_MA_NGUOI_DUNG))
                )
        }
        return null
    }
    @SuppressLint("Range")
    fun getAllInHopDong():List<HopDong>{
        val list= mutableListOf<HopDong>()
        val sql="""
            select * from ${HopDong.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)

            if(c.moveToFirst()){
                do {
                val hopDong= HopDong(
                    ma_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_MA_HOP_DONG)),
                    thoi_han = c.getInt(c.getColumnIndex(HopDong.CLM_THOI_HAN)),
                    ngay_o = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_O)),
                    ngay_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_HOP_DONG)),
                    anh_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_ANH_HOP_DONG)),
                    tien_coc = c.getInt(c.getColumnIndex(HopDong.CLM_TIEN_COC)),
                    trang_thai_hop_dong = c.getInt(c.getColumnIndex(HopDong.CLM_TRANG_THAI_HOP_DONG)),
                    ma_phong = c.getString(c.getColumnIndex(HopDong.CLM_MA_PHONG)),
                    ma_nguoi_dung = c.getString(c.getColumnIndex(HopDong.CLM_MA_NGUOI_DUNG))
                )
                list+=hopDong
                }while (c.moveToNext())
            }


        return list
    }
}