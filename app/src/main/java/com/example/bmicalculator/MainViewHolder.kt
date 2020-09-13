package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_result.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import org.jetbrains.anko.image
import android.text.format.DateFormat
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlin.coroutines.coroutineContext

class MainViewHolder (view: View) : RecyclerView.ViewHolder(view){
    var itemContainer : View = view

    fun itemBinder(data : ResultRealm, context: Context) {
        println("Binder is called")
        // onClickListener : View.OnClickListener: Need for setOnClickListener()
        itemContainer.bmiResultTextView.text = data.bmiResult
        itemContainer.bmiLevelTextView.text = String.format("%.2f", data.bmiLevel)
        itemContainer.dateTextView.text = DateFormat.format("yyyy/MM/dd", data.date)

        when {
            data.bmiLevel >= 23 ->
                itemContainer.resultImageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_dissatisfied_24
                )
            data.bmiLevel >= 18.5 ->
                itemContainer.resultImageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_satisfied_24
                )
            else ->
                itemContainer.resultImageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_satisfied_24
                )
        }
        itemContainer.itemBoxView.setOnClickListener{
            val itemIntent = Intent(context, HistoryEdit::class.java)
            itemIntent.putExtra("pk", data.id)
            itemIntent.putExtra("segueResult", data.bmiResult)  //String
            itemIntent.putExtra("segueLevel", String.format("%.2f", data.bmiLevel)) //String
            itemIntent.putExtra("segueDate", data.date) //Long
            itemIntent.putExtra("segueHeight", data.dataHeight) //Int
            itemIntent.putExtra("segueWeight", data.dataWeight) //Int
            startActivity(context, itemIntent, null)
        }
    }
}