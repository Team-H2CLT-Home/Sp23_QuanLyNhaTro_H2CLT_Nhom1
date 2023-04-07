package h2clt.fpt.quanlynhatro_h2clt_nhom1.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.ThongBaoDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.ThongBao
import java.util.UUID


const val notificationID=1
const val CHANNEL_ID="channel1"
const val TITLE_EXTRAS="titleExtras"
const val MESS_EXTRAS="messExtras"
const val MA_KHU="maKhu"
const val DATE="ngayThongBao"
const val LOAI_THONG_BAO="loaiThongBao"

class Notification: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val maKhu=intent.getStringExtra(MA_KHU)!!
        val tieuDe=intent.getStringExtra(TITLE_EXTRAS)!!
        val noiDung=intent.getStringExtra(MESS_EXTRAS)!!
        val ngayThongBao=intent.getStringExtra(DATE)!!
        val channel=intent.getStringExtra(CHANNEL_ID)!!
        val loai=intent.getIntExtra(LOAI_THONG_BAO, 0)
        val notification=NotificationCompat.Builder(context, channel)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle(tieuDe)
            .setContentText(noiDung).build()

        val manager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
        val thongBao=ThongBao(
            ma_thong_bao = UUID.randomUUID().toString(),
            tieu_de =tieuDe,
            noi_dung = noiDung,
            ma_khu = maKhu,
            ngay_thong_bao = ngayThongBao,
            loai_thong_bao = loai
        )
        ThongBaoDao(context).insertThongBao(thongBao)

    }
}