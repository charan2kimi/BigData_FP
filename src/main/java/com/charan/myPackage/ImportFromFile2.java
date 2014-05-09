package com.charan.myPackage;

import java.io.IOException;

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

public class ImportFromFile2 {
   
   
    static class ImportMapper extends
            Mapper<LongWritable, Text, ImmutableBytesWritable, Writable> {
   
        @Override
        public void map(LongWritable offset, Text line, Context context)
                throws IOException {
        	String[] test = null;
            try {
                String lineString = line.toString();
               
                lineString=lineString.replaceAll("\"", "");
                
                String[] arr = lineString.split("[;,]");
                //Apply boundary checks according to your tsv file
                test=arr;
                Put put = new Put(Bytes.toBytes(arr[0]));
                put.add("details".getBytes(), "userid".getBytes(), Bytes.toBytes(arr[0]));
                put.add("location".getBytes(),"city".getBytes(), Bytes.toBytes(arr[1]));
                put.add("location".getBytes(),"state".getBytes(), Bytes.toBytes(arr[2]));
                put.add("location".getBytes(),"country".getBytes(), Bytes.toBytes(arr[3]));
                put.add("details".getBytes(),"age".getBytes(), Bytes.toBytes(arr[4]));
                
                
                context.write(new ImmutableBytesWritable(arr[0].getBytes()), put);
                
            } catch (Exception e) {
               System.out.println(test);
            	e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        String table1 = "usersdb";
        String input1 = "/home/charan/Downloads/BX-CSV-Dump/BX-Users.csv";
        String column = "";

        conf.set("conf.column", column);
        Job job1 = new Job(conf, "Import from file " + input1 + " into table "
                + table1);
        job1.setJarByClass(ImportFromFile2.class);
        job1.setMapperClass(ImportMapper.class);
        job1.setOutputFormatClass(TableOutputFormat.class);
        job1.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, table1);
        job1.setOutputKeyClass(ImmutableBytesWritable.class);
        job1.setOutputValueClass(Writable.class);
        job1.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job1, new Path(input1));
        
        System.exit(job1.waitForCompletion(true) ? 0 : 1);
    }

}