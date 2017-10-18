using UnityEngine;
using System.Collections;

public class PlayerInfo 
{
    private string name = "";
    private Texture icon;
    private string path = "";


    public string Name
    {
        get { return name; }
        set { name = value; }
    }

    public Texture Icon
    {
        get { return icon; }
        set { icon = value; }
    }
    public PlayerInfo(string name)
    {
        Name = name;
        int num = Random.Range(0, 4);
        path = string.Format("Art/Icon/p{0}", num);
        Icon = Resources.Load<Texture>(path);
    }
}
