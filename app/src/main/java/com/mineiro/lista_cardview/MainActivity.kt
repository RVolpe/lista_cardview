package com.mineiro.lista_cardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_List)
    }
    private val adapterMain = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        updateList()
    }

    private fun bindViews() {
        rvList.adapter = adapterMain    //fiz a união dos 2 adapters
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList() {
        adapterMain.updateList(
            arrayListOf(
                Contact(
                    "Rodrigo Volpe",
                    "(21) 99222-3456",
                    "teste.png"
                ),
                 Contact(
                    "Rodrigo Volpe",
                    "(21) 99222-3456",
                    "teste.png"
                )
            )
        )
    }
}