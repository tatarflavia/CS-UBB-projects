using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class startScript : MonoBehaviour
{
    GameObject startButon;
    AudioSource introAudio;

    // Start is called before the first frame update
    void Start()
    {
        startButon = GameObject.Find("startButon");
        introAudio = GameObject.Find("introAudio").GetComponent<AudioSource>();
        introAudio.Play(0);
    }

    // Update is called once per frame
    void Update()
    {
        if (!introAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {

                if (hit.collider.name == "startButon")
                {
                    Debug.Log("game starts");
                    SceneManager.LoadScene("CaprioaraInvatare");
                }
            }
        }
    }
}
