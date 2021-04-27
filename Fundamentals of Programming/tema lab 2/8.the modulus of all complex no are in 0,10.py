import math

#checks whether the complex number is formed by an integer as a real and an imaginary part
#if not, the message 'Invalid input' will appear
def ui_input():
    while True:
        x=input("Please give an integer for the real part of the complex number:")
        y=input("Please give an integer for the imaginary part of the complex number:")
        try:
            x=int(x)
            y=int(y)
            return [x,y]
        except ValueError as ve:
            print("Invalid input.Please give an integer for the real or the imaginary part of the number!")

def init(l):
    #initialisation for the list of complex numbers
    l.append([5,6])
    l.append([7,0])
    l.append([10,0])
    l.append([6,8])
    l.append([8,6])
    l.append([14,15])
    l.append([-1,-1])
    l.append([-1,0])
    l.append([7,14])
    l.append([10,12])



def ui_add(l):
    '''
    Function that adds a complex number formed by x and y to the parameter l which is a list
    input:l
    preconditions:l-list of complex numbers
    output:l modified
    postconditions: l={a1,a2,...}U{x+i*y}={a1,a2,...,x+i*y}
    '''
    number=ui_input()
    l.append(number)


    
def ui_print(l):
    #function that prints the list of complex numbers
    for i in range(0,len(l)):
        print(str(l[i][0])+" + "+str(l[i][1])+"*i")

def modules_sequence(l):
    '''
    Function that finds the longest sequence of complex numbers that have the module in range [0,10]
    input:l
    preconditions: l- list of complex numbers
    output:sf
    postconditions: sf-list formed by the longest sequence of complex numbers that have the module in range [0,10]
    '''
    si=[]
    sf=[]
            #si is the intermediary list that we create when we find a real number
            #sf is the final list where we keep the longest sequence
    for i in range(0,len(l)):
        if math.sqrt(l[i][0]*l[i][0]+l[i][1]*l[i][1])>=0 and math.sqrt(l[i][0]*l[i][0]+l[i][1]*l[i][1])<=10: #this line checks whether the number coresponds to the task
                                                                                                             #if true, the function appends the number to the list si
            si.append([l[i][0],l[i][1]])
        if len(si)>len(sf):
            sf=si
        if math.sqrt(l[i][0]*l[i][0]+l[i][1]*l[i][1])<0 or math.sqrt(l[i][0]*l[i][0]+l[i][1]*l[i][1])>10:
            si=[]
    print("The longest sequence of complex numbers with the module in range [0,10] is:")
    ui_print(sf)
    return sf


def test_modules_sequence():
    #test function for the modules_sequence function
    l=[[5,6],[7,0],[10,0],[6,8],[8,6],[14,15],[-1,-1],[-1,0],[7,14],[10,12]]
    modules_sequence(l)
    assert modules_sequence(l)==[[5,6],[7,0],[10,0],[6,8],[8,6]]

    l=[[5,6],[7,0],[10,0],[6,8],[8,6],[14,15],[-1,-1],[-1,0],[7,14],[10,12],[6,0],[8,0],[9,0],[1,1],[2,2],[4,4]]
    modules_sequence(l)
    assert modules_sequence(l)==[[6,0],[8,0],[9,0],[1,1],[2,2],[4,4]]
        

def run_tests():
    test_modules_sequence()

def run():
    l=[]
    init(l)
    while True:
        x=input("Please give a command:")
        if x=='add':
            ui_add(l)
        elif x=='print':
            ui_print(l)
        elif x=='modules':
            modules_sequence(l)
        elif x=='exit':
            return
        else:
            print("Invalid command! Please write a valid command!")
run_tests()    
run()
    
