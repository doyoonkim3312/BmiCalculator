package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_result_save_view.*
import org.jetbrains.anko.startActivity

class ResultSaveView : AppCompatActivity() {
    val realm = Realm.getDefaultInstance() //get realm instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_save_view)

        val currentItems = realm.where<ResultRealm>().findAll()!!
        val adapter = MainAdapter(currentItems)

        resultRecyclerView.adapter = adapter

        //Update realm based on change history
        currentItems.addChangeListener { _ -> adapter.notifyDataSetChanged() }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}