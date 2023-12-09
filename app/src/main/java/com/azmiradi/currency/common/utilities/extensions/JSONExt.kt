package com.azmiradi.currency.common.utilities.extensions

import com.google.gson.Gson
import java.lang.reflect.Type

fun <M> String.getModelFromJSON(tokenType: Type): M = Gson().fromJson(this, tokenType)
