package com.paulconsulting.pcgandroidinterview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.data.Author;

import java.util.ArrayList;

public class AuthorDetailsRecyclerViewAdapter extends RecyclerView.Adapter<AuthorDetailsRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = AuthorDetailsRecyclerViewAdapter.class.getSimpleName();

    /// References

    // Represents the data in the Fragment
    private ArrayList<Author> data;

    // Used in the constructor, which will subsequently be used in the onCreateViewHolder method to inflate the layout item xml file
    private Context context;

    public AuthorDetailsRecyclerViewAdapter(ArrayList<Author> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AuthorDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_authors_details_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AuthorDetailsRecyclerViewAdapter.ViewHolder holder, int position) {

        Author currentAuthor = data.get(position);

        holder.bindTo(currentAuthor);

    }

    @Override
    public int getItemCount() {

        return data.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView authorBookTextViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the Views for the ViewHolder
            authorBookTextViewItem = itemView.findViewById(R.id.author_book_text_view_item);

        }

        public void bindTo(Author currentAuthor) {


            Log.d(LOG_TAG, "Current Author Works: " + currentAuthor.getWorks());


            authorBookTextViewItem.setText(currentAuthor.getWorks().toString());



        }



    }
}

