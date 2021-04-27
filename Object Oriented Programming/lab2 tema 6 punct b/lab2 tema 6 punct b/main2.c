#include <stdio.h>

int prime(int a)
//Boolean Function that checks whether the number a is prime or not
//input:a
//precond:a is an integer
//output:1 or 0
//postconditions:1 if the number is prime, 0 if not
{
	//    returns 1 if a is prime, else returns 0
	if (a % 2 == 0) 
	{
		return 0;
	}
	if (a < 3) {
		return 0;
	}
	int c = 2;
	while (c <= a / 2) {
		if (a%c == 0)
			return 0;
		c++;
	}
	return 1;
}

int sum(int a, int b)
{
	//Function that calculates the sum of a and b and returns it
	//input:a,b
	//precond:a,b are an integer
	//output:a+b
	//postconditions:a+b=sum of a and b
	return a + b;
}

void read_vector(int v[100],int n)
//Function that reads the elements for a vector v that has n elements.
//input:v,n
//precond:v is a vector of integers,n is a integer
//output:-
//postconditions:v is updated with the read elements
{
	int i = 0;
	printf("The elements are:");
	for (i = 0; i < n; i++) 
	{
		scanf("%u", &v[i]);
	}
}

void longest_subsequence(int v[], int n, int seq[],int* m)
//Function that gets the longest subsequence of numbers that have the sum for any 2 conseq numbers prime from the vector v
//input:v,n,seq,m
//precond:v is a vector,n is an integer, seq is a vector that will be updated,m is the length of the subsequnce
//output:-
//postconditions:seq is updated,m will also be updated
{
	int i = 0,a=0,b=0,ma=0,mb=0;
	for (i = 0; i < n-1; i++) 
	{
		
		if (prime(sum(v[i], v[i + 1])) == 0)
		{
			if (b - a + 1 > *m)
			{
				*m = b - a + 1;
				ma = a;
				mb = b;
			}
			a = i + 1;
		}
		else
			b = i + 1;
		
	}
	if (b - a + 1 > *m)
	{
		*m = b - a + 1;
		ma = a;
		mb = b;
	}
	for (i = ma; i <= mb; i++) 
	{
		seq[i] = v[i];
	}
	

}

void generate_numbers(int n) 
{
	int i = 0,k=0;
	printf("The prime numbers smaller than n are:\n");
	for (i = 3; i < n; i++) 
	{
		if (prime(i) == 1) 
		{
			printf("%d ", i);
			k = 1;
		}
	}
	if (k == 0) {
		printf("No prime numbers or n is not large enough.");
	}
}


void menu() 
{
	printf("This is the menu for the application that  given a vector of numbers, finds the longest contiguous subsequence such that the sum of any two consecutive elements is a prime number\n:");
	printf("1-for adding numbers\n");
	printf("2-for quitting\n");
	printf("3-generate all prime numbers smaller than a number n\n");
}

int main()
//Main function that calls all the other functions, like appstart.
{
	while (1) 
	{
		menu();
		int n = 0;
		printf("Please give a command:");
		scanf("%u", &n);

		if (n == 1) 
		{
			int v[100],seq[100],i=0,len2=0,n=0,m=0;
			printf("The number of elements in the vector is:");
			scanf("%u", &n); //n is the number of elements in the vector
			read_vector(v, n); //function that reads the vector
			longest_subsequence(v, n, seq,&m); //function that gets the the longest subsequnce and changes it
			len2 = m;
			if (len2 == 0) { printf("No 2 elements that correspond to the quest,any 1 prime number from the vector is okay.");
							return 0;
			}
			else {
				printf("The longest subsequence of sums that are prime numbers is:\n");
				for (i = 0; i < len2; i++)
					printf("%u\n", seq[i]);
				return 0;
				}
				
			
		}
		else {if (n == 2)
					{
						printf("You chose quitting!");
						return 0;
					}
		else { 
			if (n == 3)
			{
				int u=0;
				printf("The number n is:");
				scanf("%u", &u); //u is the number n you chose
				generate_numbers(u);
				return 0;
			}
			else {printf("Invalid command!");
			return 0;}
		}} 

	}
}