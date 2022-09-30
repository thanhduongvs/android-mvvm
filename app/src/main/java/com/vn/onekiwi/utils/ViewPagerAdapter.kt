package com.vn.onekiwi.utils

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private var fragmentList: SparseArray<Fragment> = SparseArray()

    override fun getItemCount(): Int {
        return fragmentList.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFrag(key: Int, fragment: Fragment) {
        fragmentList.put(key, fragment)
    }

    fun getFragmentByFrag(key: Int): Fragment {
        return fragmentList[key]
    }

    fun replaceAll(array: SparseArray<Fragment>) {
        fragmentList = array
    }
}