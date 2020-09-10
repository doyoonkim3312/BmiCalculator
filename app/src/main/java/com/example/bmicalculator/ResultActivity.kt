package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // passed value via intent from parent activity
        val height = intent.getStringExtra("height")?.toInt()
        val weight = intent.getStringExtra("weight")?.toInt()

        // BMI calculation
        val bmi : Double = weight!! / Math.pow((height!! / 100.0), 2.0)

        // Displaying Result
        when {
            bmi >= 35.0 -> resultTextView.text = getString(R.string.extremely_obese)
            bmi >= 30.0 -> resultTextView.text = getString(R.string.obesity_level_2)
            bmi >= 25.0 -> resultTextView.text = getString(R.string.obesity_level_1)
            bmi >= 20.0 -> resultTextView.text = getString(R.string.overweight)
            bmi >= 15.0 -> resultTextView.text = getString(R.string.Normal)
            else -> resultTextView.text = getString(R.string.underweight)
        }

        // Image Displaying
        when {
            bmi >= 23 ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_dissatisfied_24
                )
            bmi >= 18.5 ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_satisfied_24
                )
            else ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_satisfied_24
                )
        }

        // Popping up Toast Message
        /*Traditional way to display Toast Message
        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
         */

        //Displaying Toast Message using Anko Library
        toast(String.format("%.2f", bmi)) //Double value casting

    }
}