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
const val channelID="ChanelID"
const val titleExtras="titleExtras"
const val messExtras="messExtras"
const val maKhu="maKhu"
const val date="ngayThongBao"
const val loaiThongBao="loaiThongBao"

class Notification: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val maKhu=intent.getStringExtra(maKhu)!!
        val tieuDe=intent.getStringExtra(titleExtras)!!
        val noiDung=intent.getStringExtra(messExtras)!!
        val channel=intent.getStringExtra(channelID)!!
        val ngayThongBao=intent.getStringExtra(date)!!
        val loai=intent.getIntExtra(loaiThongBao, 0)
        val notification=NotificationCompat.Builder(context,channel)
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