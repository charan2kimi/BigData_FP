package com.charan.myPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.charan.myPackage.ImportFromFile1.ImportMapper;


public class TestMapper {
	

	

	public static void main(String[] args) throws Exception {
	
		RunPigQuery rp=new RunPigQuery();
		MapRExample1 mr1=new MapRExample1();
		MapRExample2 mr2=new MapRExample2();
		MapRExample3 mr3=new MapRExample3();
		ImportFromFile1 imp1=new ImportFromFile1();

		//imp1.cleanfilemah();
		
		//run pig scripts
		//rp.testPig();
		
		//Avg rating for each book
		mr1.mapJob1();

		//Number of books for each author
		mr2.mapJob2();

		//Number of votes cast by each user
		mr3.mapJob3();
		
		

		
		
		
	}
	
}
