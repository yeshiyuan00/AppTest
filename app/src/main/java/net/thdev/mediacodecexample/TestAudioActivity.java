package net.thdev.mediacodecexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.ysy.testapp.R;

/**
 * User: ysy
 * Date: 2015/8/12
 */
public class TestAudioActivity extends Activity {

    AudioDecoder audioDecoder;
    private Button btn_1;
    public static boolean isPlaying = false;
    private static final String url = Environment.getExternalStorageDirectory() + "/beijing.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_onebutton);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setText("播放歌曲");
        audioDecoder = new AudioDecoder();
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    isPlaying = true;
                    audioDecoder.shouldContinue(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            audioDecoder.decode(url);
                        }
                    }).start();
                } else {
                    isPlaying = false;
                    audioDecoder.shouldContinue(false);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        audioDecoder.stop();
        super.onDestroy();

    }
}
