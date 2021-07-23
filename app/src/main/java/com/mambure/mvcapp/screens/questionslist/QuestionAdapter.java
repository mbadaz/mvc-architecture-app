package com.mambure.mvcapp.screens.questionslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mambure.mvcapp.questions.Question;
import com.mambure.mvcapp.screens.questionslist.questionlistitem.QuestionListItemMvcView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> implements QuestionListItemMvcView.Listener {
    private final List<Question> list;
    private final OnItemClickListener onItemClickListener;

    public QuestionAdapter(OnItemClickListener onItemClickListener) {
        this.list = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void addData(List<Question> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(Question question) {
        onItemClickListener.onItemClick(question);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        QuestionListItemMvcView questionListItemMvcView;

        public ViewHolder(QuestionListItemMvcView questionListItemMvcView) {
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
        QuestionListItemMvcView questionListItemMvcView = new QuestionListItemMvcView(
                LayoutInflater.from(parent.getContext()), parent
        );
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

    public interface OnItemClickListener {
        void onItemClick(Question item);
    }
}