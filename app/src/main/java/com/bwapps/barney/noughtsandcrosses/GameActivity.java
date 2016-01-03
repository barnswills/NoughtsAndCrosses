package com.bwapps.barney.noughtsandcrosses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity
{
    private TextView squareOne, squareTwo, squareThree, squareFour;
    private TextView squareFive, squareSix, squareSeven, squareEight, squareNine;

    private TextView[] squares;

    private String touchInput = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        squareOne = (TextView) findViewById(R.id.square1);
        squareTwo = (TextView) findViewById(R.id.square2);
        squareThree = (TextView) findViewById(R.id.square3);
        squareFour = (TextView) findViewById(R.id.square4);
        squareFive = (TextView) findViewById(R.id.square5);
        squareSix = (TextView) findViewById(R.id.square6);
        squareSeven = (TextView) findViewById(R.id.square7);
        squareEight = (TextView) findViewById(R.id.square8);
        squareNine = (TextView) findViewById(R.id.square9);

        squares = new TextView[]{squareOne, squareTwo, squareThree,
                squareFour, squareFive, squareSix,
                squareSeven, squareEight, squareNine};

        clearBoard();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        for (final TextView square : squares)
        {
            square.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (!square.getText().equals(""))
                    {
                        Toast.makeText(getBaseContext(), "Square already occupied!",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        square.setText(touchInput);

                        checkBoard(touchInput);

                        if (touchInput.equals("X")) touchInput = "O";
                        else touchInput = "X";
                    }
                }
            });
        }
    }

    private void checkBoard(String input)
    {
        int squaresFilled = 0;

        if (squareOne.getText().equals(input) && squareTwo.getText().equals(input) &&
                squareThree.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareOne.getText().equals(input) && squareFour.getText().equals(input) &&
                squareSeven.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareThree.getText().equals(input) && squareSix.getText().equals(input) &&
                squareNine.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareTwo.getText().equals(input) && squareFive.getText().equals(input) &&
                squareEight.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareOne.getText().equals(input) && squareFive.getText().equals(input) &&
                squareNine.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareThree.getText().equals(input) && squareFive.getText().equals(input) &&
                squareSeven.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareFour.getText().equals(input) && squareFive.getText().equals(input) &&
                squareSix.getText().equals(input))
        {
            declareResult(input);
        }

        else if (squareSeven.getText().equals(input) && squareEight.getText().equals(input) &&
                squareNine.getText().equals(input))
        {
            declareResult(input);
        }

        else
        {
            for (TextView square : squares)
            {
                if (!square.getText().equals(""))
                {
                    squaresFilled++;
                }
            }

            if (squaresFilled == 9)
            {
                declareResult("Draw");
            }
        }
    }

    private void declareResult(String input)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (input.equals("Draw"))
        {
            builder.setMessage(input);
        }
        else
        {
            builder.setMessage(input + " is the Winner!");
        }

        builder.setCancelable(false);

        builder.setNeutralButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                clearBoard();
            }
        });

        builder.show();
    }

    private void clearBoard()
    {
        for (TextView square : squares)
        {
            square.setText("");
        }
    }
}
