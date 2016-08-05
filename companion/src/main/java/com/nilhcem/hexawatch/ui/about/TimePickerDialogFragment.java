package com.nilhcem.hexawatch.ui.about;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

public class TimePickerDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = TimePickerDialogFragment.class.getSimpleName();
    private static final String ARG_HOUR = "hour";
    private static final String ARG_MINUTE = "minute";

    public static void show(FragmentManager childFragmentManager, int hour, int minute) {
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_HOUR, hour);
        arguments.putInt(ARG_MINUTE, minute);
        fragment.setArguments(arguments);
        fragment.show(childFragmentManager, TimePickerDialogFragment.TAG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerDialog.OnTimeSetListener listener;
        try {
            listener = (TimePickerDialog.OnTimeSetListener) getParentFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "Calling fragment must implement Callback interface");
            throw e;
        }

        Bundle args = getArguments();
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), listener, args.getInt(ARG_HOUR), args.getInt(ARG_MINUTE), true);
        dialog.setTitle(null);
        return dialog;
    }
}
