package com.example.homework9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.addUser.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this@MainActivity.adapter = Adapter(userList)
            this@MainActivity.adapter.setOnItemClickListener(object : Adapter.OnItemsClickListener {
                override fun onDeleteClicked(position: Int) {
                    userList.removeAt(position)
                    this@MainActivity.adapter.notifyDataSetChanged()
                }

                override fun onUpdateClicked(position: Int) {
                    val intent = Intent(this@MainActivity, AddUserActivity::class.java)
                    intent.putExtra("position", position)
                    intent.putExtra("user", userList[position])
                    startActivity(intent)
                }
            })
            adapter = this@MainActivity.adapter
        }
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}