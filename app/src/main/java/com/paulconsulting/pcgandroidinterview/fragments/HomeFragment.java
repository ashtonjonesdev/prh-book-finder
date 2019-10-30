package com.paulconsulting.pcgandroidinterview.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private RecyclerView authorsFoundRecyclerView;
    private FoundAuthorsRecyclerViewAdapter authorsFoundRecyclerViewAdapter;

    /// Data: List of Authors
    private ArrayList<Author> authorsData;





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
        authorsData = new ArrayList<>();


        /// Assign the Views

        authorTextView = getView().findViewById(R.id.author_text_view);

        authorTextInputLayout = getView().findViewById(R.id.author_text_input_layout);

        authorTextInputEditText = getView().findViewById(R.id.author_text_input_edit_text);

        searchMaterialButton = getView().findViewById(R.id.search_material_button);

        authorsFoundTextView = getView().findViewById(R.id.authors_found_text_view);

        authorsFoundRecyclerView = getView().findViewById(R.id.authors_found_recycler_view);

        /// Setup the RecyclerView and Adapter

        authorsFoundRecyclerViewAdapter = new FoundAuthorsRecyclerViewAdapter(authorsData, getContext());

        authorsFoundRecyclerView.setAdapter(authorsFoundRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        authorsFoundRecyclerView.setLayoutManager(linearLayoutManager);

        intializeData();

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




            }
        });


    }

    private void intializeData() {

        // TODO: REMOVE THIS
        for(int i = 0; i < 5; i++) {

//            Integer authorNum = i;
//
//            String authorNumber = authorNum.toString();

            Author currentAuthor = new Author("Author", "#" + i);

            authorsData.add(currentAuthor);

            /**
             *
             * Logging/Debugging: Make sure the Author was added to the list
             *
             */

            String currentFirstName = currentAuthor.getAuthorFirst();

            String currentLastName = currentAuthor.getAuthorLast();

            Log.d(LOG_TAG, "Added Author: " + currentFirstName + " " + currentLastName);

        }

    }
}
