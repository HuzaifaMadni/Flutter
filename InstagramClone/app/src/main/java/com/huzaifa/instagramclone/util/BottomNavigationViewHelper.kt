package com.huzaifa.instagramclone.util

import android.content.Context
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.huzaifa.instagramclone.*
import com.huzaifa.instagramclone.home.HomeActivity
import com.huzaifa.instagramclone.likes.LikesActivity
import com.huzaifa.instagramclone.profile.ProfileActivity
import com.huzaifa.instagramclone.search.SearchActivity
import com.huzaifa.instagramclone.share.ShareActivity


object BottomNavigationViewHelper {

    fun enableNavigation(context: Context, bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_house -> {
                    val intent = Intent(context, HomeActivity::class.java) // 0
                    context.startActivity(intent)
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.ic_alert -> {
                    val intent = Intent(context, LikesActivity::class.java) // 3
                    context.startActivity(intent)
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.ic_android -> {
                    val intent = Intent(context, ProfileActivity::class.java) // 4
                    context.startActivity(intent)
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.ic_circle -> {
                    val intent = Intent(context, ShareActivity::class.java) // 2
                    context.startActivity(intent)
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.ic_search -> {
                    val intent = Intent(context, SearchActivity::class.java) // 1
                    context.startActivity(intent)
                    return@setOnNavigationItemSelectedListener false
                }
                else -> { return@setOnNavigationItemSelectedListener true}
            }
        }
    }
}