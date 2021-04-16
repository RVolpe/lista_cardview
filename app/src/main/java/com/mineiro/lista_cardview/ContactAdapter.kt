package com.mineiro.lista_cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {
    private val list: MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        //acontece 1 passo antes do onBindViewHolder()
        //Onde vou criar a viewHolder (criar o layoult e inflá-lo)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        //onde vou popular cada card da ViewHoder
        holder.bind((list[position]))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(listUp: List<Contact>) {
        this.list.clear()
        this.list.addAll(listUp)
        notifyDataSetChanged()   //indicando que a lista mudou, e terá que passar em todos os metodos acima novamente.
    }

    class ContactAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNome: TextView = itemView.findViewById(R.id.tv_nome)
        private val tvTelefone: TextView = itemView.findViewById(R.id.tv_telefone)
        private val ivFoto: TextView = itemView.findViewById(R.id.iv_foto)

        fun bind(contact: Contact) {
            tvNome.text = contact.nome
            tvTelefone.text = contact.telefone
            ivFoto.text = contact.foto
        }
    }
}