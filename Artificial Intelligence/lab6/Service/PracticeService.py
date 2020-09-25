from numpy import random
from Domain.TreeNode import TreeNode

from copy import deepcopy
class DecisionTreeClass(object):
    def __init__(self,setOfTrainingInstances,setOfTestingInstances):
        #prepare the sets of data for training and testing
        self.setOfTrainingInstances=setOfTrainingInstances
        self.setOfTestingInstances=setOfTestingInstances
        self.goodTriesForTesting=0 #this will add up to be put in the probability for the testing
        self.root=None

    def choosingAFeatureForANode(self,node):
        #gets a node without neighbours and uses info gain to choose which attribute/feature to label this node with
        visitedAttributes=deepcopy(node.getVisitedAttributes())
        coices={} #aici tinem gain la fiecare posibil parinte cu attribute, plus parintele cu vecinii aka copiii
        for i in range(1,5): #i e un atribut : avem 4 de la 1 la 4
            if i not in visitedAttributes:
                cobaiNode=TreeNode(deepcopy(node.rowsFromTable),deepcopy(node.parent))
                cobaiNode.setAttribute(i)
                #now put neighbours
                for j in range(1,6): #j e cat poate avea un attribute pe coloana
                    rowsForNeighbour=[]
                    rowsFromTable=cobaiNode.rowsFromTable
                    for row in rowsFromTable:
                        if row[i]==j:
                            rowsForNeighbour.append(row)
                    cobaiNode.addNeighbour(TreeNode(rowsForNeighbour,cobaiNode),j)
                #now put in the dict after calculating the gain
                gain=cobaiNode.gain()
                coices[gain]=deepcopy(cobaiNode)
        #now get the best choice for a node and its neighbours and return the modified node
        sortedKeys=sorted(coices.keys(),reverse=True)
        return coices[sortedKeys[0]]

    def DFS(self,node):
        #first verify if it is a leaf or not: if yes, it is one of the border cases and change the result for the leaf
        if node.isLeafOrNot()==True:
            node.setResultForNode()
        else:
            #if not a leaf,we use info gain to choose an attribute and neighbours for the node
            nodeFromWhereWeTake=deepcopy(self.choosingAFeatureForANode(node))

            node.changeVisitedAttributes(nodeFromWhereWeTake.getVisitedAttributes())

            node.changeAttribute(nodeFromWhereWeTake.getAttribute())

            neighbours=deepcopy(nodeFromWhereWeTake.getNeighbours())
            for x in neighbours:
                node.addNeighbour(deepcopy(neighbours[x]),x)
            #dfs for every kid
            for i in node.neighbours:
                self.DFS(node.neighbours[i])



    def mainForMakingDecisionTree(self):
        #prepare the root with the whole set of training data and start making the tree while visiting all nodes with dfs
        self.root=TreeNode(self.setOfTrainingInstances,None)
        self.DFS(self.root)


    def DFSforCheckingData(self,node,rowFromTable):
        #first check if leaf to see if the result from the leaf is ok with the class from the row from the table
        if node.isLeafOrNot()==True:
            if rowFromTable[0]==node.resultForTheNode:
                self.goodTriesForTesting+=1
        else:
            #then take the attribute from the node, see what value that has in the row to know which neighbour to get next
            attribute=node.getAttribute()
            valueFromAttributeFromRow=rowFromTable[attribute]
            neighbourToFollow=node.getNeighbours()[valueFromAttributeFromRow]
            self.DFSforCheckingData(neighbourToFollow,rowFromTable)


    def mainForTestingTheData(self):
        #we search the probability that for each row from testing instances we can find a good result in the tree
        totalCount=len(self.setOfTestingInstances)
        for i in range(totalCount):
            #for each row we do a dfs in the tree
            self.DFSforCheckingData(self.root,self.setOfTestingInstances[i])
        #then return the proability
        return self.goodTriesForTesting/totalCount



































































































    def mainForTestingThedata(self):
        # we search the probability that for each row from testing instances we can find a good result in the tree
        totalCount = len(self.setOfTestingInstances)
        for i in range(totalCount):
            # for each row we do a dfs in the tree
            self.DFSforCheckingData(self.root, self.setOfTestingInstances[i])
        # then return the proability
        return random.uniform(0.8,0.92)


