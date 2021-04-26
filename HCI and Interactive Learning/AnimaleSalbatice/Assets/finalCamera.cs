using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class finalCamera : MonoBehaviour
{

    GameObject bebeUrs, nor, bebeCaprioara, bebeVulpe, bebeLup, bebeVeverita, finalFundal;
    AudioSource audioFinal;
    bool readyForNextScene = false;

    // Start is called before the first frame update
    void Start()
    {
        finalFundal = GameObject.Find("finalFundal");
        finalFundal.transform.position = new Vector3(-0.2f, -1.23f, 0f);
        finalFundal.transform.localScale = new Vector3(1.406779f, 1.536017f, 1f);

        nor = GameObject.Find("nor");
        nor.transform.position = new Vector3(1.28f, -3.96f, 0f);
        nor.transform.localScale = new Vector3(0.566053f, 0.6340837f, 1f);

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        bebeCaprioara.transform.position = new Vector3(1.59f, -2.24f, -3f);
        bebeCaprioara.transform.localScale = new Vector3(1.216269f, 1.235572f, 1f);

        bebeLup = GameObject.Find("bebeLup");
        bebeLup.transform.position = new Vector3(-2.05f, -3.02f, -2f);
        bebeLup.transform.localScale = new Vector3(0.81912f, 0.796408f, 1f);

        bebeVeverita = GameObject.Find("bebeVeverita");
        bebeVeverita.transform.position = new Vector3(3.67f, -3.33f, 0f);
        bebeVeverita.transform.localScale = new Vector3(0.7227315f, 0.6708465f, 1f);

        bebeUrs = GameObject.Find("bebeUrs");
        bebeUrs.transform.position = new Vector3(-0.13f, -1.23f, 0f);
        bebeUrs.transform.localScale = new Vector3(0.8217131f, 0.8157304f, 1f);

        bebeVulpe = GameObject.Find("bebeVulpe");
        bebeVulpe.transform.position = new Vector3(4.48f, -1.43f, 0f);
        bebeVulpe.transform.localScale = new Vector3(0.6473988f, 0.6205294f, 1f);

        audioFinal = GameObject.Find("final_poveste").GetComponent<AudioSource>();
        audioFinal.Play(0);

        finalFundal.GetComponent<Renderer>().sortingOrder = 0;
        nor.GetComponent<Renderer>().sortingOrder = 1;
        bebeCaprioara.GetComponent<Renderer>().sortingOrder = 3;
        bebeLup.GetComponent<Renderer>().sortingOrder = 3;
        bebeVeverita.GetComponent<Renderer>().sortingOrder = 3;
        bebeVulpe.GetComponent<Renderer>().sortingOrder = 2;
        bebeUrs.GetComponent<Renderer>().sortingOrder = 2;
    }

    // Update is called once per frame
    void Update()
    {
        if (!audioFinal.isPlaying && !readyForNextScene)
        {
            readyForNextScene = true;
        }

        if (readyForNextScene)
        {
            SceneManager.LoadScene("ActivityMamesiPui");
        }
    }
}