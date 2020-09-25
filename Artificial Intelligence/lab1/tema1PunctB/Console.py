import numpy as np
import random
class Console(object):
    def __init__(self):
        self.array_of_figures=[]
        self.table=[[0,0,0,0,0,0], [0,0,0,0,0,0], [0,0,0,0,0,0], [0,0,0,0,0,0], [0,0,0,0,0,0]]
        self.number_of_figures=0
        self.table_line_max=4   #val maxima pe care o ia o linie
        self.table_col_max=5  #la fel cu coloana

    def readFromFile(self):
        # citim din fisier cele 5 figuri in array_of_figures
        f = open("demofile.txt", "r")
        self.number_of_figures=int(f.readline())
        little_array=[]
        line=f.readline()
        ar4 = line.strip().split(" ")
        ar7 = []
        for z in ar4:
            ar7.append(int(z))
        little_array.append(ar7)
        self.array_of_figures.append(little_array)
        i=1
        while i<=8:
            little_array=[]
            line=f.readline()
            ar4=line.strip().split(" ")
            ar7=[]
            for z in ar4:
                ar7.append(int(z))
            little_array.append(ar7)
            line = f.readline()
            ar=line.strip().split(" ")
            ar2=[]
            for j in ar:
                ar2.append(int(j))
            little_array.append(ar2)
            self.array_of_figures.append(little_array)
            i+=2


    def random_position(self):
        # facem un array de pozitii random ca sa umplem tabelul asa
        array_of_positions=[]
        i=1
        while i<=5:
            nr_linie=np.random.randint(5, size=1)
            nr_col=np.random.randint(4,size=1)
            array_of_positions.append((nr_linie[0],nr_col[0]))
            i+=1

        return array_of_positions

    def arrangeOnTable(self,form,position):
    #primeste o forma si o pozitie:incearca sa le puna pe tabla
    #daca nu poate=> asta nu e solutie pt problema noastra
    # daca poate,schimba tabla dupa forma
    # forma e un array sub forma de matrice [1,1,1,1] sau [[1,0,1],[1,1,1]]
        line=position[0]
        col=position[1]
        # incercam sa punem pe table forma
        nrOfRowsInForm=len(form)
        nrOfColumnsInForm=len(form[0])

        if col+nrOfColumnsInForm>self.table_col_max+1 or line+nrOfRowsInForm>self.table_line_max+1:
            print("Geometric form " + str(form) + " falls out of the table")
            return -1

        else:

            for i in range(line,nrOfRowsInForm+line):
                for j in range(col,col+nrOfColumnsInForm):
                    if self.table[i][j]==1 and form[i-line][j-col]==1:
                        print("The form "+str(form)+" overlaps on the table on line "+str(i)+" column "+str(j))
                        return -1
                    else:
                        if self.table[i][j]==0 and form[i-line][j-col]==1:
                            self.table[i][j]=form[i-line][j-col]
            print(self.table)

    def placeAllOnTable(self):
        #print("This is the table now:"+str(self.table))
        # array_of_pos = self.random_position()
        array_of_pos=[(0,0),(3,0),(1,0),(1,1),(3,3)]
        # array_of_pos=[(4,2),(0,0),(2,2),(2,3),(0,3)]
        # array_of_pos=[(0,2),(3,0),(1,2),(1,3),(3,3)]
        print("The random positions are:"+str(array_of_pos))

        nb=0
        ok=0
        possiblePos=[]
        for i in range(0,6):
            for j in range(0,5):
                possiblePos.append((i,j))
        while nb<self.number_of_figures:
            (x,y)=random.choice(possiblePos)
            if self.arrangeOnTable(self.array_of_figures[nb],array_of_pos[nb])==-1:
                print("The form "+str(self.array_of_figures[nb])+"can't be placed,so this is not a solution so far")
                ok=1
            possiblePos.remove((x,y))
            nb+=1
        if ok==0:
            print("SOLUTION!!!!")
            return 100
















    def runApp(self):
        self.readFromFile()
        # print("These are the geometric forms read from file:"+str(self.array_of_figures))
        while True:
            print()
            print()
            # print("This is the table initially:" + str(self.table))
            try:
                choice=int(input("Please give a number of trials:"))
                while choice>0:
                    print()
                    print()
                    print()
                    print(choice)
                    if self.placeAllOnTable()==100:
                        print(choice)
                        print("it has found a solution!!!!")
                        break

                    else:
                        choice-=1
            except ValueError:
                print("Try a number!")




