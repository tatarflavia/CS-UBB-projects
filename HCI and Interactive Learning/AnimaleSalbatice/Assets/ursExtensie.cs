using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ursExtensie : MonoBehaviour
{
    // Start is called before the first frame update
    private GameObject puzzle1, puzzle2, puzzle3, puzzle4, puzzle5, puzzle6, fundal1, fundal2, fundal3, fundal4, fundal5, fundal6, semn, final;
    private string firstClicked, secondClicked;
    int p1, p2, p3, p4, p5, p6;
    AudioSource bv, inceput, clickSemn;

    void Start()
    {
        GlobalVariable.Instance.ursCheck = 1;

        puzzle1 = GameObject.Find("puzzle1");
        puzzle1.transform.position = new Vector3(5.54f, 3.852f, -2f);
        puzzle1.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        puzzle2 = GameObject.Find("puzzle2");
        puzzle2.transform.position = new Vector3(5.59f, -2.81f, -2f);
        puzzle2.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        puzzle3 = GameObject.Find("puzzle3");
        puzzle3.transform.position = new Vector3(7.83f, 0.45f, -2f);
        puzzle3.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        puzzle4 = GameObject.Find("puzzle4");
        puzzle4.transform.position = new Vector3(7.89f, 3.19f, -2f);
        puzzle4.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        puzzle5 = GameObject.Find("puzzle5");
        puzzle5.transform.position = new Vector3(5.59f, 0.07f, -2f);
        puzzle5.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        puzzle6 = GameObject.Find("puzzle6");
        puzzle6.transform.position = new Vector3(7.8f, -3.73f, -2f);
        puzzle6.transform.localScale = new Vector3(0.7f, 0.7f, 1f);

        fundal1 = GameObject.Find("fundal1");
        fundal1.transform.position = new Vector3(-6.756f, 2.647f, -1f);
        fundal1.transform.localScale = new Vector3(1.276054f, 1.397184f, 1f);

        fundal2 = GameObject.Find("fundal2");
        fundal2.transform.position = new Vector3(-2.373f, 2.673f, -1f);
        fundal2.transform.localScale = new Vector3(1.291824f, 1.38141f, 1f);

        fundal3 = GameObject.Find("fundal3");
        fundal3.transform.position = new Vector3(2.12f, 2.678f, -1f);
        fundal3.transform.localScale = new Vector3(1.361322f, 1.398068f, 1f);

        fundal4 = GameObject.Find("fundal4");
        fundal4.transform.position = new Vector3(-6.797f, -2.345f, -1f);
        fundal4.transform.localScale = new Vector3(1.22931f, 1.54795f, 1f);

        fundal5 = GameObject.Find("fundal5");
        fundal5.transform.position = new Vector3(-2.284f, -2.347f, -1f);
        fundal5.transform.localScale = new Vector3(1.40471f, 1.561539f, 1f);

        fundal6 = GameObject.Find("fundal6");
        fundal6.transform.position = new Vector3(2.259f, -2.354f, -1f);
        fundal6.transform.localScale = new Vector3(1.289292f, 1.543168f, 1f);

        final = GameObject.Find("final");
        final.transform.position = new Vector3(-2.198f, 0.018f, 2f);
        final.transform.localScale = new Vector3(1.336408f, 1.310374f, 1f);

        semn = GameObject.Find("semn");
        semn.transform.position = new Vector3(8.2621f, 4.6487f, -2f);
        semn.transform.localScale = new Vector3(0.06335613f, 0.058896f, 1f);

        firstClicked = "";
        secondClicked = "";

        p1 = 0;
        p2 = 0;
        p3 = 0;
        p4 = 0;
        p5 = 0;
        p6 = 0;

        inceput = GameObject.Find("inceput").GetComponent<AudioSource>();
        inceput.Play(0);

        bv = GameObject.Find("bv").GetComponent<AudioSource>();
        clickSemn = GameObject.Find("clickSemn").GetComponent<AudioSource>();

    }

    void mutamPiesa()
    {
        if (firstClicked == "puzzle1" && secondClicked == "fundal1")
        {
            puzzle1.transform.position = new Vector3(-6.756f, 2.647f, -2f);
            puzzle1.transform.localScale = new Vector3(1.276054f, 1.397184f, 1f);
            firstClicked = "";
            secondClicked = "";
            p1 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }
        else if (firstClicked == "puzzle2" && secondClicked == "fundal2")
        {
            puzzle2.transform.position = new Vector3(-2.373f, 2.673f, -2f);
            puzzle2.transform.localScale = new Vector3(1.291824f, 1.38141f, 1f);
            firstClicked = "";
            secondClicked = "";
            p2 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }
        else if (firstClicked == "puzzle3" && secondClicked == "fundal3")
        {
            puzzle3.transform.position = new Vector3(2.12f, 2.678f, -2f);
            puzzle3.transform.localScale = new Vector3(1.361322f, 1.398068f, 1f);
            firstClicked = "";
            secondClicked = "";
            p3 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }
        else if (firstClicked == "puzzle4" && secondClicked == "fundal4")
        {
            puzzle4.transform.position = new Vector3(-6.797f, -2.345f, -2f);
            puzzle4.transform.localScale = new Vector3(1.22931f, 1.54795f, 1f);
            firstClicked = "";
            secondClicked = "";
            p4 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }
        else if (firstClicked == "puzzle5" && secondClicked == "fundal5")
        {
            puzzle5.transform.position = new Vector3(-2.284f, -2.347f, -2f);
            puzzle5.transform.localScale = new Vector3(1.40471f, 1.561539f, 1f);
            firstClicked = "";
            secondClicked = "";
            p5 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }
        else if (firstClicked == "puzzle6" && secondClicked == "fundal6")
        {
            puzzle6.transform.position = new Vector3(2.259f, -2.354f, -2f);
            puzzle6.transform.localScale = new Vector3(1.289292f, 1.543168f, 1f);
            firstClicked = "";
            secondClicked = "";
            p6 = 1;
            if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1 && !bv.isPlaying)
            {
                final.transform.position = new Vector3(-2.198f, 0.018f, -3f);
                bv.Play(0);
            }
        }

    }


    // Update is called once per frame
    void Update()
    {

        if (Input.GetMouseButtonDown(0) && !inceput.isPlaying && !bv.isPlaying && !clickSemn.isPlaying)
        {
            //mouse is clicked
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {

                //Debug.Log(hit.collider.name);
                //Piesa1
                if (hit.collider.name == "puzzle1" && secondClicked == "")
                {
                    firstClicked = "puzzle1";
                }
                else if (hit.collider.name == "fundal1" && firstClicked == "puzzle1")
                {
                    secondClicked = "fundal1";
                    mutamPiesa();
                }
                //Piesa2
                else if (hit.collider.name == "puzzle2" && secondClicked == "")
                {
                    firstClicked = "puzzle2";
                }
                else if (hit.collider.name == "fundal2" && firstClicked == "puzzle2")
                {
                    secondClicked = "fundal2";
                    mutamPiesa();
                }
                //Piesa3
                else if (hit.collider.name == "puzzle3" && secondClicked == "")
                {
                    firstClicked = "puzzle3";
                }
                else if (hit.collider.name == "fundal3" && firstClicked == "puzzle3")
                {
                    secondClicked = "fundal3";
                    mutamPiesa();
                }
                //Piesa4
                else if (hit.collider.name == "puzzle4" && secondClicked == "")
                {
                    firstClicked = "puzzle4";
                }
                else if (hit.collider.name == "fundal4" && firstClicked == "puzzle4")
                {
                    secondClicked = "fundal4";
                    mutamPiesa();
                }
                //Piesa 5
                else if (hit.collider.name == "puzzle5" && secondClicked == "")
                {
                    firstClicked = "puzzle5";
                }
                else if (hit.collider.name == "fundal5" && firstClicked == "puzzle5")
                {
                    secondClicked = "fundal5";
                    mutamPiesa();
                }
                //Piesa6
                else if (hit.collider.name == "puzzle6" && secondClicked == "")
                {
                    firstClicked = "puzzle6";
                }
                else if (hit.collider.name == "fundal6" && firstClicked == "puzzle6")
                {
                    secondClicked = "fundal6";
                    mutamPiesa();
                }
                else if (hit.collider.name == "semn")
                {
                    clickSemn.Play(0);
                }
                else
                {
                    secondClicked = "";
                }
            }
            
        }
        else if (!bv.isPlaying && p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1 && p5 == 1 && p6 == 1)
        {
            SceneManager.LoadScene("inceputExtensie");
        }
    }
}
