package com.example.firstandroid;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class PieChartMainActivity extends AppCompatActivity {
    private Button buttonShowPieChart;
    private EditText inputPersent;
    private PieChartt pieChart;
    private List<PieChartt.Slice> list;
    private Float[] values = {40f, 30f, 30f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        buttonShowPieChart = (Button) findViewById(R.id.draw_diagram);
        inputPersent = (EditText) findViewById(R.id.input_percent);
        drawDefaultValuesPieChart();

        buttonShowPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawInputValuesPieChart();
                onHideKeyboard();
                buttonShowPieChart.setEnabled(false);
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

    public void drawDefaultValuesPieChart() {
        pieChart = (PieChartt) findViewById(R.id.pie_chart);
        list = new ArrayList<>();
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_1), values[0]));
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_2), values[1]));
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_3), values[2]));
        pieChart.setSlices(list);
    }

    public void drawInputValuesPieChart() {
        pieChart = (PieChartt) findViewById(R.id.pie_chart);
        list = new ArrayList<>();
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_1), getDataFromEditText()[0]));
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_2), getDataFromEditText()[1]));
        list.add(new PieChartt.Slice(
                getResources().getColor(R.color.slice_color_3), getDataFromEditText()[2]));
        pieChart.setSlices(list);
    }
}