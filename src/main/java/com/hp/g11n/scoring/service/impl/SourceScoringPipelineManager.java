package com.hp.g11n.scoring.service.impl;

import com.google.common.base.Preconditions;
import com.hp.g11n.scoring.service.Chain;
import com.hp.g11n.scoring.service.ReportData;
import com.hp.g11n.scoring.service.SourceScoring;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by foy on 2016-08-05.
 */
@Service
public class SourceScoringPipelineManager implements SourceScoring ,ApplicationContextAware {
    private ApplicationContext applicationContext;
    private List<Chain> chains;

    @PostConstruct
    public void init(){
        if(chains == null) {
            chains=new ArrayList<>();
            Map<String,Chain> chainMap=applicationContext.getBeansOfType(Chain.class);
            chainMap.keySet().stream().forEach( k -> chains.add(chainMap.get(k)));
        }
    }
    @Override
    public String check(String sourceId, String sourceString) {
        Preconditions.checkNotNull(chains);
        chains.stream().forEach(c -> c.execute(sourceId,sourceString));
        return null;
    }

    @Override
    public List<ReportData> report() {
        Preconditions.checkNotNull(chains);
        List<ReportData> reports=new ArrayList<>();
        chains.stream().forEach( c ->  reports.add(c.gatherReport()));
        return reports;
    }

    public List<Chain> getChains() {
        return chains;
    }

    public void setChains(List<Chain> chains) {
        this.chains = chains;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
