import java.io.*;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException; //for operating system file checking
import java.util.NoSuchElementException; //for operating system file checking
import java.util.Scanner;
public class linechart
{
private static Scanner input; //input for oldmast file
private static Scanner inputTrans; //input for trans file

private static String oldMast = "oldmast.txt"; 
private static String transFile = "trans.txt";
private static String newMast = "newmast.txt";
private static String newLog= "log.txt"; //logs unknown accounts from new transaction file

private static int newAccountNumber ; //receive this out of here

public static void main( String args[] )
{
openFile(); //should be able to send it file name and input variable and reuse this.
//add processing here and break up into methods after program functioning.
readRecords();
closeFile();
//want to put an error message checking routine here before outputting this
System.out.println(newMast+" and possibly "+newLog+" outputed to file(s)");
}


public static void openFile()
{
try
{
input = new Scanner( new File( oldMast ) );
} 

catch ( FileNotFoundException fileNotFoundException )
{
System.err.println( "Error opening file." );
System.exit( 1 );
} 
} 

//read record from file
public static void readRecords()
{
System.out.printf( " -10s -12s -12s 10s n"+ " " +"Account"+" "+"First Name"+" "+ "Last Name"+" "+ "Balance"+"\n" );

//ry read records from file using Scanner Object
try {
while ( input.hasNext() )
{

int account = input.nextInt() ; //read account number
String firstName = input.next(); //read first name
String lastName = input.next();// read last name
double balance = input.nextDouble(); //read balance
double newBalance = balance;
boolean newAccount = true;

//show info
System.out.printf( " -10d -12s -12s 10.2f n" + account+" " + firstName+ " " +lastName+ " " + balance+"\n");

//**********inner loop******************************
try
{
inputTrans = new Scanner( new File( transFile));
} //end try
catch ( FileNotFoundException fileNotFoundException )
{
System.err.println( "Error opening file." );
System.exit( 1 );
} //end catch


while ( inputTrans.hasNext() )
{
int accountTrans = inputTrans.nextInt() ;// read account number
double balanceTrans = inputTrans.nextDouble();// read balance


if (account == accountTrans)
{
newBalance = balance +balanceTrans; 
System.out.printf( "Accounts match---- account -10d account trans -10d new balance -10.2f n"+account+" "+ accountTrans+" "+newBalance+"\n "); //@@@@@@@@@@@@@@@@@@@@@@@@ use this for debugging
newAccount = false;
newAccountNumber = accountTrans;
//difficulty here, I'm not able to grab the concept to stop the loop from counting and pick the transaction account number 
//I'm just picking the last one in the loop and loging that for now.
//I want to come back and figure out the logic to write the unknown account numbers to a log file
//recently only writing new account and last account number found.
//I spent about 4 hours on this part and was my only obstacle, and the cleanup into proper methods and structure.
}}
 
fileWrite (account ,firstName ,lastName ,newBalance );

//write out log file for new accounts
if (newAccount)
{
newAccount = true;
System.out.printf( "new account d n"+ newAccountNumber+" ");// @@@@@@@@@@@@@@@@@@@@@@@@ use this to print to screen for debugging 
fileWriteLog(newAccount,newAccountNumber);
}

System.out.println(" "); //@@@@@@@@@@@@@@@@@@@@@@@@ use this to print to screen for debugging 
} 

} //end try

catch ( NoSuchElementException elementException )
{
System.err.println( "File improperly formed." );
input.close();
System.exit( 1 );
} //end catch
catch ( IllegalStateException stateException )
{
System.err.println( "Error reading from file." );
System.exit( 1 );
} //end catch
}// end method readRecords

//close file
public static void closeFile()
{
if ( input == null )
input.close(); //close file
} //end method closeFile


//these should be overladed so I only have to write the code once.
public static void fileWrite(int account, String first, String last, double bal) 
{
try{
FileWriter fstream = new FileWriter(newMast,true);
BufferedWriter out = new BufferedWriter(fstream);
System.out.println("file write method ---writing n");


String outString = String.format(" d s s .2f n"+ account+ " " +first+" "+last+" "+bal+"\n") ;
out.write(outString); 

out.close(); //Close the output stream
}
catch (Exception e){ //Catch exception if any
System.err.println("Error: " + e.getMessage());
}
}


public static void fileWriteLog(boolean newAccount,int accountNewTrans) 
{
try{
FileWriter fstream = new FileWriter(newLog,true); //new log is filename
BufferedWriter out = new BufferedWriter(fstream);
System.out.println("file write method ---writing n");

String outString = String.format(" b d n"+ newAccount+" "+accountNewTrans+"\n") ;
//String outString = String.format(" d n", accountNewTrans) ;
out.write(outString); 

out.close(); //Close the output stream
}
catch (Exception e){ //Catch exception if any
System.err.println("Error: " + e.getMessage());
}
}






} 