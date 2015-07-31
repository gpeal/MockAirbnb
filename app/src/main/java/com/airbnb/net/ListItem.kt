package com.airbnb.net

/**
 * A list item that can either contain a [HeroItem] or a [ListItem].
 */
public class ListItem {

    public var heroItem: HeroItem? = null
    public var listingItem: ListingItem? = null

    public constructor(heroItem: HeroItem) {
        this.heroItem = heroItem
    }

    public constructor(listingItem: ListingItem) {
        this.listingItem = listingItem
    }
}
