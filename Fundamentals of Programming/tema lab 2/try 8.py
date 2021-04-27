

import math
def CreateComplex(a,b):
    '''
    Function that creates a complex number based on real part a and imaginary part b
    input:a,b
    preconditions:a,b, real
    output:c
    postconditions:c-complex number , real part of c=a, imaginry part of c=b
    '''
    return{"re":a,"im":b}

def test_CreateComplex():
    a=6
    b=8.7
    c=CreateComplex(a,b)
    assert GetReal(c)==a
    assert GetImg(c)==b
    d=9
    c=setReal(c,d)
    assert GetReal(c)==d
    d=9
    c=setImg(c,d)
    assert GetImg(c)==d
    
def GetReal(c):
    '''
    Function that gets the real part of complex number c
    input:c
    preconditions:c-complex number
    output:re
    postconditions:re the real part of the complex number c
    
    '''
    return c["re"]
def GetImg(c):
    '''
    Function that gets the imaginary part of complex number c
    input:c
    preconditions:c-complex number
    output:im
    postconditions:im the imaginary part of the complex number c
    
    '''
    return c["im"]
def setReal(c,x):
    '''
    Function that sets the real part of complex number c
    input:c,x
    preconditions:c-complex number,x-real number that is the new real part
    output:cc
    postconditions:cc-the modified complex number
    
    '''
    c["re"]=x
    return c
def setImg(c,x):
    '''
    Function that sets the imaginary part of complex number c
    input:c,x
    preconditions:c-complex number,x-real number that is the new imaginary part
    output:cc
    postconditions:cc-the modified complex number
    
    '''
    c["im"]=x
    return c

#initialisation for the list
def init(l):
    l.append(CreateComplex(float(5),float(6)))
    l.append(CreateComplex(float(7),float(0)))
    l.append(CreateComplex(float(10),float(0)))
    l.append(CreateComplex(float(6),float(8)))
    l.append(CreateComplex(float(8),float(6)))
    l.append(CreateComplex(float(14),float(15)))
    l.append(CreateComplex(float(-1),float(-1)))
    l.append(CreateComplex(float(-1),float(0)))
    l.append(CreateComplex(float(7),float(14)))
    l.append(CreateComplex(float(10),float(12)))

def add(l,c):
    '''Function that adds to the list l the complex number c
        input:l,c
        preconditions:l-list of complex numbers,c-complex number
    '''
    l.append(c)
   
#Checks whether the inputs given are floats
#If not, the message 'Invalid input' comes up 
def ui_add_input(l):
    '''Function that  reads the 2 real numbers that will create the complex number and calls the function that adds the complex number to the list of complex numbers
        input:l
        preconditions:l-list of complex numbers
    '''
    while True:
        x=input("Please give a float for the real part of the complex number:")
        y=input("Please give a float for the imaginary part of the complex number:")
        try:
            x=float(x)
            y=float(y)
            c=CreateComplex(x,y)
            return add(l,c)
        except ValueError as ve:
            print("Not a valid input. Please give a float for the real or imaginary part of the complex number!")
    


def printList(a,b,l):
    '''Function that prints the list l starting from a and ending with b
        input:l,a,b
        preconditions:l-list of complex numbers,a-natural number,b-natural number
    '''
    for i in range(a, b+1):
         print(toStr(l[i]))

def ui_print(l):
    '''Function that calls the function that prints the list starting from 0 and ending with len(l)-1
        input:l
        preconditions:l-list of complex numbers
    '''
    return printList(0,len(l)-1,l)





def toStr(c):
    return(str(c["re"]) + '+' + str(c["im"]) + "i" )
def modules(l):
    '''
    Function that finds the longest sequence of complex numbers that have the module in range [0,10] from the list of complex numbers given as a parameter complex_list
    input: l
    preconditions: l-list formed by complex numbers
    output:init,final
    postconditions: init,final show where the longest sequence of numbers that have the module in range [0,10] starts and ends
    '''
    maxL = 0
    init = 0
    leng = 0
    final = 0
    a=0
    b=0
    i=0
    while i<len(l):
        if math.sqrt(GetReal(l[i])*GetReal(l[i])+GetImg(l[i])*GetImg(l[i]))>=0 and math.sqrt(GetReal(l[i])*GetReal(l[i])+GetImg(l[i])*GetImg(l[i]))<=10:
            j = i
            while j<len(l):
                if  math.sqrt(GetReal(l[j])*GetReal(l[j])+GetImg(l[j])*GetImg(l[j]))>=0 and math.sqrt(GetReal(l[j])*GetReal(l[j])+GetImg(l[j])*GetImg(l[j]))<=10:
                    leng = leng+1
                else:
                    break
                j=j+1
            if leng > maxL:
                maxL = leng
                init =i
                final = j
            leng = 0
        i = i +1
    final = final - 1
    return [init,final]

def ui_modules(l):
    '''Function that calls the function that finds the longest sequence of complex numbers that have the module in range [0,10] from the list of complex numbers l and calls the function that prints said list
        input:l
        preconditions:l-list of complex numbers
    '''
    ind = modules(l)
    if ind[1] == -1:
        print("Doesn't print!(no such numbers in the list)")
    printList(ind[0],ind[1],l)

def test_ui_modules():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 7.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 8.0, 'im': 6.0},{'re': 14.0, 'im': 15.0},{'re': -1.0, 'im': -1.0},{'re': -1.0, 'im': 0.0},{'re': 7.0, 'im': 14.0},{'re': 10.0, 'im': 12.0}]
    assert modules(l)==[0,4]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 7.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 8.0, 'im': 6.0},{'re': 14.0, 'im': 15.0},{'re': -1.0, 'im': -1.0},{'re': -1.0, 'im': 0.0},{'re': 7.0, 'im': 14.0},{'re': 10.0, 'im': 12.0},{'re': 6.0, 'im': 0.0},{'re': 8.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 1.0, 'im': 1.0},{'re': 2.0, 'im': 2.0},{'re': 4.0, 'im': 4.0}]
    
    assert modules(l)==[10,15]

    l=[{'re': -1.0, 'im': 20.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 80.0},{'re': 60.0, 'im': 9.0}]
    assert modules(l)==[1,1]
    l=[{'re': -1.0, 'im': 80.0},{'re': 14.0, 'im': 15.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 10.0}]
    assert modules(l)==[0,-1]
    assert modules([{'re':0.0, 'im':0.0}]) == [0,0]
    
def printMenu():
    print("List of commands:")
    print("-add for adding a complex number in the list")
    print("-print for printing the list")
    print("-modules for showing the longest sequence of complex numbers with module in range [0,10]")
    print("-exit to exit the application")

def run():
    l=[]
    init(l)
    commands={'add':ui_add_input,'print':ui_print,'modules':ui_modules}
    while True:
        printMenu()
        x=input("Please give a command:")
        try:
            if x in commands.keys():
                commands[x](l)
            elif x=='exit':
                return
            else:
                print("Invalid command! Please give a command from the menu:")
        except Exception as ve:
            print(ve)
#tests:
def run_tests():
    test_CreateComplex()
    test_ui_modules()
    
run_tests()
run()
