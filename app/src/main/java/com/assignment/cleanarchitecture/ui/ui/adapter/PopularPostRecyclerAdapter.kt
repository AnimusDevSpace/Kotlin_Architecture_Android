package com.assignment.cleanarchitecture.ui.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.assignment.cleanarchitecture.databinding.ItemLayoutBinding
import com.assignment.cleanarchitecture.ui.ClickListener
import com.cleanarchitecture.network.utils.const.ConstantData
import com.bumptech.glide.Glide
import com.cleanarchitecture.network.models.MostPopularArticles
import java.util.*
import kotlin.collections.ArrayList


class PopularPostRecyclerAdapter(var context: Activity, var list: ArrayList<MostPopularArticles.Result>)
    : RecyclerView.Adapter<PopularPostRecyclerAdapter.PopularPostRecyclerViewHolder>(), Filterable{

    var filterList = ArrayList<MostPopularArticles.Result>()
    lateinit var listener:ClickListener
    init {
        filterList = list
    }
    class PopularPostRecyclerViewHolder(var binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(listener: ClickListener,position: Int, list:ArrayList<MostPopularArticles.Result>, context: Context) {
            binding.txtDate.text=list[position].publishedDate
            binding.txtHeading.text=list[position].abstract
            binding.txtReporterName.text=list[position].byline
            Glide.with(context)
                .load(list[position].media[ConstantData.INDEX].mediaMetadata[ConstantData.INDEX].url)
                .into(binding.imageViewAvatar)

            binding.imgArrow.setOnClickListener {
                  listener.onClickOnItem(position)
            }




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPostRecyclerViewHolder {
        val binding = ItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularPostRecyclerViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: PopularPostRecyclerViewHolder, position: Int) {
        holder.bindItem(listener,position, list, context)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }
    fun addPhoto(mList: List<MostPopularArticles.Result>) {
        this.list.apply {
            clear()
            addAll(mList)
        }

    }
    //filter the data acc. to query
    override fun getFilter(): Filter {
       return object:Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
               val charSearch = constraint.toString()
               filterList = if (charSearch.isEmpty()) {
                   list
               } else {
                   val resultList = ArrayList<MostPopularArticles.Result>()
                   for (row in list) {
                       if (row.abstract.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                           resultList.add(row)
                       }
                   }
                   resultList
               }
               val filterResults = FilterResults()
               filterResults.values = filterList
               return filterResults
           }
           @Suppress("UNCHECKED_CAST")
           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               filterList = results?.values as ArrayList<MostPopularArticles.Result>
               notifyDataSetChanged()
           }

       }

        }

    fun registerListener(listener: ClickListener){
        this.listener=listener
    }
}
