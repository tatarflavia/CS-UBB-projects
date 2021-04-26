using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Script_Numara : MonoBehaviour
{
    GameObject nr_1, nr_2, nr_3, peste, mie, ghind, iarb, carne;
    int count, ok = 1;
    int finalAudioStarted;
    int iarbaDoneStarted = 0, carneDoneStarted = 0, pesteDoneStarted = 0, ghindeDoneStarted = 0, miereDoneStarted = 0;

    AudioSource inceputAudio;
    AudioSource finalAudio;

    private AudioSource startCerinte;
    private AudioSource iarbaDone;
    private AudioSource carneDone;
    private AudioSource pesteDone;
    private AudioSource ghindeDone;
    private AudioSource miereDone;

    private AudioSource warningAudio;
    private AudioSource successAudio;

    GameObject helpButton;
    AudioSource helpAudio;

    // Start is called before the first frame update
    void Start()
    {
        finalAudioStarted = 0;
        count = 1;

        nr_1 = GameObject.Find("unu");
        nr_2 = GameObject.Find("doi");
        nr_3 = GameObject.Find("trei");
        peste = GameObject.Find("pesti");
        mie = GameObject.Find("miere");
        ghind = GameObject.Find("ghinde");
        iarb = GameObject.Find("iarba");
        carne = GameObject.Find("carnuri");

        nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
        nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
        nr_3.transform.position = new Vector3(-1000f, -1000f, -1000f);

        inceputAudio = GameObject.Find("inceput_2").GetComponent<AudioSource>();
        inceputAudio.Play(0);
        finalAudio = GameObject.Find("final_parte1").GetComponent<AudioSource>();

        startCerinte = GameObject.Find("iarba (1)").GetComponent<AudioSource>();
        miereDone = GameObject.Find("miere_bravo").GetComponent<AudioSource>();
        ghindeDone = GameObject.Find("ghinde_bravo").GetComponent<AudioSource>();
        pesteDone = GameObject.Find("pesti_bravo").GetComponent<AudioSource>();
        carneDone = GameObject.Find("carne_bravo").GetComponent<AudioSource>();
        iarbaDone = GameObject.Find("iarba_bravo").GetComponent<AudioSource>();

        warningAudio = GameObject.Find("mai incearca").GetComponent<AudioSource>();
        successAudio = GameObject.Find("bravo_scurt").GetComponent<AudioSource>();


        helpButton = GameObject.Find("semn (1)");
        helpAudio = GameObject.Find("click_pe mancare").GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && ok == 1)
        {
            startCerinte.Play(0);
            ok = 0;
        }

        else if (!inceputAudio.isPlaying && !warningAudio.isPlaying && !successAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "semn (1)" && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying && !finalAudio.isPlaying)
                {
                    Debug.Log("dsadsa");
                    helpAudio.Play(0);
                }
                if (!helpAudio.isPlaying)
                {
                    if (hit.collider.name == "iarba" && !startCerinte.isPlaying)
                    {
                        if (count == 1)
                        {
                            Debug.Log("vreau ca 1 sa apara");
                            count++;
                            nr_1.transform.position = new Vector3(-0.10139f, -0.16f, -2f);
                            Debug.Log("1 a aparut");
                            iarb.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            Debug.Log("iarba a disparut");
                            iarbaDoneStarted = 1;
                            iarbaDone.Play(0);
                        }
                        else if (count != 1 && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "pesti" && !iarbaDone.isPlaying)
                    {
                        if (count == 2)
                        {
                            Debug.Log("vreau ca 1 sa dispara");
                            nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            nr_3.transform.position = new Vector3(-0.10139f, -0.16f, -2f);
                            Debug.Log("3 a aparut");
                            peste.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            pesteDoneStarted = 1;
                            pesteDone.Play(0);
                        }
                        else if (count != 2 && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "ghinde" && !pesteDone.isPlaying)
                    {
                        if (count == 3)
                        {
                            Debug.Log("vreau ca 3 sa dispara");
                            nr_3.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            nr_2.transform.position = new Vector3(-0.10139f, -0.16f, -2f);
                            Debug.Log("2 a aparut");
                            ghind.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            ghindeDoneStarted = 1;
                            ghindeDone.Play(0);
                        }
                        else if (count != 3 && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "miere" && !ghindeDone.isPlaying)
                    {
                        if (count == 4)
                        {
                            Debug.Log("vreau ca 2 sa dispara");
                            nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            nr_1.transform.position = new Vector3(-0.10139f, -0.16f, -2f);
                            Debug.Log("1 a aparut");
                            mie.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            miereDoneStarted = 1;
                            miereDone.Play(0);
                        }
                        else if (count != 4 && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (hit.collider.name == "carnuri" && !miereDone.isPlaying)
                    {
                        if (count == 5)
                        {
                            Debug.Log("vreau ca 1 sa dispara");
                            nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            count++;
                            nr_2.transform.position = new Vector3(-0.10139f, -0.16f, -2f);
                            Debug.Log("2 a aparut");
                            carne.transform.position = new Vector3(-1000f, -1000f, -1000f);
                            carneDoneStarted = 1;
                            carneDone.Play(0);
                        }
                        else if (count != 5 && !iarbaDone.isPlaying && !miereDone.isPlaying && !ghindeDone.isPlaying && !pesteDone.isPlaying && !carneDone.isPlaying && !startCerinte.isPlaying)
                        {
                            warningAudio.Play(0);
                        }
                    }
                    else if (count == 6 && !carneDone.isPlaying)
                    {
                        Debug.Log("vreau ca 2 sa dispara");
                        nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
                        count++;
                        Debug.Log("Picnicul e gata!!!");
                    }
                }
            }
        }
        if (!warningAudio.isPlaying)
        {
            if (iarbaDoneStarted == 1 && !iarbaDone.isPlaying)
            {
                nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
                iarbaDoneStarted = 0;
            }

            if (miereDoneStarted == 1 && !miereDone.isPlaying)
            {
                nr_1.transform.position = new Vector3(-1000f, -1000f, -1000f);
                miereDoneStarted = 0;
            }
            if (carneDoneStarted == 1 && !carneDone.isPlaying)
            {
                nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
                carneDoneStarted = 0;
                finalAudioStarted = 1;
                finalAudio.Play(0);
            }
            if (pesteDoneStarted == 1 && !pesteDone.isPlaying)
            {
                nr_3.transform.position = new Vector3(-1000f, -1000f, -1000f);
                pesteDoneStarted = 0;
            }
            if (ghindeDoneStarted == 1 && !ghindeDone.isPlaying)
            {
                nr_2.transform.position = new Vector3(-1000f, -1000f, -1000f);
                ghindeDoneStarted = 0;
            }

            if (finalAudioStarted == 1 && !finalAudio.isPlaying)
            {
                SceneManager.LoadScene("Numarat2");
            }
        }
    }
}