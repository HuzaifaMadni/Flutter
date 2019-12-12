package com.huzaifa.instagramclone.likes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.huzaifa.instagramclone.R
import com.huzaifa.instagramclone.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

class LikesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        BottomNavigationViewHelper.enableNavigation(this, bottomNavViewBar)

        val menu = bottomNavViewBar.menu
        val menuItem = menu.getItem(3)
        menuItem.isChecked = true
    }

}
