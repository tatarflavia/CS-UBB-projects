using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class caprioaraCamera : MonoBehaviour
{

    GameObject bebeCaprioara, parinteCaprioara, mancareCaprioara, caprioaraFundal, casaCaprioara3, casaCaprioara, patura, casaCaprioara2;
    AudioSource audioCasaCaprioara, audioMamaCaprioara, audioMancareCaprioara, audioCuriozitateCaprioara;
    bool gataAudioCasa = false;
    bool gataAudioMama = false;
    bool gataAudioMancare = false;
    bool gataAudioCuriozitate = false;
    bool readyForNextScene = false;

    // Start is called before the first frame update
    void Start()
    {
        caprioaraFundal = GameObject.Find("caprioaraFundal");
        caprioaraFundal.transform.position = new Vector3(-1.68f, 3.57f, 0f);
        caprioaraFundal.transform.localScale = new Vector3(3.990998f, 3.960459f, 1f);
        caprioaraFundal.GetComponent<Renderer>().sortingOrder = 0;

        patura = GameObject.Find("patura");
        patura.transform.position = new Vector3(-2.56f, -2.38f, 0f);
        patura.transform.localScale = new Vector3(0.6338477f, 1.012637f, 1f);
        patura.GetComponent<Renderer>().enabled = false;
        patura.GetComponent<Renderer>().sortingOrder = 4;


        casaCaprioara = GameObject.Find("casaCaprioara");
        casaCaprioara.transform.position = new Vector3(-4.43f, -0.67f, 0f);
        casaCaprioara.transform.localScale = new Vector3(1.063936f, 0.9677542f, 1f);
        casaCaprioara.GetComponent<Renderer>().sortingOrder = 1;

        casaCaprioara2 = GameObject.Find("casaCaprioara2");
        casaCaprioara2.transform.position = new Vector3(3.699f, -0.086f, 0f);
        casaCaprioara2.transform.localScale = new Vector3(1.192854f, 1.104535f, 1f);
        casaCaprioara2.GetComponent<Renderer>().sortingOrder = 1;
        casaCaprioara2.GetComponent<SpriteRenderer>().flipX = true;

        casaCaprioara3 = GameObject.Find("casaCaprioara3");
        casaCaprioara3.transform.position = new Vector3(0.61f, -1.45f, 0f);
        casaCaprioara3.transform.localScale = new Vector3(1f, 1f, 1f);
        casaCaprioara3.GetComponent<Renderer>().sortingOrder = 2;


        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(-2.65f, 0.2f, 0f);
        bebeCaprioara.transform.localScale = new Vector3(1f, 1f, 1f);
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 3;

        parinteCaprioara = GameObject.Find("parinteCaprioara");
        parinteCaprioara.GetComponent<Renderer>().sortingOrder = 3;
        mancareCaprioara = GameObject.Find("mancareCaprioara");
        mancareCaprioara.GetComponent<Renderer>().sortingOrder = 5;
        parinteCaprioara.GetComponent<Renderer>().enabled = false;
        mancareCaprioara.GetComponent<Renderer>().enabled = false;
        audioMamaCaprioara = GameObject.Find("audioMamaCaprioara").GetComponent<AudioSource>();
        audioMancareCaprioara = GameObject.Find("audioMancareCaprioara").GetComponent<AudioSource>();
        audioCuriozitateCaprioara = GameObject.Find("audioCuriozitateCaprioara").GetComponent<AudioSource>();
        audioCasaCaprioara = GameObject.Find("audioCasaCaprioara").GetComponent<AudioSource>();
        audioCasaCaprioara.Play(0);

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }

        if (!audioCasaCaprioara.isPlaying && !gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCasa = true;
            parinteCaprioara.transform.position = new Vector3(-2.34f, 0.2f, 0f);
            parinteCaprioara.transform.localScale = new Vector3(1.508775f, 1.466087f, 1f);
            bebeCaprioara.transform.position = new Vector3(5.96f, 0.78f, 0f);
            parinteCaprioara.GetComponent<Renderer>().enabled = true;
            audioMamaCaprioara.Play(0);
        }

        if (!audioMamaCaprioara.isPlaying && gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMama = true;

            parinteCaprioara.transform.position = new Vector3(-2.19f, 1.11f, 0f);
            mancareCaprioara.transform.position = new Vector3(-2.25f, -1.73f, 0f);
            mancareCaprioara.transform.localScale = new Vector3(1.354415f, 1.116193f, 1f);
            mancareCaprioara.GetComponent<Renderer>().enabled = true;
            patura.GetComponent<Renderer>().enabled = true;
            audioMancareCaprioara.Play(0);
        }

        if (!audioMancareCaprioara.isPlaying && gataAudioCasa && gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMancare = true;
            audioCuriozitateCaprioara.Play(0);
        }

        if (!audioCuriozitateCaprioara.isPlaying && gataAudioCasa && gataAudioMama && gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCuriozitate = true;
            readyForNextScene = true;
        }


        if (readyForNextScene && gataAudioCasa && gataAudioMama && gataAudioMancare && gataAudioCuriozitate)
        {
            SceneManager.LoadScene("lupInvatare");
        }


    }
}