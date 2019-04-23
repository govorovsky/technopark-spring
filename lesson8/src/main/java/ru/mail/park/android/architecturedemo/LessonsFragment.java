package ru.mail.park.android.architecturedemo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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


    private LessonsViewModel mLessonsViewModel;

    public LessonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        mLessonsViewModel = ViewModelProviders.of(this)
                .get(LessonsViewModel.class);
        mLessonsViewModel
                .getLessons()
                .observe(this, observer);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.lessons_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            mLessonsViewModel.refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            final Lesson lesson = mLessons.get(position);
            holder.mName.setText(lesson.getName());
            holder.mPlace.setText(lesson.getPlace());
            holder.mDate.setText(mFormat.format(lesson.getDate()));
            holder.mRating.setText(String.valueOf(lesson.getRating()));
            holder.mLikeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLessonsViewModel.like(lesson);
                }
            });

            Glide.with(getContext())
                    .load(resolveImage(lesson).imageUrl)
                    .placeholder(R.drawable.icons8_question_mark_24)
                    .timeout(3000)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mIcon);
        }

        private ItemImg resolveImage(Lesson lesson) {
            try {
                return ItemImg.valueOf(lesson.getName().toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                return ItemImg.DEFAULT;
            }
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
        protected ImageView mLikeBtn;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.date);
            mPlace = itemView.findViewById(R.id.place);
            mName = itemView.findViewById(R.id.name);
            mRating = itemView.findViewById(R.id.rating);
            mIcon = itemView.findViewById(R.id.icon);
            mLikeBtn = itemView.findViewById(R.id.like);
        }
    }

    enum ItemImg {
        ANDROID("http://1.bp.blogspot.com/-47ehH6VQ8Dg/U1io7iuzMoI/AAAAAAAAAa8/rh8gAHDL12k/s1600/android.jpg"),
        IOS("https://img.etimg.com/thumb/msid-67229697,width-643,imgsize-126235,resizemode-4/applelogonew.jpg"),
        DEFAULT("https://findicons.com/files/icons/2380/android_style_icons_r1/512/computer.png");

        public final String imageUrl;

        ItemImg(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}