package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData() //Load save data.

        resultButton.setOnClickListener {
            // Tasks executed after the button is clicked
            /*
            traditional way to declaring intent that changes the view.
            val resultBtnIntent = Intent(this, ResultActivity::class.java)
            resultBtnIntent.putExtra("weight", weightEditTextBox.text.toString())
            resultBtnIntent.putExtra("height", heightEditTextBox.text.toString())
            startActivity(resultBtnIntent)
             */

            // save data before change the view
            saveData(heightEditTextBox.text.toString().toInt(), weightEditTextBox.text.toString().toInt())

            //Declaring intent that changes the view using Anko library
            startActivity<ResultActivity>(
                "weight" to weightEditTextBox.text.toString(),
                "height" to heightEditTextBox.text.toString()
            )
        }

        goToListBtn.setOnClickListener {
            startActivity<ResultSaveView>()
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
        //putInt("Key", value)
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0) //(Default value: 0
        val weight = pref.getInt("KEY_WEIGHT", 0) //Default value: 0

        if (height != 0 && weight != 0) {
            //if both values are not 0, load saved data and display on the textbox.
            weightEditTextBox.setText(weight.toString())
            heightEditTextBox.setText(height.toString())
        }
    }
}