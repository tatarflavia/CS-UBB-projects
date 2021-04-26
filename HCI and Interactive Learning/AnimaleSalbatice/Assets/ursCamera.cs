using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ursCamera : MonoBehaviour
{

    GameObject bebeUrs, mancareUrs, ursFundal, casaUrs, nor, bebeCaprioara, parinteUrs;
    AudioSource audioCasaUrs, audioMamaUrs, audioMancareUrs, audioCuriozitateUrs;
    bool gataAudioCasa = false;
    bool gataAudioMama = false;
    bool gataAudioMancare = false;
    bool gataAudioCuriozitate = false;
    bool readyForNextScene = false;

    // Start is called before the first frame update
    void Start()
    {
        ursFundal = GameObject.Find("ursFundal");
        ursFundal.transform.position = new Vector3(-0.09f, -0.19f, 0f);
        ursFundal.transform.localScale = new Vector3(0.9477481f, 0.9572142f, 1f);
        ursFundal.GetComponent<Renderer>().sortingOrder = 0;


        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(-4.532f, 2.688f, 0f);
        nor.transform.localScale = new Vector3(0.540146f, 0.5514576f, 1f);
        nor.GetComponent<Renderer>().sortingOrder = 4;

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(-4.5f, 3.55f, 0f);
        bebeCaprioara.transform.localScale = new Vector3(0.7657079f, 0.6042389f, 1f);
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 5;


        casaUrs = GameObject.Find("casaUrs");
        casaUrs.transform.position = new Vector3(2.8f, -1.34f, 0f);
        casaUrs.transform.localScale = new Vector3(1.443352f, 1.440341f, 1f);
        casaUrs.GetComponent<Renderer>().sortingOrder = 1;







        bebeUrs = GameObject.Find("bebeUrs");
        bebeUrs.transform.position = new Vector3(2.19f, -3.08f, 0f);
        bebeUrs.transform.localScale = new Vector3(1f, 1f, 1f);
        bebeUrs.GetComponent<Renderer>().sortingOrder = 3;

        parinteUrs = GameObject.Find("parinteUrs");
        parinteUrs.transform.position = new Vector3(-5.46f, -2.01f, -2f);
        parinteUrs.transform.localScale = new Vector3(1.416222f, 1.372388f, 1f);
        casaUrs.GetComponent<Renderer>().sortingOrder = 2;

        mancareUrs = GameObject.Find("mancareUrs");
        mancareUrs.GetComponent<Renderer>().sortingOrder = 4;


        mancareUrs.GetComponent<Renderer>().enabled = false;

        parinteUrs.GetComponent<Renderer>().enabled = false;

        audioMamaUrs = GameObject.Find("audioMamaUrs").GetComponent<AudioSource>();
        audioMancareUrs = GameObject.Find("audioMancareUrs").GetComponent<AudioSource>();
        audioCuriozitateUrs = GameObject.Find("audioCuriozitateUrs").GetComponent<AudioSource>();
        audioCasaUrs = GameObject.Find("audioCasaUrs").GetComponent<AudioSource>();
        audioCasaUrs.Play(0);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }

        if (!audioCasaUrs.isPlaying && !gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCasa = true;
            bebeUrs.GetComponent<Renderer>().enabled = false;
            parinteUrs.GetComponent<Renderer>().enabled = true;

            audioMamaUrs.Play(0);
        }

        if (!audioMamaUrs.isPlaying && gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMama = true;
            mancareUrs.transform.position = new Vector3(2.06f, -2.65f, 0f);
            mancareUrs.transform.localScale = new Vector3(1f, 1f, 1f);




            mancareUrs.GetComponent<Renderer>().enabled = true;


            audioMancareUrs.Play(0);
        }

        if (!audioMancareUrs.isPlaying && gataAudioCasa && gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMancare = true;
            audioCuriozitateUrs.Play(0);
        }

        if (!audioCuriozitateUrs.isPlaying && gataAudioCasa && gataAudioMama && gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCuriozitate = true;
            readyForNextScene = true;
        }


        if (readyForNextScene && gataAudioCasa && gataAudioMama && gataAudioMancare && gataAudioCuriozitate)
        {
            SceneManager.LoadScene("finalInvatare");
        }
    }
}