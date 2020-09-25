import math
from copy import deepcopy
class TreeNode(object):
    def __init__(self,rowsFromTable,parent):
        self.parent=parent
        self.rowsFromTable=rowsFromTable
        self.neighbours = {
            1: None,2: None,3: None,4:None,5:None}
        self.attribute=0
        self.visitedAttributes=[]
        self.setVisitedAttributes()
        self.instancesOfL=0
        self.instancesOfR=0
        self.instancesOfB=0
        self.resultForTheNode=""
        for i in range(len(self.rowsFromTable)):
            if self.rowsFromTable[i][0]=='R':
                self.instancesOfR+=1
            elif self.rowsFromTable[i][0]=='L':
                self.instancesOfL+=1
            else:self.instancesOfB+=1

    def changeAttribute(self,attribute):
        self.attribute=attribute

    def getAttribute(self):
        return self.attribute

    def getNeighbours(self):
        return self.neighbours

    def addNeighbour(self,neighbour,key):
        self.neighbours[key]=neighbour

    def getVisitedAttributes(self):
        return self.visitedAttributes

    def changeVisitedAttributes(self,visited):
        self.visitedAttributes=visited

    def setVisitedAttributes(self):
        if self.parent!=None:
            for x in self.parent.getVisitedAttributes():
                self.visitedAttributes.append(deepcopy(x))


    def setAttribute(self,attribute):
        self.attribute=attribute
        self.visitedAttributes.append(self.attribute)

    def getLenOfRows(self):
        return len(self.rowsFromTable)

    def containsPureSubsetOrNot(self):
        if (self.instancesOfR>0 and self.instancesOfB==0 and self.instancesOfL==0) or (self.instancesOfL>0 and self.instancesOfR==0 and self.instancesOfB==0) or (self.instancesOfB>0 and self.instancesOfR==0 and self.instancesOfL==0):
            return True
        return False

    def noAttributesOrNot(self):
        if len(self.visitedAttributes)==4:
            return True
        return False

    def noInstancesOrNot(self):
        if len(self.rowsFromTable)==0:
            return True
        return False

    def isLeafOrNot(self):
        if self.containsPureSubsetOrNot()==True or self.noAttributesOrNot()==True or self.noInstancesOrNot()==True:
            return True
        return False

    def setResultForNode(self):
        if self.noInstancesOrNot()==True:
            self.setLeafNodeResultIfNoInstancesRemain()
        elif self.noAttributesOrNot()==True:
            self.setLeafNodeResultIfNoAttributesRemain()
        else: self.setLeafNodeResulIfContainsPureSubset()


    def setLeafNodeResulIfContainsPureSubset(self):
        if self.instancesOfL>0:
            self.resultForTheNode="L"
        elif self.instancesOfR>0:
            self.resultForTheNode="R"
        else:self.resultForTheNode="B"

    def setLeafNodeResultIfNoAttributesRemain(self):
        if self.instancesOfR>self.instancesOfL and self.instancesOfR>self.instancesOfB:
            self.resultForTheNode="R"
        elif self.instancesOfL>self.instancesOfB and self.instancesOfL>self.instancesOfR:
            self.resultForTheNode="L"
        else: self.resultForTheNode="B"

    def setLeafNodeResultIfNoInstancesRemain(self):
        parentInstancesOfL= self.parent.instancesOfL
        parentInstancesOfR = self.parent.instancesOfR
        parentInstancesOfB = self.parent.instancesOfB
        if parentInstancesOfR>parentInstancesOfL and parentInstancesOfR>parentInstancesOfB:
            self.resultForTheNode="R"
        elif parentInstancesOfL>parentInstancesOfR and parentInstancesOfL>parentInstancesOfB:
            self.resultForTheNode="L"
        else: self.resultForTheNode="B"

    def getProportionForB(self):
        if self.instancesOfB==0:
            return 0
        return (self.instancesOfB/len(self.rowsFromTable))*math.log((self.instancesOfB/len(self.rowsFromTable)),2)

    def getProportionForL(self):
        if self.instancesOfL==0:
            return 0
        return (self.instancesOfL/len(self.rowsFromTable))*math.log((self.instancesOfL/len(self.rowsFromTable)),2)

    def getProportionForR(self):
        if self.instancesOfR==0:
            return 0
        return (self.instancesOfR/len(self.rowsFromTable))*math.log((self.instancesOfR/len(self.rowsFromTable)),2)


    def entropy(self):
        if self.isLeafOrNot()==True:
            return 0
        return -1*(self.getProportionForB()+self.getProportionForL()+self.getProportionForR())

    def gain(self):
        number=self.entropy()
        for x in self.neighbours:
            number-=self.neighbours[x].entropy()*(self.neighbours[x].getLenOfRows()/len(self.rowsFromTable))
        return number


    def __str__(self):
        stringRepr=""
        stringRepr += "Node attribute:" + str(self.attribute) + "\n"
        stringRepr += "visited attributes"+str(self.visitedAttributes)+"\n"
        stringRepr+= "result for the node:"+str(self.resultForTheNode)+"\n"
        stringRepr+="cati de L:"+str(self.instancesOfL)+"  cati de R:"+str(self.instancesOfR)+"  cati de B:"+str(self.instancesOfB)+"\n"
        stringRepr+="rows from table:"+str(self.rowsFromTable)+"\n"
        #stringRepr+="parent visited attributes:"+str(self.parent)
        stringRepr+="\n\n\n"
        return stringRepr



