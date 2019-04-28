package org.iiitb.spe.priceprediction.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
	private String filename;

	public Model() {
		
	}
	public Model(String filename) {
		this.setFilename(filename);
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Double> getModelWeights() throws FileNotFoundException, IOException {
		
		File file = new File(
			getClass().getClassLoader().getResource(getFilename()).getFile()
		);

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = br.readLine();
		ArrayList<Double> weights = new ArrayList<Double>();
		    
		while (line != null) {
			double wt = Double.parseDouble(line);
		    weights.add(wt);
		    line = br.readLine();
		}
		br.close();
		return weights;
	}
	
	public double predict(ArrayList<Double> test_case) throws FileNotFoundException, IOException {
		double prediction = 0;
		ArrayList<Double> weights = getModelWeights();
		for(int i=0;i<test_case.size();i++) {
			prediction += (weights.get(i)*test_case.get(i));
		}
		return (double) Math.round(prediction * 100) / 100;
	}
}
