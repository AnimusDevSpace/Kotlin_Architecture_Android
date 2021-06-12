package com.assignment.cleanarchitecture.ui.ui.master

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.cleanarchitecture.databinding.ActivityMainBinding
import com.assignment.cleanarchitecture.ui.ClickListener
import com.assignment.cleanarchitecture.ui.ui.adapter.PopularPostRecyclerAdapter
import com.assignment.cleanarchitecture.ui.ktx.viewBinding
import com.assignment.cleanarchitecture.ui.ui.viewmodel.MainViewModel
import com.assignment.cleanarchitecture.ui.ui.webview.DetailsActivity
import com.cleanarchitecture.network.utils.const.ConstantData
import com.cleanarchitecture.network.models.MostPopularArticles
import com.cleanarchitecture.network.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: PopularPostRecyclerAdapter
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private var listData = mutableListOf<MostPopularArticles.Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getPostPopularArticles().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        if (resource.data!!.results.isNotEmpty()) binding.txtMsg.visibility =
                            View.GONE else binding.txtMsg.visibility =
                            View.VISIBLE
                        addList(resource.data!!.results)
                    }
                    Status.FAILED -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.txtMsg.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING_API -> {
                        binding.txtMsg.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })


    }

    private fun setupUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                adapter.filter.filter(newText)
                return true
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            PopularPostRecyclerAdapter(this, listData as ArrayList<MostPopularArticles.Result>)
        binding.recyclerView.adapter = adapter
        adapter.registerListener(this)
    }

    private fun addList(photoList: List<MostPopularArticles.Result>) {
        adapter.apply {
            addPhoto(photoList)
            notifyDataSetChanged()
        }
    }

    override fun onClickOnItem(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(ConstantData.URL_KEY, listData[position].url)
        startActivity(intent)
    }


}