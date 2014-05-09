package com.charan.myPackage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result; 
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
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


public class MapRExample2 { 
	
	 public static class MyMapper extends TableMapper<Text, IntWritable> {

		 private Text text = new Text();

		 public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			 
				String isbn = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("isbn")));
				String title = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("title")));
				String author = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("author")));
				
				if(isbn.equals("ISBN"))
					return;
				if(title.contains("")){
				
			 text.set(author); 
			 // we can only emit Writables... 
			 

		 context.write(text,new IntWritable(1));
				}
		 }
		 }

		 public static class MyTableReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {
			 

		 public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException { 
			 int sum=0;
				int count=0;
				for (IntWritable val : values) {
					
					count++;
					} 
								
				System.out.println("Author: "+key+"\t"+"No. of Books: "+count);
				Put put = new Put(Bytes.toBytes(key.toString()));
				put.add("details".getBytes(), "count".getBytes(), Integer.toString(count).getBytes());
			 
		 context.write(null, put); } }
	
	
	
	public void mapJob2() throws IOException, ClassNotFoundException, InterruptedException{
		Configuration config = HBaseConfiguration.create(); 
		Job job = new Job(config, "ExampleSummary"); 
		job.setJarByClass(MapRExample2.class);
		// class that contains // mapper and reducer 
		String sourceTable = "booksdb"; 
		String targetTable = "outputtemp2"; 
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
		if (!b) { throw new IOException("error with job!"); 
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
		      hm.put(keyValue.getKeyString(),keyValue.getValue().toString());
		    	System.out.println("Qualifier : " + keyValue.getKeyString() + " : Value : " + Bytes.toString(keyValue.getValue()));
		    }
			rslist.add(hm);
		}
			return rslist;
	}	
	
	
	 
	}
	
		
	
		
	
