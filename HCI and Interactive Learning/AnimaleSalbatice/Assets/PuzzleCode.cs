using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PuzzleCode : MonoBehaviour
{
    GameObject img1, img2, img3, img4, imgDone;
    int countRotationsImg1, countRotationsImg2, countRotationsImg3, countRotationsImg4;
    int finalAudioStarted;

    AudioSource inceputAudio;
    AudioSource finalAudio;

    GameObject helpButton;
    AudioSource helpAudio;

    // Start is called before the first frame update
    void Start()
    {
        img1 = GameObject.Find("img1");
        img2 = GameObject.Find("img2");
        img3 = GameObject.Find("img3");
        img4 = GameObject.Find("img4");
        imgDone = GameObject.Find("imgDone");

        countRotationsImg1 = 0;
        countRotationsImg2 = 0;
        countRotationsImg3 = 0;
        countRotationsImg4 = 0;

        inceputAudio = GameObject.Find("inceput_5").GetComponent<AudioSource>();
        inceputAudio.Play(0);
        finalAudio = GameObject.Find("final_5").GetComponent<AudioSource>();

        finalAudioStarted=0;

        helpButton = GameObject.Find("semn (1)");
        helpAudio = GameObject.Find("instructiune_5").GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if (!inceputAudio.isPlaying && Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "semn (1)" && !finalAudio.isPlaying)
                {
                    helpAudio.Play(0);
                }
                if (!helpAudio.isPlaying)
                {
                    if (hit.collider.name == "img1")
                    {
                        img1.transform.Rotate(0, 0, 180);
                        countRotationsImg1++;
                    }

                    if (hit.collider.name == "img2")
                    {
                        img2.transform.Rotate(0, 0, 180);
                        countRotationsImg2++;
                    }

                    if (hit.collider.name == "img3")
                    {
                        img3.transform.Rotate(0, 0, 180);
                        countRotationsImg3++;
                    }

                    if (hit.collider.name == "img4")
                    {
                        img4.transform.Rotate(0, 0, 180);
                        countRotationsImg4++;
                    }

                    if (countRotationsImg1 % 2 != 0 && countRotationsImg2 % 2 == 0 && countRotationsImg3 % 2 == 0 && countRotationsImg4 % 2 != 0)
                    {
                        Debug.Log("game done");
                        imgDone.transform.position = new Vector3(0.16f, -0.028f, -2);
                        finalAudioStarted = 1;
                        finalAudio.Play(0);
                    }
                }
            }
        }
        if (finalAudioStarted == 1 && !finalAudio.isPlaying)
        {
            SceneManager.LoadScene("inceputExtensie");
        }
    }
}

