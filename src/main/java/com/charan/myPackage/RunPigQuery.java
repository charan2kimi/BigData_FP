package com.charan.myPackage;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.data.Tuple;

public class RunPigQuery {
	
	
	
public void testPig(){
	
	 PigServer pigServer = null;
	String mode = "local";
	 String pigScriptName = "/home/charan/Desktop/scrpig.pig";

	Map<String,String> params = null;

	List<String> paramFiles = null;

	        try {
	        	Properties props = new Properties();

	            props.setProperty("fs.default.name", "hdfs://localhost:9000");
	            props.setProperty("mapred.job.tracker", "localhost:9010");
	            pigServer = new PigServer(ExecType.MAPREDUCE,props);
	            
	            pigServer.setBatchOn();
	            pigServer.debugOn();
	            InputStream is = this.getClass().getClassLoader().getResourceAsStream(pigScriptName);
	            if(params != null){
	                pigServer.registerScript(is, params);
	            } else if(paramFiles != null){
	                pigServer.registerScript(is, paramFiles);
	            } else {
	                pigServer.registerScript(is);
	            }
	            Iterator<Tuple> iter = pigServer.openIterator(pigScriptName);
	            int counter = 0;
	            while (iter.hasNext()){
	               System.out.print(iter.next().toString());
	            }
	         
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            throw new RuntimeException(e);
	            
	        } finally {
	            if(pigServer != null){
	                pigServer.shutdown();
	            }
	        }
}
}