package com.thescore.nbateamviewer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.thescore.nbateamviewer.R;
import com.thescore.nbateamviewer.adapters.PlayerListAdapter;
import com.thescore.nbateamviewer.entities.Team;

public class TeamPageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPlayers;
    private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_page);
        if(getIntent().getSerializableExtra("TEAM") != null)
            team = (Team)getIntent().getSerializableExtra("TEAM");

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(team.getFull_name());
            toolbar.setTitleTextColor(getResources().getColor(R.color.fontTitle));

            TextView txtWins = findViewById(R.id.textview_wins);
            TextView txtLosses = findViewById(R.id.textview_losses);
            txtWins.setText(String.valueOf(team.getWins()));
            txtLosses.setText(String.valueOf(team.getLosses()));
        }

        recyclerViewPlayers = findViewById(R.id.recyclerview_players);

    }

    @Override
    public void onStart() {
        super.onStart();

        PlayerListAdapter adapter = new PlayerListAdapter(team.getPlayers());
        recyclerViewPlayers.setAdapter(adapter);
        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(TeamPageActivity.this));
        recyclerViewPlayers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlayers.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
            }
            return true;
        }
    }
