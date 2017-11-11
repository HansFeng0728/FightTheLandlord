using UnityEngine;
using System.Collections;
using LitJson;

public class SignIn : MonoBehaviour {

    public UILabel inPutLabel;
    public GameObject lobby;    
    public UIPanel window;

    void Start()
    {
    }

    public void Sign()
    {
        Manager.player0 = new PlayerInfo(inPutLabel.text);
        Manager.player1 = new PlayerInfo("电脑1");
        Manager.player1.State = 3;        
        Manager.httpVar.Connect(Manager.player0.Name);
        
        //发送信息向服务器端
        //s.SendMessage("cardlist");

        if (Manager.player0.Name == "")
        {
            window.alpha = 1;
        }
        else
        {
            lobby.GetComponent<UIPanel>().alpha = 1;            
        }     
    }
}
