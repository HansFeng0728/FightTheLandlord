using UnityEngine;
using System.Collections;

public class Lobby : MonoBehaviour {

    public UILabel SigninLabel;
    public Card[] cardList1 = new Card[17];
    public int[] cardList2 = new int[17];
    public int[] cardList3 = new int[17];

    public Animation cardA1;
    public Animation cardA2;
    public Animation cardA3;
    public Animation SendCardmask;

    private GameObject sendCard;

    void Start()
    {
        sendCard = GameObject.Find("SendCard");
    }

    
    //返回重新登陆
	public void BackView()
    {
        this.GetComponent<UIPanel>().alpha = 0;
        SignIn.nameLabel = SigninLabel;
    }
    
    public void Play()
    {        
        sendCard.active = false;
        cardA1.Play();
        cardA2.Play();
        cardA3.Play();
        SendCardmask.Play();
    }
}
