package com.paulconsulting.pcgandroidinterview.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.adapters.FoundAuthorsRecyclerViewAdapter;
import com.paulconsulting.pcgandroidinterview.data.Author;
import com.paulconsulting.pcgandroidinterview.data.AuthorViewModel;

import java.util.ArrayList;


/**
 * The Fragment that will be shown as the first destination when the user opens the app
 */
public class HomeFragment extends Fragment {

    public static final String LOG_TAG = HomeFragment.class.getSimpleName();

    /// References

    /// TextViews
    private MaterialTextView authorTextView;
    private TextInputLayout authorTextInputLayout;
    private TextInputEditText authorTextInputEditText;

    private MaterialTextView authorsFoundTextView;

    /// Button
    private MaterialButton searchMaterialButton;

    /// RecyclerView
    private RecyclerView recyclerView;
    private FoundAuthorsRecyclerViewAdapter adapter;

    /// Data: List of Authors
    private ArrayList<Author> data = new ArrayList<>();

    /// ViewModel
    public static AuthorViewModel viewModel;





    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /// Initialize the data list with an empty list, so there is no null pointer exception when initializing the data
//        data = new ArrayList<>();


        /// Assign the Views

        authorTextView = getView().findViewById(R.id.author_text_view);

        authorTextInputLayout = getView().findViewById(R.id.author_text_input_layout);

        authorTextInputEditText = getView().findViewById(R.id.author_text_input_edit_text);

        searchMaterialButton = getView().findViewById(R.id.search_material_button);

        authorsFoundTextView = getView().findViewById(R.id.authors_found_text_view);

        recyclerView = getView().findViewById(R.id.authors_found_recycler_view);






        /// Setup the ViewModel
        viewModel = ViewModelProviders.of(this).get(AuthorViewModel.class);


        // Get the data from the Viewmodel, which is an ArrayList of Authors
        data = viewModel.getPlaceholderData();


        /// Setup the RecyclerView and Adapter

        adapter = new FoundAuthorsRecyclerViewAdapter(getContext(), data);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);



        searchMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *
                 * Set the Authors Found Text View to visible
                 *
                 */

                authorsFoundTextView.setVisibility(View.VISIBLE);


                Toast.makeText(getContext(), "Search for Author's Works!", Toast.LENGTH_SHORT).show();


                resetData();

            }
        });


    }

    public void resetData() {


        // Clear the old data
        data.clear();

        // Get the fetchedData
        ArrayList<Author> fetchedData = viewModel.fetchData();

        // Add the Fetched data to the now empty ArrayList
        data.addAll(fetchedData);



        // Notify the RecyclerView of the updated data
        adapter.notifyDataSetChanged();

        for(Author author: data) {

            Log.d(LOG_TAG, "Author: " + author.getAuthorFirst() + " " + author.getAuthorLast());

        }


    }


}
