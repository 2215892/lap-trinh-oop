package com.btl.Model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModelMap {

	private ArrayList<ModelFactory> listFactory;
	private ArrayList<ModelTerminal> listTerminal;
	private ArrayList<ModelSwitch> listSwitch;

	private ModelMap() {
		this.listFactory = new ArrayList<ModelFactory>();
		this.listTerminal = new ArrayList<ModelTerminal>();
		this.listSwitch = new ArrayList<ModelSwitch>();

	}

	public ArrayList<ModelFactory> getListFactory() {
		return listFactory;
	}

	public ArrayList<ModelTerminal> getListTerminal() {
		return listTerminal;
	}

	public ModelMap createMap(final String fileDir) {
		ModelMap map = new ModelMap();
		if (map.loadMap(fileDir)) {
			return map;
		} else
			return null;
	}

	private boolean loadMap(final String fileDir) {

		BufferedReader bufferedReader = null;
		StringBuilder sb = null;

		/* Doc file */
		try {
			bufferedReader = new BufferedReader(new FileReader(fileDir));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sb = new StringBuilder();
			String line = bufferedReader.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String input = sb.toString();

		if (input.equals("")) {
			return false;
		} else {
			try {

				String temp;

				temp = input.split("factory\n{\n")[1];
				temp = temp.split("}")[0];
				readFactory(temp);

				temp = input.split("terminal\n{\n")[1];
				temp = temp.split("}")[0];
				readTerminal(temp);

				temp = input.split("switch\n{\n")[1];
				temp = temp.split("}")[0];
				readSwitch(temp);

				return true;
			} catch (Exception e) {
				return false;
			}
		}

	}
	private void readFactory(final String input) {
		String[] factories = input.split("\n");

		for (String factory : factories) {
			String[] args = factory.split(" ");
			listFactory.add(new ModelFactory(new Point(Integer
					.parseInt(args[0]), Integer.parseInt(args[1])),
					string2Direction(args[2])));
		}
	}

	private Direction string2Direction(final String str) {

		if (str == "UP")
			return Direction.UP;
		if (str == "DOWN")
			return Direction.DOWN;
		if (str == "RIGHT")
			return Direction.RIGHT;
		if (str == "LEFT")
			return Direction.LEFT;
		return null;

	}

	private void readTerminal(final String input) {
		String[] terminals = input.split("\n");

		for (String terminal : terminals) {
			String[] args = terminal.split(" ");
			listTerminal.add(new ModelTerminal(new Point(Integer
					.parseInt(args[0]), Integer.parseInt(args[1]))));
		}
	}

	private void readSwitch(final String input) {
		String[] switchs = input.split("\n");

		for (String sw : switchs) {
			String[] args = sw.split(" ");
			ModelSwitch newSwitch = new ModelSwitch(new Point(
					Integer.parseInt(args[0]), Integer.parseInt(args[1])));

			newSwitch.setCurrentDir(Integer.parseInt(args[2]));
			int size = args.length;
			for (int i = 3; i < size; ++i) {
				newSwitch.addDirection(string2Direction(args[i]));
			}

			listSwitch.add(newSwitch);
		}
	}

	public ArrayList<ModelSwitch> getListSwitch() {
		return listSwitch;
	}

}
