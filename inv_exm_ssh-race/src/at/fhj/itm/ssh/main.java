/**
 * 
 */
package at.fhj.itm.ssh;

import at.fhj.itm.ssh.db.MySQLAccess;

/**
 * @author Helmuth Weithaler, Simon Schrei, Stefan Reip
 *
 */
public class main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Seawas W�d!");
		
		MySQLAccess dao = new MySQLAccess();
	    dao.readDataBase();

	}

}
