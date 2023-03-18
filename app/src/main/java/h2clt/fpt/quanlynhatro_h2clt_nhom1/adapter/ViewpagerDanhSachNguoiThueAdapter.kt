package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerDanhSachNguoiThueAdapter (fragmentManager: FragmentManager, lifecylce: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecylce) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {FragmentNguoiDangO()}
            1 -> {FragmentNguoiDaO()}
            else ->{FragmentNguoiDangO()}
        }
    }
}