package com.example.mobileolimp;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    LinearLayout drawArea;
    ImageButton btnSun;
    ImageButton btnMoon;
    ImageButton btnHeart;
    ImageButton btnRainbow;
    ImageButton btnCat;
    ImageButton btnFrog;
    ImageButton btnRabbit;
    ImageButton btnHouse;

    public String picture = "-";
    DrawView area;
    int n = 1;
    int count = 0;
    String[][] seted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSun = findViewById(R.id.idSun);
        btnMoon = findViewById(R.id.idMoon);
        btnCat = findViewById(R.id.idCat);
        btnRabbit = findViewById(R.id.idRabbit);
        btnFrog = findViewById(R.id.idFrog);
        btnHouse = findViewById(R.id.idHouse);
        btnHeart = findViewById(R.id.idHeart);
        btnRainbow = findViewById(R.id.idRainbow);
        drawArea = findViewById(R.id.drawArea);

        seted = new String[n][3];
        count = 0;
        seted[0][0] = "rabbit";
        seted[0][1] = "900";
        seted[0][2] = "1100";

        area = new DrawView(this);
        drawArea.addView(area);
    }

    public void clickSun(View view) {
        picture = "sun";
    }

    public void clickMoon(View view) {
        picture = "moon";
    }

    public void clickHouse(View view) {
        picture = "house";
    }

    public void clickRabbit(View view) {
        picture = "rabbit";
    }

    public void clickCat(View view) {
        picture = "cat";
    }

    public void clickFrog(View view) {
        picture = "frog";
    }

    public void clickHeart(View view) {
        picture = "heart";
    }

    public void clickRainbow(View view) {
        picture = "rainbow";
    }

    public void clickClear(View view) {
        picture = "clear";

        seted = null;
        n = 0;
        count = 0;

        area.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY() - 150;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d("myTag", "ACTION_UP");

                String[][] temp = new String[n][3];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp[i][j] = seted[i][j];
                    }
                }

                count++;
                seted = null;
                n++;
                count++;
                seted = new String[n][3];

                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < 3; j++) {
                        seted[i][j] = temp[i][j];
                    }
                }
                seted[n - 1][0] = picture;
                seted[n - 1][1] = "" + x;
                seted[n - 1][2] = "" + y;

                area.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("myTag", "ACTION_MOVE");
                break;
        }

        return true;
    }

    class DrawView extends View {
        Paint paint;
        Canvas canvas;

        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            canvas = new Canvas();
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            float x, y;
            for (int i = 0; i < n; i++) {
                x = Float.parseFloat(seted[i][1]);
                y = Float.parseFloat(seted[i][2]);
                switch (seted[i][0]) {
                    case "sun":
                        drawSun(canvas, paint, x, y);
                        break;
                    case "moon":
                        drawMoon(canvas, paint, x, y);
                        break;
                    case "house":
                        drawHouse(canvas, paint, x, y);
                        break;
                    case "rainbow":
                        drawRainbow(canvas, paint, x, y);
                        break;
                    case "heart":
                        drawHeart(canvas, paint, x, y);
                        break;
                    case "cat":
                        drawCat(canvas, paint, x, y);
                        break;
                    case "frog":
                        drawFrog(canvas, paint, x, y);
                        break;
                    case "rabbit":
                        drawRabbit(canvas, paint, x, y);
                        break;
                }
            }

            if(count == 0){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Выберите какой-то рисунок и кликните по экрану в желаемое место размещения",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        private void drawSun(Canvas c, Paint p, float x, float y) {

        }

        private void drawRainbow(Canvas c, Paint p, float x, float y) {

        }

        private void drawHeart(Canvas c, Paint p, float x, float y) {

        }

        private void drawHouse(Canvas c, Paint p, float x, float y) {
            p.setStyle(Paint.Style.FILL);
            p.setStrokeWidth(20);

            p.setColor(0xffcd853f); //фасад
            c.drawRect(x - 150, y - 100, x + 150, y + 100, p);

            p.setColor(0xffe74f4f); //дверь
            c.drawRect(x - 30, y - 15, x + 30, y + 100, p);

            p.setColor(Color.BLACK); //ручка
            c.drawCircle(x - 20, y + 50, 7, p);

            p.setColor(0xff954e0a); //труба
            c.drawRect(x + 80, y - 230, x + 130, y - 100, p);
            c.drawRect(x + 75, y - 240, x + 135, y - 230, p);

            p.setColor(0xff954e0a); //крыша
            Path path = new Path();
            path.moveTo(x - 150, y - 100);
            path.lineTo(x, y - 250);
            path.lineTo(x + 150, y - 100);
            path.lineTo(x - 150, y - 100);
            path.close();
            c.drawPath(path, p);

            p.setColor(0xffad1313); //края крыши
            c.drawLine(x - 160, y - 100, x, y - 255, p);
            c.drawLine(x, y - 255, x + 160, y - 100, p);

            p.setColor(0xffcae7f1); //окна
            c.drawRect(x + 60, y - 80, x + 130, y - 20, p); //правое
            c.drawRect(x - 60, y - 80, x - 130, y - 20, p); //левое
            c.drawCircle(x, y - 160, 40, p); //центральное

            p.setColor(Color.BLACK); //обрамнеление окон
            p.setStrokeWidth(10);
            c.drawLine(x + 60, y - 50, x + 130, y - 50, p); //правое окно ----
            c.drawLine(x + 95, y - 80, x + 95, y - 20, p); //правое окно |
            c.drawLine(x - 60, y - 50, x - 130, y - 50, p); //левое окно ----
            c.drawLine(x - 95, y - 80, x - 95, y - 20, p); //левое окно |
            c.drawLine(x - 40, y - 160, x + 40, y - 160, p); //центральное окно ----
            c.drawLine(x, y - 200, x, y - 120, p); //центральное окно |
        }

        private void drawMoon(Canvas c, Paint p, float x, float y) {

        }

        private void drawRabbit(Canvas c, Paint p, float x, float y) {
            RectF oval, rectangle, rect;

            p.setStyle(Paint.Style.FILL);
            p.setStrokeWidth(20);

            p.setColor(0xff06aee7); // голубой
            c.drawCircle(x, y, 100, p); // голова

            Path path = new Path(); // уши

            c.drawCircle(x + 50, y - 200, 40, p); // правое ухо
            path.moveTo(x + 10, y - 200);
            path.lineTo(x, y - 70);
            path.lineTo(x + 30, y - 70);
            path.lineTo(x + 93, y - 200);
            path.lineTo(x + 10, y - 200);

            c.drawCircle(x - 50, y - 220, 40, p); // левое ухо
            path.moveTo(x - 10, y - 220);
            path.lineTo(x, y - 80);
            path.lineTo(x - 40, y - 70);
            path.lineTo(x - 92, y - 220);
            path.lineTo(x - 10, y - 220);

            path.close();
            c.drawPath(path, p);

            p.setColor(0xfff929a4); // розовый
            c.drawCircle(x, y - 5, 10, p); // нос

            p.setColor(Color.WHITE);
            oval = new RectF(x, y - 65, x + 45, y - 5); // правый глаз
            c.drawOval(oval, p);
            oval = new RectF(x - 40, y - 55, x, y - 5); // левый глаз
            c.drawOval(oval, p);

            rect = new RectF(); // зубы
            rect.set(x - 20, y + 41, x - 5, y + 60);
            c.drawRoundRect(rect, 100, 50, p);
            rect.set(x - 4, y + 42, x + 15, y + 57);
            c.drawRoundRect(rect, 100, 50, p);

            p.setColor(Color.BLACK);
            oval = new RectF(x + 2, y - 50, x + 25, y - 20); // правый зрачок
            c.drawOval(oval, p);
            oval = new RectF(x - 2, y - 45, x - 25, y - 15); // левый зрачок
            c.drawOval(oval, p);

            p.setColor(0xff0496c7); // темно-голубой
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10);
            p.setAntiAlias(true);

            rectangle = new RectF(x - 30, y + 10, x + 25, y + 40); // улыбка
            c.drawArc(rectangle, 180, -180, false, p);

            rectangle = new RectF(x + 15, y - 75, x + 35, y - 60); // правая бровь
            c.drawArc(rectangle, 180, 180, false, p);
            rectangle = new RectF(x - 35, y - 77, x - 15, y - 62); // левая бровь
            c.drawArc(rectangle, 180, 180, false, p);
        }

        private void drawFrog(Canvas c, Paint p, float x, float y) {

        }

        private void drawCat(Canvas c, Paint p, float x, float y) {

        }
    }
}
