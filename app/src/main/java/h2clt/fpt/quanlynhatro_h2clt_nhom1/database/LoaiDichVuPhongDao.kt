package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu


class LoaiDichVuPhongDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertLoaiDichVuPhong(loaiDichVu: LoaiDichVu):Long{
        val values=ContentValues()
        values.apply {
            put(LoaiDichVu.CLM_MA_LOAI_DICH_VU,loaiDichVu.ma_loai_dich_vu)
            put(LoaiDichVu.CLM_TEN_LOAI_DICH_VU,loaiDichVu.ten_loai_dich_vu)
            put(LoaiDichVu.CLM_GIA_DICH_VU,loaiDichVu.gia_dich_vu)
            put(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU,loaiDichVu.trang_thai_loai_dich_vu)
            put(LoaiDichVu.CLM_MA_PHONG,loaiDichVu.ma_phong)
            put(LoaiDichVu.CLM_SO_MOI, loaiDichVu.so_moi)
            put(LoaiDichVu.CLM_SO_CU, loaiDichVu.so_cu)
            put(LoaiDichVu.CLM_MA_KHU_TRO, loaiDichVu.ma_khu_tro)

        }
        return db.insert(LoaiDichVu.TB_NAME,null,values)
    }
    fun xoaLoaiDichVuByTenVaMaPhong(loiDichVu: LoaiDichVu):Int{
        return  db.delete(LoaiDichVu.TB_NAME,
            "${LoaiDichVu.CLM_TEN_LOAI_DICH_VU}=? AND ${LoaiDichVu.CLM_MA_PHONG}=?",
            arrayOf(loiDichVu.ten_loai_dich_vu, loiDichVu.ma_phong))
    }
    fun updateLoaiDichVu(id:String, soCu:Int, soMoi:Int):Int{
        val values=ContentValues()
        values.apply {
            put(LoaiDichVu.CLM_SO_CU,soCu )
            put(LoaiDichVu.CLM_SO_MOI,soMoi)
        }
        return db.update(LoaiDichVu.TB_NAME, values, "${LoaiDichVu.CLM_MA_LOAI_DICH_VU}=?", arrayOf(id))
    }
    fun upDateLoaiDichVuByMaKhuVaTen(loaiDichVu: LoaiDichVu, ten:String):Int{
        val values=ContentValues()
        values.apply {
            put(LoaiDichVu.CLM_TEN_LOAI_DICH_VU,loaiDichVu.ten_loai_dich_vu)
            put(LoaiDichVu.CLM_GIA_DICH_VU,loaiDichVu.gia_dich_vu)
            put(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU,loaiDichVu.trang_thai_loai_dich_vu)
        }

        return db.update(LoaiDichVu.TB_NAME, values,
            "${LoaiDichVu.CLM_TEN_LOAI_DICH_VU}=? and ${LoaiDichVu.CLM_MA_KHU_TRO}=?",
            arrayOf(ten, loaiDichVu.ma_khu_tro))
    }
    fun xoaLoaiDichVuByMaKhuVaTen(loiDichVu: LoaiDichVu):Int{
        return  db.delete(LoaiDichVu.TB_NAME,
            "${LoaiDichVu.CLM_TEN_LOAI_DICH_VU}=? AND ${LoaiDichVu.CLM_MA_KHU_TRO}=?",
            arrayOf(loiDichVu.ten_loai_dich_vu, loiDichVu.ma_khu_tro))
    }
    fun xoaLoaiDichVuByMaPhong(maPhong: String):Int {
        return db.delete(
            LoaiDichVu.TB_NAME,
            "${LoaiDichVu.CLM_MA_PHONG}=?",
            arrayOf(maPhong)
        )
    }

    @SuppressLint("Range")
    fun getAllInLoaiDichVu():List<LoaiDichVu>{
        val list= mutableListOf<LoaiDichVu>()
        val sql="select * from ${LoaiDichVu.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val loaiDichVu=LoaiDichVu(
                    ma_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_LOAI_DICH_VU)),
                    ten_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_TEN_LOAI_DICH_VU)),
                    gia_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_GIA_DICH_VU)),
                    trang_thai_loai_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU)),
                    ma_phong = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_PHONG)),
                    ma_khu_tro = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_KHU_TRO)),
                    so_cu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_CU)),
                    so_moi = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_MOI))
                )
                list+=loaiDichVu

            }while (c.moveToNext())
        }


        return list
    }
    @SuppressLint("Range")
    fun getAllInLoaiDichVuByPhong(maPhong:String):List<LoaiDichVu>{
        val list= mutableListOf<LoaiDichVu>()
        val sql="""
            select * from ${LoaiDichVu.TB_NAME} where ${LoaiDichVu.CLM_MA_PHONG}= "$maPhong"
            
        """
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val loaiDichVu=LoaiDichVu(
                    ma_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_LOAI_DICH_VU)),
                    ten_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_TEN_LOAI_DICH_VU)),
                    gia_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_GIA_DICH_VU)),
                    trang_thai_loai_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU)),
                    ma_phong = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_PHONG)),
                    ma_khu_tro = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_KHU_TRO)),
                    so_cu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_CU)),
                    so_moi = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_MOI))
                )
                list+=loaiDichVu

            }while (c.moveToNext())
        }


        return list
    }
    @SuppressLint("Range")
    fun getAllInLoaiDichVuByKhuTro(maKhu:String):List<LoaiDichVu>{
        val list= mutableListOf<LoaiDichVu>()
        val sql="""
            select DISTINCT * from ${LoaiDichVu.TB_NAME} where ${LoaiDichVu.CLM_MA_KHU_TRO}= "$maKhu"
            GROUP BY ${LoaiDichVu.CLM_TEN_LOAI_DICH_VU}
            
        """
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val loaiDichVu=LoaiDichVu(
                    ma_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_LOAI_DICH_VU)),
                    ten_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_TEN_LOAI_DICH_VU)),
                    gia_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_GIA_DICH_VU)),
                    trang_thai_loai_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU)),
                    ma_phong = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_PHONG)),
                    ma_khu_tro = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_KHU_TRO)),
                    so_cu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_CU)),
                    so_moi = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_MOI))
                )
                list+=loaiDichVu

            }while (c.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getLoaiDichVuById(id:String):LoaiDichVu?{
        val sql="""
            select * from ${LoaiDichVu.TB_NAME} where ${LoaiDichVu.CLM_MA_LOAI_DICH_VU}= "$id"
            
        """
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()) {
            return LoaiDichVu(
                ma_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_LOAI_DICH_VU)),
                ten_loai_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_TEN_LOAI_DICH_VU)),
                gia_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_GIA_DICH_VU)),
                trang_thai_loai_dich_vu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU)),
                ma_phong = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_PHONG)),
                ma_khu_tro = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_KHU_TRO)),
                so_cu = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_CU)),
                so_moi = c.getInt(c.getColumnIndex(LoaiDichVu.CLM_SO_MOI))
            )
        }
        return null
    }

}