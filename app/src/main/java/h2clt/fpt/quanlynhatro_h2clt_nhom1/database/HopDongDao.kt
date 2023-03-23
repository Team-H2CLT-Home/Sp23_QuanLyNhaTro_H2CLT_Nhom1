package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

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
            put(HopDong.CLM_NGAY_LAP_HOP_DONG,hopDong.ngay_lap_hop_dong)
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
                    ngay_lap_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_LAP_HOP_DONG)),
                    anh_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_ANH_HOP_DONG)),
                    tien_coc = c.getInt(c.getColumnIndex(HopDong.CLM_TIEN_COC)),
                    trang_thai_hop_dong = c.getInt(c.getColumnIndex(HopDong.CLM_TRANG_THAI_HOP_DONG)),
                    ma_phong = c.getString(c.getColumnIndex(HopDong.CLM_MA_PHONG)),
                    ma_nguoi_dung = c.getString(c.getColumnIndex(HopDong.CLM_MA_NGUOI_DUNG))
                )
        }
        return null
    }
    @SuppressLint("Range", "SuspiciousIndentation")
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
                    ngay_lap_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_LAP_HOP_DONG)),
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

    @SuppressLint("Range")
    fun getMaPhongByHopDong():List<String>{
        val list= mutableListOf<String>()
        val sql="select DISTINCT ${HopDong.CLM_MA_PHONG} from ${HopDong.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val ma_phong = c.getString(c.getColumnIndex(HopDong.CLM_MA_PHONG))
                list+=ma_phong
            }while (c.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun getTenPhongByIDHopDong(id:String):String{
        val sql="""
            select ${Phong.CLM_TEN_PHONG} from ${Phong.TB_NAME} JOIN ${HopDong.TB_NAME} ON ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} = ${HopDong.TB_NAME}.${HopDong.CLM_MA_PHONG}
             where ${HopDong.CLM_MA_HOP_DONG}= "$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(Phong.CLM_TEN_PHONG))
        }
        return "null"
    }

    @SuppressLint("Range")
    fun getTenNguoiDungByIDHopDong(id:String):String{
        val sql="""
            select ${NguoiDung.CLM_HO_TEN_NGUOI_DUNG} from ${NguoiDung.TB_NAME} JOIN ${HopDong.TB_NAME} ON ${NguoiDung.TB_NAME}.${NguoiDung.CLM_MA_NGUOI_DUNG} = ${HopDong.TB_NAME}.${HopDong.CLM_MA_NGUOI_DUNG}
             where ${HopDong.CLM_MA_HOP_DONG}= "$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG))
        }
        return "null"
    }

    @SuppressLint("Range")
    fun getAllInHopDongByMaKhu(id: String): List<HopDong> {
        val list= mutableListOf<HopDong>()
        val sql = """
            select * from ${HopDong.TB_NAME}
            join ${Phong.TB_NAME} on ${HopDong.TB_NAME}.${HopDong.CLM_MA_PHONG}=${Phong.TB_NAME}.${Phong.CLM_MA_PHONG}
            join ${KhuTro.TB_NAME} ON ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}=${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}
            where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO} = "$id" 
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val hopDong= HopDong(
                    ma_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_MA_HOP_DONG)),
                    thoi_han = c.getInt(c.getColumnIndex(HopDong.CLM_THOI_HAN)),
                    ngay_o = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_O)),
                    ngay_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_HOP_DONG)),
                    ngay_lap_hop_dong = c.getString(c.getColumnIndex(HopDong.CLM_NGAY_LAP_HOP_DONG)),
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