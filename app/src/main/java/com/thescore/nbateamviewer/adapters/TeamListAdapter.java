package com.thescore.nbateamviewer.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thescore.nbateamviewer.R;
import com.thescore.nbateamviewer.entities.Team;
import com.thescore.nbateamviewer.interfaces.OnTeamItemSelected;

import java.util.ArrayList;

/**
 * Created by rafael on 26/03/18.
 */

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {

    private ArrayList<Team> teamList = new ArrayList<>();
    private OnTeamItemSelected teamClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewTeam;
        private TextView txtTeamName, txtTotalWins, txtTotalLosses, txtAvatar;

        public ViewHolder(View view) {
            super(view);
            cardViewTeam = view.findViewById(R.id.cardview_team);
            txtTeamName = view.findViewById(R.id.textview_teamname);
            txtTotalWins = view.findViewById(R.id.textview_wins);
            txtTotalLosses = view.findViewById(R.id.textview_losses);
            txtAvatar = view.findViewById(R.id.avatar);
        }
    }

    public TeamListAdapter(ArrayList<Team> teamList, Context context) {
        this.teamList = teamList;
        this.teamClickListener = (OnTeamItemSelected) context;
    }

    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTeamName.setText(teamList.get(position).getFull_name());
        holder.txtTotalWins.setText(String.valueOf(teamList.get(position).getWins()));
        holder.txtTotalLosses.setText(String.valueOf(teamList.get(position).getLosses()));
        holder.cardViewTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamClickListener.onTeamItemSelected(teamList.get(position));
            }
        });
        holder.txtAvatar.setText(teamList.get(position).getFull_name().substring(0,1));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
