package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
class Loading(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setTitle(null)

        val view : View = LayoutInflater.from(context).inflate(R.layout.animotion_loading,null)
        setContentView(view)
    }


}