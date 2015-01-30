package com.helieu.androidtutorials.recyclerview.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.helieu.androidtutorials.R;
import com.helieu.androidtutorials.recyclerview.listeners.InputDialogCallback;

/**
 * Created by Neil on 15/01/30.
 */
public class InputDialog extends Dialog {

    private InputDialogCallback inputDialogCallback;

    public InputDialog(Context context, InputDialogCallback inputDialogCallback) {
        super(context);

        this.inputDialogCallback = inputDialogCallback;

        initializeDialog();
    }

    private void initializeDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.input_dialog_layout);
        findViewById(R.id.saveButton).setOnClickListener(dialogButtonClickListener);
        findViewById(R.id.cancelButton).setOnClickListener(dialogButtonClickListener);
    }

    private String getTextFromInput() {
        return ((EditText) findViewById(R.id.inputField)).getText().toString();
    }

    private View.OnClickListener dialogButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch (id) {
                case R.id.saveButton:
                    inputDialogCallback.inputText(getTextFromInput());
                    dismiss();
                    break;
                case R.id.cancelButton:
                    dismiss();
                    break;
            }
        }
    };
}
