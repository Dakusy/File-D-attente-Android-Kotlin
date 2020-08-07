package com.example.file_attente

import com.google.firebase.database.FirebaseDatabase

// Une class permettant de récupérer les données d'un client lambda sans Image.
class Client(val id : String, val name : String, val number : Int)

//Initialisation de la base de donnée Firebase pour une utilisation global dans toute l'application
val database = FirebaseDatabase.getInstance()
