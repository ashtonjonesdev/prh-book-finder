package com.paulconsulting.pcgandroidinterview.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.adapters.AuthorDetailsRecyclerViewAdapter;
import com.paulconsulting.pcgandroidinterview.data.Author;
import com.paulconsulting.pcgandroidinterview.data.AuthorViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorDetailsFragment extends Fragment {

    private static final String LOG_TAG = AuthorDetailsFragment.class.getSimpleName();

    /// References

    /// TextViews
    private MaterialTextView authorDetailsAuthorTextView;
    private MaterialTextView authorDetailsAuthorSpotlightTextView;
    private MaterialTextView authorDetailsBooksTextView;

    /// RecyclerView
    private RecyclerView recyclerView;
    private AuthorDetailsRecyclerViewAdapter adapter;

    // ViewModel instance
    private AuthorViewModel viewModel;

    // Data
    private ArrayList<Author> data = new ArrayList<>();





    public AuthorDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        /// Initialize the Views

        authorDetailsAuthorTextView = getView().findViewById(R.id.author_details_author_text_view);

        authorDetailsAuthorSpotlightTextView = getView().findViewById(R.id.author_details_author_spotlight_text_view);

        authorDetailsBooksTextView = getView().findViewById(R.id.author_details_books_text_view);

        recyclerView = getView().findViewById(R.id.author_details_books_recycler_view);


        // Initialize the ViewModel
        // Use the Activity context to share the same ViewModel instance amongst multiple Fragments
        viewModel = ViewModelProviders.of(getActivity()).get(AuthorViewModel.class);

        // Set the data to the current data in the ViewModel
        data = viewModel.getFetchedData();




        /// Setup the RecyclerView and Adapter

        adapter = new AuthorDetailsRecyclerViewAdapter(data, getContext());

        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);






        // Handle the case if there is not a valid author found
        if(data.size() == 0) {

            authorDetailsAuthorTextView.setText("No author found");

            Toast.makeText(getContext(), "Could not find an author!", Toast.LENGTH_SHORT).show();


        }


        // Set the data of the TextViews
        if(data.size() != 0) {

            String authorFullName = data.get(0).getAuthorfirst() + " " + data.get(0).getAuthorlast();

            String authorSpotlight = data.get(0).getSpotlight();

            ArrayList<String> authorWorks = data.get(0).getWorks();


            // If there are values for all Author fields, set the text views
            if (authorFullName != null && authorSpotlight != null && authorWorks.size() > 0) {

                authorDetailsAuthorTextView.setText(authorFullName);

                authorDetailsAuthorSpotlightTextView.setText(authorSpotlight);

//                /// Update the data in the adapter
//
//                // Copy of the data for the adapter
//                ArrayList<Author> foundAuthors = data;
//
//                // Update the data in the adapter
//                data.clear();
//
//                data.addAll(foundAuthors);
//
//                adapter.notifyDataSetChanged();






                // Get each work from the authorWorks Array

//                String concatenatedWorks = Arrays.toString(authorWorks.toArray());
//
//                Log.d(LOG_TAG, "Author Works: " + concatenatedWorks);
//
//                if(concatenatedWorks != null) {
//
//                    authorDetailsBooksTextView.setText(concatenatedWorks);
//
//                }



            }


        }








    }

}
