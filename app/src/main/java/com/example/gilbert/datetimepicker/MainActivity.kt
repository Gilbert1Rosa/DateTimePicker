package com.example.gilbert.datetimepicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gilbert.datetimepicker.datetimepicker.DatePicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
    }

    fun setupEvents() {
        showDateDialog.setOnClickListener {
            DatePicker.create().show(supportFragmentManager, "DATE_DIALOG")
        }
    }
}
