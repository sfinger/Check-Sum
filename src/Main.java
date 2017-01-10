/*
 * Sylvia Finger
 * 11/2/16
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private byte[] checkSumBytes;
	ArrayList<Byte> aListBytes = new ArrayList<Byte>();

	public void setBytes(String sFile) {
		try {
			FileInputStream fileInputStream = null;
			File newFile = new File(sFile);
			checkSumBytes = new byte[(int) newFile.length()];
			fileInputStream = new FileInputStream(newFile);
			fileInputStream.read(checkSumBytes);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be opened!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getBytes() {
		return checkSumBytes;
	}
	
	public void arrayToArrayList(byte[] sBytesList) {
		for (int i = 0; i < sBytesList.length; i++) {
			aListBytes.add(i, sBytesList[i]);
		}
	}
	
	public ArrayList<Byte> getArrayList() {
		return aListBytes;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int iDec = 0;
		int total = 0;
		String sHex = null;
		
		Main newMain = new Main();
		
		System.out.println("Please enter a filename: ");
		Scanner scan = new Scanner(System.in);
		String fileScan = scan.next();
		
		newMain.setBytes(fileScan);
		byte[] bytesArray = newMain.getBytes();
		newMain.arrayToArrayList(bytesArray);
		ArrayList<Byte> aListBytes = newMain.getArrayList();
		
		if (newMain.getArrayList().size() % 2 > 0) {
			aListBytes.add((newMain.getArrayList().size() - 1), (byte) 0);
			
		}
		for (int i = 0; i < newMain.getArrayList().size(); i=i+2) {
			sHex = String.format("%02x" + "%02x", aListBytes.get(i), aListBytes.get(i+1));
			iDec = Integer.decode("0x" + sHex);
			total += iDec;
			
		}
		while (total > Math.pow(2, 16)) {
			total = (int) ((total / Math.pow(2, 16)) + (total % Math.pow(2, 16)));
			
		}

		System.out.println(String.format("%02x", total));
	}

}
