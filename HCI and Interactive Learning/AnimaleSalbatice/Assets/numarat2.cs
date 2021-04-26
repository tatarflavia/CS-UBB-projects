using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class numarat2 : MonoBehaviour
{
    GameObject nr_1, nr_2, nr_3, peste, mie, ghind, iarb, carne, pic;
    int count;

    int finalAudioStarted, iarbaAudioStarted = 0, carneAudioStarted = 0, pesteAudioStarted = 0, ghindeAudioStarted = 0, miereAudioStarted = 0,ok=1;

    AudioSource inceputAudio;
    AudioSource finalAudio;

    private AudioSource iarbaAudio;
    private AudioSource carneAudio;
    private AudioSource pesteAudio;
    private AudioSource ghindeAudio;
    private AudioSource miereAudio;

    private AudioSource warningAudio;
    private AudioSource successAudio;

    GameObject helpButton;
    AudioSource helpAudio;

    // Start is called before the first frame update
    void Start()
    {
        count = 1;

        nr_1 = GameObject.Find("unu (1)");
        nr_2 = GameObject.Find("doi (1)");
        nr_3 = GameObject.Find("trei (1)");
        peste = GameObject.Find("pesti (1)");
        mie = GameObject.Find("miere (1)");
        ghind = GameObject.Find("ghinde (1)");
        iarb = GameObject.Find("iarba (2)");
        carne = GameObject.Find("carnuri (1)");
        pic = GameObject.Find("picnic (1)");

        peste.transform.position = new Vector3(-1000f, -1000f, -1000f);
        mie.transform.position = new Vector3(-1000f, -1000f, -1000f);
        ghind.transform.position = new Vector3(-1000f, -1000f, -1000f);
        carne.transform.position = new Vector3(-1000f, -1000f, -1000f);
        pic.transform.position = new Vector3(-1000f, -1000f, -1000f);

        inceputAudio = GameObject.Find("inceput_parte2").GetComponent<AudioSource>();
        inceputAudio.Play(0);
        finalAudio = GameObject.Find("final_joc (2)").GetComponent<AudioSource>();
        finalAudioStarted = 0;

        miereAudio = GameObject.Find("miere_2").GetComponent<AudioSource>();
        ghindeAudio = GameObject.Find("ghinde").GetComponent<AudioSource>();
        pesteAudio = GameObject.Find("pesti_2").GetComponent<AudioSource>();
        carneAudio = GameObject.Find("carne").GetComponent<AudioSource>();
        iarbaAudio = GameObject.Find("iarba_2").GetComponent<AudioSource>();

        warningAudio = GameObject.Find("mai incearca").GetComponent<AudioSource>();
        successAudio = GameObject.Find("bravo_scurt").GetComponent<AudioSource>();


        helpButton = GameObject.Find("semn (1)");
        helpAudio = GameObject.Find("click_nr care arata").GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && ok == 1)
        {
            iarbaAudio.Play(0);
            iarbaAudioStarted = 1;
            ok = 0;
        }
        else if (!inceputAudio.isPlaying && !warningAudio.isPlaying && !successAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "semn (1)" && !iarbaAudio.isPlaying && !pesteAudio.isPlaying && !miereAudio.isPlaying && !ghindeAudio.isPlaying && !carneAudio.isPlaying && !finalAudio.isPlaying)
                {
                    helpAudio.Play(0);
                }
                if (!helpAudio.isPlaying && !iarbaAudio.isPlaying && !pesteAudio.isPlaying && !miereAudio.isPlaying && !ghindeAudio.isPlaying && !carneAudio.isPlaying && !finalAudio.isPlaying)
                {
                    if (hit.collider.name == "unu (1)")
                    {
                        if (count == 1)
                        {
                            Debug.Log("vreau ca iarba sa dispara");
                            successAudio.Play(0);
                            count++;
                            iarb.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            peste.transform.position = new Vector3(-0.7f, -2.27f, -1f);
                            Debug.Log("pestii au aparut");
                        }
                        else if (count == 4)
                        {
                            Debug.Log("vreau ca mierea sa dispara");
                            successAudio.Play(0);
                            mie.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            carne.transform.position = new Vector3(-0.7f, -2.27f, -1f);
                            Debug.Log("carnea a aparut");
                        }
                        else if (!iarbaAudio.isPlaying && !pesteAudio.isPlaying && !miereAudio.isPlaying && !ghindeAudio.isPlaying && !carneAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "trei (1)")
                    {
                        if (count == 2)
                        {
                            Debug.Log("vreau ca pestii sa dispara");
                            successAudio.Play(0);
                            count++;
                            peste.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            ghind.transform.position = new Vector3(-0.7f, -2.27f, -1f);
                            Debug.Log("ghindele au aparut");
                        }
                        else if (!iarbaAudio.isPlaying && !pesteAudio.isPlaying && !miereAudio.isPlaying && !ghindeAudio.isPlaying && !carneAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "doi (1)")
                    {
                        if (count == 3)
                        {
                            Debug.Log("vreau ca ghindele sa dispara");
                            successAudio.Play(0);
                            ghind.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            mie.transform.position = new Vector3(-0.7f, -2.27f, -1f);
                            Debug.Log("mierea a aparut");
                        }
                        else if (count == 5)
                        {
                            Debug.Log("vreau ca carnea sa dispara");
                            successAudio.Play(0);
                            carne.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            pic.transform.position = new Vector3(0.62f, -0.1f, -2f);
                            nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            nr_3.transform.position = new Vector3(-1000f, -1000f, -1000f);
                        }
                        else if (!iarbaAudio.isPlaying && !pesteAudio.isPlaying && !miereAudio.isPlaying && !ghindeAudio.isPlaying && !carneAudio.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                }
            }
        }
        if (!warningAudio.isPlaying)
        {
            if (iarbaAudioStarted == 1 && !iarbaAudio.isPlaying && count == 2 && !successAudio.isPlaying)
            {
                pesteAudio.Play(0);
                pesteAudioStarted = 1;
                iarbaAudioStarted = 0;
            }
            if (pesteAudioStarted == 1 && !pesteAudio.isPlaying && count == 3 && !successAudio.isPlaying)
            {
                ghindeAudio.Play(0);
                ghindeAudioStarted = 1;
                pesteAudioStarted = 0;
            }
            if (ghindeAudioStarted == 1 && !ghindeAudio.isPlaying && count == 4 && !successAudio.isPlaying)
            {
                miereAudioStarted = 1;
                miereAudio.Play(0);
                ghindeAudioStarted = 0;
            }
            if (miereAudioStarted == 1 && !miereAudio.isPlaying && count == 5 && !successAudio.isPlaying)
            {
                carneAudio.Play(0);
                carneAudioStarted = 1;
                miereAudioStarted = 0;
            }
            if (carneAudioStarted == 1 && !carneAudio.isPlaying && count == 6 && !successAudio.isPlaying)
            {
                finalAudio.Play(0);
                finalAudioStarted = 1;
                carneAudioStarted = 0;
            }
            if (finalAudioStarted == 1 && !finalAudio.isPlaying)
            {
                SceneManager.LoadScene("Mancare");
            }
        }
    }
}