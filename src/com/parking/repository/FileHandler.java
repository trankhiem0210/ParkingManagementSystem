/**
 * 
 */
package com.parking.repository;

/**
 * class nay duoc tao ra phuc vu cho viec doc va ghi file, de luu tru thong tin ve xe va vi tri do xe do vao file de khi ung dung duoc khoi dong lai thi van co the doc duoc thong tin do va hien thi len man hinh, de nguoi dung co the biet duoc la co bao nhieu xe dang do trong bai va thong tin cua tung xe do, vi tri do cua tung xe do, va khi nguoi dung muon lay xe ra thi cung co the doc duoc thong tin ve xe do va vi tri do cua no de nguoi dung co the lay xe ra mot cach nhanh chong va de dang hon
 */
public class FileHandler {
    
	private static final String DATA_PATH = "data/";

	public FileHandler() {
		// Constructor
	}

	/**
	 * Method to save current data from DataStorage to files
	 */
	public void saveData() {
		// Implementation for saving listUsers, listSlots, listCards, etc. to .dat or .csv files
		System.out.println("Saving data to files...");
	}

	/**
	 * Method to load data from files into DataStorage
	 */
	public void loadData() {
		// Implementation for reading files and populating DataStorage lists
		System.out.println("Loading data from files...");
	}

}
