package uz.devosmon.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.devosmon.shoppingapp.R
import uz.devosmon.shoppingapp.models.ShoppingItems

class ShoppingRVAdapter(
    var list: List<ShoppingItems>,
    val shoppingItemClickInterface: ShoppingItemClickInterface
) : RecyclerView.Adapter<ShoppingRVAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)


    }


    interface ShoppingItemClickInterface {
        fun onItemClick(shoppingItems: ShoppingItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_rv_item, parent, false);

        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        holder.nameTV.text = list.get(position).itemName
        holder.quantityTV.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "$. "+list.get(position).itemPrice.toString()
        val itemTotal:Int = list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text = "$. "+itemTotal.toString()

        holder.deleteIV.setOnClickListener {
            shoppingItemClickInterface.onItemClick(list.get(position))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}