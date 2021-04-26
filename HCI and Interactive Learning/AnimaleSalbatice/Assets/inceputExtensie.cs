using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GlobalVariable
{
    public int lupCheck, caprioaraCheck, veveritaCheck, ursCheck, vulpeCheck, cupaCheck;

    private static GlobalVariable instance;

    public static GlobalVariable Instance
    {
        get
        {
            if (instance == null)
            {
                instance = new GlobalVariable();
            }

            return instance;
        }
    }
}

public class inceputExtensie : MonoBehaviour
{
    GameObject caprioara, vulpe, urs, veverita, lup, trofeu;
    int count;

    int inceputAudioStarted = 0, ok = 1;

    AudioSource inceputAudio;

    GameObject helpButton, exitButton;
    AudioSource helpAudio;

    AudioSource cupaAudio;


    // Start is called before the first frame update
    void Start()
    {
        Debug.Log(GlobalVariable.Instance.lupCheck);
        Debug.Log(GlobalVariable.Instance.caprioaraCheck);
        Debug.Log(GlobalVariable.Instance.veveritaCheck);
        Debug.Log(GlobalVariable.Instance.vulpeCheck);
        Debug.Log(GlobalVariable.Instance.ursCheck);

        caprioara = GameObject.Find("caprioara");
        urs = GameObject.Find("urs");
        vulpe = GameObject.Find("vulpe");
        veverita = GameObject.Find("veverita");
        lup = GameObject.Find("lup");

        inceputAudio = GameObject.Find("inceputExtensie").GetComponent<AudioSource>();

        if (GlobalVariable.Instance.lupCheck != 1 && GlobalVariable.Instance.caprioaraCheck != 1 && GlobalVariable.Instance.veveritaCheck != 1 && GlobalVariable.Instance.vulpeCheck != 1 && GlobalVariable.Instance.ursCheck != 1) 
        {
           inceputAudio.Play(0);
        }

        helpButton = GameObject.Find("semnIntrebare");
        helpAudio = GameObject.Find("sarcinaHelp").GetComponent<AudioSource>();

        cupaAudio = GameObject.Find("cupaAudio").GetComponent<AudioSource>();

        exitButton = GameObject.Find("exit");
        trofeu = GameObject.Find("trofeu");
        trofeu.transform.position = new Vector3(6.96f, -3.75f, 0f);
    }

    private IEnumerator DelayLoadLevel()
    {
        yield return new WaitForSeconds(1f);
        trofeu.GetComponent<Renderer>().enabled = true;
    }

    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && ok == 1)
        {
            ok = 0;
        }
        else if (!inceputAudio.isPlaying && !cupaAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "semnIntrebare")
                {
                    helpAudio.Play(0);
                }
                else if (hit.collider.name == "exit")
                {
                    Debug.Log("exit");
                    Application.Quit();
                }
                else if (!helpAudio.isPlaying)
                {
                    if (hit.collider.name == "lup")
                    {
                        SceneManager.LoadScene("lupExtensie");
                    }
                    else if (hit.collider.name == "urs") 
                    {
                        SceneManager.LoadScene("ursExtensie");
                    }
                    else if (hit.collider.name == "veverita")
                    {
                        SceneManager.LoadScene("veveritaExtensie");
                    }
                    else if (hit.collider.name == "vulpe")
                    {
                        SceneManager.LoadScene("vulpeExtensie");
                    }
                    else if (hit.collider.name == "caprioara")
                    {
                        SceneManager.LoadScene("caprioaraExtensie");
                    }
                    else if (hit.collider.name== "trofeu" && GlobalVariable.Instance.lupCheck ==1 && GlobalVariable.Instance.caprioaraCheck ==1 && GlobalVariable.Instance.veveritaCheck ==1 && GlobalVariable.Instance.ursCheck ==1 && GlobalVariable.Instance.vulpeCheck ==1)
                    {
                        SceneManager.LoadScene("diploma");
                    }
                }
            }
        }

        else if (GlobalVariable.Instance.lupCheck == 1 && GlobalVariable.Instance.caprioaraCheck == 1 && GlobalVariable.Instance.veveritaCheck == 1 && GlobalVariable.Instance.ursCheck == 1 && GlobalVariable.Instance.vulpeCheck == 1)
        {
            trofeu.transform.position = new Vector3(6.96f, -3.75f, -2f);
            if (GlobalVariable.Instance.cupaCheck != 1)
            {
                GlobalVariable.Instance.cupaCheck = 1;
                cupaAudio.Play(0);
                trofeu.GetComponent<Renderer>().enabled = false;
                StartCoroutine(DelayLoadLevel());
            }        
        }
    }
}
