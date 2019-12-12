package com.huzaifa.instagramclone.share

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huzaifa.instagramclone.R
import com.huzaifa.instagramclone.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        BottomNavigationViewHelper.enableNavigation(this, bottomNavViewBar)

        val menu = bottomNavViewBar.menu
        val menuItem = menu.getItem(2)
        menuItem.isChecked = true
    }

}
