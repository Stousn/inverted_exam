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
	 * @param init to run initial script
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Seawas Wöd!");
		
		MySQLAccess dao = new MySQLAccess();
	    dao.readDataBase();
	    
	    
	    //PARAM init runs init script
	    if(args.length >0 && args[0].equals("init")) {
	    	System.out.println("init");
	    	dao.init();
	    	
	    }
	}


}
