package Dao.msg;

import java.io.Serializable;
import java.util.List;

import Dao.poker.Card;
/**
 * @author Hans
 * 发送消息的实体
 *
 */
public class Msg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2880905758616565606L;

	private int userId;

	private List<Card> List;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Card> getList() {
		return List;
	}
	public void setList(List<Card> list) {
		List = list;
	}
	public Msg(int userId, java.util.List<Card> list) {
		super();
		this.userId = userId;
		List = list;
	}
	
	@Override
	public String toString() {
		return "Msg [userId=" + userId + ", List=" + List + "]";
	}
	
}
