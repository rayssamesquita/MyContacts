package com.example.mycontacts.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycontacts.R;

public class CardsFragment extends Fragment {

    private CardsViewModel cardsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cardsViewModel =
                ViewModelProviders.of(this).get(CardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cards, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        cardsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}