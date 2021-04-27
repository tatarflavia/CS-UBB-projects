from model import *
# initialisation for the list
def init(l):
    l.append(CreateComplex(5, 6))
    l.append(CreateComplex(6, 0))
    l.append(CreateComplex(5, 0))
    l.append(CreateComplex(4, 0))
    l.append(CreateComplex(6, 8))
    l.append(CreateComplex(4, 7))
    l.append(CreateComplex(-1, -2))
    l.append(CreateComplex(3, 0))
    l.append(CreateComplex(9, 8))
    l.append(CreateComplex(6, 9))

def PrintList(l, a, b):
    '''
    Function that prints the list l from position a to pos b
    input:l,a,b
    preconditions:l-list of complex numbers ,a,b naturals

    '''
    for i in range(a, b + 1):
        print(toStr(l[i]))


def toStr(c):
    return (str(c['re']) + "+" + str(c['im']) + "i")


def PrintListReals(l, a, b):
    '''
    Function that prints the real numbers from a to b from list l
    input:l,a,b
    preconditions:l-list of complex numbers ,a,b naturals

    '''
    for i in range(a, b + 1):
        if GetImg(l[i]) == 0:
            print(toStr(l[i]))



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

