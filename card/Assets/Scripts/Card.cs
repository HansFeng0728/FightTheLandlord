using UnityEngine;
using System.Collections;

public class Card
{
    //private string[] number = { "3", "4", "5", "6", "7", 
    //                          "8","9","10","J","Q",
    //                            "K","A","2","sJoke","BJoke"};
    private string number;
    private int type;          //{1,2,3,4} 花色
    private int color;       //{1,2}     颜色

    public string Number
    {
        get { return number; }
        set { number = value; }
    }
    public int Type
    {
        get { return type; }
        set { type = value; }
    }
    public int Color
    {
        get { return color; }
        set { color = value; }
    }

    //public Card(string num , int typ , int col)
    //{
    //    Number = num;
    //    Type = typ;
    //    Color = col;
    //}
}
