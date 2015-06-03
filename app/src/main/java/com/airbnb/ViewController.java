package com.airbnb;

import android.view.ViewGroup;

/**
 * Handles the logic for creating and modifying a view that is presented on screen.
 */
public interface ViewController {
    ViewGroup initialize(ViewGroup container);
}
