package com.charan.myPackage;

import java.io.IOException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

//import oracle.jdbc.internal.OracleTypes;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.internal.matchers.Each;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charan.myPackage.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale,HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
	//RunPigQuery r=new RunPigQuery();
	//r.testPig();
		MapRExample1 mr1=new MapRExample1();
		MapRExample2 mr2=new MapRExample2();
		MapRExample3 mr3=new MapRExample3();
		
		
		//Avg rating for each book
		mr1.mapJob1();

		//Number of books for each author
		mr2.mapJob2();

		//Number of votes cast by each user
		mr3.mapJob3();
		return "login";
	}
	
	
	}
