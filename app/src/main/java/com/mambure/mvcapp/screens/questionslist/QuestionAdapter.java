package com.mambure.mvcapp.screens.questionslist;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.common.MvcViewFactory;
import com.mambure.mvcapp.screens.questionslist.questionslistitem.QuestionsListItemMvcView;
import com.mambure.mvcapp.screens.questionslist.questionslistitem.QuestionsListItemMvcView.QuestionListItemListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> implements QuestionListItemListener {
    private final List<Question> list;
    private final OnItemClickListener onItemClickListener;
    private final MvcViewFactory mvcViewFactory;

    public QuestionAdapter(OnItemClickListener onItemClickListener, MvcViewFactory mvcViewFactory) {
        this.list = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
        this.mvcViewFactory = mvcViewFactory;
    }

    public void addData(List<Question> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        QuestionsListItemMvcView questionListItemMvcView;
        public ViewHolder(QuestionsListItemMvcView questionListItemMvcView) {
            super(questionListItemMvcView.getRootView());
            this.questionListItemMvcView = questionListItemMvcView;
        }

        public void bind(final Question question) {
            questionListItemMvcView.bindQuestion(question);
        }

    }
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        QuestionsListItemMvcView questionListItemMvcView = mvcViewFactory.getQuestionListItemMvcView(parent);
        questionListItemMvcView.registerListener(this);
        return new ViewHolder(questionListItemMvcView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClicked(Question question) {
        onItemClickListener.onItemClick(question);
    }

    public interface OnItemClickListener {
        void onItemClick(Question item);
    }
}