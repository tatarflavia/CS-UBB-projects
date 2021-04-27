from helper import *
from model import *
import math
def addElToList(l, c):
    '''
    Function that adds an complex number to a list of complex numbers
    input:l,c
    preconditions:l-list of complex numbers ,c-complex number to be put in the list
    output:-
    postconditions:l=l U c, c is the last added element in the list
    '''

    l.append(c)
    return l



def insertElToPos(l, c, pos):
    '''
    Function that inserts an complex number c to a specific position pos in the list l
    input:l,c,pos
    preconditions:l-list of complex numbers ,c-complex number to be inserted in the list,pos is the position where the element will be inserted
    output:l
    postconditions:l is the list with the inserted element already in it
    '''
    l.insert(pos, c)
    return l


def RemovePositions(l, x, y):
    '''
    Function that removes from the list l the elements from the positions between x and y
    input:l,x,y
    preconditions:l-list of complex numbers, x,y-natural numbers<len(l),(x<=y)
    output:l
    postconditions:l is the modified list
    '''
    while x - 1 != y:
        if len(l) != 0:
            l.remove(l[x])
        y = y - 1
    return l


def RemovePos(l, x):
    '''
    Function that removes from l the element from the position x
    input:l,x
    preconditions:l-list of complex numbers, x-natural number<len(l),(x<=y)
    output:l
    postconditions:l is the modified list
    '''
    l.remove(l[x])
    return l

def ExistEl(l, c):
    '''
    Function that returns true if c is in l and false otherwise
    input:l,c
    precond:l-list of complex numbers, c-complex number
    output:true or false
    postconditions:true if c in l
                    false if c not in l
    '''
    for i in range(0, len(l)):
        if GetReal(c) == GetReal(l[i]) and GetImg(c) == GetImg(l[i]):
            return True
    return False

def replaceEl(l, c1, c2):
    '''
    Function that replaces every aparition of c1 with c2 in l
    input:l,c1,c2
    precond:l-list of complex numbers,c1,c2-complex numbers
    output:l
    output:l is the modified list

    '''

    for i in range(0, len(l)):
        if GetImg(l[i]) == GetImg(c1) and GetReal(l[i]) == GetReal(c1):
            SetReal(l[i], GetReal(c2))
            SetImg(l[i], GetImg(c2))
    return l


def ModuloEq(l, x):
    '''
    Function that finds the elements that have a modulo equal with x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf = []
    for i in range(0, len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im = int(im)
        re = int(re)
        if math.sqrt(re * re + im * im) == x:
            sf.append(l[i])

    return sf

def ModuloSm(l, x):
    '''
    Function that finds the elements that have a modulo smaller than x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf = []
    for i in range(0, len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im = int(im)
        re = int(re)
        x = float(x)
        if math.sqrt(re * re + im * im) < x:
            sf.append(l[i])

    return sf


def ModuloGr(l, x):
    '''
    Function that finds the elements that have a modulo greater than x from list l and forms a new list with them
    input:l,x
    preconditions:l-list of complex numbers ,x-integer
    output:sf
    postconditions:sf is the list formed by said numbers in description
    '''
    sf = []
    for i in range(0, len(l)):
        re = GetReal(l[i])
        im = GetImg(l[i])
        im = int(im)
        re = int(re)
        x = float(x)
        if math.sqrt(re * re + im * im) > x:
            sf.append(l[i])

    return sf


def DoSum(l, a, b):
    '''
    Function that calculates the sum of the elements from the list l between the positions a and b
    input:l,a,b
    preconditions:l-list of comlex numbers,a,b-natural number
    output:s
    postconditions:s-sum of complex numbers
    '''
    s = CreateComplex(0, 0)
    for i in range(a, b + 1):
        s1 = GetReal(l[i]) + GetReal(s)
        SetReal(s, s1)
        s2 = GetImg(l[i]) + GetImg(s)
        SetImg(s, s2)
    return s


def DoProduct(l, a, b):
    '''
    Function that calculates the product of the elements from the list l between the positions a and b
    input:l,a,b
    preconditions:l-list of comlex numbers,a,b-natural number
    output:p
    postconditions:s-sum of complex numbers
    '''
    p = CreateComplex(1, 1)
    if a == b:
        if GetReal(l[a]) == 0 and GetImg(l[b]) == 0:
            SetReal(p, 0)
            SetImg(p, 0)
    for i in range(a, b + 1):
        p1 = GetReal(l[i]) * GetReal(p)
        SetReal(p, p1)
        p2 = GetImg(l[i]) * GetImg(p)
        SetImg(p, p2)
    return p


def FilterModuloSmaller(l, x):
    '''
    Function that removes the elements that have the modulo bigger or equal than x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list
    '''
    i = 0
    while i < len(l):
        im = GetImg(l[i])
        re = GetReal(l[i])
        im = int(im)
        re = int(re)
        if math.sqrt(im * im + re * re) >= x:
            l.remove(l[i])
        else:
            i = i + 1

    return l


def FilterModuloEqual(l, x):
    '''
    Function that removes the elements that have the modulo smaller or bigger than x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list
    '''
    i = 0
    while i < len(l):
        im = GetImg(l[i])
        re = GetReal(l[i])
        im = int(im)
        re = int(re)
        if math.sqrt(im * im + re * re) < x:
            l.remove(l[i])
        elif math.sqrt(im * im + re * re) > x:
            l.remove(l[i])
        else:
            i = i + 1
    if i == len(l) - 1 and (math.sqrt(im * im + re * re) < x or math.sqrt(im * im + re * re) > x):
        l.remove(l[i])
    return l

def FilterModuloGreater(l, x):
    '''
    Function that removes the elements that have the modulo smaller or equal with x from the list l
    input:l,x
    preconditions:l is a list of complex numbers,x-integer
    output:l
    postconditions:l is the modified list
    '''
    i = 0
    while i < len(l):
        im = GetImg(l[i])
        re = GetReal(l[i])
        im = int(im)
        re = int(re)
        if math.sqrt(im * im + re * re) <= x:
            l.remove(l[i])
        else:
            i = i + 1

    return l


def FilterReal(l):
    '''
    Function that removes the elements that are not real from the list l
    input:l
    preconditions:l is a list of complex numbers
    output:l
    postconditions:l is the modified list with only the real numbers in it
    '''
    i = 0
    #print(l)
    #while i < len(l):
    #    if GetImg(l[i]) != 0:
    #        l.remove(l[i])
    #    else:
    #        i = i + 1
    #print(l)
    rez=[]
    for x in l:
        print(x)
        if GetImg(x)==0:
            rez.append(x)
    print(rez)
    l[:]=rez

def ui_undo(undo_list):
    x = len(undo_list) - 1
    del undo_list[x]
    return undo_list

