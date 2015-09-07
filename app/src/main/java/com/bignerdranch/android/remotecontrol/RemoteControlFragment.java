package com.bignerdranch.android.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RemoteControlFragment extends Fragment {

    private TextView selectedTextView;
    private TextView workingTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_remote_control, parent, false);

        selectedTextView = (TextView) view
                .findViewById(R.id.fragment_remote_control_selectedTextView);
        workingTextView = (TextView) view
                .findViewById(R.id.fragment_remote_control_workingTextView);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String working = workingTextView.getText().toString();
                String text = textView.getText().toString();
                if (working.equals("0")) {
                    workingTextView.setText(text);
                } else {
                    workingTextView.setText(working + text);
                }
            }
        };

        TableLayout tableLayout = (TableLayout) view
                .findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
            // starts at index 2 to skip the two text views
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button button = (Button) row.getChildAt(j);
                button.setText("" + number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        TableRow tableRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);

        Button deleteButton = (Button) tableRow.getChildAt(0);
        deleteButton.setText(R.string.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });

        Button zeroButton = (Button) tableRow.getChildAt(1);
        zeroButton.setText(R.string.zero);
        zeroButton.setOnClickListener(numberButtonListener);

        Button enterButton = (Button) tableRow.getChildAt(2);
        enterButton.setText(R.string.enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CharSequence working = workingTextView.getText();
                if (working.length() > 0)
                    selectedTextView.setText(working);
                workingTextView.setText("0");
            }
        });

        return view;
    }
}
