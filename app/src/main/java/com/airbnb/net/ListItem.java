package com.airbnb.net;

/**
 * A list item that can either contain a {@link HeroItem} or a {@link ListItem}.
 */
public class ListItem {

    public HeroItem heroItem;
    public ListingItem listingItem;

    public ListItem(HeroItem heroItem) {
        this.heroItem = heroItem;
    }

    public ListItem(ListingItem listingItem) {
        this.listingItem = listingItem;
    }
}
