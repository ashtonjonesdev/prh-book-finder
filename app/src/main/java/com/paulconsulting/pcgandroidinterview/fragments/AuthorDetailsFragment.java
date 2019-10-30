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

import java.lang.reflect.Array;
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
    private ArrayList<Author> authorBooks;



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
        authorBooks = new ArrayList<>();



        /// Initialize the Views

        authorDetailsAuthorTextView = getView().findViewById(R.id.author_details_author_text_view);

        authorDetailsAuthorSpotlightTextView = getView().findViewById(R.id.author_details_author_spotlight_text_view);

        authorDetailsBooksTextView = getView().findViewById(R.id.author_details_books_text_view);

        authorDetailsBooksRecyclerView = getView().findViewById(R.id.author_details_books_recycler_view);

        /// Setup the RecyclerView and Adapter

        authorDetailsRecyclerViewAdapter = new AuthorDetailsRecyclerViewAdapter(authorBooks, getContext());

        authorDetailsBooksRecyclerView.setAdapter(authorDetailsRecyclerViewAdapter);

        authorDetailsBooksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        initializeData();







    }

    private void initializeData() {

        for(int i = 1; i < 5; i++) {

            ArrayList<String> currentBooks = new ArrayList<>();

            currentBooks.add("Book #" + i);

            Author currentAuthor = new Author("Author", "#" + i, "Author Spotlight #" + i, currentBooks);

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
