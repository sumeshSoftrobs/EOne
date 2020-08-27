package com.example.eone.mainLayouts.materials;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eone.R;

public class StudyMaterialsFragment extends Fragment {

    private StudyMaterialsViewModel mViewModel;

    public static StudyMaterialsFragment newInstance() {
        return new StudyMaterialsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.study_materials_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudyMaterialsViewModel.class);
        // TODO: Use the ViewModel
    }

}