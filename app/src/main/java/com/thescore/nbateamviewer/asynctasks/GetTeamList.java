package com.thescore.nbateamviewer.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.thescore.nbateamviewer.connections.JSONParser;
import com.thescore.nbateamviewer.entities.Team;

import java.util.ArrayList;

/**
 * Created by rafael on 26/03/18.
 */

public class GetTeamList extends AsyncTask<Void, Void, ArrayList<Team>> {

    private OnGetTeamList listener;
    private Context context;


    public interface OnGetTeamList {
        void onTeamListReady(ArrayList<Team> teams);
    }

    public GetTeamList(OnGetTeamList listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ArrayList<Team> doInBackground(Void... params) {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.getTeams(context);
    }

    @Override
    protected void onPostExecute(ArrayList<Team> teams) {
        listener.onTeamListReady(teams);
    }


}
