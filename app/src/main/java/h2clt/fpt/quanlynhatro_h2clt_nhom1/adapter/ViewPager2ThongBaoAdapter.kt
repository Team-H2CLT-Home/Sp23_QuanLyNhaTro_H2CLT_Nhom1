package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ThongBao.FragmentTBKhac
import h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ThongBao.FragmentTBTatCa
import h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ThongBao.FragmentTBTien

class ViewPager2ThongBaoAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {FragmentTBTatCa()}
            1 -> {FragmentTBTien()}
            2 -> {FragmentTBKhac()}
            else ->{FragmentTBTatCa()}
        }
    }
}