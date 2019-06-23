package com.kristurek.polskatv.ui.login.interactor;

import android.content.Context;

import com.kristurek.polskatv.service.DiagnosticService;
import com.kristurek.polskatv.service.LoggerService;
import com.kristurek.polskatv.service.RemoteServerService;
import com.kristurek.polskatv.ui.arch.ArrayParamAbstractInteractor;

import org.joda.time.LocalDateTime;

import java.io.File;

public class GenerateAndUploadLogsInteractor extends ArrayParamAbstractInteractor<Boolean, Object> {

    private RemoteServerService remoteService;
    private LoggerService logService;
    private DiagnosticService diagService;
    private Context context;

    public GenerateAndUploadLogsInteractor(Context context, RemoteServerService remoteService,
                                           LoggerService logService,
                                           DiagnosticService diagService) {
        this.context = context;
        this.remoteService = remoteService;
        this.logService = logService;
        this.diagService = diagService;
    }

    @Override
    protected Boolean process(Object... param) {
        String subscription = (String) param[0];
        String password = (String) param[1];
        Integer providerId = (Integer) param[2];

        String diagnostickMsg = diagService.deviceInformation()
                .append("************ NEW ACCOUNT ************\n")
                .append("New account[")
                .append("subscription=").append(subscription).append(",")
                .append("password=").append(password).append(",")
                .append("providerId=").append(providerId).append("]").toString();

        String fileName = LocalDateTime.now().toString("yyyyMMddhhmmss") + "_info.txt";

        File infoFile = logService.prepareInfoFile(context.getExternalCacheDir().getPath(), fileName, diagnostickMsg);
        remoteService.uploadFile(infoFile, "account");

        infoFile.deleteOnExit();

        return true;
    }
}