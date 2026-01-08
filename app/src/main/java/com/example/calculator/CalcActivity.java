package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class CalcActivity extends AppCompatActivity {
    TextView resultText;
    Button equalButton;
    Button delButton;
    Button clearButton;

    String lHS = "";
    String operation = "";
    boolean isEqualClicked = false;
    boolean isDotClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calc);
        resultText = findViewById(R.id.result_text_view);
        equalButton = findViewById(R.id.btn_equal);
        delButton = findViewById(R.id.btn_del);
        clearButton = findViewById(R.id.btn_ac);
        equalButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String rHS = resultText.getText().toString();
                        if (!rHS.isEmpty() && !lHS.isEmpty()) {
                            lHS = calc(lHS, operation, rHS);
                            resultText.setText(lHS);
                            lHS = "";
                            operation = "";
                            isEqualClicked = true;
                        }


                    }
                }
        );
        delButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text=resultText.getText().toString();
                        if(text.length()>1){
                            text= text.substring(0,text.length()-1);
                            resultText.setText(text);
                        }else {
                            resultText.setText("");

                        }


                    }
                }
        );
        clearButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resultText.setText(null);
                        lHS="";
                        operation="";
                        isEqualClicked=false;
                        isDotClicked=false;



                    }
                }
        );
    }

    public void digitOnClick(View view) {
        if (view instanceof Button) {
            Button button = ((Button) view);


            if (isDotClicked && button.getText().toString().equals(".")) {
                return;
            }
            if (isEqualClicked) {
                resultText.setText(((Button) view).getText());
                isEqualClicked = false;
                isDotClicked = false;
            } else {
                resultText.append(button.getText());
                if (button.getText().toString().equals(".")) {
                    isDotClicked = true;
                }
            }
        }
    }

    public void operationOnClick(View view) {
        if (view instanceof Button) {
            if (operation.isEmpty()) {
                if (!resultText.getText().toString().isEmpty()) {
                    lHS = resultText.getText().toString();
                    operation = ((Button) view).getText().toString();
                    resultText.setText(null);
                    isDotClicked = false;
                }
            } else {
                String rHS = resultText.getText().toString();
                if (!rHS.isEmpty()) {
                    lHS = calc(lHS, operation, rHS);
                    operation = ((Button) view).getText().toString();
                    resultText.setText(null);
                    isDotClicked = false;
                }
            }
        }
    }

    public String calc(String l, String o, String r) {
        double lHS = Double.parseDouble(l);
        double rHS = Double.parseDouble(r);
        double result = 0.0;
        if (operation.equals("+")) {
            result = lHS + rHS;
        } else if (operation.equals("-")) {
            result = lHS - rHS;
        } else if (operation.equals("*")) {
            result = lHS * rHS;
        } else if (operation.equals("/")) {
            if (rHS != 0) {
                result = lHS / rHS;
            } else {
                result = result;
            }

        }


        return result + "";

    }


}