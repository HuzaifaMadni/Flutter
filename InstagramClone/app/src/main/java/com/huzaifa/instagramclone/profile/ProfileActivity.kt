package com.huzaifa.instagramclone.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.huzaifa.instagramclone.R
import com.huzaifa.instagramclone.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*
import kotlinx.android.synthetic.main.snippet_top_profilebar.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        BottomNavigationViewHelper.enableNavigation(this, bottomNavViewBar)

        val menu = bottomNavViewBar.menu
        val menuItem = menu.getItem(4)
        menuItem.isChecked = true

        setSupportActionBar(profileToolbar)

        profileProgress.visibility = View.GONE

        profileMenu.setOnClickListener {
            val intent = Intent(this, ActivityAccountSettings::class.java)
            startActivity(intent)
        }
    }
}
