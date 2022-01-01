package com.aqwas.com.sa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aqwas.com.sa.R
import com.aqwas.com.sa.model.TrendingRepositoriesModel
import com.squareup.picasso.Picasso


class TrendingReposAdapter : RecyclerView.Adapter<TrendingReposAdapter.MyViewHolder>() {

    private var repositoriesList = ArrayList<TrendingRepositoriesModel>()
    fun setUpdatedRepos(items: List<TrendingRepositoriesModel>) {
        this.repositoriesList = items as ArrayList<TrendingRepositoriesModel>
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val authorImg: ImageView = itemView.findViewById(R.id.img_repo)
        private val authorName: TextView = itemView.findViewById(R.id.txt_author_name)
        private val repoName: TextView = itemView.findViewById(R.id.txt_repo_name)
        private val langName: TextView = itemView.findViewById(R.id.txt_language)
        private val starsName: TextView = itemView.findViewById(R.id.txt_stars)
        private val forkName: TextView = itemView.findViewById(R.id.txt_fork)
        private val description: TextView = itemView.findViewById(R.id.txt_description)
        var linearClicked: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var linearDescription: LinearLayout = itemView.findViewById(R.id.lin_desc)
        var linearFork: LinearLayout = itemView.findViewById(R.id.lin_fork)
        fun bind(data: TrendingRepositoriesModel) {
            authorName.text = data.author.toString()
            repoName.text = data.name.toString()
            langName.text = data.language.toString()
            starsName.text = data.stars.toString()
            forkName.text = data.forks.toString()
            description.text = data.description.toString()
            val imgUrl = data.avatar.toString()
            Picasso.get()
                .load(imgUrl)
                .into(authorImg)
//            linearClicked.setOnClickListener({
//                if (repositoriesList.get(position).is)
//           })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repositories_recycler_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(repositoriesList[position])
        var isExpanded:Boolean = repositoriesList[position].expandable
        holder.linearDescription.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.linearFork.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.linearClicked.setOnClickListener {
           val expandable = repositoriesList[position]
            expandable.expandable = !expandable.expandable
            notifyItemChanged(position)

        }
    }

    override fun getItemCount(): Int {
        return repositoriesList.size
    }
}