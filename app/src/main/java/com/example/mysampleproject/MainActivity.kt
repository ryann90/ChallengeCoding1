package com.example.mysampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysampleproject.adapter.PicsumAdapter
import com.example.mysampleproject.model.Picsum
import com.example.mysampleproject.utils.LoadingDialog
import com.example.mysampleproject.viewmodel.PicsumViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var picsumViewModel: PicsumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loading = LoadingDialog(this)
        loading.startLoading()
        loadData(loading)
    }

    private fun loadData(loading: LoadingDialog){
        picsumViewModel = ViewModelProvider(this).get(PicsumViewModel::class.java)
        picsumViewModel.getAllPicsumData()
        picsumViewModel.picsumDataList.observe(this) {
            if (it.count() != 0) {
                Handler(Looper.getMainLooper()).postDelayed({
                    loading.isDismiss()
                }, 0)

                initAdapter(it)
            }

        }
    }

    private fun initAdapter(data:List<Picsum>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PicsumAdapter(data) {
            val intent = Intent(this@MainActivity, ViewDetailsActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

}