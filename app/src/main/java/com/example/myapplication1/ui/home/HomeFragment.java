package com.example.myapplication1.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // LiveData observe pannrom
        homeViewModel.getCurrentQuote().observe(getViewLifecycleOwner(), quote -> {
            if (quote != null) {
                String text = "\"" + quote.getText() + "\"";
                binding.tvQuote.setText(text);
                binding.tvAuthor.setText("— " + quote.getAuthor());

                // Fade-in animation
                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                fadeIn.setDuration(700);
                binding.tvQuote.startAnimation(fadeIn);
                binding.tvAuthor.startAnimation(fadeIn);
            }
        });

        // New Quote button
        binding.btnNewQuote.setOnClickListener(v -> homeViewModel.showRandomQuote());

        // Copy button
        binding.btnCopy.setOnClickListener(v -> {
            String text = binding.tvQuote.getText().toString() + " " + binding.tvAuthor.getText().toString();
            if (!text.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager)
                        requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("quote", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(requireContext(), "Copied to clipboard ✅", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Nothing to copy ❌", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
