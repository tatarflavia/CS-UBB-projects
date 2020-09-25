from Domain.Problem import Problem
from Domain.State import State

class Controller(object):

    def __init__(self , problem):
        self.problem=problem
        numberOfLinesInMatrix=self.problem.readFromFile()
        #it starts preparing the empty matrix=empty table
        initialTable=[] #where we save the matrix
        for i in range(numberOfLinesInMatrix):
            line=[]
            for j in range(numberOfLinesInMatrix):
                line.append(0)
            initialTable.append(line)
        initialTable=State(initialTable) #make initialTable a State instance
        self.problem.setInitialStateOfProblem(initialTable) #set the initial state=table for the problem

    def getInitialState(self):
        #getter for the root of the tree in the problem
        return self.problem.getInitialStateOfProblem()

    def depthFirstSearchUniformedSearch(self, rootNode): #root is a state:the initial one
        #dfs searches in depth:from root to the leaves in a branch:until it finds a solution or it has no more nodes to look into
        #depth first search for uniformed search
        #basically:we mark the neighbours of the top of the stack as visited and add their children to be visited and checked for sol
        stackOfNodes = [rootNode] #stack of nodes
        visited = []
        while len(stackOfNodes) > 0: #while stack is not empty
            nodeToBeChecked = stackOfNodes.pop() #take it out and check it
            print("Node taken out from the stack is:\n"+str(nodeToBeChecked))
            if self.problem.checksIfSolutionNode(nodeToBeChecked):
                return nodeToBeChecked    #if solution return
            visited.append(nodeToBeChecked) #mark as visited
            if self.problem.checksIfPotential(nodeToBeChecked):
                childNodes = self.problem.expandNode(nodeToBeChecked) #get the child nodes
                auxForStack = [] #put unvisited children in aux
                for child in childNodes:
                    if child not in visited:
                        auxForStack.append(child)
                stackOfNodes = stackOfNodes + auxForStack #update stack

    def greedyBestFirstSearchInformedSearch(self, root): #root is a state:the initial one
        #greedy best first search:for informed search: we have info on the goal state:since calling heuristic which tells us how far we are from the solution
        #we expand the node (find the children) closest to the goal node:thanks to heuristic function(node tih the least h value)
        #we use a priority queue to hold the h for each node:we extract the minimum node, the first node in the pq
            #then,we visit unvisited neightbours of extraction node and add them in the right order(min first) in  the pq
        visitedNodes = []
        nodesToBeVisited = [root] #priority queue
        while len(nodesToBeVisited) > 0: #while pq is not empty
            nodeTakenOut = nodesToBeVisited.pop(0)  #removes the node closest to the solution which is a matrix
            print("Node taken out from the priority queue is:\n"+str(nodeTakenOut))
            visitedNodes = visitedNodes + [nodeTakenOut] #mark the node as visited(which is a matrix)
            if self.problem.checksIfSolutionNode(nodeTakenOut):
                return nodeTakenOut   #if it's a sol:return it(the solution matrix)
            if self.problem.checksIfPotential(nodeTakenOut): #if it s a potential solution we visit the children(neighbour nodes)
                auxPriorityQueue = [] #to be added to pq
                childNodes = self.problem.expandNode(nodeTakenOut) #child nodes

                for child in childNodes:
                    if child not in visitedNodes:
                        auxPriorityQueue.append(child) #mark it as visited
                auxPriorityQueue = [[node, self.problem.heuristicFunction(node)] for node in auxPriorityQueue]  #put in list the h(x) and x for every child node
                auxPriorityQueue.sort(key=lambda node: node[1]) #sort by h(x), small to big
                auxPriorityQueue = [node[0] for node in auxPriorityQueue] #only keep the nodes
                nodesToBeVisited = auxPriorityQueue[:] + nodesToBeVisited #update pq

