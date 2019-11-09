package com.paulconsulting.pcgandroidinterview.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class AuthorViewModel extends AndroidViewModel {



    // Cached copy of fetched data
    private ArrayList<Author> fetchedData;

    public AuthorViewModel(@NonNull Application application) {
        super(application);

        fetchedData = new ArrayList<>();
    }

    public ArrayList<Author> getFetchedData() {
        return fetchedData;
    }

    public void setFetchedData(ArrayList<Author> fetchedData) {
        this.fetchedData = fetchedData;
    }
}
