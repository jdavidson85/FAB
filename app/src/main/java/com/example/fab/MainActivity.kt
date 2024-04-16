package com.example.fab

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<String>()
    private lateinit var itemListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListTextView = findViewById(R.id.item_list_text_view)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Add item to list and update UI
            addItemToList("item---------------------")
        }

        itemListTextView.setOnClickListener {
            // Add item to list and update UI
            addItemToList("item--------------------")
        }
    }

    private fun addItemToList(item: String) {
        // Add item to the list
        itemList.add(item)
        // Update TextView to display list of items
        updateItemListTextView()
        // Show a message indicating the item is added
        showSnackbar("Item added: $item", "Undo") {
            // Undo action: remove the last added item
            if (itemList.isNotEmpty()) {
                itemList.removeAt(itemList.size - 1)
                updateItemListTextView()
                showSnackbar("Item removed: $item") // No action provided
            }
        }
    }

    private fun updateItemListTextView() {
        // Update TextView to display list of items
        val itemListText = itemList.joinToString(separator = "\n")
        itemListTextView.text = "$itemListText"
    }

    private fun showSnackbar(message: String, actionText: String? = null, action: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        if (actionText != null && action != null) {
            snackbar.setAction(actionText) { action.invoke() }
        }
        snackbar.show()
    }
}