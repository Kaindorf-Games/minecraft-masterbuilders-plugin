package com.gmail.stefvanschiedev.buildinggame.leaderboard;

import com.google.gson.Gson;
import org.bukkit.Bukkit;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Leaderboard {
    private String type;
    private Map<String, String> sortedBy;
    private List<Column> columns;
    private List<Member> members;

    public Leaderboard() {
        this.type = "Group";

        this.sortedBy = new HashMap<String, String>();
        this.sortedBy.put("1", "Wins");
        this.sortedBy.put("2", "Points");
        this.sortedBy.put("3", "Average Points");
        this.sortedBy.put("4", "Plays");

        this.columns = new LinkedList<Column>();
        this.columns.add(new Column("Name", "Name", false, false));
        this.columns.add(new Column("Plays", "Plays", true, false));
        this.columns.add(new Column("Average Points", "Average Points", true, true));
        this.columns.add(new Column("Points", "Points", true, false));
        this.columns.add(new Column("Wins", "Wins", true, false));

        this.members = new LinkedList<Member>();
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
            "type='" + type + '\'' +
            ", sortedBy=" + sortedBy +
            ", columns=" + columns +
            ", members=" + members +
            '}';
    }

    private String getBasicAuthenticationHeader() {
        String username = "game";
        String password = "1234";
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public void send() {
        Bukkit.getLogger().info("Sending Leaderboard");
        String hostname = "http://leaderboard:8000";

        try {
            URL url = new URL(hostname + "/leaderboard/masterbuilders");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", getBasicAuthenticationHeader());
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(this);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.getMessage());
            Bukkit.getLogger().warning(e.getStackTrace().toString());
        }

        Bukkit.getLogger().info("Leaderboard sent");
    }
}
