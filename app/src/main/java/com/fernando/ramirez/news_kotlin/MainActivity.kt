package com.fernando.ramirez.news_kotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var randomImageURL = "http://loremflickr.com/600/400"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Setup RecyclerView
        mNewsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        // Adapter
        val adapter = PostAdapter(initFakeData())
        mNewsRecyclerView.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initFakeData() : ArrayList<Post> {
        val posts = ArrayList<Post>()
        posts.add(Post("First News Headline", "Lorem ipsum dolor sit amet, one nam no scaevola adolescens.", randomImageURL + "?random=1"))
        posts.add(Post("Second News Headline", "Lorem ipsum dolor sit amet, two nam no scaevola adolescens.", randomImageURL + "?random=2"))
        posts.add(Post("Third News Headline", "Lorem ipsum dolor sit amet, three nam no scaevola adolescens.", randomImageURL + "?random=3"))
        posts.add(Post("Fourth News Headline", "Lorem ipsum dolor sit amet, four nam no scaevola adolescens.", randomImageURL + "?random=4"))
        posts.add(Post("Fifth News Headline", "Lorem ipsum dolor sit amet, five no scaevola adolescens.", randomImageURL + "?random=5"))
        return posts
    }
}
