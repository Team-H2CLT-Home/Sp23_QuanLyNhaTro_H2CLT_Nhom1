package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
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
    fun xoaPhongById(maPhong:String):Int {
        return db.delete(Phong.TB_NAME, "${Phong.CLM_MA_PHONG}=?", arrayOf(maPhong))
    }
    fun updatePhong(phong:Phong):Int{
        val values=ContentValues()
        with(values){
            put(Phong.CLM_TEN_PHONG,phong.ten_phong)
            put(Phong.CLM_DIEN_TICH,phong.dien_tich)
            put(Phong.CLM_GIA_THUE,phong.gia_thue)
            put(Phong.CLM_SO_NGUOI_O,phong.so_nguoi_o)
        }
        return db.update(Phong.TB_NAME, values, "${Phong.CLM_MA_PHONG}=?", arrayOf(phong.ma_phong))
    }

    fun updateTrangThaiPhongThanhDangO(maPhong:String):Int{
        val values=ContentValues()
        values.apply {
            put(Phong.CLM_TRANG_THAI_PHONG,1)
        }
        return db.update(Phong.TB_NAME,values,  "${Phong.CLM_MA_PHONG}=?", arrayOf(maPhong))
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
    fun getAllInPhongByTenKhuTro(tenKhuTro:String):List<Phong>{
        val list= mutableListOf<Phong>()
        val sql="""
           select * from ${Phong.TB_NAME} JOIN ${KhuTro.TB_NAME} ON ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO} = ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}
            WHERE ${KhuTro.TB_NAME}.${KhuTro.CLM_TEN_KHU_TRO} = "$tenKhuTro"
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
    fun getPhongChuaCoHopDong(maKhu:String): List<Phong> {
        val list= mutableListOf<Phong>()
        val sql="""
            SELECT DISTINCT *
            FROM Phong
            WHERE ma_phong NOT IN (SELECT ma_phong FROM Hop_Dong WHERE ${HopDong.CLM_HIEU_LUC_HOP_DONG} = 1) and ${Phong.CLM_MA_KHU} = "$maKhu" 
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
                    ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU))

                )
                list+=phong
            }while (c.moveToNext())
        }

        return list
    }

    @SuppressLint("Range")
    fun getAllInPhongDaOMaKhu(maKhu:String):List<Phong>{
        val list= mutableListOf<Phong>()
        val sql="""
           select * from ${Phong.TB_NAME} join ${Phong.TB_NAME}
           ON ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} = ${NguoiDung.TB_NAME}.${NguoiDung.CLM_MA_PHONG}
            where ${Phong.CLM_MA_KHU} = "$maKhu" AND ${NguoiDung.TB_NAME}.${NguoiDung.CLM_TRANG_THAI_O} =1
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

    fun updateTrangThaiPhongThanhDaO(maPhong:String):Int{
        val values=ContentValues()
        values.apply {
            put(Phong.CLM_TRANG_THAI_PHONG,0)
        }
        return db.update(Phong.TB_NAME,values,  "${Phong.CLM_MA_PHONG}=?", arrayOf(maPhong))
    }

    @SuppressLint("Range")
    fun demSoPhong(maKhu:String):Int{
        val list = mutableListOf<Int>()
        val sql="""
            select COUNT(${Phong.CLM_MA_PHONG}) as SoPhong from ${Phong.TB_NAME} where ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}= "$maKhu"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToNext()){
            c.getInt(c.getColumnIndex("SoPhong"))
        }
        return c.getInt(c.getColumnIndex("SoPhong"))
    }
}