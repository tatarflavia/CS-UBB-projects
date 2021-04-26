using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class diplomaExtensie : MonoBehaviour
{
    GameObject caprioara, vulpe, urs, veverita, lup;

    AudioSource inceputAudio, finalAudio, caprioaraAudio, vulpeAudio, veveritaAudio, lupAudio, ursAudio;
    int caprioaraOnScreen = 0, vulpeOnScreen = 0, ursOnScreen = 0, veveritaOnScreen = 0, lupOnScreen = 0, inceputStarted=0;

    GameObject backButton;

    // Start is called before the first frame update
    void Start()
    {
        caprioara = GameObject.Find("caprioara");
        urs = GameObject.Find("urs");
        vulpe = GameObject.Find("vulpe");
        veverita = GameObject.Find("veverita");
        lup = GameObject.Find("lup");

        backButton = GameObject.Find("back");

        inceputAudio = GameObject.Find("inceputDiploma").GetComponent<AudioSource>();
        lupAudio = GameObject.Find("lupDiploma").GetComponent<AudioSource>();//1
        vulpeAudio = GameObject.Find("vulpeDiploma").GetComponent<AudioSource>();//2
        ursAudio = GameObject.Find("ursDiploma").GetComponent<AudioSource>();//3
        caprioaraAudio = GameObject.Find("caprioaraDiploma").GetComponent<AudioSource>();//4
        veveritaAudio = GameObject.Find("veveritaDiploma").GetComponent<AudioSource>();//5
        finalAudio = GameObject.Find("finalDiploma").GetComponent<AudioSource>();

        urs.transform.position = new Vector3(-4.47f, 2.03f, 0f);
        lup.transform.position = new Vector3(-3.21f, -2.33f, 0f);
        vulpe.transform.position = new Vector3(0.49f, -3.84f, 0f);
        veverita.transform.position = new Vector3(5.26f, -2.8f, 0f);
        caprioara.transform.position = new Vector3(4.06f, 0.72f, 0f);

        inceputAudio.Play(0);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "back")
                {
                    SceneManager.LoadScene("inceputExtensie");
                }
            }
        }
        if(!inceputAudio.isPlaying && inceputStarted==0)
        {
            lup.transform.position = new Vector3(-3.21f, -2.33f, -2f);
            lupAudio.Play(0);
            inceputStarted = 1;
            lupOnScreen = 1;
        }
        else if(lupOnScreen==1 && !lupAudio.isPlaying)
        {
            vulpe.transform.position = new Vector3(0.49f, -3.84f, -2f);
            vulpeAudio.Play(0);
            vulpeOnScreen = 1;
            lupOnScreen = 0;
        }
        else if(vulpeOnScreen==1 && !vulpeAudio.isPlaying)
        {
            urs.transform.position = new Vector3(-4.47f, 2.03f, -2f);
            ursAudio.Play(0);
            ursOnScreen = 1;
            vulpeOnScreen = 0;
        }
        else if (ursOnScreen == 1 && !ursAudio.isPlaying)
        {
            caprioara.transform.position = new Vector3(4.06f, 0.72f, -2f);
            caprioaraAudio.Play(0);
            caprioaraOnScreen = 1;
            ursOnScreen = 0;
        }
        else if (caprioaraOnScreen == 1 && !caprioaraAudio.isPlaying)
        {
            veverita.transform.position = new Vector3(5.26f, -2.8f, -2f);
            veveritaAudio.Play(0);
            veveritaOnScreen = 1;
            caprioaraOnScreen = 0;
        }
        else if (veveritaOnScreen ==1 && !veveritaAudio.isPlaying)
        {
            finalAudio.Play(0);
            veveritaOnScreen = 0;
        }
    }
}
