package com.example.kotl3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kotl3.databinding.PersonItemLayoutBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class PersonItemAdapter(private val context: Context, private val personItemList: MutableList<PersonItem>)
    : RecyclerView.Adapter<PersonItemAdapter.PersonItemViewHolder>(), Filterable {

    private var personItemFilterList : MutableList<PersonItem> = mutableListOf()

    init {
        personItemFilterList = personItemList
    }

    class PersonItemViewHolder(personItemLayoutBinding: PersonItemLayoutBinding)
        : RecyclerView.ViewHolder(personItemLayoutBinding.root) {

        private val binding = personItemLayoutBinding

        fun bind(personItem: PersonItem, context: Context) {

            val resId: Int = context.resources.getIdentifier(personItem.picId, "drawable", context.packageName)

            binding.personPicId.setBackgroundResource(resId)
            binding.personName.text = personItem.name
            binding.personDates.text = personItem.dates
            binding.personDesc.text = personItem.desc
            binding.personGender.text = personItem.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonItemViewHolder {

        val binding = PersonItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)

        return PersonItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonItemViewHolder, position: Int) {

        val personItem = personItemFilterList[position]
        holder.bind(personItem, context)
        holder.itemView.setOnClickListener {
            Snackbar.make(holder.itemView, "Нажата карточка ${personItem.name}", Snackbar.LENGTH_SHORT).show()
        }
        holder.itemView.findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            Snackbar.make(holder.itemView, "Нажат лайк ${personItem.name}", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {

        return personItemFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    personItemFilterList = personItemList
                } else {
                    val resultList : MutableList<PersonItem> = mutableListOf()
                    for (row in personItemList) {
                        if (row.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    personItemFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = personItemFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                personItemFilterList = results?.values as MutableList<PersonItem>
                notifyDataSetChanged()
            }

        }
    }
}