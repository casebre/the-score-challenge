package com.thescore.nbateamviewer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.thescore.nbateamviewer.R;
import com.thescore.nbateamviewer.adapters.TeamListAdapter;
import com.thescore.nbateamviewer.asynctasks.GetTeamList;
import com.thescore.nbateamviewer.entities.Team;
import com.thescore.nbateamviewer.interfaces.OnTeamItemSelected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements OnTeamItemSelected {

    private RecyclerView recyclerViewTeam;
    private Spinner spnFilter;
    private ArrayList<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewTeam = findViewById(R.id.recyclerview_teamlist);
        spnFilter = findViewById(R.id.spinner_filter);

        new GetTeamList(new GetTeamList.OnGetTeamList() {
            @Override
            public void onTeamListReady(ArrayList<Team> teams) {
                mTeams = teams;
                Collections.sort(teams, new Comparator<Team>() {
                    @Override
                    public int compare(Team o1, Team o2) {
                        return o1.getFull_name().compareToIgnoreCase(o2.getFull_name());
                    }
                });
                populateTeamList(teams);
            }
        }, MainActivity.this).execute();

        ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.filter_options)
        );

        spnFilter.setAdapter(spnAdapter);
        spnFilter.setSelection(0);

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mTeams != null) {
                    Collections.sort(mTeams, new Comparator<Team>() {
                        @Override
                        public int compare(Team o1, Team o2) {
                            if (spnFilter.getSelectedItemId() == 0) {
                                return o1.getFull_name().compareToIgnoreCase(o2.getFull_name());
                            } else if (spnFilter.getSelectedItemId() == 1) {
                                return ((Integer) o2.getWins()).compareTo(o1.getWins());
                            } else if (spnFilter.getSelectedItemId() == 2) {
                                return ((Integer) o2.getLosses()).compareTo(o1.getLosses());
                            }
                            return 0;
                        }
                    });
                    populateTeamList(mTeams);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onTeamItemSelected(Team team) {
        Intent intent = new Intent(MainActivity.this,
                TeamPageActivity.class);
        intent.putExtra("TEAM", team);
        startActivity(intent);

    }

    public void populateTeamList(ArrayList<Team> teams) {
        TeamListAdapter adapter = new TeamListAdapter(teams, MainActivity.this);
        recyclerViewTeam.setAdapter(adapter);
        recyclerViewTeam.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewTeam.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTeam.setAdapter(adapter);
    }
}
