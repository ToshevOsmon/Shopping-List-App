package uz.devosmon.shoppingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import uz.devosmon.shoppingapp.adapter.ShoppingRVAdapter
import uz.devosmon.shoppingapp.database.ShoppingDatabase
import uz.devosmon.shoppingapp.models.ShoppingItems
import uz.devosmon.shoppingapp.mvvm.ShoppingRepository
import uz.devosmon.shoppingapp.mvvm.ShoppingViewModel
import uz.devosmon.shoppingapp.mvvm.ShoppingViewModelFactory

class MainActivity : AppCompatActivity(), ShoppingRVAdapter.ShoppingItemClickInterface {

    lateinit var itemRv: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<ShoppingItems>
    lateinit var shoppingRVAdapter: ShoppingRVAdapter
    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemRv = findViewById(R.id.idRvItems)
        addFAB = findViewById(R.id.idFABAdd)

        list = ArrayList<ShoppingItems>()
        shoppingRVAdapter = ShoppingRVAdapter(list, this)
        itemRv.layoutManager = LinearLayoutManager(this)
        itemRv.adapter = shoppingRVAdapter

        val shoppingRepository = ShoppingRepository(ShoppingDatabase(this))
        val factory = ShoppingViewModelFactory(shoppingRepository)
        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)
        viewModel.getAllItems().observe(this, Observer {
            shoppingRVAdapter.list = it
            shoppingRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            openDialog()
        }

    }

    private fun openDialog() {


        val dialog = Dialog(this)
        dialog.setContentView(R.layout.shopping_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdtName = dialog.findViewById<TextInputEditText>(R.id.idEdtItemName)
        val itemEdtQuantity = dialog.findViewById<TextInputEditText>(R.id.idEdtItemQuantity)
        val itemEdtPrice = dialog.findViewById<TextInputEditText>(R.id.idEdtItemPrice)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName: String = itemEdtName.text.toString()
            val itemQuantity: String = itemEdtQuantity.text.toString()
            val itemPrice: String = itemEdtPrice.text.toString()
            Log.d("TTT", ": $itemName , $itemQuantity, $itemPrice ")

            val quantity: Int = itemQuantity.toInt()
            val price: Int = itemPrice.toInt()

            if (!itemName.isEmpty() && !itemPrice.isEmpty() && !itemQuantity.isEmpty()) {

                Log.d("TTT", ": $itemName , ${quantity.toString()}, ${price.toString()} ")

                val items = ShoppingItems(itemName, quantity, price)

                viewModel.insert(items)
                Toast.makeText(this, "Item Inserted...", Toast.LENGTH_SHORT).show()
                shoppingRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.show()


    }

    override fun onItemClick(shoppingItems: ShoppingItems) {
        viewModel.delete(shoppingItems)
        shoppingRVAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()

    }
}