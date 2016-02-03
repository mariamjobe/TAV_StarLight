
public class arduinoCom {

	static StringBuffer inputBuffer = new StringBuffer("");
	static float torque[] = {5, 9, 2};
	static float ultra_distance[] = {100, 300, 150};
	static float ir_distance[] = {50, 60, 70};
	
	public static void sendSensorData(float[] torque, float[] ultra_distance, float[] ir_distance) {
		//codes the 3 values into packet of bitstreams
		
			for (int i = 0; i < 3; i++) {
				String delimeter = "xx";
				int torqueIntBits = Float.floatToIntBits(torque[i]);
				int udIntBits = Float.floatToIntBits(ultra_distance[i]);
				int irIntBits = Float.floatToIntBits(ir_distance[i]);

				String torqueBinary = Integer.toBinaryString(torqueIntBits);
				String udBinary = Integer.toBinaryString(udIntBits);
				String irBinary = Integer.toBinaryString(irIntBits);
				
				String packet = torqueBinary + udBinary + irBinary + delimeter; 
				//send to buffer
				writeInputBuffer(packet.length(), packet);
			}
			
		
		// add delimiter after end of each packet (3 values)s
		
		
		
	}	
	
	public void readSpeedTorque() {
		//reads speed and torque from the current Bitstream
	
	}
	
//	Write to input buffer: This interface has two parameters: a non-negative number "n" 
//	and a bitstream "s" 
//	and its appends the first "n" bits of "s' into the end of the input buffer bitstream.
	public static void writeInputBuffer(int n, String s){
		inputBuffer.append(s);
		
	}
	
	public void readOutputBuffer(){
		//
	}
	
	
	public static void main(String[] args) {
		sendSensorData(torque, ultra_distance, ir_distance);
	}
	
}