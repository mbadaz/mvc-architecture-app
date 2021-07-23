package com.mambure.mvcapp.screens.questionslist.questionlistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.common.MyObservable;
import com.mambure.mvcapp.questions.Question;

public class QuestionListItemMvcView extends MyObservable<QuestionListItemMvcView.Listener> {
    private final TextView title;
    private final TextView tags;
    private Question question;
    private final View rootView;

    public QuestionListItemMvcView(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.item_questions_list, parent, false);
        title = rootView.findViewById(R.id.txtTitle);
        tags = rootView.findViewById(R.id.txtTopics);
        rootView.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onItemClicked(question);
            }
        });
    }

    public View getRootView() {
        return rootView;
    }

    public void bindQuestion(Question question) {
        this.question = question;
        title.setText(HtmlCompat.fromHtml(
                question.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        tags.setText(question.getTags().toString());
    }

    public interface Listener {
        void onItemClicked(Question question);
    }
}