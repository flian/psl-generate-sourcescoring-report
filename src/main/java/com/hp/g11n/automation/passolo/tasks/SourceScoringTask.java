package com.hp.g11n.automation.passolo.tasks;


import com.hp.g11n.sdl.psl.interop.core.*;
import com.hp.g11n.sdl.psl.interop.core.impl.impl.PassoloApp;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class SourceScoringTask  extends Task<Void> {
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
        outer:
        for(IPslSourceList sourceList : sourceLists.toList()) {
            for(IPslSourceString sourceString : sourceList.getSourceStrings()){
                if(sourceString == null){continue;}
                String str=sourceString.getText();
                if(str == null){continue;}
                String target=str.toLowerCase().trim();
                if(target.isEmpty()){
                    continue;
                }
                //just for example...
                for(String k: KEY_WORDS){
                    if(target.startsWith(k)){
                        needCnt--;
                        String tmp=sourceString.getID()+","+sourceString.getText();
                        //log.appendText(tmp);
                        log.appendText("find one match key/value:" + sourceString.getID()+" "+sourceString.getText() + "\n");
                        fw.write(tmp+"\n");
                        if(needCnt<=0){
                            //just find top N to reduce time...
                            break outer;
                        }

                    }
                }
            }
        }


        log.appendText("process source lists done...\n");

        fw.close();

        // Close the project.
        project.close();
        // Shut down passolo instance.
        IPassoloApp.quit();
        return null;
    }
}
