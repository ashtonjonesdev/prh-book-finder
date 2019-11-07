package com.paulconsulting.pcgandroidinterview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulconsulting.pcgandroidinterview.R;
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

    private AuthorViewModel viewModel;





    public FoundAuthorsRecyclerViewAdapter(Context context, ArrayList<Author> data) {

        this.context = context;

        this.viewModel = HomeFragment.viewModel;

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



    /**
     *
     * Called when the data changes to update the data in the Adapter
     *
     * Also called when the app is first opened
     *
     */

    public void setData(ArrayList<Author> data) {

        this.data = data;

        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView authorFullNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            // Initialize the Views for the ViewHolder
            authorFullNameTextView = itemView.findViewById(R.id.author_full_name_text_view_item);

            // Get the data (ArrayList of Authors) from the ViewModel
//            data = viewModel.getPlaceholderData();

            // Set a click listener on the entire item view that will launch the Author Details destination
//            itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_dest_home_fragment_to_dest_author_details));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getLayoutPosition();

                    Toast.makeText(context, "Clicked Author " + position, Toast.LENGTH_SHORT ).show();

                }
            });

        }

        public void bindTo(Author currentAuthor) {

            // TODO: FIX THIS TO USE THE CURRENT AUTHOR TO GET THE RIGHT DATA FROM THE API
            authorFullNameTextView.setText(currentAuthor.getAuthorfirst() + " " + currentAuthor.getAuthorlast());





        }


        @Override
        public void onClick(View v) {

            Toast.makeText(context, "Clicked an Author!", Toast.LENGTH_SHORT).show();



        }
    }
}
