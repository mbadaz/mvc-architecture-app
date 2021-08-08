package com.mambure.mvcapp.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mambure.mvcapp.R;
import com.mambure.mvcapp.questions.Question;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView tags;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            tags = itemView.findViewById(R.id.txtTopics);

        }

        public void bind(final Question question, OnItemClickListener onItemClickListener) {
            title.setText(question.getTitle());
            tags.setText(question.getTags().toString());
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(question));
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_questions_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question item = list.get(position);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Question item);
    }
}