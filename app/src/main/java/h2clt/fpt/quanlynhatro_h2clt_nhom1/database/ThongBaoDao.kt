package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.ThongBao


class ThongBaoDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertThongBao(thongBao: ThongBao):Long{
        val values=ContentValues()
        values.apply {
            put(ThongBao.CLM_MA_THONG_BAO,thongBao.ma_thong_bao)
            put(ThongBao.CLM_TIEU_DE,thongBao.tieu_de)
            put(ThongBao.CLM_NGAY_THONG_BAO,thongBao.ngay_thong_bao)
            put(ThongBao.CLM_NOI_DUNG,thongBao.noi_dung)
        }
        return db.insert(ThongBao.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInThongBao():List<ThongBao>{
        val list= mutableListOf<ThongBao>()
        val sql="select*from ${ThongBao.TB_NAME}"
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val thongBao=ThongBao(
                    ma_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_MA_THONG_BAO)),
                    tieu_de = c.getString(c.getColumnIndex(ThongBao.CLM_TIEU_DE)),
                    ngay_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_NGAY_THONG_BAO)),
                    noi_dung = c.getString(c.getColumnIndex(ThongBao.CLM_NOI_DUNG))
                )
                list+=thongBao
            }while (c.moveToNext())
        }
        return list
    }
}