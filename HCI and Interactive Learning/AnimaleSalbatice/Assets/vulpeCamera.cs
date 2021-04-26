using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class vulpeCamera : MonoBehaviour
{

    GameObject bebeVulpe, mancareVulpe, vulpeFundal, mancareVulpe2, casaVulpe, nor, bebeCaprioara, parinteVulpe;
    AudioSource audioCasaVulpe, audioMamaVulpe, audioMancareVulpe, audioCuriozitateVulpe;
    bool gataAudioCasa = false;
    bool gataAudioMama = false;
    bool gataAudioMancare = false;
    bool gataAudioCuriozitate = false;
    bool readyForNextScene = false;
    // Start is called before the first frame update
    void Start()
    {

        vulpeFundal = GameObject.Find("vulpeFundal");
        vulpeFundal.transform.position = new Vector3(0.535f, -0.166f, 0f);
        vulpeFundal.transform.localScale = new Vector3(2.040073f, 2.441332f, 1f);
        vulpeFundal.GetComponent<Renderer>().sortingOrder = 0;

        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(7.66f, 1.85f, 0f);
        nor.transform.localScale = new Vector3(0.3662998f, 0.3308091f, 1f);
        nor.GetComponent<Renderer>().sortingOrder = 4;

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(7.881f, 3.286f, 0f);
        bebeCaprioara.transform.localScale = new Vector3(0.983521f, 0.8535224f, 1f);
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 5;


        casaVulpe = GameObject.Find("casaVulpe");
        casaVulpe.transform.position = new Vector3(-4.983f, 0.14f, 0f);
        casaVulpe.transform.localScale = new Vector3(2.133031f, 2.14276f, 1f);
        casaVulpe.GetComponent<Renderer>().sortingOrder = 1;







        bebeVulpe = GameObject.Find("bebeVulpe");
        bebeVulpe.transform.position = new Vector3(0.231f, -1.269f, 0f);
        bebeVulpe.transform.localScale = new Vector3(1.647945f, 1.330022f, 1f);
        bebeVulpe.GetComponent<Renderer>().sortingOrder = 2;

        parinteVulpe = GameObject.Find("parinteVulpe");
        parinteVulpe.transform.position = new Vector3(0.25f, 0.53f, 0f);
        parinteVulpe.transform.localScale = new Vector3(0.4741351f, 0.4614373f, 1f);
        parinteVulpe.GetComponent<Renderer>().sortingOrder = 2;

        mancareVulpe = GameObject.Find("mancareVulpe");
        mancareVulpe.GetComponent<Renderer>().sortingOrder = 3;
        mancareVulpe.GetComponent<SpriteRenderer>().flipY = true;
        mancareVulpe2 = GameObject.Find("mancareVulpe2");
        mancareVulpe2.GetComponent<Renderer>().sortingOrder = 4;
        mancareVulpe2.GetComponent<SpriteRenderer>().flipY = true;

        mancareVulpe.GetComponent<Renderer>().enabled = false;
        mancareVulpe2.GetComponent<Renderer>().enabled = false;
        parinteVulpe.GetComponent<Renderer>().enabled = false;

        audioMamaVulpe = GameObject.Find("audioMamaVulpe").GetComponent<AudioSource>();
        audioMancareVulpe = GameObject.Find("audioMancareVulpe").GetComponent<AudioSource>();
        audioCuriozitateVulpe = GameObject.Find("audioCuriozitateVulpe").GetComponent<AudioSource>();
        audioCasaVulpe = GameObject.Find("audioCasaVulpe").GetComponent<AudioSource>();
        audioCasaVulpe.Play(0);

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }

        if (!audioCasaVulpe.isPlaying && !gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCasa = true;
            bebeVulpe.GetComponent<Renderer>().enabled = false;
            parinteVulpe.GetComponent<Renderer>().enabled = true;

            audioMamaVulpe.Play(0);
        }

        if (!audioMamaVulpe.isPlaying && gataAudioCasa && !gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMama = true;
            mancareVulpe.transform.position = new Vector3(-4.97f, -2.98f, 0f);
            mancareVulpe.transform.localScale = new Vector3(0.8067131f, 0.7281312f, 1f);

            mancareVulpe2.transform.position = new Vector3(-4.15f, -2.63f, 0f);
            mancareVulpe2.transform.localScale = new Vector3(0.8187937f, 0.8429204f, 1f);


            mancareVulpe.GetComponent<Renderer>().enabled = true;
            mancareVulpe2.GetComponent<Renderer>().enabled = true;

            audioMancareVulpe.Play(0);
        }

        if (!audioMancareVulpe.isPlaying && gataAudioCasa && gataAudioMama && !gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioMancare = true;
            audioCuriozitateVulpe.Play(0);
        }

        if (!audioCuriozitateVulpe.isPlaying && gataAudioCasa && gataAudioMama && gataAudioMancare && !gataAudioCuriozitate)
        {
            gataAudioCuriozitate = true;
            readyForNextScene = true;
        }


        if (readyForNextScene && gataAudioCasa && gataAudioMama && gataAudioMancare && gataAudioCuriozitate)
        {
            SceneManager.LoadScene("ursInvatare");
        }
    }
}