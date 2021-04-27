 
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
    l.append(CreateComplex(float(6),float(0)))
    l.append(CreateComplex(float(5),float(0)))
    l.append(CreateComplex(float(4),float(0)))
    l.append(CreateComplex(float(6),float(8)))
    l.append(CreateComplex(float(4),float(7)))
    l.append(CreateComplex(float(-1),float(-2)))
    l.append(CreateComplex(float(3),float(0)))
    l.append(CreateComplex(float(9),float(8)))
    l.append(CreateComplex(float(6.1),float(9.7)))

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
def reals(l):
    '''
    Function that finds the longest sequence of real numbers(imaginary part=0) from the list of complex numbers given as a parameter complex_list
    input: l
    preconditions: l-list formed by complex numbers
    output:init,final
    postconditions: init,final show where the longest sequence of reals starts and ends
    '''
    maxL = 0
    init = 0
    leng = 0
    final = 0
    a=0
    b=0
    i=0
    while i<len(l):
        if GetImg(l[i]) == 0:
            j = i
            while j<len(l):
                if GetImg(l[j]) == 0:
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

def ui_reals(l):
    '''Function that calls the function that finds the longest sequence of real numbers from the list of complex numbers l and calls the function that prints said list
        input:l
        preconditions:l-list of complex numbers
    '''
    ind = reals(l)
    if ind[1] == -1:
        print("Doesn't print!(no real numbers)")
    printList(ind[0],ind[1],l)

def test_ui_reals():
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 0.0}]
    assert reals(l)==[1,3]
    l=[{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0},{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0},{'re': 6.0, 'im': 0.0},{'re': 9.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 10.0, 'im': 0.0},{'re': 3.0, 'im': 0.0}]
    assert len(l)==15
    assert reals(l)==[10,14]
    l=[{'re': 5.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0},{'re': 5.0, 'im': 0.0},{'re': 4.0, 'im': 0.0},{'re': 6.0, 'im': 8.0},{'re': 4.0, 'im': 7.0}]
    assert reals(l)==[0,3]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 0.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    assert reals(l)==[1,1]
    l=[{'re': -1.0, 'im': -2.0},{'re': 3.0, 'im': 6.0},{'re': 9.0, 'im': 8.0},{'re': 6.0, 'im': 9.0}]
    assert reals(l)==[0,-1]
    assert reals([{'re':0.0, 'im':0.0}]) == [0,0]
    
def printMenu():
    print("List of commands:")
    print("-add for adding a complex number in the list")
    print("-print for printing the list")
    print("-reals for showing the longest sequence of real numbers")
    print("-exit to exit the application")

def run():
    l=[]
    init(l)
    commands={'add':ui_add_input,'print':ui_print,'reals':ui_reals}
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
    test_ui_reals()
    
run_tests()
run()
        
    
