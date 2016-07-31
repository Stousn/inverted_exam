package at.fhj.itm.ssh;

import at.fhj.itm.ssh.utils.ConnectionFactory;

public class Main {

	public static void main(String[] args) {

		System.out.println("Welcome to the inverted exam by SSH :)");

		ConnectionFactory.runInitScript();
		ConnectionFactory.runScriptsFromScriptFolder();

		System.out.println("DB ssh-race is set up!");

	}
}
