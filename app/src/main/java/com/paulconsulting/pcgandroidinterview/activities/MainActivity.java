package com.paulconsulting.pcgandroidinterview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.paulconsulting.pcgandroidinterview.R;

public class MainActivity extends AppCompatActivity {

    //// Create references

    /// Navigation
    private NavHostFragment navHostFragment;

    private NavController navController;

    /// Top app toolbar
    private MaterialToolbar topAppToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// TOP APP BAR

        // Get the Top App Bar Toolbar
        topAppToolbar = findViewById(R.id.top_app_bar_toolbar);

        // Apply the toolbar as the Top app bar
        setSupportActionBar(topAppToolbar);


        /// Setup Navigation

        /**
         *
         * The NavController handles the swapping of destinations and is used to set up global navigation components, such as top app bars, bottom navigation, and navigation drawers
         *
         */
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();

        /// Setup the Top App Toolbar with the Navigation Library

        NavigationUI.setupWithNavController(topAppToolbar, navController);




    }
}
