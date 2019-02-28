package net.azarquiel.retrofitcoroutines

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.retrofitcoroutines.adapter.CustomAdapter
import net.azarquiel.retrofitcoroutines.model.Bar
import net.azarquiel.retrofitcoroutines.viewmodel.MainViewModel

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
        viewModel.loadData().observe(this, Observer { it ->
            it?.let{
                bares=it
                adapter.setBares(bares)
            }
        })
    }

    private fun showBar() {
        adapter = CustomAdapter(this, R.layout.rowbar)
        rvBar.layoutManager = LinearLayoutManager(this)
        rvBar.adapter = adapter
    }

    private fun addBar() {
        viewModel.saveBar("000 Pepe", "micassa", "Olias", "Toledo").observe(this, Observer {
            it?.let {
                Toast.makeText(this,"Saved Bar...",Toast.LENGTH_LONG).show()
                val bares = ArrayList(bares)
                bares.add(0, it)
                this.bares = bares
                adapter.setBares(this.bares)
            }
        })
    }
}
