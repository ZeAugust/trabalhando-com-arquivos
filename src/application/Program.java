package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.print("Enter a file path: ");
		String strPath = sc.nextLine();
		
		File file = new File(strPath);
		
		String fileFolder = file.getParent();
		
		boolean  success = new File(fileFolder + "\\out").mkdir();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			
			String line = br.readLine();
			
			while(line != null) {
				
				String[] item = line.split(", ");
				String productName = item[0];
				double productPrice = Double.parseDouble(item[1]);
				int productQuantity = Integer.parseInt(item[2]);
				
				list.add(new Product(productName, productPrice, productQuantity));
				
				line = br.readLine();
			}
		
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileFolder + "\\out\\summary.csv"))){
				for (Product product : list) {
					bw.write(product.getName()+", ");
					bw.write(String.format("%.2f",product.total()));
					bw.newLine();
				}
			}
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
		catch(IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
		System.out.println("THE FILE HAS BEEN SUCCESSFULLY CREATED!!!");
		
		
		sc.close();
	}
}
