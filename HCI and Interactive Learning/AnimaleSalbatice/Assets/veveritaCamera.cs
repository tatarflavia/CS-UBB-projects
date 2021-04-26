using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class veveritaCamera : MonoBehaviour
{

    GameObject bebeVeverita, parinteVeverita, mancareVeverita, veveritaFundal, mancareVeverita2, casaVeverita, nor, casaVeverita2, bebeCaprioara, mancareVeverita3, mancareVeverita4;
    AudioSource audioCasaVeverita, audioMamaVeverita, audioMancareVeverita, audioCuriozitateVeverita;
    bool gataAudioCasa = false;
    bool gataAudioMama = false;
    bool gataAudioMancare = false;
    bool gataAudioCuriozitate = false;
    bool readyForNextScene = false;

    // Start is called before the first frame update
    void Start()
    {
        veveritaFundal = GameObject.Find("fundalVeverita");
        veveritaFundal.transform.position = new Vector3(-0.077f, 0.669f, 0f);
        veveritaFundal.transform.localScale = new Vector3(1.608984f, 1.856928f, 1f);
        veveritaFundal.GetComponent<Renderer>().sortingOrder = 0;

        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(7.43f, 2.1f, 0f);
        nor.transform.localScale = new Vector3(0.3959123f, 0.3955696f, 1f);
        nor.GetComponent<Renderer>().sortingOrder = 4;

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(7.46f, 3.23f, 0f);
        bebeCaprioara.transform.localScale = new Vector3(0.6374449f, 0.5950328f, 1f);
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 5;


        casaVeverita = GameObject.Find("casaVeverita");
        casaVeverita.transform.position = new Vector3(-0.257f, 0.663f, 0f);
        casaVeverita.transform.localScale = new Vector3(1.337383f, 1.170652f, 1f);
        casaVeverita.GetComponent<Renderer>().sortingOrder = 1;

        casaVeverita2 = GameObject.Find("casaVeverita2");
        casaVeverita2.transform.position = new Vector3(0.09f, -1.67f, 0f);
        casaVeverita2.transform.localScale = new Vector3(1.13846f, 1.184614f, 1f);
        casaVeverita2.GetComponent<Renderer>().enabled = false;
        casaVeverita2.GetComponent<Renderer>().sortingOrder = 2;





        bebeVeverita = GameObject.Find("bebeVeverita");
        bebeVeverita.transform.position = new Vector3(2.93f, -2.67f, 0f);
        bebeVeverita.transform.localScale = new Vector3(0.8073131f, 0.7684776f, 0f);
        bebeVeverita.GetComponent<Renderer>().sortingOrder = 3;
        parinteVeverita = GameObject.Find("mamaVeverita");
        parinteVeverita.GetComponent<Renderer>().sortingOrder = 3;
        mancareVeverita = GameObject.Find("mancareVeverita");
        mancareVeverita.GetComponent<Renderer>().sortingOrder = 4;
        mancareVeverita2 = GameObject.Find("mancareVeverita2");
        mancareVeverita2.GetComponent<Renderer>().sortingOrder = 4;
        mancareVeverita3 = GameObject.Find("mancareVeverita3");
        mancareVeverita3.GetComponent<Renderer>().sortingOrder = 4;
        mancareVeverita4 = GameObject.Find("mancareVeverita4");
        mancareVeverita4.GetComponent<Renderer>().sortingOrder = 4;
        parinteVeverita.GetComponent<Renderer>().enabled = false;
        mancareVeverita.GetComponent<Renderer>().enabled = false;
        mancareVeverita2.GetComponent<Renderer>().enabled = false;
        mancareVeverita3.GetComponent<Renderer>().enabled = false;
        mancareVeverita4.GetComponent<Renderer>().enabled = false;
        audioMamaVeverita = GameObject.Find("audioMamaVeverita").GetComponent<AudioSource>();
        audioMancareVeverita = GameObject.Find("audioMancareVeverita").GetComponent<AudioSource>();
        audioCuriozitateVeverita = GameObject.Find("audioCuriozitateVeverita").GetComponent<AudioSource>();
        audioCasaVeverita = GameObject.Find("audioCasaVeverita").GetComponent<AudioSource>();
        audioCasaVeverita.Play(0);
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }

        if (!audioCasaVeverita.isPlaying && !gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCasa = true;
            parinteVeverita.transform.position = new Vector3(-2.163f, -2.403f, 0f);
            parinteVeverita.transform.localScale = new Vector3(0.3218816f, 0.2743441f, 0f);
            casaVeverita2.GetComponent<Renderer>().enabled = true;
            bebeVeverita.GetComponent<Renderer>().enabled = false;
            parinteVeverita.GetComponent<Renderer>().enabled = true;
            audioMamaVeverita.Play(0);
        }

        if (!audioMamaVeverita.isPlaying && gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMama = true;
            mancareVeverita.transform.position = new Vector3(-4.9f, -2.88f, 0f);
            mancareVeverita.transform.localScale = new Vector3(0.7571806f, 0.680119f, 1f);

            mancareVeverita2.transform.position = new Vector3(3.6f, -2.67f, 0f);
            mancareVeverita2.transform.localScale = new Vector3(0.8164045f, 0.7630512f, 1f);

            mancareVeverita3.transform.position = new Vector3(2.54f, 1.74f, 0f);
            mancareVeverita3.transform.localScale = new Vector3(0.437369f, 0.3898567f, 1f);

            mancareVeverita4.transform.localScale = new Vector3(0.4196018f, 0.4253989f, 1f);
            mancareVeverita4.transform.position = new Vector3(-2.64f, 1.03f, 0f);

            mancareVeverita.GetComponent<Renderer>().enabled = true;
            mancareVeverita2.GetComponent<Renderer>().enabled = true;
            mancareVeverita3.GetComponent<Renderer>().enabled = true;
            mancareVeverita4.GetComponent<Renderer>().enabled = true;
            audioMancareVeverita.Play(0);
        }

        if (!audioMancareVeverita.isPlaying && gataAudioCasa && gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMancare = true;
            audioCuriozitateVeverita.Play(0);
        }

        if (!audioCuriozitateVeverita.isPlaying && gataAudioCasa && gataAudioMama && gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCuriozitate = true;
            readyForNextScene = true;
        }


        if (readyForNextScene && gataAudioCasa && gataAudioMama && gataAudioMancare && gataAudioCuriozitate)
        {
            SceneManager.LoadScene("vulpeInvatare");
        }

    }
}