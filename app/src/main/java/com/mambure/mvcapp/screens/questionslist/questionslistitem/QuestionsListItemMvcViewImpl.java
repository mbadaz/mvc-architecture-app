package com.mambure.mvcapp.screens.questionslist.questionslistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.Question;

public class QuestionsListItemMvcViewImpl extends QuestionsListItemMvcView {
    private final TextView title;
    private final TextView tags;
    private Question question;

    public QuestionsListItemMvcViewImpl(LayoutInflater inflater, ViewGroup parent) {
        View rootView = inflater.inflate(R.layout.item_questions_list, parent, false);
        setRootView(rootView);
        title = findViewById(R.id.txtTitle);
        tags = findViewById(R.id.txtTopics);
        rootView.setOnClickListener(v -> {
            for (QuestionListItemListener listener : getListeners()) {
                listener.onItemClicked(question);
            }
        });
    }

    @Override
    public void bindQuestion(Question question) {
        this.question = question;
        title.setText(HtmlCompat.fromHtml(
                question.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        tags.setText(question.getTags().toString());
    }
}
