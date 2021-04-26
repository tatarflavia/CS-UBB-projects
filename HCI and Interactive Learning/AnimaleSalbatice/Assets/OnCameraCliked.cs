using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class OnCameraClicked : MonoBehaviour
{
    private int count;
    private GameObject bebeCaprioara, parinteCaprioara, casaCaprioaraH, bebeVulpe, casaVulpeH, bebeLup, parinteLup, casaLupH, bebeVeverita, parinteVeverita, casaVeveritaH, bebeUrs, casaUrsH, bebeVeveInCasa;
    private GameObject border;
    private AudioSource successAudio;
    private AudioSource warningAudio;
    private AudioSource helpAudio;

    private string lastTagClicked;


    private Dictionary<string, int> errorCount;

    void Start()
    {
        Debug.Log("wdew");

        count = 0;

        bebeCaprioara = GameObject.Find("bebeCaprioara");
        casaCaprioaraH = GameObject.Find("casaCaprioaraH");
        parinteCaprioara = GameObject.Find("parinteCaprioara");
        bebeVulpe = GameObject.Find("bebeVulpe");
        casaVulpeH = GameObject.Find("casaVulpeH");
        bebeVeverita = GameObject.Find("bebeVeverita");
        parinteVeverita = GameObject.Find("parinteVeverita");
        casaVeveritaH = GameObject.Find("casaVeveritaH");
        bebeVeveInCasa = GameObject.Find("bebeVeveInCasa");
        bebeVeveInCasa.GetComponent<Renderer>().enabled = false;
        bebeLup = GameObject.Find("bebeLup");
        casaLupH = GameObject.Find("casaLupH");
        parinteLup = GameObject.Find("parinteLup");
        bebeUrs = GameObject.Find("bebeUrs");
        casaUrsH = GameObject.Find("casaUrsH");

        border = GameObject.Find("border");
        border.GetComponent<Renderer>().enabled = false;

        lastTagClicked = "";
        successAudio = GameObject.Find("successAudio").GetComponent<AudioSource>();
        warningAudio = GameObject.Find("warningAudio").GetComponent<AudioSource>();
        helpAudio = GameObject.Find("helpAudio").GetComponent<AudioSource>();




        errorCount = new Dictionary<string, int>();


        errorCount.Add("bebeCaprioara", 0);
        errorCount.Add("bebeVulpe", 0);
        errorCount.Add("bebeUrs", 0);
        errorCount.Add("bebeLup", 0);
        errorCount.Add("bebeVeverita", 0);

    }



    void verificareEroriCaSaFacemJocul()
    {
        //luam din dict animalul care a fost gresit de 2 ori
        string key = "";
        foreach (KeyValuePair<string, int> entry in this.errorCount)
        {
            if (entry.Value == 2)
            {
                key = entry.Key;
                break;
            }
        }
        if (key != "")
        {
            Debug.Log("ii facem partea acuma pt " + key);
            faceJoculParteaPtAnimalulDat();
            helpAudio.Play(0);
            lastTagClicked = "";
            border.GetComponent<Renderer>().enabled = false;
            errorCount[key] = 0;
        }
    }




    void faceJoculParteaPtAnimalulDat()
    {
        if (this.lastTagClicked == "bebeCaprioara")
        {
            count++;
            casaCaprioaraH.GetComponent<Renderer>().enabled = false;
            //Destroy(casaCaprioaraH);
            bebeCaprioara.transform.position = new Vector3(-9.53f, -1.18f, 0f);
            bebeCaprioara.transform.localScale = new Vector3(0.4869357f, 0.388387f, 0f);
            parinteCaprioara.transform.position = new Vector3(-8.63f, -0.97f, 0f);
            parinteCaprioara.transform.localScale = new Vector3(0.8036403f, 0.7283679f, 0f);
        }
        else if (this.lastTagClicked == "bebeVulpe")
        {
            count++;
            casaVulpeH.GetComponent<Renderer>().enabled = false;
            bebeVulpe.transform.position = new Vector3(-10.02f, -4.35f, 0f);
        }
        else if (this.lastTagClicked == "bebeUrs")
        {
            count++;
            casaUrsH.GetComponent<Renderer>().enabled = false;
            bebeUrs.transform.position = new Vector3(-3.27f, 0.96f, 0f);
            bebeUrs.transform.localScale = new Vector3(0.2929426f, 0.2998285f, 1f);
        }
        else if (this.lastTagClicked == "bebeLup")
        {
            count++;
            casaLupH.GetComponent<Renderer>().enabled = false;
            bebeLup.transform.position = new Vector3(2.76f, -0.68f, 0f);
            bebeLup.transform.localScale = new Vector3(0.2698921f, 0.2041328f, 1f);
            parinteLup.transform.position = new Vector3(3.56f, -0.43f, 0f);
            parinteLup.transform.localScale = new Vector3(0.07892895f, 0.08191492f, 1f);
        }
        else if (this.lastTagClicked == "bebeVeverita")
        {
            count++;
            bebeVeveInCasa.GetComponent<Renderer>().enabled = true;
            casaVeveritaH.GetComponent<Renderer>().enabled = false;
            bebeVeverita.GetComponent<Renderer>().enabled = false;
            parinteVeverita.GetComponent<Renderer>().enabled = false;
        }

    }



    void Update()
    {

        if (Input.GetMouseButtonDown(0))
        {
            //mouse is clicked
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {

                if (hit.collider.tag == "bebeVeverita" || hit.collider.tag == "parinteVeverita")
                {
                    lastTagClicked = "bebeVeverita";
                    Debug.Log("bebeVeverita clicked ");
                    border.GetComponent<Renderer>().enabled = true;
                    border.transform.position = new Vector3(4.57f, -3.34f, 0f);
                    border.transform.localScale = new Vector3(0.2176717f, 0.2309851f, 1f);

                }
                else if (hit.collider.tag == "casaVeveritaH")
                {
                    if (lastTagClicked == "bebeVeverita")
                    {
                        Debug.Log("casaVeverita clicked");
                        successAudio.Play(0);
                        faceJoculParteaPtAnimalulDat();
                        lastTagClicked = "";
                        border.GetComponent<Renderer>().enabled = false;
                    }
                    else
                    {
                        //amintire sa apese pe animal si apoi pe casa
                        if (lastTagClicked == "")
                        {
                            warningAudio.Play(0);
                            Debug.Log("sunet pt amintire sa apese pe animal si apoi pe casa");
                        }
                        else
                        {
                            Debug.Log("a gresit casa aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                            warningAudio.Play(0);
                            this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                            verificareEroriCaSaFacemJocul();

                        }
                    }
                }


                else if (hit.collider.tag == "bebeCaprioara" || hit.collider.tag == "parinteCaprioara")
                {
                    Debug.Log("bebeCaprioara clicked");
                    lastTagClicked = "bebeCaprioara";
                    border.GetComponent<Renderer>().enabled = true;
                    border.transform.position = new Vector3(-0.52f, -1.398f, 0f);
                    border.transform.localScale = new Vector3(0.5362512f, 0.6111318f, 1f);
                }
                else if (hit.collider.tag == "casaCaprioaraH")
                {
                    if (lastTagClicked == "bebeCaprioara")
                    {
                        Debug.Log("casaCaprioara clicked");
                        successAudio.Play(0);
                        border.GetComponent<Renderer>().enabled = false;
                        faceJoculParteaPtAnimalulDat();
                        lastTagClicked = "";
                    }
                    else
                    {
                        //amintire sa apese pe animal si apoi pe casa
                        if (lastTagClicked == "")
                        {
                            warningAudio.Play(0);
                            Debug.Log("sunet pt amintire sa apese pe animal si apoi pe casa");
                        }
                        else
                        {
                            Debug.Log("a gresit casa aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                            warningAudio.Play(0);

                            this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                            verificareEroriCaSaFacemJocul();
                        }
                    }
                }

                else if (hit.collider.tag == "bebeLup" || hit.collider.tag == "parinteLup")
                {
                    Debug.Log("bebeLup clicked");
                    lastTagClicked = "bebeLup";
                    border.GetComponent<Renderer>().enabled = true;
                    border.transform.position = new Vector3(-3.6136f, -1.7674f, 0f);
                    border.transform.localScale = new Vector3(0.5362512f, 0.4330884f, 1f);
                }
                else if (hit.collider.tag == "casaLupH")
                {
                    if (lastTagClicked == "bebeLup")
                    {
                        Debug.Log("casaLup clicked");
                        successAudio.Play(0);
                        border.GetComponent<Renderer>().enabled = false;
                        faceJoculParteaPtAnimalulDat();
                        lastTagClicked = "";
                    }
                    else
                    {
                        //amintire sa apese pe animal si apoi pe casa
                        if (lastTagClicked == "")
                        {
                            warningAudio.Play(0);
                            Debug.Log("sunet pt amintire sa apese pe animal si apoi pe casa");
                        }
                        else
                        {
                            Debug.Log("a gresit casa aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                            warningAudio.Play(0);
                            this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                            verificareEroriCaSaFacemJocul();
                        }
                    }
                }

                else if (hit.collider.tag == "bebeVulpe")
                {
                    Debug.Log("bebeVulpe clicked");
                    lastTagClicked = "bebeVulpe";
                    border.GetComponent<Renderer>().enabled = true;
                    border.transform.position = new Vector3(-1.89f, -3.866f, 0f);
                    border.transform.localScale = new Vector3(0.2436024f, 0.3224128f, 1f);
                }
                else if (hit.collider.tag == "casaVulpeH")
                {
                    if (lastTagClicked == "bebeVulpe")
                    {
                        Debug.Log("casaVulpe clicked");
                        successAudio.Play(0);
                        border.GetComponent<Renderer>().enabled = false;
                        faceJoculParteaPtAnimalulDat();
                        lastTagClicked = "";
                    }
                    else
                    {
                        //amintire sa apese pe animal si apoi pe casa
                        if (lastTagClicked == "")
                        {
                            warningAudio.Play(0);
                            Debug.Log("sunet pt amintire sa apese pe animal si apoi pe casa");
                        }
                        else
                        {
                            Debug.Log("a gresit casa aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                            warningAudio.Play(0);
                            this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                            verificareEroriCaSaFacemJocul();
                        }
                    }
                }

                else if (hit.collider.tag == "bebeUrs")
                {
                    Debug.Log("bebeUrs clicked");
                    lastTagClicked = "bebeUrs";
                    border.GetComponent<Renderer>().enabled = true;
                    border.transform.position = new Vector3(2.24f, -3.58f, 0f);
                    border.transform.localScale = new Vector3(0.6881319f, 0.6688757f, 1f);
                }
                else if (hit.collider.tag == "casaUrsH")
                {
                    if (lastTagClicked == "bebeUrs")
                    {
                        Debug.Log("casaUrs clicked");
                        successAudio.Play(0);
                        border.GetComponent<Renderer>().enabled = false;
                        faceJoculParteaPtAnimalulDat();
                        lastTagClicked = "";
                    }
                    else
                    {
                        //amintire sa apese pe animal si apoi pe casa
                        if (lastTagClicked == "")
                        {
                            warningAudio.Play(0);
                            Debug.Log("sunet pt amintire sa apese pe animal si apoi pe casa");
                        }
                        else
                        {
                            Debug.Log("a gresit casa aleasa si atunci mai incearca odata si la 2 greseli ii rezolvam partea");
                            warningAudio.Play(0);
                            this.errorCount[lastTagClicked] = this.errorCount[lastTagClicked] + 1;
                            verificareEroriCaSaFacemJocul();
                        }
                    }
                }
                if (count == 5)
                {
                    Debug.Log("game done");
                    SceneManager.LoadScene("Puzzle");
                }

            }
        }
    }




}