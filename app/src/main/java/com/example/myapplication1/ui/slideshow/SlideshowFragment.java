package com.example.myapplication1.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1.databinding.FragmentSlideshowBinding;
import com.example.app1.ui.home.Quote; // 🔥 Quote class import

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private SlideshowViewModel slideshowViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // ViewModel init
        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        // Observe life quotes
        slideshowViewModel.getCurrentQuote().observe(getViewLifecycleOwner(), (Quote quote) -> {
            if (quote != null) {
                binding.textSlideshow.setText("\"" + quote.getText() + "\"");
                binding.tv1.setText("— " + quote.getAuthor());
            }
        });

        // Next button → load new quote
        binding.btnNextLife.setOnClickListener(v -> slideshowViewModel.showRandomQuote());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
