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

public class ImportFromFile3 {
   
   
    static class ImportMapper extends
            Mapper<LongWritable, Text, ImmutableBytesWritable, Writable> {
   
        @Override
        public void map(LongWritable offset, Text line, Context context)
                throws IOException, InterruptedException {
            try {
                String lineString = line.toString();
                lineString=lineString.replaceAll("\"", "");
                
                
                String[] arr = lineString.split(";");
                if(arr[1].contains("ISBN")||arr.length<3)                	
                	return;
                if(Integer.parseInt(arr[2])<0)
                	return;
                //Apply boundary checks according to your tsv file
                Put put = new Put(Bytes.add(Bytes.toBytes(arr[0]), Bytes.toBytes(arr[1])));
                
                put.add("ratings".getBytes(), "userid".getBytes(), Bytes.toBytes(arr[0]));
                put.add("ratings".getBytes(),"isbn".getBytes(), Bytes.toBytes(arr[1]));
                put.add("ratings".getBytes(),"rating".getBytes(), Bytes.toBytes(arr[2]));
                
                context.write(new ImmutableBytesWritable(arr[0].getBytes()), put);
                
            } catch (NumberFormatException ne) {
                ne.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        String table1 = "ratingsdb2";
        String input1 = "/home/charan/Downloads/BX-CSV-Dump/BX-Book-Ratings.csv";
        String column = "";

        conf.set("conf.column", column);
        Job job1 = new Job(conf, "Import from file " + input1 + " into table "
                + table1);
        job1.setJarByClass(ImportFromFile3.class);
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