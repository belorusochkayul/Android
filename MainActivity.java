package com.example.firstandroid;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonShowPieChart;
    private EditText inputPersent;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        buttonShowPieChart = (Button) findViewById(R.id.draw_diagram);
        inputPersent = (EditText) findViewById(R.id.input_percent);

        buttonShowPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawProgramValuesPieChart();
                onHideKeyboard();
                // pieChart.invalidate();
                if (flag) {
                    drawInputValuesPieChart();
                    // pieChart.invalidate();
                    //onHideKeyboard();
                    flag = false;
                } else {

                    drawInputValuesPieChart();
                    onHideKeyboard();
                    flag = true;
                }
                //inputPersent.getText().clear();
                //buttonShowPieChart.setEnabled(false);
            }
        });
    }

    public void onHideKeyboard() {
        InputMethodManager imm;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(buttonShowPieChart.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public float[] getDataFromEditText() {
        String inputResult = inputPersent.getText().toString();
        inputResult = inputResult.replaceAll("\\s+", "");
        String[] ss = inputResult.split(",(?!\\s)");
        float result[] = new float[ss.length];
        for (int i = 0; i < ss.length; i++) {
            result[i] = Float.parseFloat(ss[i]);
        }
        return result;
    }

    public void drawProgramValuesPieChart() {
        PieChartt pieChart = (PieChartt) findViewById(R.id.pie_chart);
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_1), 40f));
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_2), 20f));
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_3), 40f));
    }

    public void drawInputValuesPieChart() {
        PieChartt pieChart = (PieChartt) findViewById(R.id.pie_chart);
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_1), getDataFromEditText()[0]));
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_2), getDataFromEditText()[1]));
        pieChart.addSlice(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_3), getDataFromEditText()[2]));
    }

}