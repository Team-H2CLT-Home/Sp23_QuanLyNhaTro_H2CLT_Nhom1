package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment.FragmentQuanLy
import h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment.FragmentTongQuan

class ViewPager2TrangChuAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                FragmentQuanLy()
            }
            1 -> {
                FragmentTongQuan()
            }
            else ->{
                FragmentQuanLy()
            }
        }
    }
}