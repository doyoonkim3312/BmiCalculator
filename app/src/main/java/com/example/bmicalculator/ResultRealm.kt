package com.example.bmicalculator

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ResultRealm (
    @PrimaryKey var id : Long = 0,
    var bmiResult : String = "",
    var bmiLevel : Double = 0.0,
    var date : Long = 0,
    var dataWeight : Int = 0,
    var dataHeight : Int = 0
) : RealmObject() {

}