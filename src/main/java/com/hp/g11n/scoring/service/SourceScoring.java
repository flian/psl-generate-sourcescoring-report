package com.hp.g11n.scoring.service;

import java.util.List;

/**
 * Created by foy on 2016-08-05.
 */
public interface SourceScoring {
    String check(String key, String value);
    List<ReportData> report();
}
