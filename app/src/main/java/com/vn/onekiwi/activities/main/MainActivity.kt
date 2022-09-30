package com.vn.onekiwi.activities.main

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.vn.onekiwi.R
import com.vn.onekiwi.base.BaseActivity
import com.vn.onekiwi.databinding.ActivityMainBinding
import com.vn.onekiwi.fragments.first.FirstFragment
import com.vn.onekiwi.fragments.fourth.FourthFragment
import com.vn.onekiwi.fragments.second.SecondFragment
import com.vn.onekiwi.fragments.third.ThirdFragment
import com.vn.onekiwi.utils.ViewPagerAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    private lateinit var view: ActivityMainBinding
    override val presenter: MainContract.Presenter by inject { parametersOf(this) }

    private lateinit var adapter: ViewPagerAdapter
    private var TAB_FIRST = 0
    private var TAB_SECOND = 1
    private var TAB_THIRD = 2
    private var TAB_FOURTH = 3

    override fun onGetView(): View {
        view = ActivityMainBinding.inflate(layoutInflater)
        return view.root
    }

    override fun onSyncView() {
        super.onSyncView()
        initViewPager()
    }

    private fun initViewPager() {
        //val viewPagerAdapter=ViewPagerAdapter(this)
        adapter = ViewPagerAdapter(this)
        adapter.addFrag(TAB_FIRST, FirstFragment())
        adapter.addFrag(TAB_SECOND, SecondFragment())
        adapter.addFrag(TAB_THIRD, ThirdFragment())
        adapter.addFrag(TAB_FOURTH, FourthFragment())
        view.viewPager.adapter = adapter
        view.viewPager.offscreenPageLimit = adapter.itemCount
        view.bottomNavigation.setOnItemSelectedListener{item ->
            when(item.itemId){
                R.id.tab_first -> view.viewPager.currentItem = TAB_FIRST
                R.id.tab_second -> view.viewPager.currentItem = TAB_SECOND
                R.id.tab_third -> view.viewPager.currentItem = TAB_THIRD
                R.id.tab_fourth -> view.viewPager.currentItem = TAB_THIRD
            }
            true
        }

        view.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })
    }
}