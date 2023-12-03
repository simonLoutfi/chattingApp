package com.example.chatapp;

import android.os.Bundle;
import com.example.chatapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.chatapp.databinding.ChatUiBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.chatapp.R;

public class chat_ui extends AppCompatActivity {
    ChatUiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ChatUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new chatFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.chat) {
                replaceFragment(new chatFragment());
                return true;
            } else if (itemId == R.id.ai) {
                replaceFragment(new aiFragment());
                return true;
            } else if (itemId == R.id.settings) {
                replaceFragment(new settingsFragment());
                return true;
            } else {
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
