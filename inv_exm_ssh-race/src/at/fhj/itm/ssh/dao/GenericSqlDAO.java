package at.fhj.itm.ssh.dao;

import java.io.Serializable;
import java.sql.Connection;

import at.fhj.itm.ssh.utils.ConnectionFactory;

public abstract class GenericSqlDAO <T, PK extends Serializable> {
	Connection connection = ConnectionFactory.getConnection();
	
	public abstract PK create(T newInstance);
	
	public abstract T read(PK id);
	
	public abstract void update(T transientObject);
	
	public abstract void delete(T persistentObject);

}
