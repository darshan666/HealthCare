// Generated by view binder compiler. Do not edit!
package com.example.hc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.denzcoskun.imageslider.ImageSlider;
import com.example.hc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavbar;

  @NonNull
  public final ImageSlider imgSlider;

  private HomeBinding(@NonNull RelativeLayout rootView, @NonNull BottomNavigationView bottomNavbar,
      @NonNull ImageSlider imgSlider) {
    this.rootView = rootView;
    this.bottomNavbar = bottomNavbar;
    this.imgSlider = imgSlider;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNavbar;
      BottomNavigationView bottomNavbar = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavbar == null) {
        break missingId;
      }

      id = R.id.imgSlider;
      ImageSlider imgSlider = ViewBindings.findChildViewById(rootView, id);
      if (imgSlider == null) {
        break missingId;
      }

      return new HomeBinding((RelativeLayout) rootView, bottomNavbar, imgSlider);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
