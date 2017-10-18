using UnityEngine;
using System.Collections;

public class SignIn : MonoBehaviour {

    public static UILabel nameLabel;
    public GameObject lobby;    
    public UIPanel window;
    public UITexture icon1;
    public UITexture icon2;
    public UITexture icon3;

    void Start()
    {
        nameLabel = GameObject.Find("SignIn/Input/Label").GetComponent<UILabel>() as UILabel;
    }

    public void Sign()
    {
        PlayerInfo player = new PlayerInfo(nameLabel.text);        
        if (player.Name == "Press here to type")
        {
            window.alpha = 1;
        }
        else
        {
            nameLabel = GameObject.Find("Lobby/Player1/Name1").GetComponent<UILabel>() as UILabel;
            nameLabel.text = player.Name;
            icon1.mainTexture = player.Icon;
            lobby.GetComponent<UIPanel>().alpha = 1;            
        }     
    }
}
