#vezi de teste si comm uri,undo si modulo nu merge la list la list si filter nu merge cu lista updatata,si problema cu stringuri la modulo(vezi de comm si teste aici)

import math
from copy import deepcopy
def CreateComplex(x,y):
    '''
    Function that creates a complex number formed by real part x and imaginary part y
    input:x,y
    preconditions:x,y-integers
    output:c
    postconditions:c-complex number where x=real part of c
                                          y=imaginary part of c
    '''
    return {'re':x,'im':y}

def GetReal(c):
    '''
    Function that gets the real part of the complex number c
    input:c
    preconditions:c-complex number
    output:re
    postconditions:re is the real part of the comple number c
    '''
    return c['re']

def GetImg(c):
    '''
    Function that gets the imaginary part of the complex number c
    input:c
    preconditions:c-complex number
    output:im
    postconditions:im is the imaginary part of the comple number c
    '''
    return c['im']

def SetReal(c,x):
    '''
    Function that sets the real part of the complex number c to x
    input:c,x
    preconditions:c-complex number, x-integer
    output:cc
    postconditions:cc is the new complex number 
    '''
    c['re']=x
    return c

def SetImg(c,x):
    '''
    Function that sets the imaginary part of the complex number c to x
    input:c,x
    preconditions:c-complex number,x-integer
    output:cc
    postconditions:cc is the new complex number 
    '''
    c['im']=x
    return c

def test_CreateComplex():
    a=8
    b=9
    c=CreateComplex(a,b)
    assert GetReal(c)==8
    assert GetImg(c)==9
    a=7
    b=0
    c=CreateComplex(a,b)
    assert GetReal(c)==7
    assert GetImg(c)==0
    SetReal(c,4)
    SetImg(c,4)
    assert GetReal(c)==4
    assert GetImg(c)==4


#initialisation for the list
def init(l):
    l.append(CreateComplex(5,6))
    l.append(CreateComplex(6,0))
    l.append(CreateComplex(5,0))
    l.append(CreateComplex(4,0))
    l.append(CreateComplex(6,8))
    l.append(CreateComplex(4,7))
    l.append(CreateComplex(-1,-2))
    l.append(CreateComplex(3,0))
    l.append(CreateComplex(9,8))
    l.append(CreateComplex(6,9))
    undoList = deepcopy(l)
    



def addElToList(l,c):
    '''
    Function that adds an complex number to a list of complex numbers
    input:l,c
    preconditions:l-list of complex numbers ,c-complex number to be put in the list
    output:-
    postconditions:l=l U c, c is the last added element in the list
    '''
    
    l.append(c)
    return l
def test_addElToList():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    addElToList(l,{'re': 5.0, 'im': 6.0})
    assert l==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 5.0, 'im': 6.0}]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    addElToList(l,{'re': 5.0, 'im': 6.0})
    assert l==[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 6.0}]
    
def insertElToPos(l,c,pos):
    '''
    Function that inserts an complex number c to a specific position pos in the list l
    input:l,c,pos
    preconditions:l-list of complex numbers ,c-complex number to be inserted in the list,pos is the position where the element will be inserted
    output:l
    postconditions:l is the list with the inserted element already in it
    '''
    l.insert(pos,c)
    return l

def test_insertElToPos():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    c={'re': 5.0, 'im': 5.0}
    pos=3
    assert insertElToPos(l,c,pos)==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 5.0, 'im': 5.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    c={'re': 5.0, 'im': 5.0}
    pos=0
    assert insertElToPos(l,c,pos)==[{'re': 5.0, 'im': 5.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    c={'re': 5.0, 'im': 5.0}
    pos=3
    assert insertElToPos(l,c,pos)==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 5.0, 'im': 5.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]

def ValidInsert(x):
    if x=='insert':
        return False
    else:
        x=x.split(" ")
        if len(x)!=4:
            return False
        else:
            if x[0]=='insert':
                a,b=x[1].split("+")
                b=b.split("i")
                b=b[0]
                a=int(a)
                b=int(b)
                x[3]=int(x[3])
                if  isinstance(x[3],int) and x[2]=='at' and isinstance(a,int) and isinstance(b,int):
                    return True
                else:
                    return False
            else:
                return False


def ui_insert(l,x,undoList):
    recordForUndo(l,undoList)
    x=x.split(" ")
    a,b=x[1].split("+")
    b=b.split("i")
    b=b[0]
    a=int(a)
    b=int(b)
    x[3]=int(x[3])
    if x[3]<len(l):
        c=CreateComplex(a,b)
        l=insertElToPos(l,c,x[3])
        PrintList(l,0,len(l)-1)
    else:
        print("Invalid numbers!Position must be smaller than "+str(len(l))+"!")
        raise ValueError("Invalid numbers!Position must be smaller than "+str(len(l))+"!")
                
def ValidADD(x):
    if x=='add':
        return False
    else:
        x=x.split(" ")
        if x[0]=='add':
            a,b=x[1].split("+")
            b=b.split("i")
            b=b[0]
            a=int(a)
            b=int(b)
            if isinstance(a, int) and isinstance(b, int):
                return True
            else:
                return False
      
    
    
    
def ui_add(l,x,undoList):
    recordForUndo(l,undoList)
    x=x.split(" ")
    a,b=x[1].split("+")
    b=b.split("i")
    b=b[0]
    c=CreateComplex(a,b)
    l=addElToList(l,c)






            

def RemovePositions(l,x,y):
    '''
    Function that removes from the list l the elements from the positions between x and y
    input:l,x,y
    preconditions:l-list of complex numbers, x,y-natural numbers<len(l),(x<=y)
    output:l
    postconditions:l is the modified list
    '''
    while x-1 != y:
        if len(l)!=0:
            l.remove(l[x])
        y=y-1
    return l

def test_RemovePositions():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=0
    y=3
    assert RemovePositions(l,x,y)==[]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    x=2
    y=3
    assert RemovePositions(l,x,y)==[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    x=0
    y=0
    assert RemovePositions(l,x,y)==[{'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]

def RemovePos(l,x):
    '''
    Function that removes from l the element from the position x
    input:l,x
    preconditions:l-list of complex numbers, x-natural number<len(l),(x<=y)
    output:l
    postconditions:l is the modified list
    '''
    l.remove(l[x])
    return l

def test_Removepos():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=0
    assert RemovePos(l,x)==[{'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    x=3
    assert RemovePos(l,x)==[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0}]

def ExistEl(l,c):
    '''
    Function that returns true if c is in l and false otherwise
    input:l,c
    precond:l-list of complex numbers, c-complex number
    output:true or false
    postconditions:true if c in l
                    false if c not in l
    '''
    for i in range(0,len(l)):
        if GetReal(c)==GetReal(l[i]) and GetImg(c)==GetImg(l[i]):
            return True
    return False

def test_ExistEl():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    assert ExistEl(l,{'re': 5.0, 'im': 6.0})==True
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    assert ExistEl(l,{'re':4.0,'im':3.0})==False
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    assert ExistEl(l,{'re':3.0,'im':0.0})==True

def replaceEl(l,c1,c2):
    '''
    Function that replaces every aparition of c1 with c2 in l
    input:l,c1,c2
    precond:l-list of complex numbers,c1,c2-complex numbers
    output:l
    output:l is the modified list
    
    '''
    
    for i in range(0,len(l)):
        if GetImg(l[i])==GetImg(c1) and GetReal(l[i])==GetReal(c1):
           SetReal(l[i],GetReal(c2))
           SetImg(l[i],GetImg(c2))
    return l

def test_replaceEl():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 0.0}]
    
    assert replaceEl(l,{'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 6.0})==[{'re': 5.0, 'im': 6.0},{'re': 5.0, 'im': 6.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 5.0, 'im': 6.0}]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    assert replaceEl(l,{'re': -1.0, 'im': -2.0},{'re': 9.0, 'im': 8.0})==[{'re': 9.0, 'im': 8.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 9.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    assert replaceEl(l,{'re':3.0,'im':0.0},{'re':9.0,'im':0.0})==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 9.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 9.0, 'im': 0.0}]

def ui_replace(l,x,undoList):
    recordForUndo(l,undoList)
    x=x.split(" ")
    if len(x)!=4:
        print("Invalid input!Try again!")
        raise ValueError("Invalid input!Try again!")
    else:
        
        a1,b1=x[1].split("+")
        b1=b1.split("i")
        b1=b1[0]
        a1=int(a1)
        b1=int(b1)
        c1=CreateComplex(a1,b1)
        a2,b2=x[3].split("+")
        b2=b2.split("i")
        b2=b2[0]
        a2=int(a2)
        b2=int(b2)
        c2=CreateComplex(a2,b2)
        if ExistEl(l,c1)==True:
            l=replaceEl(l,c1,c2)
            PrintList(l,0,len(l)-1)
        else:
            print("No "+str(a1)+"+"+str(b1)+"*i in the list!Choose another complex number!")
            raise ValueError("No "+str(a1)+"+"+str(b1)+"*i in the list!Choose another complex number!")
        

def ValidReplace(x):
    if x=='replace':
        return False
    else:
        x=x.split(" ")
        if x[0]=='replace':
            a1,b1=x[1].split("+")
            b1=b1.split("i")
            b1=b1[0]
            a1=int(a1)
            b1=int(b1)
            a2,b2=x[3].split("+")
            b2=b2.split("i")
            b2=b2[0]
            a2=int(a2)
            b2=int(b2)
            if isinstance(a1,int) and isinstance(a2,int) and isinstance(b1,int) and isinstance(b2,int) and x[2]=='with':
                return True
            else:
                return False
        else:
            return False           
 
        
def ui_remove(l,x,undoList):
    recordForUndo(l,undoList)
    x=x.split(" ")
    if len(x)==2:
        x[1]=int(x[1])
        if x[1]<len(l):
            l=RemovePos(l,x[1])
            PrintList(l,0,len(l)-1)
            
        else:
            print("Invalid position!Position must be between 0 and "+str(len(l)-1)+"!")
            raise ValueError("Invalid position!Position must be between 0 and "+str(len(l)-1)+"!")
    elif len(x)==4:
            x[1]=int(x[1])
            x[3]=int(x[3])
            if x[1]<=x[3] and x[3]<len(l) and x[1]>=0 and x[3]>=0:
                l=RemovePositions(l,x[1],x[3])
                PrintList(l,0,len(l)-1)
                
                
            else:
                print("Invalid positions!Positions must be between 0 and "+str(len(l)-1)+"!The start position must be smaller than the end position!")
                    
                    

def ValidRemove(x):
    if x=='remove':
        return False
    else:
        x=x.split(" ")
        if len(x)==2:
            if x[0]=='remove':
                x[1]=int(x[1])
                if isinstance(x[1],int):
                    return True
                else:
                    return False
            else:
                return False
        elif len(x)==4:
            if x[0]=='remove':
                x[1]=int(x[1])
                x[3]=int(x[3])
                if isinstance(x[1],int) and x[2]=='to' and isinstance(x[3],int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False






def PrintList(l,a,b):
    '''
    Function that prints the list l from position a to pos b
    input:l,a,b
    preconditions:l-list of complex numbers ,a,b naturals
    
    '''
    for i in range(a,b+1):
        print( toStr(l[i]))
def toStr(c):
    return(str(c['re'])+"+"+str(c['im'])+"i")


def PrintListReals(l,a,b):
    '''
    Function that prints the real numbers from a to b from list l
    input:l,a,b
    preconditions:l-list of complex numbers ,a,b naturals
   
    '''
    for i in range(a,b+1):
        if GetImg(l[i])==0:
            print(toStr(l[i]))

def ModuloEq(l,x):
    '''
    Function that finds the elements that have a modulo equal with x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf=[]
    for i in range(0,len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im=int(im)
        re=int(re)
        if math.sqrt(re*re+im*im)==x:
            sf.append(l[i])
    
    return sf

def test_ModuloEq():
    l=[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 6.0, 'im': 8.0}]
    x=10
    assert ModuloEq(l,x)==[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0}]
    l=[{'re': 5.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 0.0, 'im': 1.0}]
    x=5
    assert ModuloEq(l,x)==[{'re': 5.0, 'im': 0.0},{'re': 5.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    x=0
    assert ModuloEq(l,x)==[]

def ModuloSm(l,x):
    '''
    Function that finds the elements that have a modulo smaller than x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf=[]
    for i in range(0,len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im=int(im)
        re=int(re)
        x=float(x)
        if math.sqrt(re*re+im*im)<x:
            sf.append(l[i])
    
    return sf

def test_ModuloSm():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=100
    assert ModuloSm(l,x)==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': 1.0, 'im': 1.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 0.0, 'im': 1.0}]
    x=3
    assert ModuloSm(l,x)==[{'re': 1.0, 'im': 1.0},{'re': 0.0, 'im': 1.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    x=0
    assert ModuloSm(l,x)==[]


def ModuloGr(l,x):
    '''
    Function that finds the elements that have a modulo greater than x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf=[]
    for i in range(0,len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im=int(im)
        re=int(re)
        x=float(x)
        if math.sqrt(re*re+im*im)>x:
            sf.append(l[i])
    
    return sf

def test_ModuloGr():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=0
    assert ModuloGr(l,x)==[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    x=3
    assert ModuloGr(l,x)==[{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    x=2000
    assert ModuloGr(l,x)==[]


def UIModulosmall(l,x):
    
    sf=[]
    sf=ModuloSm(l,x)
    if len(sf)==0:
        print("No such numbers in the list!")
    else:
        PrintList(sf,0,len(sf)-1)
    
def UIModuloequal(l,x):
    sf=[]
    sf=ModuloEq(l,x)
    if len(sf)==0:
        print("No such numbers in the list!")
    else:
        PrintList(sf,0,len(sf)-1)
        

def UIModulogreater(l,x):
    sf=[]
    sf=ModuloGr(l,x)
    if len(sf)==0:
        print("No such numbers in the list!")
    else:
        PrintList(sf,0,len(sf)-1)
    


                
    
def ValidList(x):
    
    if x=='list':
        return True
    else:
        x=x.split(" ")
        if x[0]=='list':
            if len(x)==5:
                
                if x[1]=='real':
                    x[2]=int(x[2])
                    x[4]=int(x[4])
                    if isinstance(x[2],int) and x[3]=='to' and isinstance(x[4],int):
                        print("aici")
                        return True
                    else:
                        return False
            elif len(x)==4:
                if x[1]=='modulo':
                    if x[2]=='>' or x[2]=='<' or x[2]=='=':
                        x[3]=int(x[3])
                        if isinstance(x[3],int):
                            return True
                        else:
                            return False
                    else:
                        return False
                else:
                    return False


def ui_list(l,x):
    if x=='list':
        PrintList(l,0,len(l)-1)
    else:
        x=x.split(" ")
        if x[1]=='real':
            x[2]=int(x[2])
            x[4]=int(x[4])
            if x[2]<=x[4] and x[4]<len(l):
                PrintListReals(l,x[2],x[4])
            else:
                print("Invalid positions!Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")
                raise ValueError("Invalid positions!Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")
        elif x[1]=='modulo':
            if x[2]=='>':
                return UIModulogreater(l,x[2])
            elif x[2]=='=':
                return UIModuloequal(l,x[2])
            elif x[2]=='<':
                return UIModulosmall(l,x[2])
        
    
        
        
    
def DoSum(l,a,b):
    '''
    Function that calculates the sum of the elements from the list l between the positions a and b
    input:l,a,b
    preconditions:l-list of comlex numbers,a,b-natural number
    output:s
    postconditions:s-sum of complex numbers
    '''
    s=CreateComplex(0,0)
    for i in range(a,b+1):
        s1=GetReal(l[i])+GetReal(s)
        SetReal(s,s1)
        s2=GetImg(l[i])+GetImg(s)
        SetImg(s,s2)
    return s
    
def test_DoSum():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    a=0
    b=3
    assert DoSum(l,a,b)=={'re':20.0,'im':6.0}
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    a=1
    b=2
    assert DoSum(l,a,b)=={'re':12.0,'im':8.0}

def ui_sum(l,x):
    x=x.split(" ")
    x[1]=int(x[1])
    x[3]=int(x[3])
    if x[1]>= 0 and x[3]>=0 and x[1]<=x[3] and x[3]<len(l) and x[1]<len(l):
        s=DoSum(l,x[1],x[3])
        print("sum "+str(x[1])+" to "+str(x[3])+":")
        print(toStr(s))
    else:
        print("Wrong positions! Positions must be between 0 and "+str(len(l))+"Start position must be smaller than end position.")
        raise ValueError("Wrong positions! Positions must be between 0 and "+str(len(l))+"Start position must be smaller than end position.")
    
def ValidSum(x):
    if x=='sum':
        return False
    else:
        x=x.split(" ")
        if len(x)==4:
            if x[0]=='sum':
                x[1]=int(x[1])
                x[3]=int(x[3])
                if isinstance(x[1],int) and x[2]=='to' and isinstance(x[3],int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False

def ui_product(l,x):
    x=x.split(" ")
    x[1]=int(x[1])
    x[3]=int(x[3])
    if x[1]>= 0 and x[3]>=0 and x[1]<=x[3] and x[1]<len(l) and x[3]<len(l):
        s=DoProduct(l,x[1],x[3])
        print("product "+str(x[1])+" to "+str(x[3])+":")
        print(toStr(s))
    else:
        print("Wrong positions! Positions must be between 0 and "+str(len(l))+"Start position must be smaller than end position.")
        raise ValueError("Wrong positions! Positions must be between 0 and "+str(len(l))+"Start position must be smaller than end position.")
    

def DoProduct(l,a,b):
    '''
    Function that calculates the product of the elements from the list l between the positions a and b
    input:l,a,b
    preconditions:l-list of comlex numbers,a,b-natural number
    output:p
    postconditions:s-sum of complex numbers
    '''
    p=CreateComplex(1,1)
    if a==b:
        if GetReal(l[a])==0 and GetImg(l[b])==0:
            SetReal(p,0)
            SetImg(p,0)
    for i in range(a,b+1):
            p1=GetReal(l[i])*GetReal(p)
            SetReal(p,p1)
            p2=GetImg(l[i])*GetImg(p)
            SetImg(p,p2)
    return p
    
def test_DoProduct():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    a=0
    b=3
    assert DoProduct(l,a,b)=={'re':600.0,'im':0.0}
    l=[{'re': -1.0, 'im': -2.0},{'re': 0.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    a=1
    b=1
    assert DoSum(l,a,b)=={'re':0.0,'im':0.0}
            
    
def ValidProduct(x):
    if x=='product':
        return False
    else:
        x=x.split(" ")
        if len(x)==4:
            if x[0]=='sum':
                x[1]=int(x[1])
                x[3]=int(x[3])
                if isinstance(x[1],int) and x[2]=='to' and isinstance(x[3],int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False

def FilterModuloSmaller(l,x):
    '''
    Function that removes the elements that have the modulo bigger or equal than x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list 
    '''
    i=0
    while i<len(l):
        im=GetImg(l[i])
        re=GetReal(l[i])
        im=int(im)
        re=int(re)
        if math.sqrt(im*im+re*re)>=x:
            l.remove(l[i])
        else:
            i=i+1
        
    return l

def test_FilterModuloSmaller():
    l=[{'re': 4.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0},{'re': 2.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=4
    assert FilterModuloSmaller(l,x)==[{'re': 3.0, 'im': 0.0},{'re': 2.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': 7.0, 'im': 0.0}]
    x=5
    assert FilterModuloSmaller(l,x)==[]
    l=[{'re': 4.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 10.0, 'im': 9.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 8.0},{'re': 9.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 4.0, 'im': 8.0}]
    x=10
    assert FilterModuloSmaller(l,x)==[{'re': 4.0, 'im': 6.0},{'re': 4.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 4.0, 'im': 8.0},{'re': 4.0, 'im': 8.0}]
    

def FilterModuloEqual(l,x):
    '''
    Function that removes the elements that have the modulo smaller or bigger than x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list 
    '''
    i=0
    while i<len(l):
        im=GetImg(l[i])
        re=GetReal(l[i])
        im=int(im)
        re=int(re)
        if math.sqrt(im*im+re*re)<x:
            l.remove(l[i])
        elif math.sqrt(im*im+re*re)>x:
            l.remove(l[i])
        else:
            i=i+1
    if i==len(l)-1 and (math.sqrt(im*im+re*re)<x or math.sqrt(im*im+re*re)>x):
         l.remove(l[i])
    return l
def test_FilterModuloEqual():
    l=[{'re': 4.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=4
    assert FilterModuloEqual(l,x)==[{'re': 4.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': 4.0, 'im': 0.0}]
    x=5
    assert FilterModuloEqual(l,x)==[]
    l=[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 9.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 8.0},{'re': 9.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 6.0, 'im': 8.0},{'re': 6.0, 'im': 8.0}]
    x=10
    assert FilterModuloEqual(l,x)==[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0},{'re': 6.0,'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 6.0, 'im': 8.0}]
    

def FilterModuloGreater(l,x):
    '''
    Function that removes the elements that have the modulo smaller or equal with x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list 
    '''
    i=0
    while i<len(l):
        im=GetImg(l[i])
        re=GetReal(l[i])
        im=int(im)
        re=int(re)
        if math.sqrt(im*im+re*re)<=x:
            l.remove(l[i])
        else:
            i=i+1
        
    return l

def test_FilterModuloGreater():
    l=[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    x=5
    assert FilterModuloGreater(l,x)==[{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': 4.0, 'im': 0.0}]
    x=200
    assert FilterModuloGreater(l,x)==[]
    l=[{'re': 100.0, 'im': 60.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 9.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 8.0},{'re': 9.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 10.0, 'im': 8.0},{'re': 100.0, 'im': 8.0}]
    x=20
    assert FilterModuloGreater(l,x)==[{'re': 100.0, 'im': 60.0},{'re': 100.0, 'im': 8.0}]
    
def FilterReal(l):
    '''
    Function that removes the elements that are not real from the list l
    input:l
    preconditions:l is a list of complex numbers
    output:l
    postconditions:l is the modified list with only the real numbers in it
    '''
    i=0
    while i<len(l):
        if GetImg(l[i])!=0:
            l.remove(l[i])
        else:
            i=i+1
        
    return l

def test_FilterReal():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    assert FilterReal(l)==[{'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': 4.0, 'im': 0.0}]
    assert FilterReal(l)==[{'re': 4.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 8.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 9.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 8.0},{'re': 9.0, 'im': 8.0},{'re': 5.0, 'im': 9.0},{'re': 10.0, 'im': 8.0},{'re': 3.0, 'im': 8.0}]
    
    assert FilterReal(l)==[]
    l=[{'re': 5.0, 'im': 0.0}, {'re': 6.0, 'im': 9.0},{'re': 5.0, 'im': 9.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 9.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 8.0},{'re': 3.0, 'im': 0.0}]
    assert FilterReal(l)==[{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 6.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]
    assert FilterReal(l)==[{'re': 6.0, 'im': 0.0},{'re': 6.0, 'im': 0.0},{'re': 4.0, 'im': 0.0}]

def ui_filter(l,x,undoList):
    recordForUndo(l,undoList)
    x=x.split(" ")
    if x[1]=='real':
        l=FilterReal(l)
        PrintList(l,0,len(l)-1)
    else:
        if x[1]=='modulo':
            x[3]=int(x[3])
            if x[2]=='>':
                l=FilterModuloGreater(l,x[3])
                PrintList(l,0,len(l)-1)
            elif x[2]=='<':
                l=FilterModuloSmaller(l,x[3])
                PrintList(l,0,len(l)-1)
            elif x[2]=='=':
                l=FilterModuloEqual(l,x[3])
                PrintList(l,0,len(l)-1)
                    
            
        

def ValidFilter(x):
    if x=='filter':
        return False
    else:
        x=x.split(" ")
        if len(x)==2:
            if x[0]=='filter':
                if x[1]=='real':
                    return True
                else:
                    return False
            else:
                return False
        elif len(x)==4:
            if x[0]=='filter':
                if x[1]=='modulo':
                    if x[2]=='>' or x[2]=='<'or x[2]=='=':
                        x[3]=int(x[3])
                        if isinstance(x[3],int):
                            return True
                        else:
                            return False
                    else:
                        return False
                else:
                    return False
            else:
                return False
        else:
            return False
def ValidUndo(x):
    if x=='undo':
        return True
    else:
        return False


    
def PrinMenu():
    print("Here is the list of commands available:")
    print("add <number>")
    print("insert <number> at <position>")
    print("remove <position>")
    print("remove <start position> to <end position>")
    print("replace <old number> with <new number>")
    print("list")
    print("list real <start position> to <end position>")
    print("list modulo [ < | = | > ] <number>")
    print("sum <start position> to <end position>")
    print("product <start position> to <end position>")
    print("filter real")
    print("filter modulo [ < | = | > ] <number>")
    print("undo")


def undoSubmenu(l,undoList):
   
        l.clear()
        l.extend(deepcopy(undoList))
def recordForUndo(l,undoList):

    undoList.clear()
    undoList.extend(deepcopy(l))
    
def run():
    l=[]
    init(l)
    undoList=[]
    sf=[]
    print("This aplication helps you test properties of complex numbers.")
    while True:
        PrinMenu()
        x=input("Please give a command from the menu above:")
        try:
            if ValidADD(x)==True:
                ui_add(l,x,undoList)
                
            elif ValidList(x)==True:
                ui_list(l,x)
                
            elif ValidRemove(x)==True:
                ui_remove(l,x,undoList)
                
            elif ValidInsert(x)==True:
                ui_insert(l,x,undoList)
               
            elif ValidReplace(x)==True:
                ui_replace(l,x,undoList)
                
            elif ValidSum(x)==True:
                ui_sum(l,x)
            elif ValidProduct(x)==True:
                ui_product(l,x)
            elif ValidFilter(x)==True:
                ui_filter(l,x,undoList)
                
            elif ValidUndo(x)==True:
                 if len(undoList)==0:
                    print("No more undo's for you!")
                    raise ValueError("No more undo's for you!")
                 else:
                    undoSubmenu(l,undoList)
                    PrintList(undoList,0,len(undoList))
            else:
                raise ValueError("Invalid command!Please try again!")
        except ValueError as ve:
            print("Invalid command!Please try again!")
def run_tests():
    test_addElToList()
    test_CreateComplex()
    test_insertElToPos()
    test_ModuloGr()
    test_ModuloSm()
    test_DoSum()
    test_DoProduct()
    test_RemovePositions()
    test_ExistEl()
    test_replaceEl()
    test_Removepos()
    test_FilterReal()
    test_FilterModuloGreater()
    test_FilterModuloEqual()
    test_FilterModuloSmaller()
    test_ModuloEq()
    
run_tests()    
run()
