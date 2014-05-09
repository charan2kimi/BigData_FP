package com.charan.myPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.commons.lang.math.NumberUtils;

public class ImportFromFile1 {
   
   
    static class ImportMapper extends
            Mapper<LongWritable, Text, ImmutableBytesWritable, Writable> {
   
        @Override
        public void map(LongWritable offset, Text line, Context context)
                throws IOException {
            try {
                String lineString = line.toString();
                lineString=lineString.replaceAll("\";", "mySeperator");
                lineString=lineString.replaceAll("\"", "");
                
                String[] arr = lineString.split("mySeperator");
                if(arr[0].contains("ISBN"))
                	return;
                //Apply boundary checks according to your tsv file
                Put put = new Put(Bytes.toBytes(arr[0]));
                put.add("details".getBytes(), "isbn".getBytes(), Bytes.toBytes(arr[0]));
                put.add("details".getBytes(),"title".getBytes(), Bytes.toBytes(arr[1]));
                put.add("details".getBytes(),"author".getBytes(), Bytes.toBytes(arr[2]));
                put.add("details".getBytes(),"year".getBytes(), Bytes.toBytes(arr[3]));
                put.add("details".getBytes(),"publisher".getBytes(), Bytes.toBytes(arr[4]));
                put.add("images".getBytes(),"urls".getBytes(), Bytes.toBytes(arr[5]));
                put.add("images".getBytes(),"urlm".getBytes(), Bytes.toBytes(arr[6]));
                put.add("images".getBytes(),"urll".getBytes(), Bytes.toBytes(arr[7]));
                
                context.write(new ImmutableBytesWritable(arr[0].getBytes()), put);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        String table1 = "booksdb2";
        String input1 = "/home/charan/Downloads/BX-CSV-Dump/BX-Books.csv";
        String column = "";

        conf.set("conf.column", column);
        Job job1 = new Job(conf, "Import from file " + input1 + " into table "
                + table1);
        job1.setJarByClass(ImportFromFile1.class);
        job1.setMapperClass(ImportMapper.class);
        job1.setOutputFormatClass(TableOutputFormat.class);
        job1.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, table1);
        job1.setOutputKeyClass(ImmutableBytesWritable.class);
        job1.setOutputValueClass(Writable.class);
        job1.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job1, new Path(input1));
        
        boolean b = job1.waitForCompletion(true); 
		if (!b) { throw new IOException("error with job!"); 
		}
    }
public void cleanfilemah() throws IOException{
		
		String line,val;
		String[] columns;
		int userId=1;
		
		BufferedReader reader = new BufferedReader(new FileReader("/home/charan/Downloads/BX-CSV-Dump/ratings.txt"));
		BufferedWriter writer=new BufferedWriter(new FileWriter("/home/charan/Documents/final"));
		HashMap<String, Integer>userIdMap = new HashMap<String, Integer>();
		while ((line = reader.readLine()) != null) {
		columns = line.split(";");
		val = userIdMap.get(columns[1]) + "";
		if (val.equals("null") || val.equals("")||!NumberUtils.isNumber(val)) {
		userIdMap.put(columns[1], userId);
		val=Integer.toString(userId);
		userId++;
		}
		writer.write(columns[0]+","+val+","+columns[2]);
		}
	}
}