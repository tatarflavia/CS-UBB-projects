using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MamePuiCode : MonoBehaviour
{
    GameObject lup, veverita, urs, vulpe, caprioara;
    GameObject caprioaraBebe, lupBebe, ursBebe, vulpeBebe, veveritaBebe;
    int count;
    int finalAudioStarted,ok=1;

    int caprioaraAudioStarted = 0, lupAudioStarted = 0, ursAudioStarted = 0, vulpeAudioStarted = 0, veveritaAudioStarted = 0;
    private AudioSource warningAudio;
    private AudioSource successAudio;

    AudioSource inceputAudio;
    AudioSource finalAudio;

    AudioSource lupAudio;
    AudioSource ursAudio;
    AudioSource vulpeAudio;
    AudioSource veveritaAudio;
    AudioSource caprioaraAudio;

    GameObject helpButton;
    AudioSource helpAudio;

    // Start is called before the first frame update
    void Start()
    {
        finalAudioStarted = 0;
        count = 1;

        caprioara = GameObject.Find("Caprioara"); //1
        lup = GameObject.Find("Lup"); //2
        urs = GameObject.Find("Urs"); //3
        vulpe = GameObject.Find("Vulpe"); //4
        veverita = GameObject.Find("Veverita"); //5

        caprioaraBebe = GameObject.Find("CaprioaraBebe");
        ursBebe = GameObject.Find("UrsBebe");
        vulpeBebe = GameObject.Find("VulpeBebe");
        veveritaBebe = GameObject.Find("VeveritaBebe");
        lupBebe = GameObject.Find("LupBebe");

        caprioaraBebe.transform.position = new Vector3(0.41f, -3.09f, -2f);
        lupBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
        ursBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
        veveritaBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
        vulpeBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);

        inceputAudio = GameObject.Find("inceput_joc").GetComponent<AudioSource>();
        inceputAudio.Play(0);
        finalAudio = GameObject.Find("final_joc").GetComponent<AudioSource>();
        lupAudio = GameObject.Find("lup").GetComponent<AudioSource>();
        ursAudio = GameObject.Find("urs").GetComponent<AudioSource>();
        vulpeAudio = GameObject.Find("vulpe").GetComponent<AudioSource>();
        veveritaAudio = GameObject.Find("veverita").GetComponent<AudioSource>();
        caprioaraAudio = GameObject.Find("caprioara").GetComponent<AudioSource>();

        warningAudio = GameObject.Find("mai incearca").GetComponent<AudioSource>();
        successAudio = GameObject.Find("bravo_scurt").GetComponent<AudioSource>();

        helpButton = GameObject.Find("semn");
        helpAudio = GameObject.Find("instructiune_1").GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && ok==1)
        {
            caprioaraAudio.Play(0);
            caprioaraAudioStarted = 1;
            ok = 0;
        }

        else if (!inceputAudio.isPlaying && !warningAudio.isPlaying && !successAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if(hit.collider.name=="semn" && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying && !finalAudio.isPlaying)
                {
                    helpAudio.Play(0);
                }
                if (!helpAudio.isPlaying)
                {
                    if (hit.collider.name == "Caprioara" && !caprioaraAudio.isPlaying)
                    {
                        if (count == 1)
                        {
                            Debug.Log("Caprioara is clicked by mouse");
                            successAudio.Play(0);
                            caprioara.SetActive(false);
                            count++;
                            caprioaraBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            lupBebe.transform.position = new Vector3(0.41f, -3.09f, -2f);

                        }
                        else if (count != 1 && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }

                    else if (hit.collider.name == "Lup" && !lupAudio.isPlaying)
                    {
                        if (count == 2)
                        {
                            Debug.Log("Lup is clicked by mouse");
                            successAudio.Play(0);
                            lup.SetActive(false);
                            count++;
                            lupBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            ursBebe.transform.position = new Vector3(0.41f, -3.09f, -2f);
                        }
                        else if (count != 2 && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }

                    else if (hit.collider.name == "Urs" && !ursAudio.isPlaying)
                    {
                        if (count == 3)
                        {
                            Debug.Log("Urs is clicked by mouse");
                            successAudio.Play(0);
                            urs.SetActive(false);
                            count++;
                            ursBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            vulpeBebe.transform.position = new Vector3(0.41f, -3.09f, -2f);
                        }
                        else if (count != 3 && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }

                    else if (hit.collider.name == "Vulpe" && !vulpeAudio.isPlaying)
                    {
                        if (count == 4)
                        {
                            Debug.Log("Vulpe is clicked by mouse");
                            successAudio.Play(0);
                            vulpe.SetActive(false);
                            count++;
                            vulpeBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            veveritaBebe.transform.position = new Vector3(0.41f, -3.09f, -2f);
                        }
                        else if (count != 4 && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }

                    else if (hit.collider.name == "Veverita" && !veveritaAudio.isPlaying)
                    {
                        if (count == 5)
                        {
                            Debug.Log("Veverita is clicked by mouse");
                            successAudio.Play(0);
                            veverita.SetActive(false);
                            count++;
                            veveritaBebe.transform.position = new Vector3(-1000f, -1000f, -1000f);
                        }
                        else if (count != 5 && !caprioaraAudio.isPlaying && !ursAudio.isPlaying && !veveritaAudio.isPlaying && !vulpeAudio.isPlaying && !lupAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                }
            }
        }
        if (!warningAudio.isPlaying)
        {
            if (caprioaraAudioStarted == 1 && !caprioaraAudio.isPlaying && count==2 && !successAudio.isPlaying)
            {
                lupAudio.Play(0);
                lupAudioStarted = 1;
                caprioaraAudioStarted = 0;
            }

            if (ursAudioStarted == 1 && !ursAudio.isPlaying && count == 4 && !successAudio.isPlaying)
            {
                vulpeAudio.Play(0);
                vulpeAudioStarted = 1;
                ursAudioStarted = 0;
            }
            if (lupAudioStarted == 1 && !lupAudio.isPlaying && count == 3 && !successAudio.isPlaying)
            {
                ursAudio.Play(0);
                ursAudioStarted = 1;
                lupAudioStarted = 0;
            }
            if (vulpeAudioStarted == 1 && !vulpeAudio.isPlaying && count == 5 && !successAudio.isPlaying)
            {
                veveritaAudio.Play(0);
                veveritaAudioStarted = 1;
                vulpeAudioStarted = 0;
            }
            if (veveritaAudioStarted == 1 && !veveritaAudio.isPlaying && count==6 && !successAudio.isPlaying)
            {
                veveritaAudioStarted = 0;
                finalAudioStarted = 1;
                finalAudio.Play(0);
            }
            if (finalAudioStarted == 1 && !finalAudio.isPlaying)
            {
                SceneManager.LoadScene("Numara_Activity");
            }
        }
    }
}
