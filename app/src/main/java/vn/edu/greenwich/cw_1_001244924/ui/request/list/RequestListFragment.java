package vn.edu.greenwich.cw_1_001244924.ui.request.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.greenwich.cw_1_001244924.R;
import vn.edu.greenwich.cw_1_001244924.database.ResimaDAO;
import vn.edu.greenwich.cw_1_001244924.models.Request;

public class RequestListFragment extends Fragment {
    public static final String ARG_PARAM_RESIDENT_ID = "resident_id";

    protected ArrayList<Request> _requestList = new ArrayList<>();

    protected ResimaDAO _db;
    protected TextView fmRequestListEmptyNotice;
    protected RecyclerView fmRequestListRecylerView;

    public RequestListFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        _db = new ResimaDAO(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_list, container, false);

        if (getArguments() != null) {
            Request request = new Request();
            request.setResidentId(getArguments().getLong(ARG_PARAM_RESIDENT_ID));

            _requestList = _db.getRequestList(request, null, false);
        }

        fmRequestListRecylerView = view.findViewById(R.id.fmRequestListRecylerView);
        fmRequestListEmptyNotice = view.findViewById(R.id.fmRequestListEmptyNotice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());

        fmRequestListRecylerView.addItemDecoration(dividerItemDecoration);
        fmRequestListRecylerView.setAdapter(new RequestAdapter(_requestList));
        fmRequestListRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show "No Request." message.
        fmRequestListEmptyNotice.setVisibility(_requestList.isEmpty() ? View.VISIBLE : View.GONE);

        return view;
    }
}