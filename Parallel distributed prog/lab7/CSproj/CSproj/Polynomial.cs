using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSproj
{
    [Serializable]
    public class Polynomial
    {
        //polynomial class: we have coeffs, a degree and a size of all coeffs= deg +1
        public int Degree { get; set; }
        public int[] Coefficients { get; set; }
        public int size = 0;

        public Polynomial(int s)
        {
            Degree = s;
            size = s + 1;
            Coefficients = new int[size]; //looks like this: [0] is from x^0, [1] is from x^1
        }

        public void GenerateRandom()
        {
            Random rnd = new Random();

            for (int i = 0; i < size; i++)
            {
                Coefficients[i] = rnd.Next(-10, 10);
                if (i == size - 1)
                {
                    while (Coefficients[i] == 0)
                    {
                        Coefficients[i] = rnd.Next(-10, 10);
                    }
                }
            }
        }


        //start from bigger degree that is at the end of coeffs and go to x^0 that is at beginning of the coeffs
        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();

            for (int i = size - 1; i >= 0; i--)
            {
                if (Coefficients[i] != 0)
                {
                    if (Coefficients[i] < 0)
                    {
                        sb.Append(Coefficients[i]);
                    }
                    else if (Coefficients[i] > 0)
                    {
                        if (i < size - 1)
                        {
                            sb.Append("+");
                        }
                        sb.Append(Coefficients[i]);
                    }


                    if (i == 1)
                    {
                        sb.Append("*");
                        sb.Append("X");
                    }
                    else if (i != 0)
                    {
                        sb.Append("*");
                        sb.Append("X^");
                        sb.Append(i);
                    }

                }
            }

            return sb.ToString();
        }


        //get a poly with coeffs formed of deg 0, 1, 2,... m-1 
        internal Polynomial GetLast(int m)
        {
            Polynomial result = new Polynomial(m - 1);

            for (int i = 0; i < m; i++)
            {
                result.Coefficients[i] = Coefficients[i];
            }

            return result;

        }


        //get a poly with coeffs formed of last degree, last deg -1, ...,until we have m coeffs
        // ex size 8,deg=7,  m=3 => [0] will be from x^5, [1] will be from x^6, [2] will be from x^7
        internal Polynomial GetFirst(int m)
        {
            Polynomial result = new Polynomial(m - 1);

            int k = 0;

            for (int i = size - m; i < size; i++)
            {
                result.Coefficients[k] = Coefficients[i];
                k++;
            }

            return result;
        }

        internal Polynomial Sum(Polynomial b)
        {
            int size1 = size;
            int size2 = b.size;

            int sizeMax = (size1 > size2) ? size1 : size2;

            Polynomial result = new Polynomial(sizeMax - 1);

            for (int i = 0; i < sizeMax; i++)
            {
                int res = 0;
                if (i < size1)
                {
                    res = res + Coefficients[i];
                }
                if (i < size2)
                {
                    res = res + b.Coefficients[i];
                }
                result.Coefficients[i] = res;
            }

            return result;
        }

        //adds a number of v zero's to left of repr x^big degree....x^0 => x^(deg+v) ....x^0
        internal Polynomial AddZerosLeft(int v)
        {
            int[] newCoef = new int[size + v];

            for (int i = 0; i < size; i++)
            {
                newCoef[i] = Coefficients[i];
            }
            for (int i = size; i < size + v; i++)
            {
                newCoef[i] = 0;
            }

            Coefficients = newCoef;
            size = Coefficients.Length;

            return this;
        }

        //adds a number of v zero's to right of repr x^big degree....x^0 => x^(deg) ....x^0 + v number of zeros
        //we have 0 0 0 0 up to deg v, and then from our poly x^0 , x^1, ..., up untill all the coeffs have been put
        //ex size=4, v=5 => rez size=9..we put zeros for the first 5 pos in coeffs and then take from out poly x^0, x^1,..x^deg
        internal Polynomial AddZerosRight(int v)
        {
            Polynomial result = new Polynomial(size + v - 1);

            for (int i = v; i < size + v; i++)
            {
                result.Coefficients[i] = Coefficients[i - v];
            }

            return result;
        }

        internal Polynomial Difference(Polynomial b)
        {
            int size1 = size;
            int size2 = b.size;

            int sizeMax = (size1 > size2) ? size1 : size2;

            Polynomial result = new Polynomial(sizeMax - 1);

            for (int i = 0; i < sizeMax; i++)
            {
                int res = 0;
                if (i < size1)
                {
                    res = Coefficients[i];
                }
                if (i < size2)
                {
                    res = res - b.Coefficients[i];
                }
                result.Coefficients[i] = res;
            }

            return result;
        }
    }
}

