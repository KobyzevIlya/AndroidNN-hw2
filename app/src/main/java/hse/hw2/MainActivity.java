package hse.hw2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hse.hw2.adapter.ItemAdapter;
import hse.hw2.model.Item;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;
    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private EditText editText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

        addButton = findViewById(R.id.addButton);
        editText = findViewById(R.id.editText);

        addButton.setOnClickListener(v -> addItem());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addItem() {
        String itemName = editText.getText().toString().trim();

        if (!itemName.isEmpty()) {
            itemList.add(new Item(itemName));
            itemAdapter.notifyDataSetChanged();
            editText.setText("");
        }
    }
}