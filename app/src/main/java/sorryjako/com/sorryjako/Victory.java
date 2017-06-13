package sorryjako.com.sorryjako;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jinatonic.confetti.CommonConfetti;

import java.util.Random;

public class Victory extends AppCompatActivity {
    static final int floatingPeriod = 1000;
    static final float floatingFactor = 0.1f;

    static MediaPlayer mp;
    ImageButton restart;
    ImageButton end;

    String scoreTOP;
    String scoreBOT;
    String winner;

    TextView tScoreBOT;
    TextView elseBOT;
    TextView winBOT;

    TextView tScoreTOP;
    TextView elseTOP;
    TextView winTOP;


    ViewGroup backTOP;
    ViewGroup backBOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.victory);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mp = MediaPlayer.create(getApplicationContext(),R.raw.hallelujah);
        mp.start();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if(bundle != null) {
            scoreBOT = (String) bundle.get("scoreBOT");
            scoreTOP = (String) bundle.get("scoreTOP");
            winner = (String) bundle.get("winner");
            //Toast.makeText(getApplicationContext(), "BOT: " + scoreBOT + " TOP: " + scoreTOP + " winner: " + winner, Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), "Sorry jako, problém", Toast.LENGTH_SHORT).show();
        }

        tScoreBOT = (TextView) findViewById(R.id.id_victory_scoreBOT_TV);
        elseBOT = (TextView) findViewById(R.id.id_victory_elseBOT_TV);
        winBOT = (TextView) findViewById(R.id.id_victory_winBOT_TV);

        tScoreTOP = (TextView) findViewById(R.id.id_victory_scoreTOP_TV);
        elseTOP = (TextView) findViewById(R.id.id_victory_elseTOP_TV);
        winTOP = (TextView) findViewById(R.id.id_victory_winTOP_TV);

        backTOP = (ViewGroup) findViewById(R.id.id_victory_layoutTOP);
        backBOT = (ViewGroup) findViewById(R.id.id_victory_layoutBOT);

        restart = (ImageButton) findViewById(R.id.id_victory_restart_BT);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                mp.reset();
                setResult(RESULT_OK);
                finish();
            }
        });

        end = (ImageButton) findViewById(R.id.id_victory_end_BT);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                mp.reset();
                finish();
            }
        });

        Random random = new Random();
        int money = random.nextInt(46) + 5;


        if(winner.equals("B"))  {
//            Spannable spanOne = new SpannableString(scoreBOT + ":" + scoreTOP);
//            spanOne.setSpan(new ForegroundColorSpan(Color.parseColor("#EF0000")), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            Spannable spanTwo = new SpannableString(scoreTOP + ":" + scoreBOT);
//            spanTwo.setSpan(new ForegroundColorSpan(Color.parseColor("#27286D")), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            winBOT.setText("Vítězství");
            winTOP.setText("Bude líp...");
            elseBOT.setText("ZÍSKÁVÁŠ DOTACI VE VÝŠI " + money +" MILIONŮ KČ");
            elseTOP.setText(" ");
            tScoreBOT.setText(scoreBOT + ":" + scoreTOP);
            tScoreTOP.setText(scoreTOP + ":" + scoreBOT);
            //CommonConfetti.explosion(l, 0, 0, new int[] { Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.YELLOW }).oneShot().setAccelerationX(10f);

        } else  {
            winBOT.setText("Bude líp...");
            winTOP.setText("Vítězství");
            elseBOT.setText(" ");
            elseTOP.setText("ZÍSKÁVÁŠ DOTACI VE VÝŠI " + money +" MILIONŮ KČ");
            tScoreBOT.setText(scoreBOT + ":" + scoreTOP);
            tScoreTOP.setText(scoreTOP + ":" + scoreBOT);
        }
        biggerFaces.run();
    }

    @Override
    public void onBackPressed() {
    }

    final Runnable biggerFaces = new Runnable() {
        @Override
        public void run() {
            makeBigger(restart.animate());
            makeBigger(end.animate());
        }

        private void makeBigger(ViewPropertyAnimator a) {
            a.scaleXBy(floatingFactor);
            a.scaleYBy(floatingFactor);
            a.setDuration(floatingPeriod);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                a.withEndAction(smallerFaces);
            }
            a.start();
        }
    };
    final Runnable smallerFaces = new Runnable() {
        @Override
        public void run() {
            makeBigger(restart.animate());
            makeBigger(end.animate());
        }

        private void makeBigger(ViewPropertyAnimator a) {
            a.scaleXBy(-floatingFactor);
            a.scaleYBy(-floatingFactor);
            a.setDuration(floatingPeriod);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                a.withEndAction(biggerFaces);
            }
            a.start();
        }
    };
}
