package com.tikoyapps.justgothome.data;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.dbflow.android.sqlcipher.SQLCipherOpenHelper;

/**
 * Created by xcptan on 04/09/2016.
 */
public class DBHelper extends SQLCipherOpenHelper {
    public DBHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener listener) {
        super(databaseDefinition, listener);
    }

    @Override
    protected String getCipherSecret() {
        //TODO Encrypt cipher secret
        return "onlyt1koy";
    }
}
