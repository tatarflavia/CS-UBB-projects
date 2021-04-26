using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class lupExtensie : MonoBehaviour
{
    GameObject semnIntrebare, tufis, copac, jocLupEvaluareFundal, puiMicLup1, puiMicLup2, puiMareLup1, puiMareLup2, puiMareLup3, nor, unuCopac, doiCopac, treiCopac, unuTufis, doiTufis, treiTufis;
    AudioSource audioBravoLup, audioMaiIncearcaLup, audioIntroFullLup, audioInstructiuneLup, audioOutLup;
    bool gameStarted = false;
    bool gameStopped = false;
    string lastNameClicked;
    int errorCount;
    int successPtAnimaleCount;
    int successPtNumereCount;

    // Start is called before the first frame update
    void Start()
    {
        GlobalVariable.Instance.lupCheck = 1;
        Debug.Log(GlobalVariable.Instance.lupCheck);

        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(0.19f, 2.14f, 0f);
        nor.transform.localScale = new Vector3(0.5258456f, 0.5412653f, 1f);
        nor.GetComponent<Renderer>().sortingOrder = 2;

        jocLupEvaluareFundal = GameObject.Find("jocLupEvaluareFundal");
        jocLupEvaluareFundal.transform.position = new Vector3(-0.95f, 0f, 0f);
        jocLupEvaluareFundal.transform.localScale = new Vector3(1.259297f, 1.275905f, 1f);
        jocLupEvaluareFundal.GetComponent<Renderer>().sortingOrder = 0;

        copac = GameObject.Find("copac");
        copac.transform.position = new Vector3(-6.75f, 0.28f, 0f);
        copac.transform.localScale = new Vector3(0.8380657f, 0.8380657f, 1f);
        copac.GetComponent<Renderer>().sortingOrder = 1;


        tufis = GameObject.Find("tufis");
        tufis.transform.position = new Vector3(6.96f, 1.091f, 0f);
        tufis.transform.localScale = new Vector3(0.3929321f, 0.3631969f, 1f);
        tufis.GetComponent<Renderer>().sortingOrder = 1;

        unuCopac = GameObject.Find("unuCopac");
        unuCopac.transform.position = new Vector3(-3.52f, 3.79f, 0f);
        unuCopac.transform.localScale = new Vector3(0.4647608f, 0.434226f, 1f);
        unuCopac.GetComponent<Renderer>().sortingOrder = 3;
        unuCopac.GetComponent<Renderer>().enabled = false;

        semnIntrebare = GameObject.Find("semnIntrebare");
        semnIntrebare.transform.position = new Vector3(-8.366f, 4.63f, 0f);
        semnIntrebare.transform.localScale = new Vector3(0.05305377f, 0.05602552f, 1f);
        semnIntrebare.GetComponent<Renderer>().sortingOrder = 5;

        doiCopac = GameObject.Find("doiCopac");
        doiCopac.transform.position = new Vector3(-3.46f, 2.08f, 0f);
        doiCopac.transform.localScale = new Vector3(0.5024304f, 0.4906119f, 1f);
        doiCopac.GetComponent<Renderer>().sortingOrder = 3;
        doiCopac.GetComponent<Renderer>().enabled = false;

        treiCopac = GameObject.Find("treiCopac");
        treiCopac.transform.position = new Vector3(-3.49f, 0.29f, 0f);
        treiCopac.transform.localScale = new Vector3(0.5843831f, 0.6077126f, 1f);
        treiCopac.GetComponent<Renderer>().sortingOrder = 3;
        treiCopac.GetComponent<Renderer>().enabled = false;

        unuTufis = GameObject.Find("unuTufis");
        unuTufis.transform.position = new Vector3(3.87f, 3.76f, 0f);
        unuTufis.transform.localScale = new Vector3(0.5668219f, 0.5257421f, 1f);
        unuTufis.GetComponent<Renderer>().sortingOrder = 3;
        unuTufis.GetComponent<Renderer>().enabled = false;

        doiTufis = GameObject.Find("doiTufis");
        doiTufis.transform.position = new Vector3(5.46f, 3.69f, 0f);
        doiTufis.transform.localScale = new Vector3(0.5785293f, 0.5550172f, 1f);
        doiTufis.GetComponent<Renderer>().sortingOrder = 3;
        doiTufis.GetComponent<Renderer>().enabled = false;

        treiTufis = GameObject.Find("treiTufis");
        treiTufis.transform.position = new Vector3(7.195f, 3.765f, 0f);
        treiTufis.transform.localScale = new Vector3(0.5668218f, 0.5608723f, 1f);
        treiTufis.GetComponent<Renderer>().sortingOrder = 3;
        treiTufis.GetComponent<Renderer>().enabled = false;

        puiMicLup1 = GameObject.Find("puiMicLup1");
        puiMicLup1.transform.position = new Vector3(0.19f, 2.95f, 0f);
        puiMicLup1.transform.localScale = new Vector3(0.5461692f, 0.4992954f, 1f);
        puiMicLup1.GetComponent<Renderer>().sortingOrder = 3;

        puiMicLup2 = GameObject.Find("puiMicLup2");
        puiMicLup2.transform.position = new Vector3(-1.47f, -1.77f, 0f);
        puiMicLup2.transform.localScale = new Vector3(0.2809876f, 0.2691783f, 1f);
        puiMicLup2.GetComponent<Renderer>().sortingOrder = 2;

        puiMareLup1 = GameObject.Find("puiMareLup1");
        puiMareLup1.transform.position = new Vector3(0.92f, -0.99f, 0f);
        puiMareLup1.transform.localScale = new Vector3(0.3950831f, 0.353693f, 1f);
        puiMareLup1.GetComponent<Renderer>().sortingOrder = 2;

        puiMareLup2 = GameObject.Find("puiMareLup2");
        puiMareLup2.transform.position = new Vector3(3.6f, -3.36f, 0f);
        puiMareLup2.transform.localScale = new Vector3(0.3501563f, 0.2397565f, 1f);
        puiMareLup2.GetComponent<Renderer>().sortingOrder = 4;

        puiMareLup3 = GameObject.Find("puiMareLup3");
        puiMareLup3.transform.position = new Vector3(0.96f, -3.55f, 0f);
        puiMareLup3.transform.localScale = new Vector3(0.5834504f, 0.4657425f, 1f);
        puiMareLup3.GetComponent<Renderer>().sortingOrder = 3;

        audioIntroFullLup = GameObject.Find("audioIntroFullLup").GetComponent<AudioSource>();
        audioIntroFullLup.Play(0);

        audioInstructiuneLup = GameObject.Find("audioInstructiuneLup").GetComponent<AudioSource>();
        audioOutLup = GameObject.Find("audioOutLup").GetComponent<AudioSource>();
        audioBravoLup = GameObject.Find("audioBravoLup").GetComponent<AudioSource>();
        audioMaiIncearcaLup = GameObject.Find("audioMaiIncearcaLup").GetComponent<AudioSource>();

        lastNameClicked = "";

        errorCount = 0;
        successPtAnimaleCount = 0;
        successPtNumereCount = 0;

    }

    void seSchimbaJoculLaSuccess()
    {
        if (successPtAnimaleCount == 5)
        {
            unuCopac.GetComponent<Renderer>().enabled = true;
            doiCopac.GetComponent<Renderer>().enabled = true;
            unuTufis.GetComponent<Renderer>().enabled = true;
            doiTufis.GetComponent<Renderer>().enabled = true;
            treiTufis.GetComponent<Renderer>().enabled = true;
            errorCount = 0;
        }

        if (this.lastNameClicked == "puiMicLup1")
        {
            if (successPtAnimaleCount != 5)
            {
                audioBravoLup.Play(0);
            }
            lastNameClicked = "";
            successPtAnimaleCount++;
            puiMicLup1.transform.position = new Vector3(-5.25f, -2.38f, 0f);
        }
        else if (this.lastNameClicked == "puiMicLup2")
        {
            if (successPtAnimaleCount != 5)
            {
                audioBravoLup.Play(0);
            }
            lastNameClicked = "";
            successPtAnimaleCount++;
            puiMicLup2.transform.position = new Vector3(-7.4f, -2.23f, 0f);
        }
        else if (this.lastNameClicked == "puiMareLup1")
        {
            if (successPtAnimaleCount != 5)
            {
                audioBravoLup.Play(0);
            }
            lastNameClicked = "";
            successPtAnimaleCount++;
            puiMareLup1.transform.position = new Vector3(3.77f, -0.08f, 0f);
        }
        else if (this.lastNameClicked == "puiMareLup2")
        {
            if (successPtAnimaleCount != 5)
            {
                audioBravoLup.Play(0);
            }
            lastNameClicked = "";
            successPtAnimaleCount++;
            puiMareLup2.transform.position = new Vector3(7.88f, -0.42f, 0f);
        }
        else if (this.lastNameClicked == "puiMareLup3")
        {
            if (successPtAnimaleCount != 5)
            {
                audioBravoLup.Play(0);
            }
            lastNameClicked = "";
            successPtAnimaleCount++;
            puiMareLup3.transform.position = new Vector3(5.67f, -0.57f, 0f);
        }
        if (successPtAnimaleCount == 5)
        {
            unuCopac.GetComponent<Renderer>().enabled = true;
            doiCopac.GetComponent<Renderer>().enabled = true;
            treiCopac.GetComponent<Renderer>().enabled = true;
            unuTufis.GetComponent<Renderer>().enabled = true;
            doiTufis.GetComponent<Renderer>().enabled = true;
            treiTufis.GetComponent<Renderer>().enabled = true;
            errorCount = 0;
        }



    }

    private IEnumerator DelayLoadLevel()
    {
        yield return new WaitForSeconds(1f);
        semnIntrebare.GetComponent<Renderer>().enabled = true;

    }



    // Update is called once per frame
    void Update()
    {
        //semnIntrebare.GetComponent<Renderer>().enabled = true;
        //game hasn't started
        if (!gameStopped && !audioIntroFullLup.isPlaying && !gameStarted && !audioBravoLup.isPlaying && !audioInstructiuneLup.isPlaying && !audioMaiIncearcaLup.isPlaying && !audioOutLup.isPlaying)
        {
            gameStarted = true;
            puiMicLup1.transform.position = new Vector3(-1.76f, -3.52f, 0f);
            nor.GetComponent<Renderer>().enabled = false;
        }

        //mouse click only when audio not playing + game started
        else if (!gameStopped && Input.GetMouseButtonDown(0) && !audioIntroFullLup.isPlaying && gameStarted && !audioBravoLup.isPlaying && !audioInstructiuneLup.isPlaying && !audioMaiIncearcaLup.isPlaying && !audioOutLup.isPlaying)
        {
            //mouse is clicked
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {
                //intructiune cu semnul intrebarii
                if (hit.collider.name == "semnIntrebare")
                {
                    audioInstructiuneLup.Play(0);
                }
                if (!audioInstructiuneLup.isPlaying && successPtAnimaleCount != 5)
                {
                    //alt click pt animale zone
                    if (hit.collider.name == "puiMicLup1")
                    {
                        lastNameClicked = "puiMicLup1";
                    }
                    else if (hit.collider.name == "copac")
                    {
                        //vedem de puii mici
                        if (lastNameClicked == "")
                        {
                            //niciun animal clicked=> eroare
                            audioMaiIncearcaLup.Play(0);
                        }
                        else
                        {
                            if (lastNameClicked == "puiMicLup1" || lastNameClicked == "puiMicLup2")
                            {
                                // a ales bine
                                seSchimbaJoculLaSuccess();
                            }
                            else
                            {
                                //nu a ales bine animalul
                                errorCount++;
                                Debug.Log("greseala cu error count " + errorCount + " la copac");
                                audioMaiIncearcaLup.Play(0);
                                if (errorCount == 2)
                                {
                                    semnIntrebare.GetComponent<Renderer>().enabled = false;
                                    StartCoroutine(DelayLoadLevel());
                                }
                                else if (errorCount == 3)
                                {

                                    errorCount = 0;
                                    semnIntrebare.GetComponent<Renderer>().enabled = false;
                                    StartCoroutine(DelayLoadLevel());


                                }
                            }
                        }
                    }
                    else if (hit.collider.name == "puiMicLup2")
                    {
                        lastNameClicked = "puiMicLup2";
                    }
                    else if (hit.collider.name == "puiMareLup1")
                    {
                        lastNameClicked = "puiMareLup1";
                    }
                    else if (hit.collider.name == "puiMareLup2")
                    {
                        lastNameClicked = "puiMareLup2";
                    }
                    else if (hit.collider.name == "puiMareLup3")
                    {
                        lastNameClicked = "puiMareLup3";
                    }
                    else if (hit.collider.name == "tufis")
                    {
                        //vedem de puii mari
                        if (lastNameClicked == "")
                        {
                            //niciun animal clicked=> eroare
                            audioMaiIncearcaLup.Play(0);
                        }
                        else
                        {
                            if (lastNameClicked == "puiMareLup1" || lastNameClicked == "puiMareLup2" || lastNameClicked == "puiMareLup3")
                            {
                                //a ales bine 
                                seSchimbaJoculLaSuccess();
                            }
                            else
                            {
                                //nu a ales bine animalul pt locul asta

                                errorCount++;
                                Debug.Log("greseala cu error count " + errorCount + " la tufis");
                                audioMaiIncearcaLup.Play(0);
                                if (errorCount == 2)
                                {
                                    semnIntrebare.GetComponent<Renderer>().enabled = false;
                                    StartCoroutine(DelayLoadLevel());
                                }
                                else if (errorCount == 3)
                                {

                                    errorCount = 0;
                                    semnIntrebare.GetComponent<Renderer>().enabled = false;
                                    StartCoroutine(DelayLoadLevel());


                                }
                            }
                        }
                    }
                }
                if (!audioInstructiuneLup.isPlaying && successPtAnimaleCount == 5 && successPtNumereCount != 2)
                {
                    if (hit.collider.name == "doiCopac")
                    {
                        successPtNumereCount++;
                        audioBravoLup.Play(0);
                        unuCopac.GetComponent<Renderer>().enabled = false;
                        treiCopac.GetComponent<Renderer>().enabled = false;
                    }
                    else if (hit.collider.name == "treiTufis")
                    {
                        successPtNumereCount++;
                        audioBravoLup.Play(0);
                        unuTufis.GetComponent<Renderer>().enabled = false;
                        doiTufis.GetComponent<Renderer>().enabled = false;
                    }
                    else if (hit.collider.name == "doiTufis" || hit.collider.name == "unuTufis" || hit.collider.name == "unuCopac" || hit.collider.name == "treiCopac")
                    {
                        Debug.Log("greseala cu error count " + errorCount + " la numere");
                        errorCount++;
                        audioMaiIncearcaLup.Play(0);
                        if (errorCount == 2)
                        {
                            semnIntrebare.GetComponent<Renderer>().enabled = false;
                            StartCoroutine(DelayLoadLevel());
                        }
                        else if (errorCount == 3)
                        {

                            errorCount = 0;
                            semnIntrebare.GetComponent<Renderer>().enabled = false;
                            StartCoroutine(DelayLoadLevel());


                        }
                    }
                }


            }
        }

        else if (gameStarted && !gameStopped && !audioIntroFullLup.isPlaying && !audioBravoLup.isPlaying && !audioInstructiuneLup.isPlaying && !audioMaiIncearcaLup.isPlaying && !audioOutLup.isPlaying && successPtAnimaleCount == 5 && successPtNumereCount == 2)
        {
            //gata numere zone
            gameStopped = true;
            audioOutLup.Play(0);
        }

        else if (gameStarted && gameStopped && !audioIntroFullLup.isPlaying && !audioBravoLup.isPlaying && !audioInstructiuneLup.isPlaying && !audioMaiIncearcaLup.isPlaying && !audioOutLup.isPlaying)
        {
            Debug.Log("go to next scene now");
            SceneManager.LoadScene("inceputExtensie");
        }
    }
}
