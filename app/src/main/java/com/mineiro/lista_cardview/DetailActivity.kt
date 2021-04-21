package com.mineiro.lista_cardview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {
    private var contact: Contact? = null
    companion object{
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)
        initToolbar()   // cria icones de navegação

        getExtras()     //vai recuperar dados da tela anterior (enviado pelo putExtra())

        bindViews()     //vai receber os dados que estão chegando pelo getExtras(),
                        // e colocar nas variaveis desta tela
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)      //coloca a barra Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  //coloca o botao "Voltar" na toolbar
    }

    private fun getExtras() {
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    private fun bindViews() {
        findViewById<TextView>(R.id.tv_nome).text = contact?.nome
        findViewById<TextView>(R.id.tv_telefone).text = contact?.telefone
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}