using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class lupCamera : MonoBehaviour
{

    GameObject bebeLup, parinteLup, mancareLup, casaLup, fundalLup, mancareLup2, nor, bebeCaprioara;
    AudioSource audioCasaLup, audioMamaLup, audioMancareLup, audioCuriozitateLup;
    bool gataAudioCasa = false;
    bool gataAudioMama = false;
    bool gataAudioMancare = false;
    bool gataAudioCuriozitate = false;
    bool readyForNextScene = false;


    // Start is called before the first frame update
    void Start()
    {

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(-7.64f, 3.705f, 0f);
        bebeCaprioara.transform.localScale = new Vector3(0.7721081f, 0.6510573f, 1f);
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 5;


        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(-7.804f, 2.53f, 0f);
        nor.transform.localScale = new Vector3(0.3506617f, 0.3496665f, 1f);
        nor.GetComponent<Renderer>().sortingOrder = 4;

        fundalLup = GameObject.Find("fundalLup");
        fundalLup.transform.position = new Vector3(0.21f, 2.35f, 0f);
        fundalLup.transform.localScale = new Vector3(1.842352f, 2.158193f, 1f);
        fundalLup.GetComponent<Renderer>().sortingOrder = 0;

        casaLup = GameObject.Find("casaLup");
        casaLup.transform.position = new Vector3(1.838f, -0.324f, 0f);
        casaLup.transform.localScale = new Vector3(0.7558663f, 0.666145f, 1f);
        casaLup.GetComponent<Renderer>().sortingOrder = 1;
        casaLup.GetComponent<SpriteRenderer>().flipX = true;

        bebeLup = GameObject.Find("bebeLup");
        bebeLup.GetComponent<Renderer>().sortingOrder = 2;
        bebeLup.transform.position = new Vector3(-7.15f, -3.13f, 0f);
        bebeLup.transform.localScale = new Vector3(1f, 1f, 1f);
        parinteLup = GameObject.Find("parinteLup");
        parinteLup.GetComponent<Renderer>().sortingOrder = 2;
        mancareLup = GameObject.Find("mancareLup");
        mancareLup.GetComponent<Renderer>().sortingOrder = 3;
        mancareLup2 = GameObject.Find("mancareLup2");
        mancareLup2.GetComponent<Renderer>().sortingOrder = 3;
        mancareLup2.GetComponent<SpriteRenderer>().flipX = true;
        parinteLup.GetComponent<Renderer>().enabled = false;
        mancareLup.GetComponent<Renderer>().enabled = false;
        mancareLup2.GetComponent<Renderer>().enabled = false;
        audioMamaLup = GameObject.Find("audioMamaLup").GetComponent<AudioSource>();
        audioMancareLup = GameObject.Find("audioMancareLup").GetComponent<AudioSource>();
        audioCuriozitateLup = GameObject.Find("audioCuriozitateLup").GetComponent<AudioSource>();
        audioCasaLup = GameObject.Find("audioCasaLup").GetComponent<AudioSource>();
        audioCasaLup.Play(0);
    }

    // Update is called once per frame
    void Update()
    {

        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }

        if (!audioCasaLup.isPlaying && !gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCasa = true;
            parinteLup.transform.position = new Vector3(1.53f, -2.28f, 0f);
            parinteLup.transform.localScale = new Vector3(0.3490008f, 0.3181381f, 1f);
            parinteLup.GetComponent<Renderer>().enabled = true;
            audioMamaLup.Play(0);
        }

        if (!audioMamaLup.isPlaying && gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMama = true;
            parinteLup.transform.position = new Vector3(3.55f, -0.92f, 0f);
            mancareLup.transform.position = new Vector3(-2.15f, -2.3f, 0f);
            mancareLup.transform.localScale = new Vector3(0.611849f, 0.6271312f, 1f);
            mancareLup2.transform.position = new Vector3(-0.75f, -2.23f, 0f);
            mancareLup2.transform.localScale = new Vector3(0.6303326f, 0.6271313f, 1f);
            mancareLup.GetComponent<Renderer>().enabled = true;
            mancareLup2.GetComponent<Renderer>().enabled = true;
            audioMancareLup.Play(0);
        }

        if (!audioMancareLup.isPlaying && gataAudioCasa && gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMancare = true;
            audioCuriozitateLup.Play(0);
        }

        if (!audioCuriozitateLup.isPlaying && gataAudioCasa && gataAudioMama && gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCuriozitate = true;
            readyForNextScene = true;
        }


        if (readyForNextScene && gataAudioCasa && gataAudioMama && gataAudioMancare && gataAudioCuriozitate)
        {
            SceneManager.LoadScene("veveritaInvatare");
        }
    }
}