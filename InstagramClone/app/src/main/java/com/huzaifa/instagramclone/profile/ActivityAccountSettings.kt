package com.huzaifa.instagramclone.profile

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.huzaifa.instagramclone.R
import kotlinx.android.synthetic.main.layout_center_settings.*
import kotlinx.android.synthetic.main.snippet_top_settingsbar.*

class ActivityAccountSettings: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accountsettings)

        backArrow.setOnClickListener {
            finish()
        }

        val list = ArrayList<String>()

        list.add("Edit Profile")
        list.add("Sign Out")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        settingsLv.adapter = adapter
    }

}