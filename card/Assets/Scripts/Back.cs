using UnityEngine;
using System.Collections;

public class Back : MonoBehaviour {

	public void backView()
    {
        UIPanel panel = this.transform.parent.GetComponent<UIPanel>();
        panel.alpha = 0;
    }
}
