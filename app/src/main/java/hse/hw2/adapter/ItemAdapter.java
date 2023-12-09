package hse.hw2.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hse.hw2.R;
import hse.hw2.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_layout,
                parent,
                false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemTextView.setText(item.getItemName());

        holder.deleteImageView.setOnClickListener(v -> {
            itemList.remove(position);
            notifyDataSetChanged();
        });

        holder.itemView.setOnClickListener(v -> {
            showEditDialog(item, position);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showEditDialog(Item item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Редактировать товар");

        EditText editText = new EditText(context);
        editText.setText(item.getItemName());
        builder.setView(editText);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String editedName = editText.getText().toString().trim();
            if (!editedName.isEmpty()) {
                itemList.get(position).setItemName(editedName);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Отмена", null);

        builder.show();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        ImageView deleteImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
        }
    }
}