package vn.edu.greenwich.cw_1_001244924.ui.resident;

import static vn.edu.greenwich.cw_1_001244924.R.id.fmResidentRegisterConfirmNote;
import static vn.edu.greenwich.cw_1_001244924.R.id.fmResidentRegisterConfirmParticipants;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import vn.edu.greenwich.cw_1_001244924.R;
import vn.edu.greenwich.cw_1_001244924.database.ResimaDAO;
import vn.edu.greenwich.cw_1_001244924.models.Resident;

public class ResidentRegisterConfirmFragment extends DialogFragment {
    protected ResimaDAO _db;
    protected Resident _resident;
    protected Button fmResidentRegisterConfirmButtonConfirm, fmResidentRegisterConfirmButtonCancel;
    protected TextView fmResidentRegisterConfirmName, fmResidentRegisterConfirmStartDate, fmResidentRegisterConfirmOwner ,fmResidentRegisterConfirmDestination , fmResidentRegisterConfirmParticipants,fmResidentRegisterConfirmNote, fmResidentRegisterConfirmDescription;

    public ResidentRegisterConfirmFragment() {
        _resident = new Resident();
    }

    public ResidentRegisterConfirmFragment(Resident resident) {
        _resident = resident;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _db = new ResimaDAO(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_register_confirm, container, false);

        String name = getString(R.string.error_no_info);
        String startDate = getString(R.string.error_no_info);
        String ownerType = getString(R.string.error_no_info);
        String destination = getString(R.string.error_no_info);
        String participants = getString(R.string.error_no_info);
        String description = getString(R.string.error_no_info);
        String note = getString(R.string.error_no_info);


        fmResidentRegisterConfirmName = view.findViewById(R.id.fmResidentRegisterConfirmName);

        fmResidentRegisterConfirmDestination = view.findViewById(R.id.fmResidentRegisterConfirmDestination);
        fmResidentRegisterConfirmParticipants = view.findViewById(R.id.fmResidentRegisterConfirmParticipants);

        fmResidentRegisterConfirmNote = view.findViewById(R.id.fmResidentRegisterConfirmNote);
        fmResidentRegisterConfirmDescription = view.findViewById(R.id.fmResidentRegisterConfirmDescription);

        fmResidentRegisterConfirmStartDate = view.findViewById(R.id.fmResidentRegisterConfirmStartDate);
        fmResidentRegisterConfirmOwner = view.findViewById(R.id.fmResidentRegisterConfirmOwner);
        fmResidentRegisterConfirmButtonCancel = view.findViewById(R.id.fmResidentRegisterConfirmButtonCancel);
        fmResidentRegisterConfirmButtonConfirm = view.findViewById(R.id.fmResidentRegisterConfirmButtonConfirm);

        if (_resident.getOwner() != -1) {
            ownerType = _resident.getOwner() == 1 ? "Yes" : "No";
        }

        if (_resident.getName() != null && !_resident.getName().trim().isEmpty()) {
            name = _resident.getName();
        }

        if (_resident.getStartDate() != null && !_resident.getStartDate().trim().isEmpty()) {
            startDate = _resident.getStartDate();
        }

        if (_resident.getDescription() != null && !_resident.getDescription().trim().isEmpty()) {
            description = _resident.getDescription();
        }

        if (_resident.getDestination() != null && !_resident.getDestination().trim().isEmpty()) {
            destination = _resident.getDestination();
        }
        if (_resident.getParticipants() != null && !_resident.getParticipants().trim().isEmpty()) {
            participants = _resident.getParticipants();
        }

        if (_resident.getNote() != null && !_resident.getNote().trim().isEmpty()) {
            note = _resident.getNote();
        }

        fmResidentRegisterConfirmName.setText(name);
        fmResidentRegisterConfirmStartDate.setText(startDate);
        fmResidentRegisterConfirmOwner.setText(ownerType);
        fmResidentRegisterConfirmDestination.setText(destination);
        fmResidentRegisterConfirmParticipants.setText(participants);
        fmResidentRegisterConfirmDescription.setText(description);
        fmResidentRegisterConfirmNote.setText(note);

        fmResidentRegisterConfirmButtonCancel.setOnClickListener(v -> dismiss());
        fmResidentRegisterConfirmButtonConfirm.setOnClickListener(v -> confirm());

        return view;
    }

    protected void confirm() {
        long status = _db.insertResident(_resident);

        FragmentListener listener = (FragmentListener) getParentFragment();
        listener.sendFromResidentRegisterConfirmFragment(status);

        dismiss();
    }

    public interface FragmentListener {
        void sendFromResidentRegisterConfirmFragment(long status);
    }
}