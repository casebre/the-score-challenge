package com.thescore.nbateamviewer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thescore.nbateamviewer.R;
import com.thescore.nbateamviewer.entities.Player;

import java.util.ArrayList;

/**
 * Created by rafael on 26/03/18.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    private ArrayList<Player> playerList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFirsName, txtLastName, txtPosition, txtNumber;

        public ViewHolder(View view) {
            super(view);
            txtFirsName = view.findViewById(R.id.textview_firstname);
            txtLastName = view.findViewById(R.id.textview_lastname);
            txtPosition = view.findViewById(R.id.textview_position);
            txtNumber = view.findViewById(R.id.textview_number);
        }
    }

    public PlayerListAdapter(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtFirsName.setText(playerList.get(position).getFirst_name());
        holder.txtLastName.setText(playerList.get(position).getLast_name());
        holder.txtPosition.setText(playerList.get(position).getPosition());
        holder.txtNumber.setText(String.valueOf(playerList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
