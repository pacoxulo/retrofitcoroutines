package net.azarquiel.retrofitcoroutines

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.recetasclase.viewmodel.MainViewModel
import net.azarquiel.retrofitcoroutines.adapter.CustomAdapter
import net.azarquiel.retrofitcoroutines.model.Bar

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter
    private lateinit var viewModel: MainViewModel

    private lateinit var bares: List<Bar>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showBar()
        fab.setOnClickListener {
            addBar()
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getDataBares().observe(this, Observer {
            //adapter.setBares(it!!) // with nullable
            //it?.let{adapter.setBares(it)} // unwrap nullable it
            it?.let(adapter::setBares)  // to lambda
        })
    }

    private fun showBar() {
        adapter = CustomAdapter(this, R.layout.rowbar)
        rvBar.layoutManager = LinearLayoutManager(this)
        rvBar.adapter = adapter
    }

    private fun addBar() {
        viewModel.saveBar("111 Paco", "micassa", "Olias", "Toledo")
    }

//    private fun uiBares(bares: List<Bar>) {
//        for (bar in bares){
//                Log.d("Paco", bar.toString())
//        }
//    }
}
