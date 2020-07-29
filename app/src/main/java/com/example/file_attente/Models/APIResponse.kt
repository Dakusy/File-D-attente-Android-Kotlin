package com.example.file_attente.Models

import Json4Kotlin_Base

class APIResponse {

    var isError: Boolean=false
    var uid:String?=null
    var error_msg:String?=null
    var user:Json4Kotlin_Base?=null;
}