package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerDanhSachPhongAdapter(fragmentManager: FragmentManager, lifecylce: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecylce) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {FragmentTatCaPhong()}
            1 -> {FragmentPhongTrong()}
            else ->{FragmentPhongDaO()}
        }
    }
}