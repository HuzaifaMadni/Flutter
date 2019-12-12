package com.huzaifa.instagramclone.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.huzaifa.instagramclone.R
import com.huzaifa.instagramclone.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*
import kotlinx.android.synthetic.main.layout_center_viewpager.*
import kotlinx.android.synthetic.main.layout_tabs_bar.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        BottomNavigationViewHelper.enableNavigation(this, bottomNavViewBar)

        val menu = bottomNavViewBar.menu
        val menuItem = menu.getItem(0)
        menuItem.isChecked = true

        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(CameraFragment())
        fragmentList.add(HomeFragment())
        fragmentList.add(MessagesFragment())

        val sectionsPageAdapter = SectionsPageAdapter(supportFragmentManager, fragmentList)

        container.adapter = sectionsPageAdapter

        tabs.setupWithViewPager(container)

        tabs.getTabAt(0)?.setIcon(R.drawable.ic_camera)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_home)
        tabs.getTabAt(2)?.setIcon(R.drawable.ic_messages)
    }

}
