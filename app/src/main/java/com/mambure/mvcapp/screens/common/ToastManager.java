package com.mambure.mvcapp.screens.common;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.mambure.mvcapp.R;

public class ToastManager {

    private final Activity mActivity;

    public ToastManager(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showErrorOccurredMessage() {
        showMessage(R.string.an_error_occurred);
    }

    private void showMessage(@StringRes int message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

//    public void showMessage (@StringRes int message) {
//        Snackbar snackbar = Snackbar.make(
//                mActivity.findViewById(android.R.id.content),
//                message,
//                Snackbar.LENGTH_SHORT);
//        snackbar.show();
//    }
}
