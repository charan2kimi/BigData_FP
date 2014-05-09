package com.charan.myPackage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result; 
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.ipc.HBaseClient;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


public class MapRExample1 { 
	
	 public static class MyMapper extends TableMapper<Text, IntWritable> {

		 private Text text = new Text();

		 public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			 
				String val1 = new String(value.getValue(Bytes.toBytes("ratings"), Bytes.toBytes("isbn"))); 
				if(val1.equals("ISBN"))
					return;
				String val2 = new String(value.getValue(Bytes.toBytes("ratings"), Bytes.toBytes("rating"))); 
			 text.set(val1); 
			 // we can only emit Writables... 
			 Float f = Float.parseFloat(val2); 
			 int rat = Math.round(f);

		 context.write(text,new IntWritable(rat)); 
		 }
		 }

		 public static class MyTableReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {
			 

		 public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException { 
			 int sum=0;
				int count=0;
				for (IntWritable val : values) {
					sum=sum+val.get();
					count++;
					} 
				int avg=sum/count;
				
				System.out.println("ISBN:"+key+"\t"+"Average: "+avg);
				Put put = new Put(key.getBytes());
				put.add("details".getBytes(), "isbn".getBytes(), key.toString().getBytes());
				put.add("details".getBytes(), "avg".getBytes(), Integer.toString(avg).getBytes());
			 
		 context.write(null, put); } }
	
	
	
	public void mapJob1() throws IOException, ClassNotFoundException, InterruptedException{
		Configuration config = HBaseConfiguration.create(); 
		Job job = new Job(config, "ExampleSummary"); 
		job.setJarByClass(MapRExample1.class);
		// class that contains // mapper and reducer 
		String sourceTable = "ratingsdb"; 
		String targetTable = "outputtemp1"; 
		Scan scan = new Scan(); 
		scan.setCaching(500); 
		// 1 is the default in Scan, which will be bad for // MapReduce jobs 
		scan.setCacheBlocks(false); 
		// don't set to true for MR jobs // set other scan attrs 
		TableMapReduceUtil.initTableMapperJob(sourceTable, // input table
				scan, // Scan instance to control CF and attribute selection 
				MyMapper.class, // mapper class 
				Text.class, // mapper output key 
				IntWritable.class, // mapper output value 
				job);
				//job.setCombinerClass(MyTableCombiner.class); 
		TableMapReduceUtil.initTableReducerJob(targetTable, // output table 
				MyTableReducer.class, // reducer class 
				job); 
		job.setNumReduceTasks(1); // at least one, adjust as required 
		boolean b = job.waitForCompletion(true); 
		if (!b) {
			throw new IOException("error with job!"); 
		
//		}else{
//			for(HashMap<String, String> hm:getdata()){
//				Iterator it = hm.entrySet().iterator();
//			    while (it.hasNext()) {
//			        Map.Entry pairs = (Map.Entry)it.next();
//			        System.out.println(pairs.getKey() + " = " + pairs.getValue());
//			        it.remove(); // avoids a ConcurrentModificationException
//			    }
			}
			
		}
	
	
	
	
	 
	
	
	public List<HashMap<String, String>> getdata() throws IOException{
		@SuppressWarnings("resource")
		HTable table = new HTable(HBaseConfiguration.create(), "outputtemp1");
		Scan scan = new Scan();
		scan.setCaching(20);

		scan.addFamily(Bytes.toBytes("details"));
		ResultScanner scanner = table.getScanner(scan);
			List<HashMap<String, String>> rslist=new ArrayList<HashMap<String, String>>();
		for (Result result = scanner.next(); (result != null); result = scanner.next()) {
			HashMap<String, String> hm=new HashMap<String, String>();
			for(KeyValue keyValue : result.list()) {
		      if(keyValue.toString().contains("isbn"))
		    	  hm.put("isbn", keyValue.getValue().toString());
		      else if(keyValue.toString().contains("avg"))
		    	  hm.put("avg", keyValue.getValue().toString());
		    	}
			rslist.add(hm);
		}
			return rslist;
	}	
	
		
	
}