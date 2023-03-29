package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.*

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
            put(HoaDon.CLM_GIA_THUE,hoaDon.gia_thue)
            put(HoaDon.CLM_GIA_DICH_VU,hoaDon.gia_dich_vu)
            put(HoaDon.CLM_TONG,hoaDon.tong)


        }
        return db.insert(HoaDon.TB_NAME,null,values)
    }
    fun update(hoaDon: HoaDon): Int{
        val values=ContentValues()
        values.apply {
            put(HoaDon.CLM_TRANG_THAI_HOA_DON,hoaDon.trang_thai_hoa_don)
        }
        return db.update(HoaDon.TB_NAME,values,"${HoaDon.CLM_MA_HOA_DON}=?", arrayOf(hoaDon.ma_hoa_don))
    }

    @SuppressLint("Range")
    fun getAllInHoaDonByThang(maKhu:String,Thang:String,nam:String):HoaDon?{
        val sql="""
            select * from ${HoaDon.TB_NAME} 
            join ${Phong.TB_NAME} on ${HoaDon.TB_NAME}.${HoaDon.CLM_MA_PHONG}=${Phong.TB_NAME}.${Phong.CLM_MA_PHONG}
            join ${KhuTro.TB_NAME} on ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}=${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}
            where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO} = "$maKhu" and strftime('%m', ${HoaDon.CLM_NGAY_TAO_HOA_DON}) = "$Thang" and strftime("%Y",${HoaDon.CLM_NGAY_TAO_HOA_DON})="$nam"
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
                ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG)),
                tong = c.getInt(c.getColumnIndex(HoaDon.CLM_TONG))
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
                    ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG)),
                    tong = c.getInt(c.getColumnIndex(HoaDon.CLM_TONG))
                )
                list+=hoaDon
            }while (c.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun getAllInHoaDonByMaKhu(maKhu :String): List<HoaDon>{
        val list= mutableListOf<HoaDon>()
        val sqlHoaDon="""
            select * from ${HoaDon.TB_NAME} join ${Phong.TB_NAME} on ${HoaDon.TB_NAME}.${HoaDon.CLM_MA_PHONG}
            = ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} where ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}= "$maKhu"
       
        """.trimIndent()
        val c=db.rawQuery(sqlHoaDon,null)
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
                    ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG)),
                    tong = c.getInt(c.getColumnIndex(HoaDon.CLM_TONG))
                )
                list+=hoaDon
            }while (c.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllInHoaDonByMaPhong(maPhong :String): List<HoaDon>{
        val list= mutableListOf<HoaDon>()
        val sqlHoaDon="""
            select * from ${HoaDon.TB_NAME} join ${Phong.TB_NAME} on ${HoaDon.TB_NAME}.${HoaDon.CLM_MA_PHONG}
            = ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} where ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}= "$maPhong"
       
        """.trimIndent()
        val c=db.rawQuery(sqlHoaDon,null)
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
                    ma_phong = c.getString(c.getColumnIndex(HoaDon.CLM_MA_PHONG)),
                    tong = c.getInt(c.getColumnIndex(HoaDon.CLM_TONG))
                )
                list+=hoaDon
            }while (c.moveToNext())
        }
        return list
    }
}