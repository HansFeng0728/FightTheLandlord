using UnityEngine;
using System.Collections;
using LitJson;
//using System.Runtime.Serialization;

public class Person
{
    // C# 3.0 auto-implemented properties
    public string Name { get; set; }
    public int Age { get; set; }
    public int Birthday { get; set; } 
}

public class Json : MonoBehaviour {

	// Use this for initialization
	void Start () {
        Person person = new Person();
        string str = JsonMapper.ToJson(person);
        Person person1 = JsonMapper.ToObject<Person>(str);
        //JsonData  jsData = JsonMapper.ToObject(str);
        //if (jsData.IsObject)
        //    Debug.Log("Sss");
        //if
    }
	
	// Update is called once per frame
	void Update () {
	
	}
}
