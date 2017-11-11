package Dao.user;

import java.io.Serializable;
import java.net.Socket;

/**
 * @author Hans
 * 斗地主用户的实体类
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2075646653862790711L;

	private int userId;
	
	private String name;
	
	private Socket socket;
	
	private boolean islandlord;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isIslandlord() {
		return islandlord;
	}

	public void setIslandlord(boolean islandlord) {
		this.islandlord = islandlord;
	}

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", socket=" + socket + ", islandlord=" + islandlord + "]";
	}
	
	public User(){
		
	}
	
	public User(int userId, String name, Socket socket, boolean islandlord) {
		super();
		this.userId = userId;
		this.name = name;
		this.socket = socket;
		this.islandlord = islandlord;
	}
}
