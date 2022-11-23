package br.buss.calculodeaprovacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        EditText studentNameView = findViewById(R.id.editTextStudentName);
        EditText gradeOneView = findViewById(R.id.editTextGradeOne);
        EditText gradeTwoView = findViewById(R.id.editTextGradeTwo);
        EditText frequencyView = findViewById(R.id.editTextFrequency);

        if (verifyNullFields(studentNameView, gradeOneView, gradeTwoView, frequencyView)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            String name = studentNameView.getText().toString();
            Double gradeOne = Double.parseDouble(gradeOneView.getText().toString());
            Double gradeTwo = Double.parseDouble(gradeTwoView.getText().toString());
            Integer frequency = Integer.parseInt(frequencyView.getText().toString());
            Double average = (gradeOne + gradeTwo) / 2;
            String studentSituation = getStudentSituation(average, frequency);

            Intent it = new Intent(this, SituationActivity.class);
            it.putExtra("name", name);
            it.putExtra("average", average);
            it.putExtra("frequency", frequency);
            it.putExtra("studentSituation", studentSituation);
            startActivity(it);

        }

    }

    private boolean verifyNullFields(EditText studentNameView, EditText gradeOneView, EditText gradeTwoView, EditText frequencyView) {
        return studentNameView.length() == 0 || gradeOneView.length() == 0
                || gradeTwoView.length() == 0 || frequencyView.length() == 0;
    }

    @NonNull
    private String getStudentSituation(Double average, Integer frequency) {
        String studentSituation;
        if (frequency < 75) {
            studentSituation = "Reprovado por falta";
        }
        else {
            if (average >= 7) {
                studentSituation = "Aprovado";
            } else if (average > 4 && average < 7) {
                studentSituation = "Final";
            } else {
                studentSituation = "Reprovado por nota";
            }
        }
        return studentSituation;
    }
}