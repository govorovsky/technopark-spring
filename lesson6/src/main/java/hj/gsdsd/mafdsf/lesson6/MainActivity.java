package hj.gsdsd.mafdsf.lesson6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hj.gsdsd.mafdsf.lesson6.db.SqliteActivity;
import hj.gsdsd.mafdsf.lesson6.filemanager.FileManagerActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_shared_preferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharedPreferencesActivity.class));
            }
        });

        findViewById(R.id.btn_directories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DirectoriesActivity.class));
            }
        });

        findViewById(R.id.btn_file_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FileManagerActivity.class));
            }
        });

        findViewById(R.id.btn_sqlite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SqliteActivity.class));
            }
        });
    }
}
