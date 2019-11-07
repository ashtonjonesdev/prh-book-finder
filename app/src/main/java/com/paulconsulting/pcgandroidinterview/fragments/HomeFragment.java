package com.paulconsulting.pcgandroidinterview.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

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
import com.paulconsulting.pcgandroidinterview.adapters.NetworkUtils;
import com.paulconsulting.pcgandroidinterview.background.GetJSONDataWorker;
import com.paulconsulting.pcgandroidinterview.data.Author;
import com.paulconsulting.pcgandroidinterview.data.AuthorViewModel;

import java.util.ArrayList;


/**
 * The Fragment that will be shown as the first destination when the user opens the app
 */
public class HomeFragment extends Fragment {

    /// Tags
    public static final String LOG_TAG = HomeFragment.class.getSimpleName();

    private static final String AUTHORS_FOUND_JSON_WORKER_TAG = "authorsFoundJsonWork";

    /// Keys
    public static final String QUERY_PARAM_FIRST_NAME_KEY = "firstName";

    public static final String QUERY_PARAM_LAST_NAME_KEY = "lastName";

    /// References

    /// TextViews
    private MaterialTextView authorTextView;

    // First Edit Text field for author first name
    private TextInputLayout authorFirstNameTextInputLayout;
    private TextInputEditText authorFirstNameTextInputEditText;

    // Second edit text field for author last name
    private TextInputLayout authorLastNameTextInputLayout;
    private TextInputEditText authorLastNameTextInputEditText;


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


        authorFirstNameTextInputLayout = getView().findViewById(R.id.author_first_name_text_input_layout);

        authorFirstNameTextInputEditText = getView().findViewById(R.id.author_first_name_text_input_edit_text);


        authorLastNameTextInputLayout = getView().findViewById(R.id.author_last_name_text_input_layout);

        authorLastNameTextInputEditText = getView().findViewById(R.id.author_last_name_text_input_edit_text);


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

                // Get the first name entered

                String queryFirstName = authorFirstNameTextInputEditText.getText().toString();

                // Get the last name entered

                String queryLastName = authorLastNameTextInputEditText.getText().toString();







                /**
                 *
                 * Get the text entered in the edit text views
                 *
                 */

                if(queryFirstName.length() > 0 && queryLastName.length() > 0) {



                    Log.d(LOG_TAG, "First Name Entered: " + queryFirstName);



                    Log.d(LOG_TAG, "Last name entered: " + queryLastName);






                }

                else {

                    Toast.makeText(getContext(), "Please enter a First and Last Name", Toast.LENGTH_SHORT).show();

                    return;

                }




                /**
                 *
                 * Set the Authors Found Text View to visible
                 *
                 */



                authorsFoundTextView.setVisibility(View.VISIBLE);

//               /// Create a Worker that will find the authors

                // Create the Worker and give it parameters

                Data queryParams = new Data.Builder().putString(QUERY_PARAM_FIRST_NAME_KEY, queryFirstName).putString(QUERY_PARAM_LAST_NAME_KEY, queryLastName).build();


                OneTimeWorkRequest foundAuthorsWorkRequest = new OneTimeWorkRequest.Builder(GetJSONDataWorker.class).setInputData(queryParams).addTag(AUTHORS_FOUND_JSON_WORKER_TAG).build();


                // Hand of the Worker to WorkManager

                WorkManager.getInstance(getContext()).enqueue(foundAuthorsWorkRequest);

                // Add an observer to the Worker to perform logic when the work is finished
                // TODO: Figure out exactly what this is doing and how LiveData is involved
                WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(foundAuthorsWorkRequest.getId()).observe(HomeFragment.this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            Toast.makeText(getContext(), "Work finished!", Toast.LENGTH_SHORT).show();

                            // Get the JSON String after the work has finished
                            String authorsJSONResponse = GetJSONDataWorker.getAuthorsJSONResponse();

                            Log.d(LOG_TAG, "JSON author response received from Worker: " + authorsJSONResponse);

                            // Use that JSON String to pass it to the parseJSON method in NetworkUtils
                            // Will return an ArrayList of Authors

                            ArrayList<Author> foundAuthors = NetworkUtils.parseAuthorsJSON(authorsJSONResponse);

                            Log.d(LOG_TAG, "Found Authors ArrayList size: " + foundAuthors.size());

                            for(Author author: foundAuthors) {

                                Log.d(LOG_TAG, "Found Author: " + author.getAuthorfirst() + " " + author.getAuthorlast());

                            }

                            // TODO: Double check on this logic

                            // Update the data in the Adapter
                            data.clear();

                            data.addAll(foundAuthors);

                            adapter.notifyDataSetChanged();



                        }
                    }
                });

            }
        });


    }

    public void resetData() {


        // Get the fetched data
        ArrayList<Author> fetchedData = viewModel.getFetchedData();

        if(fetchedData != null) {

            Log.d(LOG_TAG, "Fetched data: " + fetchedData);

            data.clear();

            data = fetchedData;


            // Add the fetched data to the data for the RecyclerView
            data.addAll(fetchedData);

            // Notify the adapter to be updated with new data
            adapter.notifyDataSetChanged();

        }

        else {

            Log.d(LOG_TAG, "Fetched Data is null!");

        }


    }

}
