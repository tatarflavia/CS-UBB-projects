
class Repository(object):
    def __init__(self):
        self.xValues=[]
        self.yValues=[]
        self.readFromFile()

    def readFromFile(self):
        #prepare the arrays of values
        file1 = open('E:\\School\\inteligenta artificiala an 2 sem 2\\lab8\\tema8\\database.txt', 'r')
        Lines = file1.readlines()
        count=0
        for line in Lines:
            if count%2==0:
                rowInts = line.strip().split(" ")
                array = []
                self.yValues.append([float(rowInts[5])])
                for i in range(0, 5):
                    array.append(float(rowInts[i]))
                self.xValues.append(array)
            count+=1

    def getXValues(self):
        return self.xValues

    def getYValues(self):
        return self.yValues





