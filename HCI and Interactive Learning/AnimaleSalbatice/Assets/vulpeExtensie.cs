using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System;

public class vulpeExtensie : MonoBehaviour
{
    GameObject cupa, semnIntrebare, fundalEvaluareVulpe, vulpe, cosCerc, cosTriunghi, cosPatrat, pesteCerc1, pesteCerc2, pesteTriunghi1, pesteTriunghi2, pestePatrat1, pestePatrat2, pesteCupa;
    AudioSource audioBravoVulpe, audioMaiIncearcaVulpe, audioIntroFullVulpe, audioInstructiuneVulpe, audioOutVulpe;
    bool gameStarted = false;
    bool gameStopped = false;
    bool aPrinsCupa = false;
    DateTime firstTime;
    Dictionary<string, Vector3> numeCuPozitieDict;

    Dictionary<string, Vector3> numeCuPozitieInCosDict;
    Dictionary<string, Vector3> numeCuRotatieInCosDict;
    Dictionary<string, Vector3> numeCuScaleInCosDict;
    private List<Vector3> positionList;
    private List<Vector3> positionListForCupa;


    string lastPesteAlesSaAparaPeEcran;
    int pestiPrinsiCount;
    int errorCount;


    // Start is called before the first frame update
    void Start()
    {
        GlobalVariable.Instance.vulpeCheck = 1;

        fundalEvaluareVulpe = GameObject.Find("fundalEvaluareVulpe");
        fundalEvaluareVulpe.transform.position = new Vector3(1.37f, 1.01f, 0f);
        fundalEvaluareVulpe.transform.localScale = new Vector3(0.3857892f, 0.3208216f, 1f);
        fundalEvaluareVulpe.GetComponent<Renderer>().sortingOrder = 0;

        semnIntrebare = GameObject.Find("semnIntrebare");
        semnIntrebare.transform.position = new Vector3(-8.11f, 4.36f, 0f);
        semnIntrebare.transform.localScale = new Vector3(0.1205805f, 0.1173347f, 1f);
        semnIntrebare.GetComponent<Renderer>().sortingOrder = 1;

        cupa = GameObject.Find("cupa");
        cupa.transform.position = new Vector3(1.09f, -0.63f, 0f);
        cupa.transform.localScale = new Vector3(0.8396032f, 0.8636239f, 1f);
        cupa.GetComponent<Renderer>().sortingOrder = 4;
        cupa.GetComponent<Renderer>().enabled = false;

        vulpe = GameObject.Find("vulpe");
        vulpe.transform.position = new Vector3(-3.89f, 2.83f, 0f);
        vulpe.transform.localScale = new Vector3(1f, 1f, 1f);
        vulpe.GetComponent<Renderer>().sortingOrder = 1;

        cosCerc = GameObject.Find("cosCerc");
        cosCerc.transform.position = new Vector3(1.77f, 2.11f, 0f);
        cosCerc.transform.localScale = new Vector3(0.6497082f, 0.743991f, 1f);
        cosCerc.GetComponent<Renderer>().sortingOrder = 1;

        cosTriunghi = GameObject.Find("cosTriunghi");
        cosTriunghi.transform.position = new Vector3(7.15f, 2.03f, 0f);
        cosTriunghi.transform.localScale = new Vector3(0.3705567f, 0.3536027f, 1f);
        cosTriunghi.GetComponent<Renderer>().sortingOrder = 1;

        cosPatrat = GameObject.Find("cosPatrat");
        cosPatrat.transform.position = new Vector3(4.41f, 2.14f, 0f);
        cosPatrat.transform.localScale = new Vector3(0.1434864f, 0.127637f, 1f);
        cosPatrat.GetComponent<Renderer>().sortingOrder = 1;


        pesteCerc1 = GameObject.Find("pesteCerc1");
        pesteCerc1.transform.position = new Vector3(6.86f, -0.86f, 0f);
        pesteCerc1.transform.localScale = new Vector3(0.1143243f, 0.1160349f, 1f);
        pesteCerc1.transform.localEulerAngles = new Vector3(0, 0, 0);
        pesteCerc1.GetComponent<Renderer>().sortingOrder = 2;
        pesteCerc1.GetComponent<Renderer>().enabled = false;

        pesteCerc2 = GameObject.Find("pesteCerc2");
        pesteCerc2.transform.position = new Vector3(-6.21f, -0.93f, 0f);
        pesteCerc2.transform.localScale = new Vector3(0.3225357f, 0.3128993f, 1f);
        pesteCerc2.transform.localEulerAngles = new Vector3(0, 0, 0);
        pesteCerc2.GetComponent<Renderer>().sortingOrder = 2;
        pesteCerc2.GetComponent<Renderer>().enabled = false;

        pesteTriunghi1 = GameObject.Find("pesteTriunghi1");
        pesteTriunghi1.transform.position = new Vector3(-1.94f, -2.46f, 0f);
        pesteTriunghi1.transform.localEulerAngles = new Vector3(0, 0, 0);
        pesteTriunghi1.transform.localScale = new Vector3(0.2411275f, 0.2287195f, 1f);
        pesteTriunghi1.GetComponent<Renderer>().sortingOrder = 2;
        pesteTriunghi1.GetComponent<Renderer>().enabled = false;

        pesteTriunghi2 = GameObject.Find("pesteTriunghi2");
        pesteTriunghi2.transform.position = new Vector3(2.17f, -3.28f, 0f);
        pesteTriunghi2.transform.localEulerAngles = new Vector3(0, 0, 0);
        pesteTriunghi2.transform.localScale = new Vector3(0.2537735f, 0.2337729f, 1f);
        pesteTriunghi2.GetComponent<Renderer>().sortingOrder = 2;
        pesteTriunghi2.GetComponent<Renderer>().enabled = false;

        pestePatrat1 = GameObject.Find("pestePatrat1");
        pestePatrat1.transform.position = new Vector3(1.69f, -1.34f, 0f);
        pestePatrat1.transform.localEulerAngles = new Vector3(0, 0, 0);
        pestePatrat1.transform.localScale = new Vector3(0.4139924f, 0.3163736f, 1f);
        pestePatrat1.GetComponent<Renderer>().sortingOrder = 2;
        pestePatrat1.GetComponent<Renderer>().enabled = false;

        pestePatrat2 = GameObject.Find("pestePatrat2");
        pestePatrat2.transform.position = new Vector3(-5.93f, -3.71f, 0f);
        pestePatrat2.transform.localEulerAngles = new Vector3(0, 0, 0);
        pestePatrat2.transform.localScale = new Vector3(0.09105979f, 0.08249158f, 1f);
        pestePatrat2.GetComponent<Renderer>().sortingOrder = 2;
        pestePatrat2.GetComponent<Renderer>().enabled = false;

        pesteCupa = GameObject.Find("pesteCupa");
        pesteCupa.transform.position = new Vector3(5.752f, -3.206f, 0f);
        pesteCupa.transform.localScale = new Vector3(0.3926212f, 0.3765225f, 1f);
        pesteCupa.GetComponent<Renderer>().sortingOrder = 3;
        pesteCupa.GetComponent<Renderer>().enabled = false;



        audioIntroFullVulpe = GameObject.Find("audioIntroFullVulpe").GetComponent<AudioSource>();
        audioIntroFullVulpe.Play(0);

        audioInstructiuneVulpe = GameObject.Find("audioInstructiuneVulpe").GetComponent<AudioSource>();
        audioOutVulpe = GameObject.Find("audioOutVulpe").GetComponent<AudioSource>();
        audioBravoVulpe = GameObject.Find("audioBravoVulpe").GetComponent<AudioSource>();
        audioMaiIncearcaVulpe = GameObject.Find("audioMaiIncearcaVulpe").GetComponent<AudioSource>();


        lastPesteAlesSaAparaPeEcran = "";
        numeCuPozitieDict = new Dictionary<string, Vector3>();
        numeCuPozitieDict.Add("pesteCerc1", new Vector3(6.86f, -0.86f, 0f));
        numeCuPozitieDict.Add("pesteCerc2", new Vector3(-6.21f, -0.93f, 0f));
        numeCuPozitieDict.Add("pesteTriunghi1", new Vector3(-1.94f, -2.46f, 0f));
        numeCuPozitieDict.Add("pesteTriunghi2", new Vector3(2.17f, -3.28f, 0f));
        numeCuPozitieDict.Add("pestePatrat1", new Vector3(1.69f, -1.34f, 0f));
        numeCuPozitieDict.Add("pestePatrat2", new Vector3(-5.93f, -3.71f, 0f));
        positionList = new List<Vector3>();
        positionList.Add(new Vector3(6.86f, -0.86f, 0f));
        positionList.Add(new Vector3(-6.21f, -0.93f, 0f));
        positionList.Add(new Vector3(-1.94f, -2.46f, 0f));
        positionList.Add(new Vector3(2.17f, -3.28f, 0f));
        positionList.Add(new Vector3(1.69f, -1.34f, 0f));
        positionList.Add(new Vector3(-5.93f, -3.71f, 0f));
        positionListForCupa = new List<Vector3>();
        positionListForCupa.Add(new Vector3(6.86f, -0.86f, 0f));
        positionListForCupa.Add(new Vector3(-6.21f, -0.93f, 0f));
        positionListForCupa.Add(new Vector3(-1.94f, -2.46f, 0f));
        positionListForCupa.Add(new Vector3(2.17f, -3.28f, 0f));
        positionListForCupa.Add(new Vector3(1.69f, -1.34f, 0f));
        positionListForCupa.Add(new Vector3(-5.93f, -3.71f, 0f));

        pestiPrinsiCount = 0;
        errorCount = 0;

        numeCuPozitieInCosDict = new Dictionary<string, Vector3>();
        numeCuPozitieInCosDict.Add("pesteCerc1", new Vector3(2.53f, 1.97f, 0f));
        numeCuPozitieInCosDict.Add("pesteCerc2", new Vector3(1.48f, 2.11f, 0f));
        numeCuPozitieInCosDict.Add("pesteTriunghi1", new Vector3(6.68f, 1.93f, 0f));
        numeCuPozitieInCosDict.Add("pesteTriunghi2", new Vector3(7.5f, 2.08f, 0f));
        numeCuPozitieInCosDict.Add("pestePatrat1", new Vector3(3.87f, 1.96f, 0f));
        numeCuPozitieInCosDict.Add("pestePatrat2", new Vector3(4.93f, 1.87f, 0f));

        numeCuRotatieInCosDict = new Dictionary<string, Vector3>();
        numeCuRotatieInCosDict.Add("pesteCerc1", new Vector3(-0.682f, -0.039f, 23.887f));
        numeCuRotatieInCosDict.Add("pesteCerc2", new Vector3(0f, 0f, -46.82f));
        numeCuRotatieInCosDict.Add("pesteTriunghi1", new Vector3(0f, 0f, -19.284f));
        numeCuRotatieInCosDict.Add("pesteTriunghi2", new Vector3(0f, 0f, -33.607f));
        numeCuRotatieInCosDict.Add("pestePatrat1", new Vector3(-4.69f, -6.169f, -26.392f));
        numeCuRotatieInCosDict.Add("pestePatrat2", new Vector3(2.112f, 0f, 17.62f));

        numeCuScaleInCosDict = new Dictionary<string, Vector3>();
        numeCuScaleInCosDict.Add("pesteCerc1", new Vector3(0.05926224f, 0.06084747f, 1f));
        numeCuScaleInCosDict.Add("pesteCerc2", new Vector3(0.1726375f, 0.1850292f, 1f));
        numeCuScaleInCosDict.Add("pesteTriunghi1", new Vector3(0.1470999f, 0.1260499f, 1f));
        numeCuScaleInCosDict.Add("pesteTriunghi2", new Vector3(0.1553968f, 0.1464088f, 1f));
        numeCuScaleInCosDict.Add("pestePatrat1", new Vector3(0.2553642f, 0.2149316f, 1f));
        numeCuScaleInCosDict.Add("pestePatrat2", new Vector3(0.04614563f, 0.04357845f, 1f));
    }

    private void movePesteLaCos(string nameOfObj)
    {
        //stergem din dict si din pozList
        Vector3 posToBeErased = numeCuPozitieDict[nameOfObj];
        positionList.Remove(posToBeErased);
        numeCuPozitieDict.Remove(nameOfObj);
        //apoi punem in cos
        if (nameOfObj == "pesteCerc1")
        {
            pesteCerc1.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pesteCerc1.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pesteCerc1.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
        else if (nameOfObj == "pesteCerc2")
        {
            pesteCerc2.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pesteCerc2.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pesteCerc2.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
        else if (nameOfObj == "pestePatrat1")
        {
            pestePatrat1.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pestePatrat1.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pestePatrat1.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
        else if (nameOfObj == "pestePatrat2")
        {
            pestePatrat2.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pestePatrat2.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pestePatrat2.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
        else if (nameOfObj == "pesteTriunghi1")
        {
            pesteTriunghi1.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pesteTriunghi1.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pesteTriunghi1.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
        else if (nameOfObj == "pesteTriunghi2")
        {
            pesteTriunghi2.transform.position = numeCuPozitieInCosDict[nameOfObj];
            pesteTriunghi2.transform.localScale = numeCuScaleInCosDict[nameOfObj];
            pesteTriunghi2.transform.localEulerAngles = numeCuRotatieInCosDict[nameOfObj];
        }
    }


    private void shufflePositionList()
    {
        for (int i = 0; i < positionList.Count; i++)
        {
            int rnd = UnityEngine.Random.Range(0, positionList.Count);
            Vector3 tempGO = positionList[rnd];
            positionList[rnd] = positionList[i];
            positionList[i] = tempGO;
        }
    }

    private void changePositionsForPestiDict()
    {
        shufflePositionList();
        int posInArray = 0;
        foreach (KeyValuePair<string, Vector3> entry in numeCuPozitieDict)
        {
            changePositionForAGameObject(positionList[posInArray], entry.Key);
            posInArray++;
        }
    }

    private void changePositionForAGameObject(Vector3 newPos, string nameOfObj)
    {
        if (nameOfObj == "pesteCerc1")
        {
            pesteCerc1.transform.position = newPos;
        }
        else if (nameOfObj == "pesteCerc2")
        {
            pesteCerc2.transform.position = newPos;
        }
        else if (nameOfObj == "pestePatrat1")
        {
            pestePatrat1.transform.position = newPos;
        }
        else if (nameOfObj == "pestePatrat2")
        {
            pestePatrat2.transform.position = newPos;
        }
        else if (nameOfObj == "pesteTriunghi1")
        {
            pesteTriunghi1.transform.position = newPos;
        }
        else if (nameOfObj == "pesteTriunghi2")
        {
            pesteTriunghi2.transform.position = newPos;
        }
    }

    private void makePesteVizibil(string nameOfObj)
    {
        if (nameOfObj == "pesteCerc1")
        {
            pesteCerc1.GetComponent<Renderer>().enabled = true;
        }
        else if (nameOfObj == "pesteCerc2")
        {
            pesteCerc2.GetComponent<Renderer>().enabled = true;
        }
        else if (nameOfObj == "pestePatrat1")
        {
            pestePatrat1.GetComponent<Renderer>().enabled = true;
        }
        else if (nameOfObj == "pestePatrat2")
        {
            pestePatrat2.GetComponent<Renderer>().enabled = true;
        }
        else if (nameOfObj == "pesteTriunghi1")
        {
            pesteTriunghi1.GetComponent<Renderer>().enabled = true;
        }
        else if (nameOfObj == "pesteTriunghi2")
        {
            pesteTriunghi2.GetComponent<Renderer>().enabled = true;
        }
    }

    private void makePesteInvizibil(string nameOfObj)
    {
        if (nameOfObj == "pesteCerc1")
        {
            pesteCerc1.GetComponent<Renderer>().enabled = false;
        }
        else if (nameOfObj == "pesteCerc2")
        {
            pesteCerc2.GetComponent<Renderer>().enabled = false;
        }
        else if (nameOfObj == "pestePatrat1")
        {
            pestePatrat1.GetComponent<Renderer>().enabled = false;
        }
        else if (nameOfObj == "pestePatrat2")
        {
            pestePatrat2.GetComponent<Renderer>().enabled = false;
        }
        else if (nameOfObj == "pesteTriunghi1")
        {
            pesteTriunghi1.GetComponent<Renderer>().enabled = false;
        }
        else if (nameOfObj == "pesteTriunghi2")
        {
            pesteTriunghi2.GetComponent<Renderer>().enabled = false;
        }
    }

    private string chooseRandomPesteName()
    {
        int rnd = UnityEngine.Random.Range(0, numeCuPozitieDict.Count - 1);
        int i = 0;
        foreach (KeyValuePair<string, Vector3> entry in numeCuPozitieDict)
        {
            if (rnd == i)
            {
                return entry.Key;
            }
            i++;
        }
        return "";
    }

    private void changePositionForPesteCupa()
    {
        int rnd = UnityEngine.Random.Range(0, positionListForCupa.Count - 1);
        //change pos for cupa
        pesteCupa.transform.position = positionListForCupa[rnd];
    }

    private IEnumerator DelayLoadLevel()
    {
        yield return new WaitForSeconds(2f);
        semnIntrebare.GetComponent<Renderer>().enabled = true;
    }

    private IEnumerator DelayLoadLevelCupa()
    {
        yield return new WaitForSeconds(3f);
        pesteCupa.GetComponent<Renderer>().enabled = false;
        cupa.GetComponent<Renderer>().enabled = true;

    }

    private IEnumerator Delay()
    {
        yield return new WaitForSeconds(2f);
        changePositionsForPestiDict();
        lastPesteAlesSaAparaPeEcran = chooseRandomPesteName();
        makePesteVizibil(lastPesteAlesSaAparaPeEcran);
        firstTime = System.DateTime.Now;
    }





    // Update is called once per frame
    void Update()
    {
        //game hasn't started
        if (!gameStopped && !audioIntroFullVulpe.isPlaying && !gameStarted && !audioBravoVulpe.isPlaying && !audioInstructiuneVulpe.isPlaying && !audioMaiIncearcaVulpe.isPlaying && !audioOutVulpe.isPlaying)
        {
            gameStarted = true;
            changePositionsForPestiDict();
            lastPesteAlesSaAparaPeEcran = chooseRandomPesteName();
            makePesteVizibil(lastPesteAlesSaAparaPeEcran);
            firstTime = System.DateTime.Now;
        }


        else if (!aPrinsCupa && pestiPrinsiCount != 6 && gameStarted && !gameStopped && !audioIntroFullVulpe.isPlaying && !audioBravoVulpe.isPlaying && !audioInstructiuneVulpe.isPlaying && !audioMaiIncearcaVulpe.isPlaying && !audioOutVulpe.isPlaying)
        {
            DateTime now = System.DateTime.Now;
            var diffInSeconds = (now - firstTime).TotalSeconds;
            if (diffInSeconds > 5)
            {
                //a trecut timpul si nu a apasat pe nimic
                //schimb pestele
                changePositionsForPestiDict();
                makePesteInvizibil(lastPesteAlesSaAparaPeEcran);
                lastPesteAlesSaAparaPeEcran = chooseRandomPesteName();
                makePesteVizibil(lastPesteAlesSaAparaPeEcran);
                firstTime = System.DateTime.Now;
            }
            else if (Input.GetMouseButtonDown(0))
            {
                RaycastHit hit;
                Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
                if (Physics.Raycast(ray, out hit))
                {
                    Debug.Log(hit.collider.name);
                    if (hit.collider.name == "semnIntrebare")
                    {
                        audioInstructiuneVulpe.Play(0);
                    }
                    if (!audioInstructiuneVulpe.isPlaying)
                    {
                        if (hit.collider.name == lastPesteAlesSaAparaPeEcran)
                        {
                            //a prins pestele
                            movePesteLaCos(lastPesteAlesSaAparaPeEcran);
                            pestiPrinsiCount++;
                            StartCoroutine(Delay());
                            audioBravoVulpe.Play(0);
                        }
                        else
                        {
                            //a dat click pe altceva 
                            errorCount++;
                            Debug.Log("greseala cu error count " + errorCount + " la copac");
                            audioMaiIncearcaVulpe.Play(0);
                            if (errorCount == 2)
                            {
                                semnIntrebare.GetComponent<Renderer>().enabled = false;
                                StartCoroutine(DelayLoadLevel());
                            }
                            else if (errorCount == 3)
                            {
                                errorCount = 0;
                                semnIntrebare.GetComponent<Renderer>().enabled = false;
                                StartCoroutine(DelayLoadLevel());
                            }
                        }

                    }

                }
            }
        }

        else if (!aPrinsCupa && pestiPrinsiCount == 6 && gameStarted && !gameStopped && !audioIntroFullVulpe.isPlaying && !audioBravoVulpe.isPlaying && !audioMaiIncearcaVulpe.isPlaying && !audioOutVulpe.isPlaying)
        {
            DateTime now = System.DateTime.Now;
            var diffInSeconds = (now - firstTime).TotalSeconds;
            if (diffInSeconds > 5)
            {
                //a trecut timpul si nu a apasat pe nimic
                //mut cupa
                pesteCupa.GetComponent<Renderer>().enabled = true;
                changePositionForPesteCupa();
                firstTime = System.DateTime.Now;
            }
            else if (Input.GetMouseButtonDown(0))
            {
                RaycastHit hit;
                Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
                if (Physics.Raycast(ray, out hit))
                {
                    Debug.Log(hit.collider.name);
                    if (hit.collider.name == "semnIntrebare")
                    {
                        audioInstructiuneVulpe.Play(0);
                    }
                    if (!audioInstructiuneVulpe.isPlaying)
                    {
                        if (hit.collider.name == "pesteCupa")
                        {
                            //a prins pestele cupa
                            pesteCupa.transform.position = new Vector3(1.95f, -0.13f, 0f);
                            pesteCupa.transform.localScale = new Vector3(1.659756f, 1.620041f, 1f); aPrinsCupa = true;
                            StartCoroutine(DelayLoadLevelCupa());
                            audioBravoVulpe.Play(0);

                        }


                    }

                }
            }
        }

        else if (aPrinsCupa && gameStarted && !gameStopped && !audioIntroFullVulpe.isPlaying && !audioBravoVulpe.isPlaying && !audioInstructiuneVulpe.isPlaying && !audioMaiIncearcaVulpe.isPlaying && !audioOutVulpe.isPlaying && pestiPrinsiCount == 6)
        {
            //gata cupa
            gameStopped = true;
            audioOutVulpe.Play(0);
        }

        else if (pestiPrinsiCount == 6 && aPrinsCupa && gameStarted && gameStopped && !audioIntroFullVulpe.isPlaying && !audioBravoVulpe.isPlaying && !audioInstructiuneVulpe.isPlaying && !audioMaiIncearcaVulpe.isPlaying && !audioOutVulpe.isPlaying)
        {
            Debug.Log("go to next scene now");
            SceneManager.LoadScene("inceputExtensie");
        }


    }
}
