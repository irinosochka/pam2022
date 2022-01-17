package com.example.pillservice

class Pill {
    var id: Int = 0
    var namePill: String = ""
    var quantity: Int = 0
    var datePill: String = ""

    constructor(id: Int, namePill: String, quantity: Int, datePill: String){
        this.id = id
        this.namePill = namePill
        this.quantity = quantity
        this.datePill = datePill
    }

    constructor(namePill: String, quantity: Int, datePill: String){
        this.id = id
        this.namePill = namePill
        this.quantity = quantity
        this.datePill = datePill
    }
}