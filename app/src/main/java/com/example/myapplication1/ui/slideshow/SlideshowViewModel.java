package com.example.myapplication1.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<com.example.app1.ui.home.Quote> currentQuote = new MutableLiveData<>();

    // Quotes list
    private final com.example.app1.ui.home.Quote[] quotes = {
            new com.example.app1.ui.home.Quote("Life is what happens when you're busy making other plans.", "John Lennon"),
            new com.example.app1.ui.home.Quote("Get busy living or get busy dying.", "Stephen King"),
            new com.example.app1.ui.home.Quote("You only live once, but if you do it right, once is enough.", "Mae West"),
            new com.example.app1.ui.home.Quote("In the middle of every difficulty lies opportunity.", "Albert Einstein"),
            new com.example.app1.ui.home.Quote("Keep smiling, because life is a beautiful thing and there’s so much to smile about.", "Marilyn Monroe"),
            new com.example.app1.ui.home.Quote("Life is short, and it’s up to you to make it sweet.", "Sarah Louise Delany"),
            new com.example.app1.ui.home.Quote("Do not take life too seriously. You will never get out of it alive.", "Elbert Hubbard"),
            new com.example.app1.ui.home.Quote("Good friends, good books, and a sleepy conscience: this is the ideal life.", "Mark Twain"),
            new com.example.app1.ui.home.Quote("The purpose of our lives is to be happy.", "Dalai Lama"),
            new com.example.app1.ui.home.Quote("Difficulties in life are intended to make us better, not bitter.", "Dan Reeves")
    };

    private int currentIndex = -1;

    public SlideshowViewModel() {
        showRandomQuote(); // load first random quote
    }

    public LiveData<com.example.app1.ui.home.Quote> getCurrentQuote() {
        return currentQuote;
    }

    // Random quote logic
    public void showRandomQuote() {
        if (quotes.length == 0) return;

        Random random = new Random();
        int newIndex = random.nextInt(quotes.length);

        // avoid repeating same quote immediately
        int attempts = 0;
        while (newIndex == currentIndex && attempts < 5) {
            newIndex = random.nextInt(quotes.length);
            attempts++;
        }

        currentIndex = newIndex;
        currentQuote.setValue(quotes[currentIndex]);
    }
}
