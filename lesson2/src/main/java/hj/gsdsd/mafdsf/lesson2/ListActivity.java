package hj.gsdsd.mafdsf.lesson2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<String> strings = new ArrayList<>();
        fillList(strings);

        RecyclerView recyclerView = findViewById(R.id.my_list);
        final LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(new MyAdapter(strings));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /* some logic with scrolling should be placed here */
            }
        });
    }

    void fillList(List<String> toFill) {
        /* any data from server etc */
        for (int i = 0; i < 200; i++) {
            toFill.add(i + "");
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mImageView = itemView.findViewById(R.id.avatar);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<String> mData;
        private Bitmap mBitmap;

        public MyAdapter(List<String> data) {
            mData = data;
            mBitmap = Bitmap.createBitmap(fillColors(), 200, 200, Bitmap.Config.ARGB_8888);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            Log.d("TAG", "onCreateViewHolder for element type " + i);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            String str = mData.get(i);
            myViewHolder.mTextView.setText(str);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), mBitmap);
            Log.d("TAG", "binding element at position " + i);
            myViewHolder.mImageView.setBackground(bitmapDrawable);
        }

        private int[] fillColors() {
            int[] ints = new int[40000];
            Random random = new Random();
            for (int j = 0; j < ints.length; j++) {
                ints[j] = Color.rgb(random.nextInt(100), 100, random.nextInt(100));
            }
            return ints;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
