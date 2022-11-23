package br.buss.calculodeaprovacao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        Intent it = getIntent();
        String name = it.getStringExtra("name");
        String situation = it.getStringExtra("studentSituation");
        Double average = it.getDoubleExtra("average", 0.0);
        Integer frequency = it.getIntExtra("frequency", 0);

        TextView nameView = findViewById(R.id.studentNameView);
        TextView situationView = findViewById(R.id.studentSituationView);
        TextView averageView = findViewById(R.id.studentGradeView);
        TextView frequencyView = findViewById(R.id.studentFrequencyView);

        if (average < 4) {
            averageView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (average < 7) {
            averageView.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }

        if (frequency < 75) {
            frequencyView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        if (situation.equals("Reprovado por falta") || situation.equals("Reprovado por nota")) {
            situationView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (situation.equals("Final")) {
            situationView.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }

        nameView.setText(name);
        situationView.setText(situation);
        averageView.setText(String.valueOf(average));
        frequencyView.setText(frequency + "%");

    }
}