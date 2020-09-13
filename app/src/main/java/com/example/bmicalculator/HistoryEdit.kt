package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_history_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast

class HistoryEdit : AppCompatActivity() {
    val realm = Realm.getDefaultInstance() // get Realm instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_edit)

        val targetPK = intent.getLongExtra("pk", 0)
        println(targetPK)
        val targetResult = intent.getStringExtra("segueResult")
        //println(targetResult)
        val targetLevel = intent.getStringExtra("segueLevel")
        //println(targetLevel)
        val targetDate = intent.getLongExtra("segueDate", 0)
        //println(targetDate)
        val targetHeight = intent.getIntExtra("segueHeight", 0)
        //println(targetHeight)
        val targetWeight = intent.getIntExtra("segueWeight", 0)
        //println(targetWeight)

        resultHistoryText.text = targetResult.toString()
        bmiResultDate.text = DateFormat.format("yyyy/MM/dd", targetDate)
        bmiResultHeight.text = targetHeight.toString()
        bmiResultWeight.text = targetWeight.toString()
        bmiResultLevelText. text = targetLevel.toString()

        historyDeleteBtn.setOnClickListener{
            deleteRealmData(targetPK)
            toast(getString(R.string.delete_approved))
            finish()
            /*
            alert (getString(R.string.delete_result_sure)){
                okButton {
                    deleteRealmData(targetDate)
                    toast(getString(R.string.delete_approved))
                    finish()
                }
                noButton {
                    toast(getString(R.string.delete_denied))
                }
            }
             */
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun deleteRealmData(id: Long) {
        realm.beginTransaction()
        val deleteTarget = realm.where<ResultRealm>().equalTo("id",id).findFirst()!!
        deleteTarget.deleteFromRealm()
        realm.commitTransaction()
    }
}