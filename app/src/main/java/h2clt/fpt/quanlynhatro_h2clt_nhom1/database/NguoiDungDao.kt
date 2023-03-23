package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class NguoiDungDao(context: Context) {
    val dbHelper= DbHelper(context)
    val db=dbHelper.writableDatabase

    fun insertNguoiDung(nguoiDung: NguoiDung):Long{
        val values=ContentValues()
        values.apply {
            put(NguoiDung.CLM_MA_NGUOI_DUNG,nguoiDung.ma_nguoi_dung)
            put(NguoiDung.CLM_HO_TEN_NGUOI_DUNG,nguoiDung.ho_ten_nguoi_dung)
            put(NguoiDung.CLM_CCCD,nguoiDung.cccd)
            put(NguoiDung.CLM_NAM_SINH,nguoiDung.nam_sinh)
            put(NguoiDung.CLM_SDT_NGUOI_DUNG,nguoiDung.sdt_nguoi_dung)
            put(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG,nguoiDung.trang_thai_chu_hop_dong)
            put(NguoiDung.CLM_TRANG_THAI_O,nguoiDung.trang_thai_o)
            put(NguoiDung.CLM_MA_PHONG,nguoiDung.ma_phong)
        }
        return db.insert(NguoiDung.TB_NAME,null,values)
    }

    @SuppressLint("Range")
    fun getAllInNguoiDung():List<NguoiDung>{
      val list= mutableListOf<NguoiDung>()
        val sql="""
            select * from ${NguoiDung.TB_NAME}
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val nguoiDung=NguoiDung(
                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
                    nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH)),
                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
                    que_quan = c.getString(c.getColumnIndex(NguoiDung.CLM_QUE_QUAN_NGUOI_DUNG)),
                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))
                )
                list+=nguoiDung
            }while (c.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun getAllInNguoiDungByID(id:String):NguoiDung?{
        val sql="""
            select * from ${NguoiDung.TB_NAME} where ${NguoiDung.CLM_MA_NGUOI_DUNG} ="$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
                return NguoiDung(
                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
                    nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH)),
                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
                    que_quan = c.getString(c.getColumnIndex(NguoiDung.CLM_QUE_QUAN_NGUOI_DUNG)),
                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))
                )
        }
        return null
    }
//    @SuppressLint("Range")
//    fun getAllInNguoiDungByMaKhu(id: String):List<NguoiDung>{
//        val list= mutableListOf<NguoiDung>()
//        val sql = """
//            select * from ${NguoiDung.TB_NAME} join ${Phong.TB_NAME} on ${NguoiDung.TB_NAME}.${NguoiDung.CLM_MA_PHONG}
//             = ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} join ${KhuTro.TB_NAME} on ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}
//             = ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}
//        where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}="$id"
//        """.trimIndent()
//        val c=db.rawQuery(sql,null)
//        if(c.moveToFirst()){
//            do {
//                val nguoiDung=NguoiDung(
//                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
//                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
//                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
//                    nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH)),
//                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
//                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
//                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
//                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))
//                )
//                list+=nguoiDung
//            }while (c.moveToNext())
//        }
//        return list
//    }

//    @SuppressLint("Range")
//    fun getAllInNguoiDungByMaKhu(id:String):NguoiDung? {
//        val sql = """
//            select * from ${NguoiDung.TB_NAME} join ${Phong.TB_NAME} on ${NguoiDung.TB_NAME}.${NguoiDung.CLM_MA_PHONG}
//             = ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} join ${KhuTro.TB_NAME} on ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}
//             = ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO} where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}="${KhuTro.CLM_MA_KHU_TRO}"
//        """.trimIndent()
//        val c = db.rawQuery(sql, null)
//        if (c.moveToFirst()) {
//            return NguoiDung(
//                ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
//                ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
//                cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
//                nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH)),
//                sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
//                trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
//                trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
//                ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))
//            )
//        }
//        return null
//    }

    @SuppressLint("Range")
    fun getNguoiDungByMaPhong(maPhong:String):List<NguoiDung>{
        val list= mutableListOf<NguoiDung>()
        val sql="""
            select * from ${NguoiDung.TB_NAME} WHERE ${NguoiDung.CLM_MA_PHONG} = "${maPhong}"
        """.trimIndent()

        val c=db.rawQuery(sql, null)
        if(c.moveToFirst()){
            do {
                val nguoiDung=NguoiDung(
                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
                    que_quan = c.getString(c.getColumnIndex(NguoiDung.CLM_QUE_QUAN_NGUOI_DUNG)),
                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG)),
                    nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH))
                )
                list+=nguoiDung
            }while (c.moveToNext())
        }
        return list
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
                    ma_dich_vu = c.getString(c.getColumnIndex(Phong.CLM_MA_DICH_VU))
                )
                list+=phong
            }while (c.moveToNext())
        }

        return list
    }
    @SuppressLint("Range")
    fun getAllInPhongById(id:String):Phong?{
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
                ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU)),
                ma_dich_vu = c.getString(c.getColumnIndex(Phong.CLM_MA_DICH_VU))
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
                ma_khu = c.getString(c.getColumnIndex(Phong.CLM_MA_KHU)),
                ma_dich_vu = c.getString(c.getColumnIndex(Phong.CLM_MA_DICH_VU))
            )
        }
        return null
    }

    @SuppressLint("Range")
    fun getAllInNguoiDungByMaKhu(id: String):List<NguoiDung>{
        val list= mutableListOf<NguoiDung>()
        val sql = """
            select * from ${NguoiDung.TB_NAME} join ${Phong.TB_NAME} on ${NguoiDung.TB_NAME}.${NguoiDung.CLM_MA_PHONG}
             = ${Phong.TB_NAME}.${Phong.CLM_MA_PHONG} join ${KhuTro.TB_NAME} on ${Phong.TB_NAME}.${Phong.CLM_MA_KHU}
             = ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}
        where ${KhuTro.TB_NAME}.${KhuTro.CLM_MA_KHU_TRO}="$id"
        """.trimIndent()
        val c=db.rawQuery(sql,null)
        if(c.moveToFirst()){
            do {
                val nguoiDung=NguoiDung(
                    ma_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_NGUOI_DUNG)),
                    ho_ten_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_HO_TEN_NGUOI_DUNG)),
                    cccd = c.getString(c.getColumnIndex(NguoiDung.CLM_CCCD)),
                    nam_sinh = c.getString(c.getColumnIndex(NguoiDung.CLM_NAM_SINH)),
                    sdt_nguoi_dung = c.getString(c.getColumnIndex(NguoiDung.CLM_SDT_NGUOI_DUNG)),
                    que_quan = c.getString(c.getColumnIndex(NguoiDung.CLM_QUE_QUAN_NGUOI_DUNG)),
                    trang_thai_chu_hop_dong = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG)),
                    trang_thai_o = c.getInt(c.getColumnIndex(NguoiDung.CLM_TRANG_THAI_O)),
                    ma_phong = c.getString(c.getColumnIndex(NguoiDung.CLM_MA_PHONG))
                )
                list+=nguoiDung
            }while (c.moveToNext())
        }
        return list
    }
}