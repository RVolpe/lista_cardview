package com.mineiro.lista_cardview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize     //para trafegar dados entre as telas do app
data class Contact(
    var nome: String,
    var telefone: String,
    var foto: String
): Parcelable
