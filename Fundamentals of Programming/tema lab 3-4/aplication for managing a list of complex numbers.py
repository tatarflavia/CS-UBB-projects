#vezi de insert sa updateze lista,si problema cu stringuri la modulo(vezi de comm si teste aici)

import math
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
    

def ui_input():
    '''
    Function that reads a complex number and returns it
    output:c
    postconditions:c-complex number
    '''
    while True:
        try:
            x=input("Please give a complex number in the form a+bi , a and b integers:")
            a,b=x.split("+")
            b=b.split("i")
            b=b[0]
            a=int(a)
            b=int(b)
            if isinstance(a, int) and isinstance(b, int):
                c=CreateComplex(a,b)
                return c        
        except ValueError as ve:
            print("Invalid complex number! Please give a complex number in the form a+bi:")

def ui_addEl(l):
    '''
    Function that calls the function ui_input and addElToList
    input:l
    preconditions:l-list of complex numbers
    '''
    c=ui_input()
    addElToList(l,c)
def ui_insert(l):
    '''
    Function that calls the function ui_input and reads a position and calls the function insertElToPos and then calls the function that prints the new list
    input:l
    preconditions:l-list of complex numbers 
    output:-
    '''
    c=ui_input()
    while True:
        try:
            x=input("Please give a position:")
            x=int(x)
            if isinstance(x, int) and x>=0 and x<len(l):
                print("insert"+" "+str(GetReal(c))+"+"+str(GetImg(c))+"*i to "+str(x))
                l=insertElToPos(l,c,x)
                return PrintList(l,0,len(l)-1)
            else: raise ValueError("The position must be between 0 and the length of the list!")
        except ValueError as ve:
            print("Invalid position! Please give a natural number:")
    
    

def addElToList(l,c):
    '''
    Function that adds an complex number to a list of complex numbers
    input:l,c
    preconditions:l-list of complex numbers ,c-complex number to be put in the list
    output:-
    postconditions:l=l U c, c is the last added element in the list
    '''
    
    l.append(c)
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
    output:sf
    postconditions:sf is the list with the inserted element already in it
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
            
    
def ui_add(l):
    print("You chose the command add. Would you like to add at the end of the list or at a specific position?")
    print("Choose 'add' or 'insert':")
    while True:
        try:
            x=input("Please give your answer:")
            if x=='add':
                return ui_addEl(l)
            elif x=='insert':
                return ui_insert(l)
            else:
                print("Invalid answer!Please choose add or insert!")
        except ValueError as ve:
            print("Invalid command!")

def RemovePositions(l,x,y):
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
    l.remove(l[x])
    return l

def ui_removeONE(l):
    while True:
        try:
            x=input("Please give a position:")
            x=int(x)
            if isinstance(x,int) and x>=0 and x<len(l):
                l=RemovePos(l,x)
                PrintList(l,0,len(l)-1)
                break
            else:
                raise ValueError("Invalid position!Position must be between 0 and "+str(len(l)-1)+"!")
        except ValueError as ve:
            print("Invalid position!Position must be between 0 and "+str(len(l)-1)+"!")

def ui_replaceMORE(l):
   while True:
        try:
            x=input("Please give a start position:")
            y=input("Please give an end position:")
            x=int(x)
            y=int(y)
            if isinstance(x,int) and x>=0 and x<len(l) and isinstance(y,int) and y>=0 and y<len(l) and x<=y:
                l=RemovePositions(l,x,y)
                PrintList(l,0,len(l)-1)
                break
            else:
                raise ValueError("Invalid positions!Positions must be between 0 and "+str(len(l)-1)+"!")
        except ValueError as ve:
            print("Invalid position!Positions must be between 0 and "+str(len(l)-1)+"!The start position must be smaller than the end position!")
 
        
def ui_remove(l):
    print("Would you like to remove one element or multiple elements?")
    while True:
        try:
            x=input("Choose 'one' or 'more':")
            if x=='one':
                ui_removeONE(l)
                return l
            elif x=='more':
                ui_replaceMORE(l)
                return l
            else:
                raise ValueError("Invalid input!")
        except ValueError as ve:
            print("Invalid input!Please try again!Choose 'one' or 'more'!")


def ui_modify(l):
    print("You chose the command modify. Would you like to remove something or replace something?")
    while True:
        try:
            
            x=input("Please choose remove or replace:")
            if x=='remove':
                ui_remove(l)
                return l
            elif x=='replace':
                ui_replace(l)
                return l
            else:
                raise ValueError("Invalid input!")
        except ValueError as ve:
            print("Invalid input!Please try again!")




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
def UIPrintReals_input(l):
    while True:
        try:
            x=input("Please give the start position:")
            y=input("Please give the finish position:")
            x=int(x)
            y=int(y)
            if isinstance(x,int) and x>=0 and isinstance(y,int) and y>=0 and x<=y and y<len(l):
                return [x,y]
            else:
                raise ValueError("Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")
        except ValueError as ve:
            print("Please give natural numbers for the positions!Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")

def PrintListReals(l,a,b):
    '''
    Function that prints the real numbers from a to b from list l
    input:l,a,b
    preconditions:l-list of complex numbers ,a,b naturals
   
    '''
    for i in range(a,b+1):
        if GetImg(l[i])==0:
            print(toStr(l[i]))
    
def UIPrintReals(l):
    '''
    Function that calls the fucntion that reads the positions and calls the function that prints reals from start to finish
    input:l,
    preconditions:l-list of complex numbers 
    
    '''
    [x,y]=UIPrintReals_input(l)
    PrintListReals(l,x,y)

def ModuloGr(l,x):
##    sf=[]
##    print("aici")
##    print( l[0])
##    print('here')
##    for i in range (0,len(l)):
##        print(i)
##        print(type(l[i]))
##        
##        if math.sqrt(GetReal(l[i])*GetReal(l[i])+GetImg(l[i])*GetImg(l[i]))>=x:
##            
##            sf.append(l[i])
##    print(sf)
##    return sf
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
        img = GetImg(l[i])
        if math.sqrt(re*re+img*img)>x:
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

def UIModuloEqualsPrint(l,x):
    pass

def UIModuloSmallPrint(l,x):
    pass

def UIModulosmall(l):
    pass
def UIModuloequal(l):
    pass       

def UIModulogreater(l):
    sf=[]
    while True:
        x=input("Please choose a number:")
        try:
            x=int(x)
            if isinstance(x,int) and x>=0:
                x=int(x)
                sf=ModuloGr(l,x)
                print("bla")
                PrintList(sf,0,len(sf)-1)
        except ValueError as ve:
            print("Invalid input!Please choose a natural number!")

def UIPrintModulo(l):
    l=['>','<','=']
    while True:
        try:
            x=input("Please choose between <,>,=:")
            if x in l:
                if x=='>':
                    UIModulogreater(l)
                elif x=='=':
                    UIModuloequal(l)
                elif x=='<':
                    UIModulosmall(l)
            else:
                raise ValueError("Invalid input! Please try again!")

        except ValueError as ve:
            print("Invalid input!Please choose between <,>,=!")
                
    
    

def ui_list(l):
    print("You chose the command list. Would you like to print the whole list,only the reals, or specific numbers regarding modulo's?")
    print("Choose 'list' for the whole,'real' for real numbers or 'modulo' for modules:")
    while True:
        try:
            x=input("Please give your answer:")
            if x=='list':
                return PrintList(l,0,len(l)-1)
            elif x=='real':
                return UIPrintReals(l)
            elif x=='modulo':
                return UIPrintModulo(l)
            else:
                print("Invalid answer!Please choose list, real or modulo!")
        except ValueError as ve:
            print("Invalid command!")
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

def ui_sum(l):
    print("You chose the command sum.")
    while True:
        try:
            x=input("Please give a start position:")
            y=input("Please give an end position:")
            x=int(x)
            y=int(y)
            if isinstance(x,int) and x>=0 and x<len(l) and isinstance(y,int) and y>=0 and y<len(l) and x<=y:
                print("sum "+str(x)+" to "+str(y)+":")
                DoSum(l,x,y)
                print(str(GetReal(DoSum(l,x,y)))+" + "+str(GetImg(DoSum(l,x,y)))+"*i")
                break
            else:
                raise ValueError("Invalid inputs!Please give natural numbers between 0 and the length of the list!")
        except ValueError as ve:
            print("Invalid inputs!Please give natural numbers for the positions!Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")

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
            
def ui_product(l):
    print("You chose the command product.")
    while True:
        try:
            x=input("Please give a start position:")
            y=input("Please give an end position:")
            x=int(x)
            y=int(y)
            if isinstance(x,int) and x>=0 and x<len(l) and isinstance(y,int) and y>=0 and y<len(l) and x<=y:
                print("product "+str(x)+" to "+str(y)+":")
                DoProduct(l,x,y)
                print(str(GetReal(DoProduct(l,x,y)))+" + "+str(GetImg(DoProduct(l,x,y)))+"*i")
                break
            else:
                raise ValueError("Invalid inputs!Please give natural numbers between 0 and the length of the list!")
        except ValueError as ve:
            print("Invalid inputs!Please give natural numbers for the positions!Please let the start position be smaller than the finish position and the finish be smaller than "+str(len(l))+"!")
def ui_filter():
    pass
def ui_undo():
    pass
def PrinMenu():
    print("")
    print("Here is the list of commands available:")
    print("-add-for adding numbers to the list")
    print("-modify-for modifying elements from the list")
    print("-list-for writing numbers having different properties")
    print("-sum-to get the sum of a sublist")
    print("-product-to get the product of a sublist")
    print("-filter-to filter the list")
    print("-undo-to undo the last operation that modified program data")

    
def run():
    l=[]
    init(l)
    commands={'add':ui_add,'modify':ui_modify,'list':ui_list,'sum':ui_sum,'product':ui_product,'filter':ui_filter,'undo':ui_undo}
    print("This aplication helps you test properties of complex numbers.")
    while True:
        PrinMenu()
        x=input("Please give a command from the menu above:")
        try:
            if x in commands:
                commands[x](l)
            else:
                print("Invalid command! Please give a command from the menu:")
        except Exception as ve:
            print(ve)
def run_tests():
    test_addElToList()
    test_CreateComplex()
    test_insertElToPos()
    test_ModuloGr()
    test_DoSum()
    test_DoProduct()
    test_RemovePositions()
    
run_tests()    
run()
