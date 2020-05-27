package com.thrivikraman.sreejith.dev.splitter.networks;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class firbaseConnectivity {

    private DatabaseReference database;

    public firbaseConnectivity() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabasePath(String path) {
        database = database.child(path);
        return database;
    }
}
