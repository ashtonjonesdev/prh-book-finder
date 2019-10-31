package com.paulconsulting.pcgandroidinterview.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textview.MaterialTextView;
import com.paulconsulting.pcgandroidinterview.R;
import com.paulconsulting.pcgandroidinterview.adapters.AuthorDetailsRecyclerViewAdapter;
import com.paulconsulting.pcgandroidinterview.data.Author;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorDetailsFragment extends Fragment {

    /// References

    /// TextViews
    private MaterialTextView authorDetailsAuthorTextView;
    private MaterialTextView authorDetailsAuthorSpotlightTextView;
    private MaterialTextView authorDetailsBooksTextView;

    /// RecyclerView
    private RecyclerView authorDetailsBooksRecyclerView;
    private AuthorDetailsRecyclerViewAdapter authorDetailsRecyclerViewAdapter;

    /// Data: List of Authors
    private ArrayList<Author> authorDetails;

    // Array of Author Books
    private ArrayList<String> currentBooks;



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

        // Intialize the ArrayList to an empty list to avoid null pointer exception
        authorDetails = new ArrayList<>();

        currentBooks = new ArrayList<>();



        /// Initialize the Views

        authorDetailsAuthorTextView = getView().findViewById(R.id.author_details_author_text_view);

        authorDetailsAuthorSpotlightTextView = getView().findViewById(R.id.author_details_author_spotlight_text_view);

        authorDetailsBooksTextView = getView().findViewById(R.id.author_details_books_text_view);

        authorDetailsBooksRecyclerView = getView().findViewById(R.id.author_details_books_recycler_view);

        /// Setup the RecyclerView and Adapter

        authorDetailsRecyclerViewAdapter = new AuthorDetailsRecyclerViewAdapter(authorDetails, getContext());

        authorDetailsBooksRecyclerView.setAdapter(authorDetailsRecyclerViewAdapter);

        authorDetailsBooksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        initializeData();







    }

    private void initializeData() {

        for(int i = 0; i < 4; i++) {

            currentBooks.add("Book " + i + "Book " + i + 1 );

            Author currentAuthor = new Author("Author", "#" + i, "Author Spotlight #" + i, currentBooks);

            authorDetails.add(currentAuthor);

            /**
             *
             * Set the text of the all the Views not in the RecyclerView
             *
             */

            authorDetailsAuthorTextView.setText(currentAuthor.getAuthorFirst() + " " + currentAuthor.getAuthorLast());

            authorDetailsAuthorSpotlightTextView.setText(currentAuthor.getAuthorSpotlight());





        }

    }
}
