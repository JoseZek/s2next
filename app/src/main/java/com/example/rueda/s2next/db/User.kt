package com.example.rueda.s2next.db

class user{

    var id: Int = 0
    var name: String = ""
    var middle_name: String = ""
    var last_name : String = ""
    var second_name : String = ""
    var birthdate: String = ""
    var gender: Int = 1 // 1 hombre y 2 mujer

    constructor(name2: String,middle_name : String,last_name : String,second_name : String,birthdate : String,gender : Int){

        this.name = name2
        this.middle_name = middle_name
        this.last_name = last_name
        this.second_name = second_name
        this.birthdate = birthdate
        this.gender = gender

    }
    constructor(){

    }
}