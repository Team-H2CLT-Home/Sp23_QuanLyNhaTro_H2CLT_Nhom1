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
            put(LoaiDichVu.CLM_MA_DICH_VU,loaiDichVu.ma_dich_vu)

        }
        return db.insert(LoaiDichVu.TB_NAME,null,values)
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
                    ma_dich_vu = c.getString(c.getColumnIndex(LoaiDichVu.CLM_MA_DICH_VU))

                )
                list+=loaiDichVu

            }while (c.moveToNext())
        }


        return list
    }
}