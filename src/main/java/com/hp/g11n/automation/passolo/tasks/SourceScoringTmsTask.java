package com.hp.g11n.automation.passolo.tasks;


import com.hp.g11n.sdl.psl.interop.core.*;
import com.hp.g11n.sdl.psl.interop.core.impl.impl.PassoloApp;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SourceScoringTmsTask extends Task<Void> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final static Set<String> KEY_WORDS=new HashSet<String>() {{
            String  keys="of, is, are, if, by, with, in, at, on, from, to, and, " +
                    "or, for, an, a, the, where, which, when, but, this, that, over, " +
                    "under, after, since";
            for(String k:keys.split(",")){
                add(k.toLowerCase().trim()+" ");
            }
        }};
    private String source;
    private String report;
    private TextArea log;


    public void setUp(String sourceDir,String reportDir,TextArea ta){
        this.source=sourceDir;
        this.report=reportDir;
        this.log=ta;
    }

    @Override
    protected Void call() throws Exception {
        int needCnt=5;
        //input
        File[] lpuFiles = (new File(source)).listFiles((File f) -> !f.isDirectory() && f.getName().endsWith(".lpu"));
        File lpuFile=lpuFiles[0];

        //output
        FileWriter fw=new FileWriter(report);

        // Create a Passolo instance.
        IPassoloApp app = PassoloApp.getInstance();
        // Open an lpu file to load a Passolo project.
        IPslProject project = app.open(lpuFile.toPath());
        // Get translation lists of the project
        log.appendText("starting process source lists...\n");
        IPslSourceLists sourceLists = project.getSourceLists();

        List<String> sourceTxt=new ArrayList<String>(5000);
        int total=0;
       // outer:
        long t1=System.currentTimeMillis();
        List<IPslSourceList> s1= sourceLists.toList();
        long t2=System.currentTimeMillis();
        log.appendText("t2-t1:" + (t2-t1)+"ms\n");
        //213326
        AA:for(int n=0;n<s1.size();n++){
            IPslSourceList sourceList = s1.get(n);
            log.appendText("sourceList "+ n +" total count:"+ sourceList.getStringCount()+"\n");

            long tt1=System.currentTimeMillis();
            List<IPslSourceString> lpss=sourceList.getSourceStrings(); //FIXME need about 15s (3w data)!!!!
            long tt2=System.currentTimeMillis();
            log.appendText("tt2-tt1:" +lpss.size()+" - "+ (tt2-tt1)+"ms\n");
            for(int i=0;i<lpss.size();i++){ //FIXME loop one List<IPslSourceString>  need about 10s (3w data)!!!
                IPslSourceString ss=lpss.get(i);
                StringBuffer sb= new StringBuffer(100);
                String keyVal=sb.append(ss.getID()).append("-").append(ss.getText()).toString();
            }
            long tte=System.currentTimeMillis();
            log.appendText("tte-tt2:" +lpss.size()+" - "+ (tte-tt2)+"ms\n");
            //break AA;
            }
        long t3=System.currentTimeMillis();
        log.appendText("t3-t2:" + (t3-t2)+"ms\n");
   /*     for(IPslSourceList sourceList : sourceLists.toList()) {
            for(IPslSourceString sourceString : sourceList.getSourceStrings()){

                total++;
         *//*       if(sourceString == null){continue;}
                String str=sourceString.getText();
                if(str == null){continue;}
                String target=str.toLowerCase().trim();
                if(target.isEmpty()){
                    continue;
                }
                sourceTxt.add(sourceString.getID()+" "+sourceString.getText());*//*
            }
        }*/

        log.appendText("total:"+total+"\n");
        log.appendText("process source lists done...\n");

        fw.close();

        // Close the project.
        project.close();
        // Shut down passolo instance.
        IPassoloApp.quit();
        return null;
    }
}
