package com.example.htmlandimagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button[] buttons;int b,c;Button btn, btn1,btn2,btn3,btn4;int points=0;TextView textView1;TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==buttons[c].getId()){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            points++;
            yes();
        }
        else{
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
        }

    }

    public static class Imagedownload extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
                HttpsURLConnection httpsURLConnection =(HttpsURLConnection)url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                //return "failed";
                return null;
            }
        }
    }
    public void Onclick(View view) {
      btn = (Button) findViewById(R.id.button);
      final ImageView imageView =(ImageView)findViewById(R.id.imageView);
      imageView.setEnabled(true);
         btn1 = (Button) findViewById(R.id.button2);
         btn2 = (Button) findViewById(R.id.button3);
         btn3 = (Button) findViewById(R.id.button4);
         btn4 = (Button) findViewById(R.id.button5);
        buttons = new Button[]{btn1, btn2, btn3, btn4};
        textView1=(TextView)findViewById(R.id.textView3);
        btn.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView = (TextView)findViewById(R.id.textView);
                textView.setVisibility(View.VISIBLE);

                textView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }
            @Override
            public void onFinish() {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                btn.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                btn.setBackgroundColor(Color.BLUE);
                textView.setVisibility(View.INVISIBLE);
                btn.setText("Play Again!!");
                textView1.setText("Your Score: "+Integer.toString(points));
                imageView.setEnabled(false);
                points=0;
            }
        }.start();
        yes();
    }
    public void yes(){
        String result=null;
        Bitmap myImage;
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Imagedownload Imagedownload = new Imagedownload();
        try{

            String mylink="<img src=\"//specials-images.forbesimg.com/imageserve/5ed55c763dbc9800060b27d5/832x832.jpg?background=000000&amp;cropX1=0&amp;cropX2=1080&amp;cropY1=0&amp;cropY2=1080\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ed00f17d4a99d0006d2e738/832x832.jpg?background=000000&amp;cropX1=154&amp;cropX2=4820&amp;cropY1=651&amp;cropY2=5314\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ed53e8fa40c3d0007ed25b3/832x832.jpg?background=000000&amp;cropX1=509&amp;cropX2=1693&amp;cropY1=78&amp;cropY2=1262\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ec593cc431fb70007482137/832x832.jpg?background=000000&amp;cropX1=1321&amp;cropX2=3300&amp;cropY1=114&amp;cropY2=2093\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ec595d45f39760007b05c07/832x832.jpg?background=000000&amp;cropX1=989&amp;cropX2=2480&amp;cropY1=74&amp;cropY2=1564\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ecc520236d6f40008dcdd7d/832x832.jpg?background=000000&amp;cropX1=622&amp;cropX2=3127&amp;cropY1=5&amp;cropY2=2509\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ec59a8cda48890006292eee/832x832.jpg?background=000000&amp;cropX1=845&amp;cropX2=2277&amp;cropY1=97&amp;cropY2=1528\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ed55ed6b14861000600bb71/832x832.jpg?background=000000&amp;cropX1=15&amp;cropX2=1067&amp;cropY1=26&amp;cropY2=1078\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5ecc5358798e4c00060d2274/832x832.jpg?background=000000&amp;cropX1=1184&amp;cropX2=3286&amp;cropY1=30&amp;cropY2=2130\" alt=\"\"><img src=\"//specials-images.forbesimg.com/imageserve/5b3a90cba7ea4353dd3f9804/832x832.jpg?background=000000&amp;cropX1=186&amp;cropX2=3092&amp;cropY1=449&amp;cropY2=3354\" alt=\"\">";
            Pattern pattern =Pattern.compile("<img src=\"(.*?)\"");

            String[] array = new String[10];int i=0;
            Matcher matcher = pattern.matcher(mylink);
            while (matcher.find()){
                array[i]=matcher.group(1);
                i++;
            }

             String[] names = {"Kylie Jenner","Kanye West","Roger Federer","Cristiano Ronaldo","Lionel Messi","Tyler Perry","Neymar","Howard Stern","Lebron James","Dywan Jhonson"};

            //System.out.print("https:"+array);
            b=new Random().nextInt(10);
             c=new Random().nextInt(4);
            for(i=0;i<4;i++){
                if(i==c){
                    buttons[i].setText(names[b]);
                }
                else {
                    int d = new Random().nextInt(10);
                    while(d==b){
                        d=new Random().nextInt(10);
                    }
                    buttons[i].setText(names[d]);

                }
            }
            myImage=Imagedownload.execute("https:"+array[b]).get();
            image.setImageBitmap(myImage);
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
            btn4.setOnClickListener(this);
        }catch (Exception e){
            textView1.setText("Check your Internet connection");
        }

    }
}