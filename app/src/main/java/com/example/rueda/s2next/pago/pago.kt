package com.example.rueda.s2next.pago

class pago {

    var id_payment: Int = 0
    var id_customer: Int = 0
    var register_date: String = ""
    var fecha: String = ""
    var amount: Int = 0
    var total: Int = 0


    constructor(id_customer: Int, register_date: String, fecha: String, amount: Int) {
        this.id_customer = id_customer
        this.register_date = register_date
        this.fecha = fecha
        this.amount = amount
    }

    constructor() {

    }

}