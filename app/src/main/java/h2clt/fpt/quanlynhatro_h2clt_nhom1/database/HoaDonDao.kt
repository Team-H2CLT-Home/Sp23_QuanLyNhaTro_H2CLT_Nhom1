package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class HoaDonDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertHoaDon(hoaDon: HoaDon):Long{
        val values=ContentValues()
        values.apply {
            put(HoaDon.CLM_MA_HOA_DON,hoaDon.ma_hoa_don)
            put(HoaDon.CLM_NGAY_TAO_HOA_DON,hoaDon.ngay_tao_hoa_don)
            put(HoaDon.CLM_TRANG_THAI_HOA_DON,hoaDon.trang_thai_hoa_don)
            put(HoaDon.CLM_SO_DIEN,hoaDon.so_dien)
            put(HoaDon.CLM_SO_NUOC,hoaDon.so_nuoc)
            put(HoaDon.CLM_MIEN_GIAM,hoaDon.mien_giam)
            put(HoaDon.CLM_MA_PHONG,hoaDon.ma_phong)


        }
        return db.insert(HoaDon.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInHoaDonByThang(maKhu:String,Thang:String):HoaDon?{
        val sql="""
            select * from ${HoaDon.TB_NAME} 
            join ${Phong.TB_NAME} on ${HoaDon.TB_NAME}.${HoaDon.CLM_MA_PHONG}=${Phong.TB_NAME}.${Phong.CLM_MA_PHONG}
            join ${KhuTro.TB_NAME} on ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}=${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}
            where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO} = "$maKhu" and strftime('%m', ${HoaDon.CLM_NGAY_TAO_HOA_DON}) = "$Thang"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return HoaDon(
                ma_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_MA_HOA_DON)),
                ngay_tao_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_NGAY_TAO_HOA_DON)),
                trang_thai_hoa_don = c.getInt(c.getColumnIndex(HoaDon.CLM_TRANG_THAI_HOA_DON)),
                so_dien = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_DIEN)),
                so_nuoc = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_NUOC)),
                gia_thue = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_THUE)),
                gia_dich_vu = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_DICH_VU)),
                mien_giam = c.getInt(c.getColumnIndex(HoaDon.CLM_MIEN_GIAM)),
                ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG))
            )
        }
        return null
    }

    @SuppressLint("Range")
    fun getAllInHoaDonBytrangThai(trangThai:Int):HoaDon?{
        val sql="""
            select * from ${HoaDon.TB_NAME} where ${HoaDon.CLM_TRANG_THAI_HOA_DON}= "$trangThai=1"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            return HoaDon(
                ma_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_MA_HOA_DON)),
                ngay_tao_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_NGAY_TAO_HOA_DON)),
                trang_thai_hoa_don = c.getInt(c.getColumnIndex(HoaDon.CLM_TRANG_THAI_HOA_DON)),
                so_dien = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_DIEN)),
                so_nuoc = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_NUOC)),
                gia_thue = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_THUE)),
                gia_dich_vu = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_DICH_VU)),
                mien_giam = c.getInt(c.getColumnIndex(HoaDon.CLM_MIEN_GIAM)),
                ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG))
            )
        }
        return null
    }
    @SuppressLint("Range", "SuspiciousIndentation")
    fun getAllInHoaDon():List<HoaDon>{
        val list= mutableListOf<HoaDon>()
        val sql="select * from ${HoaDon.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val hoaDon=HoaDon(
                    ma_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_MA_HOA_DON)),
                    ngay_tao_hoa_don = c.getString(c.getColumnIndex(HoaDon.CLM_NGAY_TAO_HOA_DON)),
                    trang_thai_hoa_don = c.getInt(c.getColumnIndex(HoaDon.CLM_TRANG_THAI_HOA_DON)),
                    so_dien = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_DIEN)),
                    so_nuoc = c.getInt(c.getColumnIndex(HoaDon.CLM_SO_NUOC)),
                    gia_thue = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_THUE)),
                    gia_dich_vu = c.getInt(c.getColumnIndex(HoaDon.CLM_GIA_DICH_VU)),
                    mien_giam = c.getInt(c.getColumnIndex(HoaDon.CLM_MIEN_GIAM)),
                    ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG))
                )
                list+=hoaDon
            }while (c.moveToNext())
        }
        return list
    }
}