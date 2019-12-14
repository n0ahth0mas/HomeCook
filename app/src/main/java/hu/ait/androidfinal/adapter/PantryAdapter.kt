package hu.ait.androidfinal.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.androidfinal.R
import hu.ait.androidfinal.data.Ingredient
import hu.ait.androidfinal.data.Meal
import hu.ait.androidfinal.fragments.RecipeViewModel
import kotlinx.android.synthetic.main.pantry_list_item.view.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class PantryAdapter(context: Context) : RecyclerView.Adapter<PantryAdapter.ViewHolder>() {
    val context = context
    var pantryList = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PantryAdapter.ViewHolder {
        val ingredientCard = LayoutInflater.from(context).inflate(
            R.layout.pantry_list_item, parent, false

        )

        return ViewHolder(ingredientCard)
    }

    override fun getItemCount(): Int {
        return pantryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pantryItem = pantryList.get(holder.adapterPosition)

        holder.tvPantryIngredient.text = pantryItem.name
        holder.cbInclude.isChecked = pantryItem.include
        holder.tvPantryQuant.text = (pantryItem.quantity + " " + pantryItem.unit)
        holder.tvType.text = spinnerTypeMap(pantryItem.type)
        Log.d("type", pantryItem.type.toString())
    }

    fun spinnerTypeMap(position: Int): String{
        when (position){
            0 -> return "None"
            1 -> return "Protein"
            2 -> return "Carb"
            3 -> return "Vegetable"
            4 -> return "Staple"
            else -> return "Fruit"
        }
    }

    fun replaceItems(pantry: MutableList<Ingredient>) {
        this.pantryList = pantry
        notifyDataSetChanged()
    }

    fun addItem(item: Ingredient){
        pantryList.add(item)
        notifyItemInserted(pantryList.lastIndex)
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvPantryIngredient = itemView.tvPantryIngredient
        val cbInclude = itemView.cbInclude
        val tvPantryQuant = itemView.tvPantryQuant
        val tvType = itemView.tvType
    }
}