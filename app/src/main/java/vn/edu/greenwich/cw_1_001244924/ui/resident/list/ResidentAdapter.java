package vn.edu.greenwich.cw_1_001244924.ui.resident.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.greenwich.cw_1_001244924.R;
import vn.edu.greenwich.cw_1_001244924.models.Resident;
import vn.edu.greenwich.cw_1_001244924.ui.resident.ResidentDetailFragment;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentAdapter.ViewHolder> implements Filterable {
    protected ArrayList<Resident> _originalList;
    protected ArrayList<Resident> _filteredList;
    protected ItemFilter _itemFilter = new ItemFilter();

    public ResidentAdapter(ArrayList<Resident> list) {
        _originalList = list;
        _filteredList = list;
    }

    public void updateList(ArrayList<Resident> list) {
        _originalList = list;
        _filteredList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_resident, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resident resident = _filteredList.get(position);

        String owner = holder.itemView.getResources().getString(R.string.label_owner);
        String tenant = holder.itemView.getResources().getString(R.string.label_tenant);

        holder.listItemResidentName.setText("Name Trip: "+resident.getName());
        holder.listItemResidentStartDate.setText("Date: "+resident.getStartDate());
        holder.listItemResidentOwner.setText(resident.getOwner() == 1 ? owner : tenant);
    }

    @Override
    public int getItemCount() {
        return _filteredList == null ? 0 : _filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return _itemFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout listItemResident;
        protected TextView listItemResidentName, listItemResidentStartDate, listItemResidentOwner;

        public ViewHolder(View itemView) {
            super(itemView);

            listItemResidentName = itemView.findViewById(R.id.listItemResidentName);
            listItemResidentStartDate = itemView.findViewById(R.id.listItemResidentStartDate);
            listItemResidentOwner = itemView.findViewById(R.id.listItemResidentOwner);

            listItemResident = itemView.findViewById(R.id.listItemResident);
            listItemResident.setOnClickListener(v -> showDetail(v));
        }

        protected void showDetail(View view) {
            Resident resident = _filteredList.get(getAdapterPosition());

            Bundle bundle = new Bundle();
            bundle.putSerializable(ResidentDetailFragment.ARG_PARAM_RESIDENT, resident);

            Navigation.findNavController(view).navigate(R.id.residentDetailFragment, bundle);
        }
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final ArrayList<Resident> list = _originalList;
            final ArrayList<Resident> nlist = new ArrayList<>(list.size());

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            for (Resident resident : list) {
                String filterableString = resident.toString();
                if (filterableString.toLowerCase().contains(filterString)) nlist.add(resident);
            }

            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _filteredList = (ArrayList<Resident>) results.values;
            notifyDataSetChanged();
        }
    }
}