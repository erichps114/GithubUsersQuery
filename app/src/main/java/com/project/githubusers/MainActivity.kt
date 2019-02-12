package com.project.githubusers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.project.githubusers.model.UserModel
import com.project.githubusers.uicomponent.ResultsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mList = arrayListOf(
        UserModel("Erich","https://avatars0.githubusercontent.com/u/36195560?v=4"),
        UserModel("Pratama","https://avatars0.githubusercontent.com/u/1018543?v=4"))
    private val mAdapter by lazy { ResultsAdapter(mList, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recylerView.adapter = mAdapter
    }
}
