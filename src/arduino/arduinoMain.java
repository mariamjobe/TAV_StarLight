package arduino;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class arduinoMain {
	static String buffer = new String("");
    private static String readBuffer = new String("10001100110010001100000010111000110000010111110011010100110000001011100011000000100011");

	
	//Contains made up values
//	As text: #20.0_50.0#
//	As binary: 10001100110010001100000010111000110000010111110011010100110000001011100011000000100011
	
	
	//with errors
//	As text: #EDS-120.0__EDT-140.0#
//	As binary: 100011010001010100010001010011001011010011000100110010001100000010111000110000010111110101111101000101010001000101010000101101001100010011010000110000001011100011000000100011	

	
	//static StringBuffer buffer = new StringBuffer("");
	//static StringBuffer readBuffer = new StringBuffer("");
	private static final Charset UTF_8 = Charset.forName("UTF-8");
   
    public static void sendSensorData(double torque, double ultra_distance, double ir_distance) {
    	//add beginning of packet
    	String packet = "#";
    	//System.out.println(packet);
    	//limits 0 -100
    	if (torque < 0 || torque > 100 ) {
			String stringTorque = "EDT-" + torque;
			//append to string
			packet += stringTorque + "_";
			//System.out.println(packet);
		} else{
			packet += torque + "_";
		}
    	
    	if (ultra_distance < 0 || ultra_distance > 100 ) {
			String stringUltra_distance = "EDU-" + ultra_distance;
			//append to string
			packet += stringUltra_distance + "_";
			//System.out.println(packet);
		} else{
			packet += ultra_distance + "_";
		}
    	
    	if (ir_distance < 0 || ir_distance > 100 ) {
			String stringIr_distance = "EDI-" + ir_distance;
			//append to string
			packet += stringIr_distance;
			//System.out.println(packet);
		} else{
			packet += ir_distance;
		}
    	
    	// add end of packet
    	packet += "#";
    	System.out.println("As text: "+packet);
    	
    	
    	//convert to binary
    	String binary = new BigInteger(packet.getBytes()).toString(2);
    	System.out.println("As binary: "+binary);
    	
    	
    	
    	//convert back to string
//    	String text2 = new String(new BigInteger(binary, 2).toByteArray());
//    	System.out.println("As text: "+text2);
    	
    	//write to buffer
    	writeInputBuffer(binary.length(), binary);
    	
    	
    }
    
    
    
    public static void readSpeedTorqueFakeData(double speed, double torque) {
    	//add beginning of packet
    	String packet = "#";
    	//System.out.println(packet);
    	//limits 0 -100
    	if (speed < 0 || speed > 100 ) {
			String stringSpeed = "EDS-" + speed + "_";
			//append to string
			packet += stringSpeed + "_";
			//System.out.println(packet);
		} else{
			packet += speed + "_";
		}
    	
    	if (torque < 0 || torque > 100 ) {
			String stringTorque = "EDT-" + torque;
			//append to string
			packet += stringTorque;
			//System.out.println(packet);
		} else{
			packet += torque;
		}
    	
    
    	
    	
    	// add end of packet
    	packet += "#";
    	System.out.println("As text: "+packet);
    	
    	
    	//convert to binary
    	String binary = new BigInteger(packet.getBytes()).toString(2);
    	System.out.println("As binary: "+binary);
    	
    	
    	
    	//convert back to string
//    	String text2 = new String(new BigInteger(binary, 2).toByteArray());
//    	System.out.println("As text: "+text2);
    	
    	//write to buffer
    	writeInputBuffer(binary.length(), binary);
    	
    	
    }
    
    public static void  readSpeedTorque() {
       speedTorque data = new speedTorque();   		
   
   	//convert back to string
   	String text2 = new String(new BigInteger(readBuffer, 2).toByteArray());
   	System.out.println("As text: "+text2);
   	
   	
   	//IN:#20.0_50.0#
//  //splits into packages find between # # 
//   	Pattern p = Pattern.compile(".*\\# *(.*) *\\#.*");
//    Matcher m = p.matcher(text2);
//    m.find();
//    String text = m.group(1);
//    System.out.println(text);
//   	
//   	//OUT: 20.0_50.0
   	
   	
  //check if string contains error messages
   	
   		//else
   	
   	
   	
   	//SPEED
    //splits into packages find between # # 
   	Pattern p = Pattern.compile(".*\\# *(.*) *\\_.*");
    Matcher m = p.matcher(text2);
    m.find();
    String speed = m.group(1);
    //System.out.println(speed);
    float fSpeed = Float.parseFloat(speed);
    data.speed = fSpeed;
    System.out.println(data.speed());
    
    
   	//OUT: 20.0
    
    
    //TORQUE
	Pattern pt = Pattern.compile(".*\\_ *(.*) *\\#.*");
    Matcher mt = pt.matcher(text2);
    mt.find();
//    String torque = mt.group(1);
//    System.out.println(torque);
    String torque = mt.group(1);
    float f = Float.parseFloat(torque);
    data.torque = f;
    System.out.println(data.torque());
   	//OUT: 50.0

    
    
    
   	
   	
   	//split packet into values
   	
   	//assign values to speed and torque
    //data.speed() = speed from binary
    // data.torque() = torque from binary
       
       
       
//       data.speed = 20;
//       System.out.println(data.speed());

       
      // return data;
   
    }
 
    public static void writeInputBuffer(int n, String s){
		buffer += s;
    }
   
    public static void readOutputBuffer(int n){
       // remove n bits from buffer
    	System.out.println("remove "+ n +" bits");

    	System.out.println("Before\n:" +buffer.length());

    	
		buffer = buffer.substring(0, buffer.length() - n);
    	System.out.println("after\n:" +buffer.length());



    }
   
    public static void main(String[] args) {
//        sendSensorData(1.11 , 2.22, 3.33); 
//        sendSensorData(100.44 , 200.55, 200.66);
//        sendSensorData(7.79 , 8.88, 9.99);
//        readOutputBuffer(10);
//    	readSpeedTorqueFakeData(20, 50);
        readSpeedTorque();
    }
   
}


///speed and torque make up numbers