package hj.gsdsd.mafdsf.lesson6.db;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Collection;

import hj.gsdsd.mafdsf.lesson6.R;


/**
 * Activity для демонстрации примера работы с SQLite при помощи выделенного
 * менеджера (см. {@link DbManager})
 */
public class SqliteActivity extends AppCompatActivity {

    private final DbManager.ReadAllListener<String> readListener = new DbManager.ReadAllListener<String>() {
        @Override
        public void onReadAll(final Collection<String> allItems) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showStringList(allItems);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DbManager manager = DbManager.getInstance(this);

        setContentView(R.layout.activity_sqlite);

        final EditText editText = findViewById(R.id.edit_text);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.insert(editText.getText().toString());
                editText.setText("");
            }
        });

        findViewById(R.id.enumerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.readAll(readListener);
            }
        });

        findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.clean();
            }
        });


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "populus-database").build();
    }

    private void showStringList(final Collection<String> list) {
        new AlertDialog.Builder(this)
                .setItems(list.toArray(new String[0]), null)
                .show();
    }
}
