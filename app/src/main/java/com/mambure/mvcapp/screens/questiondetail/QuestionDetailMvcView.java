package com.mambure.mvcapp.screens.questiondetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.QuestionDetail;

import java.text.DateFormat;
import java.util.Date;

public class QuestionDetailMvcView {
    private final View rootView;
    private final TextView txtTitle;
    private final TextView txtDate;
    private final TextView txtBody;
    private final ProgressBar progressBar;

    public QuestionDetailMvcView(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.activity_question_detail, parent, false);
        txtTitle = rootView.findViewById(R.id.txtTitle);
        txtDate = rootView.findViewById(R.id.txtDate);
        txtBody = rootView.findViewById(R.id.txtBody);
        progressBar = rootView.findViewById(R.id.progressBar2);
    }

    public View getRootView() {
        return rootView;
    }

    public void bindQuestionDetail(QuestionDetail questionDetail) {
        txtTitle.setText(HtmlCompat.fromHtml(questionDetail.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        txtBody.setText(HtmlCompat.fromHtml(questionDetail.getBody(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        Date dateTime = new Date(questionDetail.getCreationDate() * 1000);
        String date = DateFormat.getDateInstance().format(dateTime);
        txtDate.setText(date);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}