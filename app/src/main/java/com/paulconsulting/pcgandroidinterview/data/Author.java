package com.paulconsulting.pcgandroidinterview.data;

import android.text.Html;

import java.util.ArrayList;

/**
 * A data class for an Author
 */
public class Author {

    private String authorfirst;

    private String authorlast;

    private String spotlight;

    private ArrayList<String> works;

    /**
     * Constructor that will be used in the FoundAuthors RecyclerView
     *
     * @param authorfirst
     * @param authorlast
     */
    public Author(String authorfirst, String authorlast) {
        this.authorfirst = authorfirst;
        this.authorlast = authorlast;
    }

    /**
     * Constructors that will be used in the AuthorsDetails RecyclerView
     *
     * @param authorfirst
     * @param authorlast
     * @param spotlight
     */

    public Author(String authorfirst, String authorlast, String spotlight) {
        this.authorfirst = authorfirst;
        this.authorlast = authorlast;
        this.spotlight = spotlight;
    }

    public Author(String authorfirst, String authorlast, String spotlight, ArrayList<String> works) {
        this.authorfirst = authorfirst;
        this.authorlast = authorlast;
        this.spotlight = spotlight;
        this.works = works;
    }

    public Author() {
    }

    public Author(String authorfirst) {
        this.authorfirst = authorfirst;
    }

    public String getAuthorfirst() {
        return authorfirst;
    }

    public void setAuthorfirst(String authorfirst) {
        this.authorfirst = authorfirst;
    }

    public String getAuthorlast() {
        return authorlast;
    }

    public void setAuthorlast(String authorlast) {
        this.authorlast = authorlast;
    }

    public String getSpotlight() {
        return Html.fromHtml(spotlight).toString();
    }

    public void setSpotlight(String spotlight) {
        this.spotlight = spotlight;
    }

    public ArrayList<String> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<String> works) {
        this.works = works;
    }
}
