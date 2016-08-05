package com.hp.g11n.scoring.service;

/**
 * Created by foy on 2016-08-05.
 */
public interface Chain {
    boolean execute(String sourceKey, String sourceString);
    ReportData gatherReport();
}
