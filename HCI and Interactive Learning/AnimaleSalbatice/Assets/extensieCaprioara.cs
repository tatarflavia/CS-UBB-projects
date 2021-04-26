using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class extensieCaprioara : MonoBehaviour
{
    private GameObject iarba1, iarba2, corn1, corn2, nor1, nor2, semn;
    private string lastNameClicked;
    AudioSource instr, bravo, help;
    int diferenteGasite, dif1,dif2,dif3;
    // Start is called before the first frame update
    void Start()
    {
        GlobalVariable.Instance.caprioaraCheck = 1;

        iarba1 = GameObject.Find("iarba1");
        iarba1.transform.position = new Vector3(-2.26f, -3.6f, -2f);
        iarba1.transform.localScale = new Vector3(0.48785f, 0.4877055f, 1f);

        iarba2 = GameObject.Find("iarba2");
        iarba2.transform.position = new Vector3(6.63f, -3.57f, 2f);
        iarba2.transform.localScale = new Vector3(0.4992698f, 0.5635263f, 1f);

        corn1 = GameObject.Find("corn1");
        corn1.transform.position = new Vector3(4.462f, 1.176f, -2f);
        corn1.transform.localScale = new Vector3(0.08873653f, 0.1070819f, 1f);

        corn2 = GameObject.Find("corn2");
        corn2.transform.position = new Vector3(-4.485f, 1.147f, 2f);
        corn2.transform.localScale = new Vector3(0.08630151f, 0.09979526f, 1f);

        nor1 = GameObject.Find("nor1");
        nor1.transform.position = new Vector3(-7.64f, 2.82f, -2f);
        nor1.transform.localScale = new Vector3(0.09493568f, 0.08537892f, 1f);

        nor2 = GameObject.Find("nor2");
        nor2.transform.position = new Vector3(1.25f, 2.89f, 2f);
        nor2.transform.localScale = new Vector3(0.08532742f, 0.09319219f, 1f);

        semn = GameObject.Find("semn");
        semn.transform.position = new Vector3(7.97f, 4.32f, -2f);
        semn.transform.localScale = new Vector3(0.07576747f, 0.07531497f, 1f);

        lastNameClicked = "";

        diferenteGasite = 0;
        dif1 = 0;
        dif2 = 0;
        dif3 = 0;

        instr = GameObject.Find("instr").GetComponent<AudioSource>();
        instr.Play(0);

        bravo = GameObject.Find("bravo").GetComponent<AudioSource>();
        help = GameObject.Find("instrHelp").GetComponent<AudioSource>();

    }

    // Update is called once per frame
    void Update()
    {

        if (Input.GetMouseButtonDown(0) && ! instr.isPlaying && ! bravo.isPlaying && !help.isPlaying)
        {
            //mouse is clicked
            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {

                //Debug.Log(hit.collider.name);
                if (hit.collider.name == "corn1")
                {


                    corn2.transform.position = new Vector3(-4.485f, 1.147f, -2f);
                    if (dif1==0)
                    {
                        diferenteGasite++;
                        dif1 = 1;
                    }
                    if (diferenteGasite == 3)
                    {
                        bravo.Play(0);
                    }

                }
                else if (hit.collider.name == "nor1")
                {

                    nor2.transform.position = new Vector3(1.25f, 2.89f, -2f);
                    if (dif2==0)
                    {
                        diferenteGasite++;
                        dif2 = 1;
                    }
                    if (diferenteGasite == 3)
                    {
                        bravo.Play(0);
                    }

                }
                else if (hit.collider.name == "iarba1")
                {

                    iarba2.transform.position = new Vector3(6.63f, -3.57f, -2f);
                    if (dif3==0)
                    {
                        diferenteGasite++;
                        dif3 = 1;
                    }
                    if (diferenteGasite == 3)
                    {
                        bravo.Play(0);
                    }

                }

                else if (hit.collider.name == "semn")
                {
                    help.Play(0);
                }
            }
        }
        else if(! bravo.isPlaying && diferenteGasite==3)
        {
            SceneManager.LoadScene("inceputExtensie");
        }
    }

}
