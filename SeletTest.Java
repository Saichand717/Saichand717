package com.sai.project;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest1
{
	public static void main(String[] args) 
	{
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			//read inputs
			sc = new Scanner(System.in);
			float startSalary=0.0f,endSalary=0.0f;
			
			if(sc!=null){
				System.out.println("Enter Start Range Emp Slary");
				startSalary = sc.nextFloat();
				System.out.println("Enter the End Range of EMP Salary");
				endSalary =sc.nextFloat();
			}
			//Load JDBC Driver class(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the Connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Dolly");

			//Create jdbc Statement obj
			if(con!=null)
				st = con.createStatement();

			//Prepare SQL Query
			//select empno,ename,sal from emp where sal>=2000 and sal>=1000;
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+" AND SAL<="+endSalary;
			System.out.println(query);

			//Send executeQuery in DB S/W
			if (st!=null)
				rs=st.executeQuery(query);

			//process the ResultSet Obj

			if(rs!=null)
				while(rs.next()==true){
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3)+""+rs.getFloat(4));

				}//while
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(ClassNotFoundException cnf){
				cnf.printStackTrace();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					try{
						if(rs!=null)
							rs.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}

					try{
						if(st!=null)
							st.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
					try{
						if(con!=null)
							con.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
					try{
						if(sc!=null)
							sc.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
				}//finally
			}

	}
