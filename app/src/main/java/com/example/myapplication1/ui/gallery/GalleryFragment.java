package com.example.myapplication1.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private com.example.myapplication1.ui.gallery.GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        galleryViewModel = new ViewModelProvider(this).get(com.example.myapplication1.ui.gallery.GalleryViewModel.class);

        // Observe LiveData for quotes
        galleryViewModel.getCurrentQuote().observe(getViewLifecycleOwner(), quote -> {
            binding.textGallery.setText("\"" + quote.getText() + "\"\n\n— " + quote.getAuthor());
        });

        // New Quote Button
        binding.btnNewQuoteGallery.setOnClickListener(v -> {
            galleryViewModel.showRandomQuote();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
