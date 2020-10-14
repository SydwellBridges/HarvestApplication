package com.example.harvest.navigate

class User(val uid: String, val name: String, val email: String, val phoneNumber: String, val profileImageUrl: String) {

    constructor():this("", "","","","")
}