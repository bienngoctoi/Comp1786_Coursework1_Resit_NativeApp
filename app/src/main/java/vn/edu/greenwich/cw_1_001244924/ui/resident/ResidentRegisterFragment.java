package vn.edu.greenwich.cw_1_001244924.ui.resident;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

import vn.edu.greenwich.cw_1_001244924.R;
import vn.edu.greenwich.cw_1_001244924.database.ResimaDAO;
import vn.edu.greenwich.cw_1_001244924.models.Resident;
import vn.edu.greenwich.cw_1_001244924.ui.dialog.CalendarFragment;

public class ResidentRegisterFragment extends Fragment
        implements ResidentRegisterConfirmFragment.FragmentListener, CalendarFragment.FragmentListener {
    public static final String ARG_PARAM_RESIDENT = "resident";

    protected EditText fmResidentRegisterName, fmResidentRegisterStartDate , fmResidentRegisterDestination , fmResidentRegisterParticipants, fmResidentRegisterNote , fmResidentRegisterDescription;
    protected LinearLayout fmResidentRegisterLinearLayout;
    protected SwitchMaterial fmResidentRegisterOwner;
    protected TextView fmResidentRegisterError;
    protected Button fmResidentRegisterButton;

    protected ResimaDAO _db;

    public ResidentRegisterFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _db = new ResimaDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_register, container, false);

        fmResidentRegisterError = view.findViewById(R.id.fmResidentRegisterError);
        fmResidentRegisterName = view.findViewById(R.id.fmResidentRegisterName);
        fmResidentRegisterDestination = view.findViewById(R.id.fmResidentRegisterDestination);
        fmResidentRegisterStartDate = view.findViewById(R.id.fmResidentRegisterStartDate);
        fmResidentRegisterParticipants = view.findViewById(R.id.fmResidentRegisterParticipants);
        fmResidentRegisterNote = view.findViewById(R.id.fmResidentRegisterNote);
        fmResidentRegisterDescription = view.findViewById(R.id.fmResidentRegisterDescription);
        fmResidentRegisterOwner = view.findViewById(R.id.fmResidentRegisterOwner);
        fmResidentRegisterButton = view.findViewById(R.id.fmResidentRegisterButton);
        fmResidentRegisterLinearLayout = view.findViewById(R.id.fmResidentRegisterLinearLayout);

        // Show Calendar for choosing a date.
        fmResidentRegisterStartDate.setOnTouchListener((v, motionEvent) -> showCalendar(motionEvent));

        // Update current resident.
        if (getArguments() != null) {
            Resident resident = (Resident) getArguments().getSerializable(ARG_PARAM_RESIDENT);

            fmResidentRegisterName.setText(resident.getName());
            fmResidentRegisterDestination.setText(resident.getDestination());
            fmResidentRegisterParticipants.setText(resident.getParticipants());
            fmResidentRegisterNote.setText(resident.getNote());
            fmResidentRegisterStartDate.setText(resident.getStartDate());
            fmResidentRegisterDescription.setText(resident.getDescription());
            fmResidentRegisterOwner.setChecked(resident.getOwner() == 1 ? true : false);
            fmResidentRegisterButton.setText(R.string.label_update);
            fmResidentRegisterButton.setOnClickListener(v -> update(resident.getId()));

            return view;
        }

        // Create new resident.
        fmResidentRegisterButton.setOnClickListener(v -> register());

        return view;
    }

    protected void register() {
        if (isValidForm()) {
            Resident resident = getResidentFromInput(-1);

            new ResidentRegisterConfirmFragment(resident).show(getChildFragmentManager(), null);

            return;
        }

    }

    protected void update(long id) {
        if (isValidForm()) {
            Resident resident = getResidentFromInput(id);

            long status = _db.updateResident(resident);

            FragmentListener listener = (FragmentListener) getParentFragment();
            listener.sendFromResidentRegisterFragment(status);

            return;
        }

    }

    protected boolean showCalendar(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            new CalendarFragment().show(getChildFragmentManager(), null);
        }

        return false;
    }

    protected Resident getResidentFromInput(long id) {
        String name = fmResidentRegisterName.getText().toString();
        String destination = fmResidentRegisterDestination.getText().toString();
        String participants = fmResidentRegisterParticipants.getText().toString();
        String note = fmResidentRegisterNote.getText().toString();
        String description = fmResidentRegisterDescription.getText().toString();
        String startDate = fmResidentRegisterStartDate.getText().toString();
        int owner = fmResidentRegisterOwner.isChecked() ? 1 : 0;

        return new Resident(id, name, startDate, owner ,destination , participants , note , description );
    }

    protected boolean isValidForm() {
        boolean isValid = true;

        String error = "";
        String name = fmResidentRegisterName.getText().toString();
        String destination = fmResidentRegisterDestination.getText().toString();
        String participants = fmResidentRegisterParticipants.getText().toString();
        String note = fmResidentRegisterNote.getText().toString();
        String description = fmResidentRegisterDescription.getText().toString();
        String startDate = fmResidentRegisterStartDate.getText().toString();

        if (name == null || name.trim().isEmpty()) {
            error += "* " + getString(R.string.error_blank_name) + "\n";
            isValid = false;
        }

        if (destination == null || destination.trim().isEmpty()) {
            error += "* " + "Destination cannot be blank." + "\n";
            isValid = false;
        }

//        if (participants == null || participants.trim().isEmpty()) {
//            error += "* " + "Total Cost of Trip cannot be blank." + "\n";
//            isValid = false;
//        }
//        if (note== null || note.trim().isEmpty()) {
//            error += "* " + "Description cannot be blank." + "\n";
//            isValid = false;
//        }
//
//        if (description== null || description.trim().isEmpty()) {
//            error += "* " + "Transport cannot be blank." + "\n";
//            isValid = false;
//        }

        if (startDate == null || startDate.trim().isEmpty()) {
            error += "* " + getString(R.string.error_blank_start_date) + "\n";
            isValid = false;
        }

        fmResidentRegisterError.setText(error);

        return isValid;
    }


    @Override
    public void sendFromResidentRegisterConfirmFragment(long status) {
        switch ((int) status) {
            case -1:
                Toast.makeText(getContext(), R.string.notification_create_fail, Toast.LENGTH_SHORT).show();
                return;

            default:
                Toast.makeText(getContext(), R.string.notification_create_success, Toast.LENGTH_SHORT).show();

                fmResidentRegisterName.setText("");
                fmResidentRegisterDestination.setText("");
                fmResidentRegisterParticipants.setText("");
                fmResidentRegisterNote.setText("");
                fmResidentRegisterDescription.setText("");
                fmResidentRegisterStartDate.setText("");
                fmResidentRegisterName.requestFocus();
        }
    }

    @Override
    public void sendFromCalendarFragment(String date) {
        fmResidentRegisterStartDate.setText(date);
    }

    public interface FragmentListener {
        void sendFromResidentRegisterFragment(long status);
    }
}