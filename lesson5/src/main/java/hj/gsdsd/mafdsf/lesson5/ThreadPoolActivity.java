package hj.gsdsd.mafdsf.lesson5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Демонстрация работы с Executor'ом и Thread Pool'ом с фиксированным числом потоков.
 * Представленный код добавляет задачи в очередь Executor'у, а сами задачи имитируют пятисекундную
 * работу и выводят соответствующие сообщения в Logcat. Для удобства чтения логов задачи выводят
 * сообщения с отступом.
 */
public class ThreadPoolActivity extends AppCompatActivity {

    private static final int THREAD_COUNT = 3;

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

        findViewById(R.id.add_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                executorService.execute(createTask());
            }
        });
    }

    private Runnable createTask() {
        final int id = taskId++;
        return new Runnable() {
            @Override
            public void run() {
                Log.d("TASK", String.format("%sTask %d started on thread '%s'",
                        getPadding(id), id, Thread.currentThread().getName()));
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d("TASK", String.format("%sTask %d finished on thread '%s'",
                        getPadding(id), id, Thread.currentThread().getName()));
            }
        };
    }

    private static String getPadding(final int id) {
        final int padding = id % THREAD_COUNT;
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            builder.append("    ");
        }
        return builder.toString();
    }
}
