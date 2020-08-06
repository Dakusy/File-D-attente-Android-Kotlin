package com.example.file_attente

import com.google.firebase.database.FirebaseDatabase

class Client(val id : String, val name : String, val number : Int)

val database = FirebaseDatabase.getInstance()
