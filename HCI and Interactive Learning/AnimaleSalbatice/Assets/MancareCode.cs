using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MancareCode : MonoBehaviour
{
    private int count;
    int finalAudioStarted;
    private GameObject farfurie_lup, farfurie_vulpe, farfurie_caprioara, farfurie_urs, farfurie_veverita, peste, miere, iarba, ghinde, carne, carneLaLup;
    private string lastTagClicked;

    private Dictionary<string, int> errorCount;

    AudioSource inceputAudio;
    AudioSource finalAudio;

    private AudioSource helpLupAudio;
    private AudioSource helpUrsAudio;
    private AudioSource helpCaprioaraAudio;
    private AudioSource helpVeveritaAudio;
    private AudioSource helpVulpeAudio;

    private AudioSource warningAudio;
    private AudioSource successAudio;

    GameObject helpButton;
    AudioSource helpAudio;

    void Start()
    {
        finalAudioStarted = 0;
        count = 0;
        farfurie_lup = GameObject.Find("farfurie-lup");
        farfurie_caprioara = GameObject.Find("farfurie-caprioara");
        farfurie_vulpe = GameObject.Find("farfurie-vulpe");
        farfurie_veverita = GameObject.Find("farfurie-veverita");
        farfurie_urs = GameObject.Find("farfurie-urs");

        peste = GameObject.Find("peste");
        carne = GameObject.Find("carne");
        ghinde = GameObject.Find("ghinde");
        miere = GameObject.Find("miere");
        iarba = GameObject.Find("iarba");


        lastTagClicked = "";

        warningAudio = GameObject.Find("mai incearca").GetComponent<AudioSource>();
        successAudio = GameObject.Find("bravo_scurt").GetComponent<AudioSource>();

        errorCount = new Dictionary<string, int>();

        errorCount.Add("peste", 0);
        errorCount.Add("carne", 0);
        errorCount.Add("miere", 0);
        errorCount.Add("ghinde", 0);
        errorCount.Add("iarba", 0);

        inceputAudio = GameObject.Find("inceput_3").GetComponent<AudioSource>();
        inceputAudio.Play(0);
        finalAudio = GameObject.Find("final_3").GetComponent<AudioSource>();


        helpLupAudio = GameObject.Find("carne (1)").GetComponent<AudioSource>();
        helpUrsAudio = GameObject.Find("miere (1)").GetComponent<AudioSource>();
        helpVulpeAudio = GameObject.Find("peste (1)").GetComponent<AudioSource>();
        helpVeveritaAudio = GameObject.Find("ghinde (1)").GetComponent<AudioSource>();
        helpCaprioaraAudio = GameObject.Find("iarba (1)").GetComponent<AudioSource>();

        helpButton = GameObject.Find("semn (2)");
        helpAudio = GameObject.Find("instructiune_3").GetComponent<AudioSource>();

    }

    void verificareEroriCaSaFacemJocul()
    {
        //luam din dict mancarea care a fost gresit de 2 ori
        string key = "";
        foreach (KeyValuePair<string, int> entry in this.errorCount)
        {
            if (entry.Value == 2)
            {
                key = entry.Key;
                break;
            }
        }
        if (key != "")
        {
            Debug.Log("ii facem partea acuma pt " + key);
            mancareaMergeSinguraLaFarfurie();
            if (key == "iarba")
                helpCaprioaraAudio.Play(0);
            else if (key == "carne")
                helpLupAudio.Play(0);
            else if (key == "miere")
                helpUrsAudio.Play(0);
            else if (key == "peste")
                helpVulpeAudio.Play(0);
            else if (key == "ghinde")
                helpVeveritaAudio.Play(0);
            lastTagClicked = "";
            errorCount[key] = 0;
        }
    }


    void mancareaMergeSinguraLaFarfurie()
    {
        if (this.lastTagClicked == "peste")
        {
            peste.layer = LayerMask.NameToLayer("Ignore Raycast");
            farfurie_vulpe.layer = LayerMask.NameToLayer("Ignore Raycast");
            peste.transform.position = new Vector3(0.17f, -3.8f, -3f);
            peste.transform.localScale = new Vector3(0.4067419f, 0.3532811f, 1f);
            count++;
        }

        else if (this.lastTagClicked == "ghinde")
        {
            ghinde.layer = LayerMask.NameToLayer("Ignore Raycast");
            farfurie_veverita.layer = LayerMask.NameToLayer("Ignore Raycast");
            ghinde.transform.position = new Vector3(4.7f, -3.4f, -3f);
            ghinde.transform.localScale = new Vector3(0.5249423f, 0.5696594f, 1f);
            count++;
        }

        else if (this.lastTagClicked == "iarba")
        {
            iarba.layer = LayerMask.NameToLayer("Ignore Raycast");
            farfurie_caprioara.layer = LayerMask.NameToLayer("Ignore Raycast");
            iarba.transform.position = new Vector3(1.4f, 2f, -3f);
            iarba.transform.localScale = new Vector3(0.5329899f, 0.4602287f, 1f);
            count++;
        }
        else if (this.lastTagClicked == "miere")
        {
            miere.layer = LayerMask.NameToLayer("Ignore Raycast");
            farfurie_urs.layer = LayerMask.NameToLayer("Ignore Raycast");
            miere.transform.position = new Vector3(2.29f, -1.37f, -3f);
            miere.transform.localScale = new Vector3(0.6076733f, 0.6160328f, 1f);
            count++;
        }
        else if (this.lastTagClicked == "carne")
        {
            carne.layer = LayerMask.NameToLayer("Ignore Raycast");
            farfurie_lup.layer = LayerMask.NameToLayer("Ignore Raycast");
            carne.transform.position = new Vector3(-3.4f, -3.4f, -3f);
            carne.transform.localScale = new Vector3(0.4105837f, 0.5157263f, 1f);
            count++;
        }
    }


    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && !warningAudio.isPlaying && !successAudio.isPlaying && !helpLupAudio.isPlaying && !helpUrsAudio.isPlaying && !helpVeveritaAudio.isPlaying && !helpCaprioaraAudio.isPlaying && !helpVulpeAudio.isPlaying &&  Input.GetMouseButtonDown(0))
        {
            //mouse is clicked
            RaycastHit hit;
            Debug.Log("hit ");
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            Debug.Log("ray");
            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "semn (2)" && !helpLupAudio.isPlaying && !helpUrsAudio.isPlaying && !helpVeveritaAudio.isPlaying && !helpCaprioaraAudio.isPlaying && !helpVulpeAudio.isPlaying && !finalAudio.isPlaying)
                {
                    helpAudio.Play(0);
                }
                if (!helpAudio.isPlaying)
                {
                    Debug.Log(hit.collider.tag);
                    if (hit.collider.tag == "peste")
                    {

                        lastTagClicked = "peste";
                        Debug.Log("peste clicked ");

                    }
                    else if (hit.collider.tag == "farfurie-vulpe")
                    {
                        if (lastTagClicked == "peste")
                        {
                            Debug.Log("farfurie-vulpe clicked");
                            mancareaMergeSinguraLaFarfurie();
                            if (count != 5)
                                successAudio.Play(0);
                            lastTagClicked = "";
                        }
                        else
                        {
                            //amintire sa apese pe mancare si apoi pe farfurie
                            if (lastTagClicked == "")
                            {
                                warningAudio.Play(0);
                                Debug.Log("sunet pt amintire sa apese pe mancare si apoi pe farfurie");
                            }
                            else
                            {
                                Debug.Log("a gresit farfuria aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                                if (this.errorCount[lastTagClicked] == 0)
                                    warningAudio.Play(0);
                                this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                                verificareEroriCaSaFacemJocul();

                            }
                        }
                    }




                    else if (hit.collider.tag == "ghinde")
                    {
                        Debug.Log("ghinde clicked");
                        lastTagClicked = "ghinde";
                    }
                    else if (hit.collider.tag == "farfurie-veverita")
                    {
                        if (lastTagClicked == "ghinde")
                        {
                            Debug.Log("farfurie-veverita clicked");
                            mancareaMergeSinguraLaFarfurie();
                            if (count != 5)
                                successAudio.Play(0);
                            lastTagClicked = "";
                        }
                        else
                        {
                            //amintire sa apese pe mancare si apoi farfuria
                            if (lastTagClicked == "")
                            {
                                warningAudio.Play(0);
                                Debug.Log("sunet pt amintire sa apese pe mancare si apoi pe farfurie");
                            }
                            else
                            {
                                Debug.Log("a gresit farfuria aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                                if (this.errorCount[lastTagClicked] == 0)
                                    warningAudio.Play(0);
                                this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                                verificareEroriCaSaFacemJocul();
                            }
                        }
                    }

                    else if (hit.collider.tag == "iarba")
                    {
                        Debug.Log("iarba clicked");
                        lastTagClicked = "iarba";

                    }
                    else if (hit.collider.tag == "farfurie-caprioara")
                    {
                        if (lastTagClicked == "iarba")
                        {
                            Debug.Log("farfurie-caprioara clicked");
                            mancareaMergeSinguraLaFarfurie();
                            if (count != 5)
                                successAudio.Play(0);
                            lastTagClicked = "";
                        }
                        else
                        {
                            //amintire sa apese pe macare si apoi pe farfurie
                            if (lastTagClicked == "")
                            {
                                warningAudio.Play(0);
                                Debug.Log("sunet pt amintire sa apese pe mancare si apoi pe farfurie");
                            }
                            else
                            {
                                Debug.Log("a gresit farfuria aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                                if (this.errorCount[lastTagClicked] == 0)
                                    warningAudio.Play(0);
                                this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                                verificareEroriCaSaFacemJocul();
                            }
                        }
                    }

                    else if (hit.collider.tag == "miere")
                    {
                        Debug.Log("miere clicked");
                        lastTagClicked = "miere";
                    }
                    else if (hit.collider.tag == "farfurie-urs")
                    {
                        if (lastTagClicked == "miere")
                        {
                            Debug.Log("farfurie-urs clicked");
                            mancareaMergeSinguraLaFarfurie();
                            if (count != 5)
                                successAudio.Play(0);
                            lastTagClicked = "";
                        }
                        else
                        {
                            //amintire sa apese pe mancare si apoi pe farfurie
                            if (lastTagClicked == "")
                            {
                                warningAudio.Play(0);
                                Debug.Log("sunet pt amintire sa apese pe mancate si apoi pe farfurie");
                            }
                            else
                            {
                                Debug.Log("a gresit farfuria aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                                if (this.errorCount[lastTagClicked] == 0)
                                    warningAudio.Play(0);
                                this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                                verificareEroriCaSaFacemJocul();
                            }
                        }
                    }

                    else if (hit.collider.tag == "carne")
                    {
                        Debug.Log("carne clicked");
                        lastTagClicked = "carne";

                    }
                    else if (hit.collider.tag == "farfurie-lup")
                    {
                        if (lastTagClicked == "carne")
                        {
                            Debug.Log("farfurie-lup clicked");
                            mancareaMergeSinguraLaFarfurie();
                            if (count != 5)
                                successAudio.Play(0);
                            lastTagClicked = "";
                        }
                        else
                        {
                            //amintire sa apese pe mancare si apoi pe farfurie
                            if (lastTagClicked == "")
                            {
                                warningAudio.Play(0);
                                Debug.Log("sunet pt amintire sa apese pe mancare si apoi pe farfurie");
                            }
                            else
                            {
                                Debug.Log("a gresit farfuria aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                                if (this.errorCount[lastTagClicked] == 0)
                                    warningAudio.Play(0);
                                this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                                verificareEroriCaSaFacemJocul();
                            }
                        }
                    }
                }

                if (count == 5 && !helpLupAudio.isPlaying && !helpUrsAudio.isPlaying && !helpVeveritaAudio.isPlaying && !helpCaprioaraAudio.isPlaying && !helpVulpeAudio.isPlaying)
                {
                    finalAudioStarted = 1;
                    finalAudio.Play(0);
                }

            }
        }


        if (finalAudioStarted == 1 && !finalAudio.isPlaying)
        {
            SceneManager.LoadScene("Activity4");
        }
    }

}


