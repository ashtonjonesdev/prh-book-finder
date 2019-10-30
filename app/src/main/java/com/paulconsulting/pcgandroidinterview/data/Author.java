package com.paulconsulting.pcgandroidinterview.data;

import java.util.ArrayList;

/**
 *
 * A data class for an Author
 *
 */
public class Author {

    private String authorFirst;

    private String authorLast;

    private String authorSpotlight;

    private ArrayList<String> authorBooks;

    /**
     *
     * Constructor that will be used in the FoundAuthors RecyclerView
     *
     * @param authorFirst
     * @param authorLast
     */
    public Author(String authorFirst, String authorLast) {
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
    }

    /**
     *
     * Constructors that will be used in the AuthorsDetails RecyclerView
     *
     * @param authorFirst
     * @param authorLast
     * @param authorSpotlight
     */

    public Author(String authorFirst, String authorLast, String authorSpotlight) {
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.authorSpotlight = authorSpotlight;
    }

    public Author(String authorFirst, String authorLast, String authorSpotlight, ArrayList<String> authorBooks) {
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.authorSpotlight = authorSpotlight;
        this.authorBooks = authorBooks;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public String getAuthorSpotlight() {
        return authorSpotlight;
    }

    public void setAuthorSpotlight(String authorSpotlight) {
        this.authorSpotlight = authorSpotlight;
    }

    public ArrayList<String> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(ArrayList<String> authorBooks) {
        this.authorBooks = authorBooks;
    }
}
