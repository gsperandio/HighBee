package com.br.highbee.model;

data class User(
    val FirstName: String,
    val LastName: String,
    val Phone: String,
    val Code: String,
    val Status: Boolean = false
)

