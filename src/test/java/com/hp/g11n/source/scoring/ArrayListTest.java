package com.hp.g11n.source.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foy on 2016-08-04.
 */
public class ArrayListTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private List<String> doDefault(int loop){
        List<String> tmp=new ArrayList<String>();
        for(int i=0;i<loop;i++){
            tmp.add(""+i+"AAA");
        }
        return tmp;
    }
    private List<String> doSetCapacity (int loop){
        List<String> tmp=new ArrayList<String>(loop+1);
        for(int i=0;i<loop;i++){
            tmp.add(""+i+"ABC");
        }
        return tmp;
    }
    public static void main(String[] args) {
        ArrayListTest at=new ArrayListTest();
        long t1=System.currentTimeMillis();
        int n=213326;
        List<String> AA=at.doDefault(n);
        long t2=System.currentTimeMillis();
        List<String> BB=at.doSetCapacity(n);
        long t3=System.currentTimeMillis();

        at.logger.debug("doDefault:"+(t2-t1)+"ms");
        at.logger.debug("doDefault:"+(t3-t2)+"ms");

    }
}
