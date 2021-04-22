package com.mineiro.lista_cardview

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mineiro.lista_cardview.DetailActivity.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_List)
    }
    private val adapterMain = ContactAdapter(this)   //minha interface já implementa o ClickItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fetchListContact()
        bindViews()
    }

    private fun fetchListContact() {
        // para ler dados de favoritos no SharedPreferences
        var list = arrayListOf(
                Contact(
                        "Rodrigo Volpe",
                        "(21) 99222-3456",
                        "teste.png"
                ),
                Contact(
                        "Tiago XXXX",
                        "(21) 99333-3444",
                        "teste2.png"
                )
        )
        // transformar a lista acima em string, usando JSON
        getInstanceSharedPreferences()?.edit() {
            putString("Contacts", Gson().toJson(list))
            commit()       // vai salvar os dados em disco, antes de fazer a consulta
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences? {
        return getSharedPreferences("com.mineiro.lista_cardview.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //abrir e fechar a toolbar
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun bindViews() {
        rvList.adapter = adapterMain    //fiz a união dos 2 adapters
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts() : List<Contact>{
        //converte uma string em objeto de classe
        val list2 = getInstanceSharedPreferences()?.getString("Contacts", "[]")
        val turnsType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list2, turnsType)
    }

    private fun updateList() {
        adapterMain.updateList(getListContacts())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                showToast("Exibindo item de Menu 1")
                true
            }
            R.id.item_menu_2 -> {
                showToast("Exibindo item de Menu 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        //quando clica no item, abre a Activity do detalhe
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)     //chave, valor -> para enviar dados para outra tela
        startActivity(intent)
    }
}