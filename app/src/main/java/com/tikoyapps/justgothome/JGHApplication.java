package com.tikoyapps.justgothome;

import android.app.Application;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.android.dbflow.structure.database.OpenHelper;
import com.tikoyapps.justgothome.data.DBHelper;
import com.tikoyapps.justgothome.data.JGHDatabase;

/**
 * Created by xcptan on 4/27/16.
 */
public class JGHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this)
            .addDatabaseConfig(
                new DatabaseConfig.Builder(JGHDatabase.class)
                    .openHelper(new DatabaseConfig.OpenHelperCreator() {
                        @Override
                        public OpenHelper createHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener helperListener) {
                            return new DBHelper(databaseDefinition, helperListener);
                        }
                    })
                    .build())
            .build());
    }
}
