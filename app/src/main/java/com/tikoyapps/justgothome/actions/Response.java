package com.tikoyapps.justgothome.actions;

import com.tikoyapps.justgothome.BuildConfig;

/**
 * Created by xcptan on 4/27/16.
 */
public interface Response {
    String SEND_SMS_SUCCESS = BuildConfig.APPLICATION_ID+".action.send_sms.success";
    String SEND_SMS_FAIL = BuildConfig.APPLICATION_ID+".action.send_sms.fail";
}
