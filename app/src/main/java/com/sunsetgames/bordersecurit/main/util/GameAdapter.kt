package com.sunsetgames.bordersecurit.main.util

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sunsetgames.bordersecurit.first.First
import com.sunsetgames.bordersecurit.second.Second
import com.sunsetgames.bordersecurit.third.Third


class GameAdapter (gamesList: List<GameClass>):
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {



    val gamesListi : List<GameClass> = gamesList


   inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){
       var imagePoster: ImageView
       var titleGame: TextView
       var textCategory: TextView
       init {
           imagePoster = itemView.findViewById(com.sunsetgames.bordersecurit.R.id.imageGame)
           titleGame = itemView.findViewById(com.sunsetgames.bordersecurit.R.id.titleGame)
           textCategory  = itemView.findViewById(com.sunsetgames.bordersecurit.R.id.textCategory)


       }

       fun setGame(game: GameClass) {
           imagePoster.setImageResource(game.poster)
           titleGame.text = game.name
           textCategory.text = game.category
       }






   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.sunsetgames.bordersecurit.R.layout.item_container_game, parent, false)
        return GameViewHolder(view)
    }



    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.setGame(gamesListi[position])


    }

    override fun getItemCount(): Int {
        return gamesListi.size
    }
}



