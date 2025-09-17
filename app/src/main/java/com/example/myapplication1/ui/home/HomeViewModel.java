package com.example.myapplication1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<com.example.app1.ui.home.Quote> currentQuote = new MutableLiveData<>();
    private final List<com.example.app1.ui.home.Quote> quotes = new ArrayList<>();
    private final Random random = new Random();

    public HomeViewModel() {
        // Quotes list add pannirukken
        quotes.add(new com.example.app1.ui.home.Quote("Be yourself; everyone else is already taken.", "Oscar Wilde"));
        quotes.add(new com.example.app1.ui.home.Quote("In the middle of every difficulty lies opportunity.", "Albert Einstein"));
        quotes.add(new com.example.app1.ui.home.Quote("Do what you can, with what you have, where you are.", "Theodore Roosevelt"));
        quotes.add(new com.example.app1.ui.home.Quote("Happiness depends upon ourselves.", "Aristotle"));
        quotes.add(new com.example.app1.ui.home.Quote("Simplicity is the ultimate sophistication.", "Leonardo da Vinci"));

        // App open pannumbodhu oru random quote show pannum
        showRandomQuote();
    }

    public LiveData<com.example.app1.ui.home.Quote> getCurrentQuote() {
        return currentQuote;
    }

    public void showRandomQuote() {
        if (!quotes.isEmpty()) {
            com.example.app1.ui.home.Quote randomQuote = quotes.get(random.nextInt(quotes.size()));
            currentQuote.setValue(randomQuote);
        }
    }
}
