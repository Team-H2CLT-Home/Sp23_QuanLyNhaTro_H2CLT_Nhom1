package h2clt.fpt.quanlynhatro_h2clt_nhom1.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.DbHelper.H2CLT.DB_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.DbHelper.H2CLT.DB_VERSION
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.*

class   DbHelper(context: Context): SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    object H2CLT{
        const val DB_NAME="MyDB"
        const val DB_VERSION=2
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val admin="""
            CREATE TABLE ${Admin.TB_NAME}(
            ${Admin.CLM_SDT} text unique not NULL,
            ${Admin.CLM_TEN_DANG_NHAP} PRIMARY key NOT NULL,
            ${Admin.CLM_HO_TEN} text NOT NULL,
            ${Admin.CLM_STK} text,
            ${Admin.CLM_NGAY_SINH} text,
            ${Admin.CLM_MAT_KHAU} text NOT NULL );
        """.trimIndent()
        db?.execSQL(admin)

        val khu_tro="""
            CREATE TABLE ${KhuTro.TB_NAME}(
            ${KhuTro.CLM_MA_KHU_TRO} text PRIMARY KEY NOT NULL,
            ${KhuTro.CLM_TEN_KHU_TRO} text NOT NULL,
            ${KhuTro.CLM_DIA_CHI} text NOT NULL,
            ${KhuTro.CLM_SO_LUONG_PHONG} integer NOT NULL,
            ${KhuTro.CLM_TEN_DANG_NHAP} text NOT NULL,
            FOREIGN KEY (${KhuTro.CLM_TEN_DANG_NHAP}) REFERENCES ${Admin.TB_NAME}(${Admin.CLM_TEN_DANG_NHAP}));
        """.trimIndent()
        db?.execSQL(khu_tro)


        val loai_dich_vu="""
            CREATE TABLE ${LoaiDichVu.TB_NAME}(
            ${LoaiDichVu.CLM_MA_LOAI_DICH_VU} text PRIMARY KEY NOT NULL,
            ${LoaiDichVu.CLM_TEN_LOAI_DICH_VU} text  NOT NULL,
            ${LoaiDichVu.CLM_GIA_DICH_VU} integer NOT NULL,
            ${LoaiDichVu.CLM_MA_PHONG} text NOT NULL ,
            ${LoaiDichVu.CLM_TRANG_THAI_LOAI_DICH_VU} Integer NOT NULL,
            FOREIGN KEY(${LoaiDichVu.CLM_MA_PHONG})REFERENCES ${Phong.TB_NAME}(${Phong.CLM_MA_PHONG}));
        """.trimIndent()
        db?.execSQL(loai_dich_vu)

        val phong="""
            CREATE TABLE ${Phong.TB_NAME}(
            ${Phong.CLM_MA_PHONG} text PRIMARY KEY NOT NULL,
            ${Phong.CLM_TEN_PHONG} text NOT NULL,
            ${Phong.CLM_DIEN_TICH} integer NOT NULL,
            ${Phong.CLM_GIA_THUE} long NOT NULL,
            ${Phong.CLM_SO_NGUOI_O} integer NOT NULL,
            ${Phong.CLM_TRANG_THAI_PHONG} integer NOT NULL,
            ${Phong.CLM_MA_KHU} text NOT NULL,
            FOREIGN KEY(${Phong.CLM_MA_KHU})REFERENCES ${KhuTro.TB_NAME}(${KhuTro.CLM_MA_KHU_TRO})); 
        """.trimIndent()
        db?.execSQL(phong)

        val hoa_don="""
            
            CREATE table ${HoaDon.TB_NAME}(
            ${HoaDon.CLM_MA_HOA_DON} text PRIMARY KEY NOT NULL,
            ${HoaDon.CLM_NGAY_TAO_HOA_DON} text NOT NULL,
            ${HoaDon.CLM_GIA_THUE} integer not null,
            ${HoaDon.CLM_SO_DIEN} integer NOT NULL,
            ${HoaDon.CLM_SO_NUOC} integer NOT NULL,
            ${HoaDon.CLM_TRANG_THAI_HOA_DON} integer NOT NULL,
            ${HoaDon.CLM_MIEN_GIAM} integer NOT NULL,
            ${HoaDon.CLM_GIA_DICH_VU} integer not null,
            ${HoaDon.CLM_MA_PHONG} text NOT NULL,
            FOREIGN KEY (${HoaDon.CLM_MA_PHONG} ) REFERENCES ${Phong.TB_NAME}(${Phong.CLM_MA_PHONG}));
        """.trimIndent()
        db?.execSQL(hoa_don)

        val nguoi_dung="""
            CREATE TABLE ${NguoiDung.TB_NAME}(
            ${NguoiDung.CLM_MA_NGUOI_DUNG} text PRIMARY key NOT NULL,
            ${NguoiDung.CLM_HO_TEN_NGUOI_DUNG} text NOT NULL,
            ${NguoiDung.CLM_CCCD} text NOT NULL,
            ${NguoiDung.CLM_NAM_SINH} text not null,
            ${NguoiDung.CLM_QUE_QUAN_NGUOI_DUNG} text not null,
            ${NguoiDung.CLM_SDT_NGUOI_DUNG} text unique NOT NULL,
            ${NguoiDung.CLM_MA_PHONG} text NOT NULL,
            ${NguoiDung.CLM_TRANG_THAI_O} integer not NULL,
            ${NguoiDung.CLM_TRANG_THAI_CHU_HOP_DONG} integer NOT NULL,
            FOREIGN KEY (${NguoiDung.CLM_MA_PHONG} ) REFERENCES ${Phong.TB_NAME}(${Phong.CLM_MA_PHONG}));
        """.trimIndent()
        db?.execSQL(nguoi_dung)

        val hop_dong="""
            CREATE TABLE ${HopDong.TB_NAME}(
            ${HopDong.CLM_MA_HOP_DONG} text PRIMARY KEY NOT NULL,
            ${HopDong.CLM_THOI_HAN} integer NOT NULL,
            ${HopDong.CLM_NGAY_O} text NOT NULL,
            ${HopDong.CLM_NGAY_HOP_DONG} text NOT NULL,
            ${HopDong.CLM_NGAY_LAP_HOP_DONG} text NOT NULL,
            ${HopDong.CLM_ANH_HOP_DONG}  text NOT NULL,
            ${HopDong.CLM_TIEN_COC} long NOT NULL,
            ${HopDong.CLM_TRANG_THAI_HOP_DONG} integer NOT NULL,
            ${HopDong.CLM_MA_PHONG} text NOT NULL,
            ${HopDong.CLM_MA_NGUOI_DUNG} text NOT NULL,
            FOREIGN KEY (${HopDong.CLM_MA_PHONG} ) REFERENCES ${Phong.TB_NAME}(${Phong.CLM_MA_PHONG}),
            FOREIGN KEY (${HopDong.CLM_MA_NGUOI_DUNG} ) REFERENCES ${NguoiDung.TB_NAME}(${NguoiDung.CLM_MA_NGUOI_DUNG}));
        """.trimIndent()
        db?.execSQL(hop_dong)

        val thong_bao="""
            CREATE TABLE ${ThongBao.TB_NAME}(
            ${ThongBao.CLM_MA_THONG_BAO} text PRIMARY KEY NOT NULL,
            ${ThongBao.CLM_TIEU_DE} text NOT NULL,
            ${ThongBao.CLM_NGAY_THONG_BAO} text NOT NULL,
            ${ThongBao.CLM_NOI_DUNG} text NOT NULL
            );
        """.trimIndent()
        db?.execSQL(thong_bao)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}