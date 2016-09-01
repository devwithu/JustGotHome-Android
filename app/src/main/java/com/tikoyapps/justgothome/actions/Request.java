package com.tikoyapps.justgothome.actions;

import com.tikoyapps.justgothome.BuildConfig;

/**
 * Created by xcptan on 4/27/16.
 */
public interface Request {
    String SEND_SMS = BuildConfig.APPLICATION_ID+".action.send_sms";
    String GET_CID = BuildConfig.APPLICATION_ID+".action.get_cid";
}
