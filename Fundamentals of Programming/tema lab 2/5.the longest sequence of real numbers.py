#Checks whether the inputs given are integers
#If not, the message 'Invalid input' comes up 
def ui_input():
    while True:
        x=input("Please give an integer for the real part of the complex number:")
        y=input("Please give an integer for the imaginarry part of the complex number:")
        try:
            x=int(x)
            y=int(y)
            return [x, y]
        except ValueError as ve:
            print("Not a valid input. Please give an integer for the real or imaginary part of the complex number!")
        
#initialisation for the list
def init(complex_list):
    complex_list.append([5,6])
    complex_list.append([6,0])
    complex_list.append([5,0])
    complex_list.append([4,0])
    complex_list.append([6,8])
    complex_list.append([4,7])
    complex_list.append([-1,-2])
    complex_list.append([3,0])
    complex_list.append([9,8])
    complex_list.append([6,9])

def ui_add(complex_list):
    '''
    function that appends to the list complex_list the complex number given by the real part x and imaginary part y
    input: complex_list
    preconditions:  x-integer representing the real part of the number,
                    y-integer representing the maginarry part of the number,
                    complex_list-list formed by complex numbers
    output:-
    postconditions:complex_list={a1,a2,a3,..}U{x+y*i}={a1,a2,a3,..,x+y*i}
    '''
    number = ui_input()
    complex_list.append(number)


    
    


def ui_print(complex_list):
    for i in range(0, len(complex_list)):
        print(str(complex_list[i][0]) + " + " +str(complex_list[i][1]) + "*i")

def longest_sequence(complex_list):
    '''
    Function that finds the longest sequence of real numbers(imaginary part=0) from the list of complex numbers given as a parameter complex_list
    input: complex_list
    preconditions: complex_list-list formed by complex numbers
    output:sf
    postconditions: sf-the longest sequence of real numbers from complex_list
    '''

    si=[]
    sf=[]        #si is the intermediary list that we create when we find a real number
                 #sf is the final list where we keep the longest sequence
    for i in range(0, len(complex_list)):
        if complex_list[i][1]==0:
            si.append([complex_list[i][0],complex_list[i][1]]) #this line checks whether the number is a real one or not
                                                                #if true, the function appends the number to the list si
        if len(si)>len(sf):
            sf=si
        if complex_list[i][1]!=0:
            si=[]
   
    
    print("The longest sequence of real numbers is:")
    ui_print(sf)
    return sf

def test_longest_sequence():
    #test function for the longest_sequence function
    l=[[5,6],[6,0],[5,0],[4,0],[6,8],[4,7],[-1,-2],[3,0],[9,8],[6,9]]
    longest_sequence(l)
    assert longest_sequence(l)==[[6,0],[5,0],[4,0]]

    l=[[5,6],[6,0],[5,0],[4,0],[6,8],[4,7],[-1,-2],[3,0],[9,8],[6,9],[7,0],[6,0],[8,0],[9,0]]
    longest_sequence(l)
    assert longest_sequence(l)==[[7,0],[6,0],[8,0],[9,0]]
        

def run():
    complex_list=[]
    init(complex_list)
    while True:
        x=input("Please give a command:")
        commands={"add":ui_add,"print":ui_print}
        if x == 'add':
            ui_add(complex_list)
        elif x=='print':
            ui_print(complex_list)
        elif x=="reals":
            longest_sequence(complex_list)
        
        elif x == 'exit':
            return
        else:
            print("Invalid command. Try again!")
def run_tests():
    test_longest_sequence()

run_tests()
run()
