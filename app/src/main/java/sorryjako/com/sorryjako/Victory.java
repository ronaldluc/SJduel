package sorryjako.com.sorryjako;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Victory extends AppCompatActivity {

    static MediaPlayer mp;
    Button restart;

    String scoreTOP;
    String scoreBOT;
    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.victory);

        mp = MediaPlayer.create(getApplicationContext(),R.raw.finish);
        mp.start();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if(bundle != null) {
            scoreBOT = (String) bundle.get("scoreBOT");
            scoreTOP = (String) bundle.get("scoreTOP");
            winner = (String) bundle.get("winner");
            Toast.makeText(getApplicationContext(), "BOT: " + scoreBOT + " TOP: " + scoreTOP + " winner: " + winner, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Sorry jako, problém", Toast.LENGTH_SHORT).show();
        }

        restart = (Button) findViewById(R.id.id_victory_restart_BT);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
