package com.example.thecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util.Arith;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_result;
    private String operator = "";
    private String firstNum = "";
    private String nextNum = "";
    private String result = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_result = findViewById(R.id.tv_result);
        tv_result.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        String inputText;
        if (resId == R.id.btn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) v).getText().toString();
        }
        if (resId == R.id.btn_clear) {
            clear("");
        } else if (resId == R.id.btn_cancel) {
            if (operator.equals("")) {
                if (firstNum.length() == 1) {
                    firstNum = "0";
                } else if (firstNum.length() > 1) {
                    firstNum = firstNum.substring(0, firstNum.length() - 1);
                } else {
                    Toast.makeText(this, "没有可取消的数字了", Toast.LENGTH_SHORT).show();
                    return;
                }
                showText = firstNum;
                tv_result.setText(showText);
            } else {
                if (nextNum.length() == 1) {
                    nextNum = "";
                } else if (nextNum.length() > 1) {
                    nextNum = nextNum.substring(0, nextNum.length() - 1);
                } else {
                    Toast.makeText(this, "没有可取消的数字了", Toast.LENGTH_SHORT).show();
                    return;
                }
                showText = showText.substring(0, showText.length() - 1);
                tv_result.setText(showText);
            }
        } else if (resId == R.id.btn_equal) {
            if (operator.length() == 0 || operator.equals("=")) {
                Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
                return;
            } else if (nextNum.length() <= 0) {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (caculate()) {
                operator = inputText;
                showText = showText + "=" + result;
                tv_result.setText(showText);
            } else {
                return;
            }
        } else if (resId == R.id.btn_plus || resId == R.id.btn_minus || resId == R.id.btn_multiply || resId == R.id.btn_divide) { // 点击了加、减、乘、除按钮
            if (firstNum.length() <= 0) {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (operator.length() == 0 || operator.equals("=") || operator.equals("√")) {
                operator = inputText;
                showText = showText + operator;
                tv_result.setText(showText);
            } else {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (resId == R.id.btn_sqrt) {
            if (firstNum.length() <= 0) {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Double.parseDouble(firstNum) < 0) {
                Toast.makeText(this, "开根号的数值不能小于0", Toast.LENGTH_SHORT).show();
                return;
            }

            result = String.valueOf(Math.sqrt(Double.parseDouble(firstNum)));
            firstNum = result;
            nextNum = "";
            operator = inputText;
            showText = showText + "√=" + result;
            tv_result.setText(showText);
        } else {
            if (operator.equals("=")) {
                operator = "";
                firstNum = "";
                showText = "";
            }
            if (resId == R.id.btn_dot) {
                inputText = ".";
            }
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                nextNum = nextNum + inputText;
            }
            showText = showText + inputText;
            tv_result.setText(showText);
        }
    }

    private void clear(String s) {
        showText = s;
        tv_result.setText(showText);
        operator = "";
        firstNum = "";
        nextNum = "";
        result = "";
    }

    private boolean caculate() {
        if (operator.equals("+")) {
            result = Arith.add(firstNum, nextNum);
        } else if (operator.equals("-")) {
            result = Arith.sub(firstNum, nextNum);
        } else if (operator.equals("×")) {
            result = Arith.mul(firstNum, nextNum);
        } else if (operator.equals("÷")) {
            if ("0".equals(nextNum)) {
                Toast.makeText(this, "被除数不能为零", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                result = Arith.div(firstNum, nextNum);
            }
        }
        firstNum = result;
        nextNum = "";
        return true;
    }
}

