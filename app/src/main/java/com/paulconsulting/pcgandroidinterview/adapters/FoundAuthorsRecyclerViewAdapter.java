package com.paulconsulting.pcgandroidinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.data.Author;

import java.util.ArrayList;
import java.util.List;

public class FoundAuthorsRecyclerViewAdapter extends RecyclerView.Adapter<FoundAuthorsRecyclerViewAdapter.ViewHolder> {

    /// References

    // Represents the data in the Fragment
    private ArrayList<Author> authorsFoundList;

    // Used in the constructor, which will subsequently be used in the onCreateViewHolder method to inflate the layout item xml file
    private Context context;

    public FoundAuthorsRecyclerViewAdapter(ArrayList<Author> authorsFoundList, Context context) {
        this.authorsFoundList = authorsFoundList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoundAuthorsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_authors_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull FoundAuthorsRecyclerViewAdapter.ViewHolder holder, int position) {

        Author currentAuthor = authorsFoundList.get(position);

        holder.bindTo(currentAuthor);

    }

    @Override
    public int getItemCount() {

        return authorsFoundList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView authorFullNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            // Initialize the Views for the ViewHolder
            authorFullNameTextView = itemView.findViewById(R.id.author_full_name_text_view_item);

            // Set a click listener on the entire item view that will launch the Author Details destination
            itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_dest_home_fragment_to_dest_author_details));

        }

        public void bindTo(Author currentAuthor) {

            // TODO: FIX THIS TO USE THE CURRENT AUTHOR TO GET THE RIGHT DATA FROM THE API
            authorFullNameTextView.setText(currentAuthor.getAuthorFirst() + " " + currentAuthor.getAuthorLast());

        }


        @Override
        public void onClick(View v) {

            Toast.makeText(context, "Clicked an Author!", Toast.LENGTH_SHORT).show();



        }
    }
}
