package com.fernando.ramirez.news_kotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.beust.klaxon.*
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var mPosts = ArrayList<Post>()
    var mAdapter = PostAdapter(mPosts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Setup RecyclerView
        mNewsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        // Adapter
        mNewsRecyclerView.adapter = mAdapter

//        initFakeData()
        getArticles()

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
//        mPosts.add(Post("First News Headline", "Lorem ipsum dolor sit amet, one nam no scaevola adolescens.", randomImageURL + "?random=1"))
//        mPosts.add(Post("Second News Headline", "Lorem ipsum dolor sit amet, two nam no scaevola adolescens.", randomImageURL + "?random=2"))
//        mPosts.add(Post("Third News Headline", "Lorem ipsum dolor sit amet, three nam no scaevola adolescens.", randomImageURL + "?random=3"))
//        mPosts.add(Post("Fourth News Headline", "Lorem ipsum dolor sit amet, four nam no scaevola adolescens.", randomImageURL + "?random=4"))
//        mPosts.add(Post("Fifth News Headline", "Lorem ipsum dolor sit amet, five no scaevola adolescens.", randomImageURL + "?random=5"))
        return mPosts
    }

    fun getArticles() {
        Fuel.get("https://newsapi.org/v1/articles?source=the-verge&sortBy=latest&apiKey=" + Constants.newsApiKey).responseString { request, response, result ->
            result.fold({ data ->
                parseArticlesJsonResponse(data)
            }, { error ->
                print("Error fetching news articles $error")
            })
        }
    }

    fun parseArticlesJsonResponse(data: String) {
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(data)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        json.array<JsonObject>("articles")!!.map {
            var title = it.string("title")!!
            var description = it.string("description")!!
            var imageURL = it.string("urlToImage")!!
            mPosts.add(Post(title, description, imageURL))
        }
        refreshRecyclerView()
    }

    fun refreshRecyclerView() {
        mAdapter.notifyDataSetChanged()
    }

}
