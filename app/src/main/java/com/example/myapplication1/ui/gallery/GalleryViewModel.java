package com.example.myapplication1.ui.gallery;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app1.ui.home.Quote;

import java.util.Random;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<Quote> currentQuote = new MutableLiveData<>();
    private final Quote[] quotes = {
            new Quote("The best way to get started is to quit talking and begin doing.", "Walt Disney"),
            new Quote("Don't let yesterday take up too much of today.", "Will Rogers"),
            new Quote("It's not whether you get knocked down, it's whether you get up.", "Vince Lombardi"),
            new Quote("If you are working on something exciting, it will keep you motivated.", "Steve Jobs"),
            new Quote("Success is not in what you have, but who you are.", "Bo Bennett"),
            new Quote("Hard times create strong men.", "Unknown"),
            new Quote("Dream it. Wish it. Do it.", "Unknown"),
            new Quote("Great things never come from comfort zones.", "Unknown"),
            new Quote("Push yourself, because no one else is going to do it for you.", "Unknown"),
            new Quote("Do something today that your future self will thank you for.", "Unknown")
    };

    private int currentIndex = -1;

    public GalleryViewModel() {
        showRandomQuote();
    }

    public LiveData<Quote> getCurrentQuote() {
        return currentQuote;
    }

    public void showRandomQuote() {
        if (quotes.length == 0) return;
        Random random = new Random();
        int newIndex = random.nextInt(quotes.length);

        int attempts = 0;
        while (newIndex == currentIndex && attempts < 5) {
            newIndex = random.nextInt(quotes.length);
            attempts++;
        }

        currentIndex = newIndex;
        currentQuote.setValue(quotes[currentIndex]);
    }
}