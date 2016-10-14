package com.pi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class test {

	public static void main(String[] args) {
		try {
			// read file content from file
			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader("C:\\Users\\xn036608\\Desktop\\test");
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			int i=0;
			while ((str = br.readLine()) != null) {
				if(i%14==0){
					sb.append(str + "\n./export.sh ");
				}else{
					sb.append(str + " ");
				}
				i++;
			}
			br.close();
			reader.close();
			// write string to file
			FileWriter writer = new FileWriter("C:\\Users\\xn036608\\Desktop\\test3");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());

			bw.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
