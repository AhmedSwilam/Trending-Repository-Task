package com.aqwas.com.sa

import com.google.gson.annotations.SerializedName


data class BuiltBy (

  @SerializedName("href"     ) var href     : String? = null,
  @SerializedName("avatar"   ) var avatar   : String? = null,
  @SerializedName("username" ) var username : String? = null

)