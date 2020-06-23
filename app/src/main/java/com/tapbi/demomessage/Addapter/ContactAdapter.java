package com.tapbi.demomessage.Addapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tapbi.demomessage.DTO.ItemContact;
import com.tapbi.demomessage.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolderContact> {
    private List<ItemContact> contactList;
    private OnCallBack mListener;
    private int VIEW_TYPE_LOADING = 1;
    private int VIEW_TYPE_ITEM = 0;

    public ContactAdapter(List<ItemContact> contactList, OnCallBack listener) {
        this.contactList = contactList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderContact onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        return new ViewHolderContact(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContact holder, int position) {
            ItemContact itemContact = contactList.get(position);
            holder.tv_name_contact.setText(itemContact.getName());
            holder.tv_number.setText(itemContact.getNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolderContact extends RecyclerView.ViewHolder{
         TextView tv_name_contact, tv_number;
         ImageView iv_contact;
         RelativeLayout relativeLayout;
        public ViewHolderContact(@NonNull View itemView) {
            super(itemView);
            tv_name_contact = itemView.findViewById(R.id.tv_name_contact);
            tv_number = itemView.findViewById(R.id.tv_number);
            iv_contact = itemView.findViewById(R.id.iv_contact);
            relativeLayout = itemView.findViewById(R.id.rl_item);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mListener.onItemClicked(getPosition());
               }
           });
        }
    }

    public void filterList(ArrayList<ItemContact> filterdNames) {
        this.contactList = filterdNames;
        notifyDataSetChanged();
    }

    public interface OnCallBack{
        void onItemClicked(int position);
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return contactList.get(position) == null ? VIEW_TYPE_LOADING: VIEW_TYPE_ITEM;
    }
}
