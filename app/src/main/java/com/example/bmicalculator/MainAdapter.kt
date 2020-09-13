package com.example.bmicalculator

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.realm.*
import org.jetbrains.anko.startActivity
import io.realm.kotlin.where

class MainAdapter(val resultRealm : RealmResults<ResultRealm>, val context: Context) : RecyclerView.Adapter<MainViewHolder>() {
    // for onClickListener, get context from here.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        println("Create Holder")
        val inflatedView = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout,
        parent, false)
        return MainViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        //println(resultRealm.findAll().size)
        return resultRealm.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        println("item Called: Position $position")
            val data = resultRealm[position]!!
            holder.apply {
                itemBinder(data, context)
            }
    }

}