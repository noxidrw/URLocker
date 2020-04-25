package com.example.urlocker;

/**
 * This is the class for the locker.
 *
 * @author William Dixon
 * @version 1.0
 */
public class TheLocker
{
    private long id;
    private String urlAddress;
    private String filter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return urlAddress + ", " + filter;
    }
}