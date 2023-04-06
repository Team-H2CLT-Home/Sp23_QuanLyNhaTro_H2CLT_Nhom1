package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.ThongBao


class ThongBaoDao(context: Context) {
    private val dbHelper= DbHelper(context)
    private val db =dbHelper.writableDatabase

    fun insertThongBao(thongBao: ThongBao):Long{
        val values=ContentValues()
        values.apply {
            put(ThongBao.CLM_MA_THONG_BAO,thongBao.ma_thong_bao)
            put(ThongBao.CLM_TIEU_DE,thongBao.tieu_de)
            put(ThongBao.CLM_NGAY_THONG_BAO,thongBao.ngay_thong_bao)
            put(ThongBao.CLM_NOI_DUNG,thongBao.noi_dung)
            put(ThongBao.CLM_MA_KHU,thongBao.ma_khu)
            put(ThongBao.CLM_LOAI_THONG_BAO, thongBao.loai_thong_bao)
        }
        return db.insert(ThongBao.TB_NAME,null,values)
    }
    @SuppressLint("Range", "SuspiciousIndentation")
    fun getAllInThongBao():List<ThongBao>{
        val list= mutableListOf<ThongBao>()
        val sql="""
            select*from ${ThongBao.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()){
            do {
                val thongBao=ThongBao(
                    ma_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_MA_THONG_BAO)),
                    tieu_de = c.getString(c.getColumnIndex(ThongBao.CLM_TIEU_DE)),
                    ngay_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_NGAY_THONG_BAO)),
                    noi_dung = c.getString(c.getColumnIndex(ThongBao.CLM_NOI_DUNG)),
                    ma_khu = c.getString(c.getColumnIndex(ThongBao.CLM_MA_KHU)),
                    loai_thong_bao = c.getInt(c.getColumnIndex(ThongBao.CLM_LOAI_THONG_BAO))
                )
                list+=thongBao
            }while (c.moveToNext())
        }


        return list
    }
    @SuppressLint("Range")
    fun getAllInThongBaoByID(id:String):ThongBao?{
        val sql="""
            select*from ${ThongBao.TB_NAME} where ${ThongBao.CLM_MA_THONG_BAO}="$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            val thongBao=ThongBao(
                ma_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_MA_THONG_BAO)),
                tieu_de = c.getString(c.getColumnIndex(ThongBao.CLM_TIEU_DE)),
                ngay_thong_bao = c.getString(c.getColumnIndex(ThongBao.CLM_NGAY_THONG_BAO)),
                noi_dung = c.getString(c.getColumnIndex(ThongBao.CLM_NOI_DUNG)),
                ma_khu = c.getString(c.getColumnIndex(ThongBao.CLM_MA_KHU)),
                loai_thong_bao = c.getInt(c.getColumnIndex(ThongBao.CLM_LOAI_THONG_BAO))
            )
        }
        return null
    }

    fun deleteInThongBao(thongBao: ThongBao):Int{
        return db.delete(ThongBao.TB_NAME,"${ThongBao.CLM_MA_THONG_BAO}", arrayOf(thongBao.ma_thong_bao))
    }
}