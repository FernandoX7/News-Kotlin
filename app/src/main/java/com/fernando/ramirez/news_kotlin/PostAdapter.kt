package com.fernando.ramirez.news_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item_layout.view.*

/**
 * Created by fernando on 7/19/17.
 */

class PostAdapter(val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var mContext: Context? = null

    // Returns the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.post_item_layout, parent, false)
        return ViewHolder(view)
    }

    // Bind to the data on the list
    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        holder.bindItems(postList[position])
    }

    // Returns the size of the list
    override fun getItemCount(): Int {
        return postList.size
    }

    // Holds the list view itself
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(post: Post) {
            Picasso.with(itemView.context).load(post.imageURL).into(itemView.postImage)
            itemView.titleTextView.text = post.title
            itemView.descriptionTextView.text = post.description
        }
    }
}