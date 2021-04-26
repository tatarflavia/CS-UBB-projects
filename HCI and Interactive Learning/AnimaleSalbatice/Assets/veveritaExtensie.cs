using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class veveritaExtensie : MonoBehaviour
{
    private GameObject caprioara1, caprioara2, fundalCaprioara1, fundalCaprioara2, cardCaprioara1, cardCaprioara2;
    private GameObject vulpe1, vulpe2, fundalVulpe1, fundalVulpe2, cardVulpe1, cardVulpe2;
    private GameObject urs1, urs2, fundalUrs1, fundalUrs2, cardUrs1, cardUrs2;
    private GameObject lup1, lup2, fundalLup1, fundalLup2, cardLup1, cardLup2;
    private GameObject semn;

    AudioSource inceput, bravo, help, maiIncearca, final;
    int pairsFound = 0, lupiFound=0, vulpiFound = 0, ursiFound = 0, caprioareFound = 0, jocStarted=0;
    int intoarceLup1 = 0, intoarceLup2 = 0, intoarceVulpe1 = 0, intoarceVulpe2 = 0, intoarceUrs1 = 0, intoarceUrs2 = 0, intoarceCaprioara1 = 0, intoarceCaprioara2 = 0;
    string lastTagClicked="";

    // Start is called before the first frame update
    void Start()
    {
        GlobalVariable.Instance.veveritaCheck = 1;

        inceput = GameObject.Find("veveritaInceputExtensie").GetComponent<AudioSource>();
        inceput.Play(0);

        caprioara1 = GameObject.Find("caprioara1");
        fundalCaprioara1 = GameObject.Find("fundalCaprioara1");
        cardCaprioara1 = GameObject.Find("cardCaprioara1");

        caprioara2 = GameObject.Find("caprioara2");
        fundalCaprioara2 = GameObject.Find("fundalCaprioara2");
        cardCaprioara2 = GameObject.Find("cardCaprioara2");

        urs1 = GameObject.Find("urs1");
        fundalUrs1 = GameObject.Find("fundalUrs1");
        cardUrs1 = GameObject.Find("cardUrs1");

        urs2 = GameObject.Find("urs2");
        fundalUrs2 = GameObject.Find("fundalUrs2");
        cardUrs2 = GameObject.Find("cardUrs2");

        lup1 = GameObject.Find("lup1");
        fundalLup1 = GameObject.Find("fundalLup1");
        cardLup1 = GameObject.Find("cardLup1");

        lup2 = GameObject.Find("lup2");
        fundalLup2 = GameObject.Find("fundalLup2");
        cardLup2 = GameObject.Find("cardLup2");

        vulpe1 = GameObject.Find("vulpe1");
        fundalVulpe1 = GameObject.Find("fundalVulpe1");
        cardVulpe1 = GameObject.Find("cardVulpe1");

        vulpe2 = GameObject.Find("vulpe2");
        fundalVulpe2 = GameObject.Find("fundalVulpe2");
        cardVulpe2 = GameObject.Find("cardVulpe2");

        semn = GameObject.Find("semn");

        caprioara1.transform.position = new Vector3(7.39f, -2.43f, -4f);
        fundalCaprioara1.transform.position = new Vector3(7.36f, -2.44f, -2f);
        cardCaprioara1.transform.position = new Vector3(7.38f, -2.45f, 0f);

        urs1.transform.position = new Vector3(-1.16f, -2.4f, -4f);
        fundalUrs1.transform.position = new Vector3(-1.21f, -2.42f, -2f);
        cardUrs1.transform.position = new Vector3(-1.21f, -2.42f, 0f);

        caprioara2.transform.position = new Vector3(4.64f, 1.85f, -4f);
        fundalCaprioara2.transform.position = new Vector3(4.575f, 1.78f, -2f);
        cardCaprioara2.transform.position = new Vector3(4.59f, 1.81f, 0f);

        urs2.transform.position = new Vector3(1.86f, 1.79f, -4f);
        fundalUrs2.transform.position = new Vector3(1.76f, 1.78f, -2f);
        cardUrs2.transform.position = new Vector3(1.76f, 1.81f, 0f);

        lup1.transform.position = new Vector3(4.58f, -2.3f, -4f);
        fundalLup1.transform.position = new Vector3(4.6f, -2.42f, -2f);
        cardLup1.transform.position = new Vector3(4.6f, -2.45f, 0f);

        lup2.transform.position = new Vector3(-1.255f, 1.889f, -4f);
        fundalLup2.transform.position = new Vector3(-1.195f, 1.795f, -2f);
        cardLup2.transform.position = new Vector3(-1.17f, 1.78f, 0f);

        vulpe1.transform.position = new Vector3(1.73f, -2.4f, -4f);
        fundalVulpe1.transform.position = new Vector3(1.71f, -2.45f, -2f);
        cardVulpe1.transform.position = new Vector3(1.71f, -2.45f, 0f);

        vulpe2.transform.position = new Vector3(7.38f, 1.81f, -4f);
        fundalVulpe2.transform.position = new Vector3(7.36f, 1.785f, -2f);
        cardVulpe2.transform.position = new Vector3(7.37f, 1.81f, 0f);

        bravo = GameObject.Find("veveritaBravo").GetComponent<AudioSource>();
        help = GameObject.Find("veveritaHelp").GetComponent<AudioSource>();
        maiIncearca = GameObject.Find("veveritaMaiIncearca").GetComponent<AudioSource>(); 
        final = GameObject.Find("veveritaFinalExtensie").GetComponent<AudioSource>(); 
    }

    void intoarcePrimaCarte(string lastTagClicked)
    {
        if(lastTagClicked == "cardLup1")
        {
            intoarceLup1 = 1;
        }
        else if (lastTagClicked == "cardLup2")
        {
            intoarceLup2 = 1;
        }
        else if (lastTagClicked == "cardUrs1")
        {
            intoarceUrs1 = 1;
        }
        else if (lastTagClicked == "cardUrs2")
        {
            intoarceUrs2 = 1;
        }
        else if (lastTagClicked == "cardCaprioara1")
        {
            intoarceCaprioara1 = 1;
        }
        else if (lastTagClicked == "cardCaprioara2")
        {
            intoarceCaprioara2 = 1;
        }
        else if (lastTagClicked == "cardVulpe1")
        {
            intoarceVulpe1 = 1;
        }
        else if (lastTagClicked == "cardVulpe2")
        {
            intoarceVulpe2 = 1;
        }

        this.lastTagClicked = "";
    }

    // Update is called once per frame
    void Update()
    {
        if(!inceput.isPlaying && jocStarted==0 && pairsFound==0)
        {
            caprioara1.transform.position = new Vector3(7.39f, -2.43f, 0f);
            fundalCaprioara1.transform.position = new Vector3(7.36f, -2.44f, 0f);
            cardCaprioara1.transform.position = new Vector3(7.38f, -2.45f, -2f);

            urs1.transform.position = new Vector3(-1.16f, -2.4f, 0f);
            fundalUrs1.transform.position = new Vector3(-1.21f, -2.42f, 0f);
            cardUrs1.transform.position = new Vector3(-1.21f, -2.42f, -2f);

            caprioara2.transform.position = new Vector3(4.64f, 1.85f, 0f);
            fundalCaprioara2.transform.position = new Vector3(4.575f, 1.78f, 0f);
            cardCaprioara2.transform.position = new Vector3(4.59f, 1.81f, -2f);

            urs2.transform.position = new Vector3(1.86f, 1.79f, 0f);
            fundalUrs2.transform.position = new Vector3(1.76f, 1.78f, 0f);
            cardUrs2.transform.position = new Vector3(1.76f, 1.81f, -2f);

            lup1.transform.position = new Vector3(4.58f, -2.3f, 0f);
            fundalLup1.transform.position = new Vector3(4.6f, -2.42f, 0f);
            cardLup1.transform.position = new Vector3(4.6f, -2.45f, -2f);
            lup2.transform.position = new Vector3(-1.255f, 1.889f, 0f);
            fundalLup2.transform.position = new Vector3(-1.195f, 1.795f, 0f);
            cardLup2.transform.position = new Vector3(-1.17f, 1.78f, -2f);

            vulpe1.transform.position = new Vector3(1.73f, -2.4f, 0f);
            fundalVulpe1.transform.position = new Vector3(1.71f, -2.45f, 0f);
            cardVulpe1.transform.position = new Vector3(1.71f, -2.45f, -2f);

            vulpe2.transform.position = new Vector3(7.38f, 1.81f, 0f);
            fundalVulpe2.transform.position = new Vector3(7.36f, 1.785f, 0f);
            cardVulpe2.transform.position = new Vector3(7.37f, 1.81f, -2f);

            jocStarted = 1;
        }
        else if (Input.GetMouseButtonDown(0) && !inceput.isPlaying && !bravo.isPlaying && !help.isPlaying && !maiIncearca.isPlaying && !final.isPlaying)
        {
            //mouse is clicked
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.name == "cardLup1" && lupiFound==0)
                {
                    lup1.transform.position = new Vector3(4.58f, -2.3f, -4f);
                    fundalLup1.transform.position = new Vector3(4.6f, -2.42f, -2f);
                    cardLup1.transform.position = new Vector3(4.6f, -2.45f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardLup1";
                    }
                    else if (lastTagClicked == "cardLup2")
                    {
                        lupiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceLup1 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }   
                }
                else if (hit.collider.name == "cardLup2" && lupiFound==0)
                {
                    lup2.transform.position = new Vector3(-1.255f, 1.889f, -4f);
                    fundalLup2.transform.position = new Vector3(-1.195f, 1.795f, -2f);
                    cardLup2.transform.position = new Vector3(-1.17f, 1.78f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardLup2";
                    }
                    else if (lastTagClicked == "cardLup1")
                    {
                        lupiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceLup2 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardVulpe1" && vulpiFound==0)
                {
                    vulpe1.transform.position = new Vector3(1.73f, -2.4f, -4f);
                    fundalVulpe1.transform.position = new Vector3(1.71f, -2.45f, -2f);
                    cardVulpe1.transform.position = new Vector3(1.71f, -2.45f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardVulpe1";
                    }
                    else if (lastTagClicked == "cardVulpe2")
                    {
                        vulpiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceVulpe1 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardVulpe2" && vulpiFound==0)
                {
                    vulpe2.transform.position = new Vector3(7.38f, 1.81f, -4f);
                    fundalVulpe2.transform.position = new Vector3(7.36f, 1.785f, -2f);
                    cardVulpe2.transform.position = new Vector3(7.37f, 1.81f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardVulpe2";
                    }
                    else if (lastTagClicked == "cardVulpe1")
                    {
                        vulpiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceVulpe2 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardUrs1" && ursiFound==0)
                {
                    urs1.transform.position = new Vector3(-1.16f, -2.4f, -4f);
                    fundalUrs1.transform.position = new Vector3(-1.21f, -2.42f, -2f);
                    cardUrs1.transform.position = new Vector3(-1.21f, -2.42f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardUrs1";
                    }
                    else if (lastTagClicked == "cardUrs2")
                    {
                        ursiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceUrs1 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardUrs2" && ursiFound==0)
                {
                    urs2.transform.position = new Vector3(1.86f, 1.79f, -4f);
                    fundalUrs2.transform.position = new Vector3(1.76f, 1.78f, -2f);
                    cardUrs2.transform.position = new Vector3(1.76f, 1.81f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardUrs2";
                    }
                    else if (lastTagClicked == "cardUrs1")
                    {
                        ursiFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceUrs2 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardCaprioara1" && caprioareFound==0)
                {
                    caprioara1.transform.position = new Vector3(7.39f, -2.43f, -4f);
                    fundalCaprioara1.transform.position = new Vector3(7.36f, -2.44f, -2f);
                    cardCaprioara1.transform.position = new Vector3(7.38f, -2.45f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardCaprioara1";
                    }
                    else if (lastTagClicked == "cardCaprioara2")
                    {
                        caprioareFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceCaprioara1 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "cardCaprioara2" && caprioareFound==0)
                {
                    caprioara2.transform.position = new Vector3(4.64f, 1.85f, -4f);
                    fundalCaprioara2.transform.position = new Vector3(4.575f, 1.78f, -2f);
                    cardCaprioara2.transform.position = new Vector3(4.59f, 1.81f, 0f);

                    if (lastTagClicked == "")
                    {
                        lastTagClicked = "cardCaprioara2";
                    }
                    else if (lastTagClicked == "cardCaprioara1")
                    {
                        caprioareFound = 1;
                        pairsFound++;
                        if (pairsFound != 4)
                            bravo.Play(0);
                        else
                            final.Play(0);
                        lastTagClicked = "";
                    }
                    else
                    {
                        maiIncearca.Play(0);
                        intoarceCaprioara2 = 1;
                        intoarcePrimaCarte(lastTagClicked);
                    }
                }
                else if (hit.collider.name == "semn")
                {
                    help.Play(0);
                }
            }       
        }
        else if(!maiIncearca.isPlaying && intoarceLup1==1)
        {
            lup1.transform.position = new Vector3(4.58f, -2.3f, 0f);
            fundalLup1.transform.position = new Vector3(4.6f, -2.42f, 0f);
            cardLup1.transform.position = new Vector3(4.6f, -2.45f, -2f);
            intoarceLup1 = 0;

        }
        else if (!maiIncearca.isPlaying && intoarceLup2 == 1)
        {
            lup2.transform.position = new Vector3(-1.255f, 1.889f, 0f);
            fundalLup2.transform.position = new Vector3(-1.195f, 1.795f, 0f);
            cardLup2.transform.position = new Vector3(-1.17f, 1.78f, -2f);
            intoarceLup2 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceVulpe1 == 1)
        {
            vulpe1.transform.position = new Vector3(1.73f, -2.4f, 0f);
            fundalVulpe1.transform.position = new Vector3(1.71f, -2.45f, 0f);
            cardVulpe1.transform.position = new Vector3(1.71f, -2.45f, -2f);
            intoarceVulpe1 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceVulpe2 == 1)
        {
            vulpe2.transform.position = new Vector3(7.38f, 1.81f, 0f);
            fundalVulpe2.transform.position = new Vector3(7.36f, 1.785f, 0f);
            cardVulpe2.transform.position = new Vector3(7.37f, 1.81f, -2f);
            intoarceVulpe2 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceUrs1 == 1)
        {
            urs1.transform.position = new Vector3(-1.16f, -2.4f, 0f);
            fundalUrs1.transform.position = new Vector3(-1.21f, -2.42f, 0f);
            cardUrs1.transform.position = new Vector3(-1.21f, -2.42f, -2f);
            intoarceUrs1 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceUrs2 == 1)
        {
            urs2.transform.position = new Vector3(1.86f, 1.79f, 0f);
            fundalUrs2.transform.position = new Vector3(1.76f, 1.78f, 0f);
            cardUrs2.transform.position = new Vector3(1.76f, 1.81f, -2f);
            intoarceUrs2 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceCaprioara1 == 1)
        {
            caprioara1.transform.position = new Vector3(7.39f, -2.43f, 0f);
            fundalCaprioara1.transform.position = new Vector3(7.36f, -2.44f, 02f);
            cardCaprioara1.transform.position = new Vector3(7.38f, -2.45f, -2f);
            intoarceCaprioara1 = 0;
        }
        else if (!maiIncearca.isPlaying && intoarceCaprioara2 == 1)
        {
            caprioara2.transform.position = new Vector3(4.64f, 1.85f, 0f);
            fundalCaprioara2.transform.position = new Vector3(4.575f, 1.78f, 0f);
            cardCaprioara2.transform.position = new Vector3(4.59f, 1.81f, -2f);
            intoarceCaprioara2 = 0;
        }
        else if (!final.isPlaying && pairsFound == 4)
        {
            SceneManager.LoadScene("inceputExtensie");
        }
    }

}
