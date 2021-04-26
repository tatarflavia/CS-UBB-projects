#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <mpi.h>



#define true 1
#define false 0

//the files from where we read the graph and where we write the result
const char * READ_FILE_PATH = "JONES2.txt";
const char * WRITE_FILE_PATH = "result.txt";

int rankOfCurrentProcess, nbOfProcesses, mainProcessRank = 0;  //main process is the parent one, always rank 0

int numberOfVertexes, numberOfEdges; //for the graph, the number of vertexes and edges

//smallest possible nb of colors for a graph is the chromatic nb for it
//upper bound for it:
int chromaticNbUpperMax = -1;

int *graph;//the symmetric adjacency graph matrix. The index of element at 
		   //col,row is idx = row*V+col. Similarly the element at index i is at 
		   //row = i/V,col=i%V

int *vertexCosts;//the random costs,one for each vertex

int *colors;//the colors as ints, one for each vertex


int compare(const void *a, const void *b)
{
	int la = *(const int*)a;
	int lb = *(const int*)b;

	return (la > lb) - (la < lb);
}

//only main process will read the input file
void read_graph(char *filename)
{
	FILE* file = fopen(filename, "r");
	if (!file) {
		printf("Can't read the input file %s\n", filename);
		return;
	}
	char line[1000];
	char *token;
	int i, j;
	int max_degree = -1;
	int rowIndex, columnIndex;

	//start making the graph
	//read file line by line
	j = 0;
	bool graph_initialized = false;
	while (fgets(line, 1000, file) != NULL) {
		strtok(line, " ");
		//read number of vertices and edges from the very first line of the file
		if (strcmp(line, "p") == 0) {
			token = (char *)strtok(NULL, " ");
			token = (char *)strtok(NULL, " ");
			numberOfVertexes = atoi(token);
			token = (char *)strtok(NULL, " ");
			numberOfEdges = atoi(token);
		}
		//read edges into graph matrix: into the file we have "e 1 2"
		//1.Initialize graph to all zeros before filling the edges
		if (graph_initialized == false && numberOfVertexes > 0) {
			graph = (int *)malloc(numberOfVertexes*numberOfVertexes * sizeof(int));
			memset(graph, 0, numberOfVertexes*numberOfVertexes * sizeof(int));
			graph_initialized = true;
		}

		//2.then fill the edges into it for lines that start with e, as in "e 1 2"
		if (strcmp(line, "e") == 0) {
			token = (char *)strtok(NULL, " ");
			rowIndex = atoi(token) - 1; //row index will be 0
			token = (char *)strtok(NULL, " "); //column index will be 1
			columnIndex = atoi(token) - 1;
			graph[rowIndex*numberOfVertexes + columnIndex] = 1; //elem index rowIndex*numberOfVertexes + columnIndex =1
			graph[columnIndex*numberOfVertexes + rowIndex] = 1; //make the same for the other sense
			j++;
		}
	}

	//fixing the maxim for the chromatic number
	if (max_degree == -1) {
		if (numberOfVertexes % 2 == 0)
			chromaticNbUpperMax = numberOfVertexes - 1;
		else
			chromaticNbUpperMax = numberOfVertexes; //so that we know the max number of colors, can't reach this number
	}

	//verify if all edges were read
	if (rankOfCurrentProcess == mainProcessRank && j != numberOfEdges && j != numberOfEdges / 2) {
		printf("Not all edges were read: There are %d edges but read %d.\n", numberOfEdges, j);
	}
	fclose(file);
}

void write_colors(char * filename)
{
	//function which writes to file for every vertex the color chosen
	FILE* file = fopen(filename, "w");
	if (!file) {
		printf("Can't open writing file %s\n", filename);
		return;
	}
	int i;

	//for every vertex take the color
	for (i = 0; i < numberOfVertexes; i++)
		fprintf(file, "Vertex number=%d, colored with color nb=%d\n", (i + 1), colors[i]);

	//write largest color
	qsort(colors, numberOfVertexes, sizeof(int), compare);
	fprintf(file, "Largest color used was %d\n We used a number of %d colors", colors[numberOfVertexes - 1], colors[numberOfVertexes - 1]);

	fclose(file);
}

void jones_plassmann(int* rangeOfVertexes, int * offsets, int * processGraph)
{
	//alg for a process

	int i, j, k;

	int nbOfVertexesPerProcess, restNbOfVertexesPerProcess, indexOfFirstVertex;
	int * colorsForTheVertexesOfProc, *neighColorsFofThisProc;
									 
	int weightsForProc, nbOfColors, min_color;
	bool j_weight_is_max;

	//ALG:::
	//Go through the vertices of this process and compare their weights with
	//neighboring vertices to find which of them are local maxima. 
	//Those form the independent set in each iteration
	//The minimum number of colors, is the chromatic number of the graph
	//can not be larger than chromaticity_upper so only iterate that many times
	//we iterate till we reach the upper bound of the chromatic number

	//first init the params for this process
	nbOfVertexesPerProcess = numberOfVertexes / nbOfProcesses;
	colorsForTheVertexesOfProc = (int *)malloc(rangeOfVertexes[rankOfCurrentProcess] * sizeof(int));
	memset(colorsForTheVertexesOfProc, 0, rangeOfVertexes[rankOfCurrentProcess] * sizeof(int));

	for (i = 0; i < chromaticNbUpperMax; i++) {
		//for each vertex in this process
		restNbOfVertexesPerProcess = (numberOfVertexes + rankOfCurrentProcess) % nbOfProcesses;
		//take index of the first vertex for process i
		indexOfFirstVertex = nbOfVertexesPerProcess * rankOfCurrentProcess + restNbOfVertexesPerProcess * (restNbOfVertexesPerProcess < rankOfCurrentProcess);

		//for each vertex of this process
		for (j = 0; j < rangeOfVertexes[rankOfCurrentProcess]; j++) {
			//get the vertex cost
			weightsForProc = vertexCosts[indexOfFirstVertex + j];
			j_weight_is_max = true;
			neighColorsFofThisProc = (int *)malloc(numberOfVertexes * sizeof(int));
			memset(neighColorsFofThisProc, 0, numberOfVertexes * sizeof(int));
			nbOfColors = 0;
			//compare vertex weight to weights of its non-colored neighbors to see
			//if it is a maximum. Also gather the colors of all neighbors of the
			//vertex j that have been colored
			for (k = 0; k < numberOfVertexes; k++) {
				//if there is an edge between j vertex and neighbor k vertex
				if (processGraph[j*numberOfVertexes + k] == 1) {
					//if neighbor is colored just add its color to the neighbor_colors
					if (colors[k] != 0) {
						neighColorsFofThisProc[nbOfColors++] = colors[k];
					}
					//conflict solving
					//if the weights match, solve conflict by looking at the vertices
					//ids and taking the vertex with higher id as the max 
					else if (weightsForProc < vertexCosts[k] || (weightsForProc == vertexCosts[k] && k > j)) {
						j_weight_is_max = false;
						break;
					}
				}
			}
			//if the vertex weight is a max and vertex hasnt been colored, 
			//color it with the smallest color possible that is not one of
			//neighbor_colors
			if (j_weight_is_max == true && colors[indexOfFirstVertex + j] == 0) {
				//find smallest color to assign to the j vertex
				//that color is either 
				//a)1 if none of the neighbors is colored or the smallest color
				//of a neighbor is >1
				//b)In between a color in the array of neighbors colors if there is
				//a gap between two of the (sorted) neighbors colors
				//c) 1 more than the last color in the sorted array of neighbors
				//colors
				//sort neighbors colors. 
				qsort(neighColorsFofThisProc, nbOfColors, sizeof(int), compare);
				if (nbOfColors == 0 || neighColorsFofThisProc[0] > 1)
					min_color = 1;
				else {
					for (k = 0; k < numberOfVertexes; k++) {
						if (k < numberOfVertexes - 1 && (neighColorsFofThisProc[k + 1] - neighColorsFofThisProc[k] > 1)) {
							min_color = neighColorsFofThisProc[k] + 1;
							break;
						}
						else {
							min_color = neighColorsFofThisProc[nbOfColors - 1] + 1;
						}
					}
				}
				colorsForTheVertexesOfProc[j] = min_color;
			}
			free(neighColorsFofThisProc);
		}
		printf("Process nb=%d sends the its colors to the parent process...\n",rankOfCurrentProcess);
		//each process sends the computed colors of its vertices back to the main process
		MPI_Gatherv(colorsForTheVertexesOfProc, rangeOfVertexes[rankOfCurrentProcess], MPI_INT, colors, rangeOfVertexes, offsets, MPI_INT, mainProcessRank, MPI_COMM_WORLD);
		//main process will sync the colors of all the processes and then at the end they will be written in the out file
		MPI_Bcast(colors, numberOfVertexes, MPI_INT, mainProcessRank, MPI_COMM_WORLD);
	}
	free(colorsForTheVertexesOfProc);
}

int main(int argc, char** argv)
{
	char *read_filename, *write_filename; //filenames from paths
	int numberOfVertexesPerProcessor, restOfVertexesPerProcessor; //we need to know how to distribute the vertexes between processes
	int firstVertexInProc, lastVertexInProc, *range;//the first and last vertixes in a processor
								
	int i, j, k;
	//graph made only with edges corresponding to vertexes from this process +size
	//if we have V nb of vertexes, processGraph will have 2*V size of matrix
	int *processGraph, *processGraphSize, *offsets;//offsets is the index in big graph where to start copying to procGraph
	
	int *vertex_offsets;
	double start_time, end_time, runtime, largest_runtime;

	//Initialize MPI
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rankOfCurrentProcess); //we initialise the rank of the process
	MPI_Comm_size(MPI_COMM_WORLD, &nbOfProcesses); //initialise the nb of processes

	printf("Nb of processes is=%d \n", nbOfProcesses);

	read_filename = (char *)malloc(900);
	strcpy(read_filename, READ_FILE_PATH);

	write_filename = (char *)malloc(900);
	strcpy(write_filename, WRITE_FILE_PATH);

	//only main process reads file
	//only root reads the file and loads the full graph
	if (rankOfCurrentProcess == mainProcessRank) {
		read_graph(read_filename);
		printf("Nb of vertexes=%d Nb of edges=%d Chromatic number Upper Bound=%d\n", numberOfVertexes, numberOfEdges, chromaticNbUpperMax);
		printf("Main process finished reading the graph from the input file.\n");
		fflush(stdout);
	}
	//main process sends nb of vertexes, nb of edges and chromatic upper bound to all other processes
	printf("Main process sends the nb of vertexes, edges and chromatic upper bound to all the processes...\n");
	MPI_Bcast(&numberOfVertexes, 1, MPI_INT, mainProcessRank, MPI_COMM_WORLD);
	MPI_Bcast(&numberOfEdges, 1, MPI_INT, mainProcessRank, MPI_COMM_WORLD);
	MPI_Bcast(&chromaticNbUpperMax, 1, MPI_INT, mainProcessRank, MPI_COMM_WORLD);

	//Distribute the number of vertexes as uniformly as possible among the processes
	

	//first init the params
	processGraphSize = (int *)malloc(nbOfProcesses * sizeof(int)); //size of process graph
	offsets = (int *)malloc(nbOfProcesses * sizeof(int));  //index from where we copy the proc graph
	vertex_offsets = (int *)malloc(nbOfProcesses * sizeof(int));
	range = (int *)malloc(nbOfProcesses * sizeof(int));  //interval of vertexes
	numberOfVertexesPerProcessor = numberOfVertexes / nbOfProcesses;

	printf("Main process starts distributing the work by dividing accoring to vertexes...\n");
	//then give each one their values for every process each
	for (i = 0; i < nbOfProcesses; i++) {
		restOfVertexesPerProcessor = (numberOfVertexes + i) % nbOfProcesses;

		//index of the first vertex for process i
		firstVertexInProc = numberOfVertexesPerProcessor * i + restOfVertexesPerProcessor * (restOfVertexesPerProcessor < i);
		//index of the last vertex for process i
		lastVertexInProc = (i + 1)* numberOfVertexesPerProcessor + (restOfVertexesPerProcessor + 1) * (restOfVertexesPerProcessor < i) - 1;
		
		//to know how many between first and last vertex
		range[i] = lastVertexInProc - firstVertexInProc + 1;
		processGraphSize[i] = range[i] * numberOfVertexes;

		offsets[0] = 0;
		vertex_offsets[0] = 0;
		if (i > 0) {
			offsets[i] = offsets[i - 1] + processGraphSize[i - 1]; //to know from where we read from the matrix
			vertex_offsets[i] = vertex_offsets[i - 1] + range[i - 1]; //to know till where
		}
	}

	//Main process starts sending all these distributions to all the other processes
	processGraph = (int *)malloc(processGraphSize[rankOfCurrentProcess] * sizeof(int));

	//here all of them get their work
	MPI_Scatterv(graph, processGraphSize, offsets, MPI_INT, processGraph, processGraphSize[rankOfCurrentProcess], MPI_INT, mainProcessRank, MPI_COMM_WORLD);

	start_time = MPI_Wtime();
	vertexCosts = (int *)malloc(numberOfVertexes * sizeof(int));

	printf("Main process generated random costs for the vertexes and sends them...\n");
	//Main process generates random costs which are a permutation of the vertices
	if (rankOfCurrentProcess == mainProcessRank) {
		for (i = 0; i < numberOfVertexes; i++) {
			vertexCosts[i] = rand() % (numberOfVertexes * 1000);
		}
	}

	//we send the costs too to the other processes
	MPI_Bcast(vertexCosts, numberOfVertexes, MPI_INT, mainProcessRank, MPI_COMM_WORLD);

	//initialize colors to 0
	colors = (int *)malloc(numberOfVertexes * sizeof(int));
	memset(colors, 0, numberOfVertexes * sizeof(int));

	printf("Processes are starting the computations aka coloring of the vertexes given...\n");
	//Jones-Plassman algorithm starts: here each process will do its job
	jones_plassmann(range, vertex_offsets, processGraph);

	end_time = MPI_Wtime();
	runtime = end_time - start_time;

	MPI_Allreduce(&runtime, &largest_runtime, 1, MPI_DOUBLE, MPI_MAX, MPI_COMM_WORLD);

	printf("Biggest time of runnning the computations was %f\n", largest_runtime);

	//writing to output file 
	if (rankOfCurrentProcess == mainProcessRank) {
		write_colors(write_filename);
	}
	printf("Main process finished writing the colors into the out file.\n");

	//freeing all the params
	free(read_filename);
	free(write_filename);
	free(processGraph);
	free(range);
	free(processGraphSize);
	free(offsets);
	free(graph);
	free(vertexCosts);
	free(colors);
	//Finalize
	MPI_Finalize();
	_getch();
	return 0;
}
