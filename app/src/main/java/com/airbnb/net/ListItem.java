package com.airbnb.net;

/**
 * A list item that can either contain a {@link HeroItem} or a {@link ListItem}.
 */
public class ListItem {

    public HeroItem heroItem;
    public Listing listing;

    public ListItem(HeroItem heroItem) {
        this.heroItem = heroItem;
    }

    public ListItem(Listing listing) {
        this.listing = listing;
    }
}
