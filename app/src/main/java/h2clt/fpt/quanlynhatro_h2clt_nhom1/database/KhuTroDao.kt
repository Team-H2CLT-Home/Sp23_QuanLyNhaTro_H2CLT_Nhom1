package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class KhuTroDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertKhuTro(khuTro: KhuTro):Long{
        val values=ContentValues()
        values.apply {
            put(KhuTro.CLM_MA_KHU_TRO,khuTro.ma_khu_tro)
            put(KhuTro.CLM_TEN_KHU_TRO,khuTro.ten_khu_tro)
            put(KhuTro.CLM_DIA_CHI,khuTro.dia_chi)
            put(KhuTro.CLM_SO_LUONG_PHONG,khuTro.so_luong_phong)
            put(KhuTro.CLM_TEN_DANG_NHAP,khuTro.ten_dang_nhap)
        }
        return db.insert(KhuTro.TB_NAME,null,values)
    }

    fun updateInKhuTro(khuTro: KhuTro):Int{
        val values=ContentValues()
        values.apply {
            put(KhuTro.CLM_MA_KHU_TRO,khuTro.ma_khu_tro)
            put(KhuTro.CLM_TEN_KHU_TRO,khuTro.ten_khu_tro)
            put(KhuTro.CLM_DIA_CHI,khuTro.dia_chi)
            put(KhuTro.CLM_SO_LUONG_PHONG,khuTro.so_luong_phong)
            put(KhuTro.CLM_TEN_DANG_NHAP,khuTro.ten_dang_nhap)
        }
        return db.update(KhuTro.TB_NAME,values,"${KhuTro.CLM_MA_KHU_TRO}", arrayOf(khuTro.ma_khu_tro))
    }

    fun deleteKhuTro(khuTro: KhuTro):Int{
        return db.delete(KhuTro.TB_NAME,"${KhuTro.CLM_MA_KHU_TRO} =?", arrayOf<String>(khuTro.ma_khu_tro) )
    }

    @SuppressLint("Range")
    fun getAllInKhuTroByAdmin(s:String):List<KhuTro>{
        val list= mutableListOf<KhuTro>()
        val sql="""
            select * from ${KhuTro.TB_NAME} where ${KhuTro.CLM_TEN_DANG_NHAP}= "$s"
        """
        val c=db.rawQuery(sql, null)
        if(c.moveToFirst()){
            do {
                val khuTro=KhuTro(
                    ma_khu_tro = c.getString(c.getColumnIndex(KhuTro.CLM_MA_KHU_TRO)),
                    ten_khu_tro = c.getString(c.getColumnIndex(KhuTro.CLM_TEN_KHU_TRO)),
                    dia_chi = c.getString(c.getColumnIndex(KhuTro.CLM_DIA_CHI)),
                    so_luong_phong = c.getInt(c.getColumnIndex(KhuTro.CLM_SO_LUONG_PHONG)),
                    ten_dang_nhap = c.getString(c.getColumnIndex(KhuTro.CLM_TEN_DANG_NHAP))
                )
                list+=khuTro
            }while (c.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun getTenKhuTro():String{
        val sql="""
            select ${KhuTro.CLM_TEN_KHU_TRO} from ${KhuTro.TB_NAME} 
        """.trimIndent()
        val c=db.rawQuery(sql,null)

        if(c.moveToFirst()) {
            return c.getString(c.getColumnIndex(KhuTro.CLM_TEN_KHU_TRO))
        }

        return "null"
    }
}