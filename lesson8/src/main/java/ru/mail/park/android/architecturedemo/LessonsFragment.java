package ru.mail.park.android.architecturedemo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.mail.park.android.architecturedemo.database.Lesson;


public class LessonsFragment extends Fragment {


    public LessonsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lessons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.lessons);
        final LessonsAdapter adapter = new LessonsAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Observer<List<Lesson>> observer = new Observer<List<Lesson>>() {
            @Override
            public void onChanged(List<Lesson> lessons) {
                if (lessons != null) {
                    adapter.setLessons(lessons);
                }
            }
        };
        ViewModelProviders.of(this)
                .get(LessonsViewModel.class)
                .getLessons()
                .observe(this, observer);
    }

    private class LessonsAdapter extends RecyclerView.Adapter<LessonViewHolder> {

        private List<Lesson> mLessons = new ArrayList<>();
        private SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM", Locale.US);

        public void setLessons(List<Lesson> lessons) {
            mLessons = lessons;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LessonViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lesson_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
            Lesson lesson = mLessons.get(position);
            holder.mName.setText(lesson.getName());
            holder.mPlace.setText(lesson.getPlace());
            holder.mDate.setText(mFormat.format(lesson.getDate()));
            holder.mRating.setText(String.valueOf(lesson.getRating()));
        }

        @Override
        public int getItemCount() {
            return mLessons.size();
        }
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {

        protected TextView mName;
        protected TextView mPlace;
        protected TextView mDate;
        protected TextView mRating;
        protected ImageView mIcon;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.date);
            mPlace = itemView.findViewById(R.id.place);
            mName = itemView.findViewById(R.id.name);
            mRating = itemView.findViewById(R.id.rating);
            mIcon = itemView.findViewById(R.id.icon);
        }
    }
}