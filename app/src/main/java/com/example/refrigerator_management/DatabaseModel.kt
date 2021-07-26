package com.example.refrigerator_management

class DatabaseModel() {
    lateinit var id:String
    lateinit var name:String
    lateinit var date:String
    constructor(name:String,date:String,id:String):this(){
        this.name=name
        this.date=date
        this.id=id
    }
}