package com.paulconsulting.pcgandroidinterview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.activities.MainActivity;
import com.paulconsulting.pcgandroidinterview.data.Author;
import com.paulconsulting.pcgandroidinterview.data.AuthorViewModel;
import com.paulconsulting.pcgandroidinterview.fragments.HomeFragment;

import java.util.ArrayList;

public class FoundAuthorsRecyclerViewAdapter extends RecyclerView.Adapter<FoundAuthorsRecyclerViewAdapter.ViewHolder> {

    private final String LOG_TAG = FoundAuthorsRecyclerViewAdapter.class.getSimpleName();

    /// References

    // Represents the data in the Fragment
    private ArrayList<Author> data;

    // Used in the constructor, which will subsequently be used in the onCreateViewHolder method to inflate the layout item xml file
    private Context context;


    public FoundAuthorsRecyclerViewAdapter(Context context, ArrayList<Author> data) {

        this.context = context;

        this.data = data;

    }

    @NonNull
    @Override
    public FoundAuthorsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_authors_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull FoundAuthorsRecyclerViewAdapter.ViewHolder holder, int position) {

        Author currentAuthor = data.get(position);

        holder.bindTo(currentAuthor);

    }

    @Override
    public int getItemCount() {

        return data.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView authorFullNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            // Initialize the Views for the ViewHolder
            authorFullNameTextView = itemView.findViewById(R.id.author_full_name_text_view_item);

            /**
             *
             * Navigate to the Author Details screen when an Author is clicked
             *
             * @param v
             */


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // If a valid Author was not found, do not navigate to the Author details screen
                    if(data.size() == 0) {

                        Log.d(LOG_TAG, "Cannot Navigate because no Author was found");

                        // break from the method
                        return;

                    }

                    Navigation.findNavController(v).navigate(R.id.dest_author_details);

                }
            });

        }

        public void bindTo(Author currentAuthor) {

            authorFullNameTextView.setText(currentAuthor.getAuthorfirst() + " " + currentAuthor.getAuthorlast());


        }

    }
}
